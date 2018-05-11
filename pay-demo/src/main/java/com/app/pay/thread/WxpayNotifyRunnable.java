package com.app.pay.thread;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.app.pay.code.PayConstant;
import com.app.pay.code.SwiftTradeStatus;
import com.app.pay.code.WxpayPort;
import com.app.pay.entity.dto.WxpayOrderQueryResponse;
import com.app.pay.service.impl.PayCheckService;
import com.app.pay.service.impl.PayDBService;
import com.app.pay.util.LogUtil;
import com.app.pay.util.WxpayUtil;
import com.app.util.IocUtil;
import com.app.util.XmlUtil;

/**
 * <p>功 能：微信支付通知处理线程</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月3日 上午10:43:01</p>
 * @author 王建
 * @version 1.0
 */
public class WxpayNotifyRunnable implements Runnable {
	private static final Logger LOG = LogManager.getLogger(WxpayNotifyRunnable.class);

	private String input = null;

	public WxpayNotifyRunnable() {
	}

	public WxpayNotifyRunnable(String input) {
		this.input = input;
	}

	public void run() {
		try {
			// 将xml字符串转换为javabean
			WxpayOrderQueryResponse model = XmlUtil.toBeanWithCData(input, WxpayOrderQueryResponse.class);
			// 将javabean转换为json字符串
			String json = JSON.toJSONString(model);
			if (LOG.isInfoEnabled())
				LOG.info("收到微信支付通知：{}", json);

			// 获取通知参数
			String appId = model.getAppid();
			Integer totalFee = model.getTotalFee();
			String outTradeNo = model.getOutTradeNo();
			String tradeNo = model.getTransactionId();
			String tradeStatus = SwiftTradeStatus.SUCCESS;
			String timeEnd = WxpayUtil.formatWxDate(model.getTimeEnd());

			// 校验数据有效性
			if (checkNotify(appId, outTradeNo, totalFee))
				return;
			// 将支付通知发送给接口调用端
			sendNotify(outTradeNo, tradeNo, totalFee, tradeStatus, timeEnd);
			// 记录支付通知并入库
			recordNotify(outTradeNo, tradeNo, tradeStatus, timeEnd, json);
		} catch (Exception e) {
			LOG.error("微信支付通知处理出错", e);
		}
	}

	/**
	 * 校验微信支付通知是否有效
	 * @param appId
	 * @param outTradeNo
	 * @param totalFee
	 * @return
	 * @throws Exception
	 */
	private boolean checkNotify(String appId, String outTradeNo, Integer totalFee) throws Exception {
		// 校验数据有效性
		PayCheckService payCheckService = IocUtil.getBean(PayCheckService.class);
		boolean appIdValid = StringUtils.equalsIgnoreCase(PayConstant.WXPAY_APP_ID, appId);
		boolean tradeStateValid = payCheckService.isTradeSuccess(outTradeNo);
		boolean outTradeNoAndFeeValid = payCheckService.isMatchTradeAndFee(outTradeNo, totalFee);
		// 如果appid不匹配，或订单支付状态已记录，或订单金额不匹配，任何一个条件成立都不在继续执行
		return !appIdValid || tradeStateValid || !outTradeNoAndFeeValid;
	}

	/**
	 * 将支付通知发送给接口调用端
	 * @param outTradeNo
	 * @param totalFee
	 * @param tradeStatus
	 * @param timeEnd
	 * @throws Exception
	 */
	private void sendNotify(String outTradeNo, String tradeNo, Integer totalFee, String tradeStatus, String timeEnd) throws Exception {
		// 调用用户系统接口，处理用户余额，不再发送MQ消息
		if (LOG.isInfoEnabled())
			LOG.info("微信通知已成功发送至用户系统，商户订单号：{}", outTradeNo);
	}

	/**
	 * 记录支付通知并入库
	 * @param outTradeNo
	 * @param tradeNo
	 * @param tradeStatus
	 * @param timeEnd
	 * @param jsonOjb
	 * @throws Exception
	 */
	private void recordNotify(String outTradeNo, String tradeNo, String tradeStatus, String timeEnd, String jsonOjb) throws Exception {
		// 记录通知报文
		LogUtil.writePayLog(WxpayPort.WXPAY_TRADE_APP_PAY_NOTIFY, jsonOjb);
		// 更新订单表
		PayDBService payDBService = IocUtil.getBean(PayDBService.class);
		payDBService.updateTrade(outTradeNo, null, tradeNo, tradeStatus, timeEnd);
	}
}