package com.app.pay.thread;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.app.pay.code.AlipayNotifyType;
import com.app.pay.code.AlipayPort;
import com.app.pay.code.AlipayTradeStatus;
import com.app.pay.code.PayConstant;
import com.app.pay.service.impl.PayCheckService;
import com.app.pay.service.impl.PayDBService;
import com.app.pay.util.LogUtil;
import com.app.util.IocUtil;

/**
 * <p>功 能：支付宝支付通知处理线程</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月3日 上午10:43:01</p>
 * @author 王建
 * @version 1.0
 */
public class AlipayNotifyRunnable implements Runnable {
	private static final Logger LOG = LogManager.getLogger(AlipayNotifyRunnable.class);

	/** 支付宝支付通知内容 */
	private Map<String, String> paramMap = null;

	public AlipayNotifyRunnable() {
	}

	public AlipayNotifyRunnable(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}

	public void run() {
		if (MapUtils.isNotEmpty(paramMap)) {
			try {
				if (LOG.isInfoEnabled())
					LOG.info("收到支付宝支付通知：{}", paramMap);

				// 获取通知参数
				String notifyType = MapUtils.getString(paramMap, "notify_type");
				String appId = MapUtils.getString(paramMap, "app_id");
				Integer totalFee = getTotalFee();
				String outTradeNo = MapUtils.getString(paramMap, "out_trade_no");
				String tradeNo = MapUtils.getString(paramMap, "trade_no");
				String tradeStatus = MapUtils.getString(paramMap, "trade_status");
				String gmtPayment = MapUtils.getString(paramMap, "gmt_payment");

				// 暂时只处理支付通知
				if (StringUtils.equalsIgnoreCase(notifyType, AlipayNotifyType.TRADE_STATUS_SYNC)) {
					// 转换交易状态为威富通的交易状态
					tradeStatus = AlipayTradeStatus.getPayTradeStatus(tradeStatus);
					// 校验数据有效性
					if (checkNotify(appId, outTradeNo, totalFee))
						return;
					// 将支付通知发送给接口调用端
					sendNotify(outTradeNo, tradeNo, totalFee, tradeStatus, gmtPayment);
					// 记录支付通知并入库
					recordNotify(outTradeNo, tradeNo, tradeStatus, gmtPayment);
				}
			} catch (Exception e) {
				LOG.error("支付宝支付通知处理出错", e);
			}
		}
	}

	/**
	 * 校验支付宝支付通知是否有效
	 * @param appId
	 * @param outTradeNo
	 * @param totalFee
	 * @return
	 * @throws Exception
	 */
	private boolean checkNotify(String appId, String outTradeNo, Integer totalFee) throws Exception {
		// 校验数据有效性
		PayCheckService payCheckService = IocUtil.getBean(PayCheckService.class);
		boolean appIdValid = StringUtils.equalsIgnoreCase(PayConstant.ALIPAY_APP_ID, appId);
		boolean tradeStateValid = payCheckService.isTradeSuccess(outTradeNo);
		boolean outTradeNoAndFeeValid = payCheckService.isMatchTradeAndFee(outTradeNo, totalFee);
		// 如果appid不匹配，或订单支付状态已记录，或订单金额不匹配，任何一个条件成立都不在继续执行
		return !appIdValid || tradeStateValid || !outTradeNoAndFeeValid;
	}

	/**
	 * 将支付通知发送给接口调用端
	 * @param outTradeNo
	 * @param tradeNo
	 * @param totalFee
	 * @param tradeStatus
	 * @param gmtPayment
	 * @throws Exception
	 */
	private void sendNotify(String outTradeNo, String tradeNo, Integer totalFee, String tradeStatus, String gmtPayment) throws Exception {
		// 调用用户系统接口，处理用户余额，不再发送MQ消息
		if (LOG.isInfoEnabled())
			LOG.info("支付宝通知已成功发送至用户系统，商户订单号：{}", outTradeNo);
	}

	/**
	 * 记录支付通知并入库
	 * @param outTradeNo
	 * @param tradeNo
	 * @param tradeStatus
	 * @param gmtPayment
	 * @throws Exception
	 */
	private void recordNotify(String outTradeNo, String tradeNo, String tradeStatus, String gmtPayment) throws Exception {
		// 记录通知报文
		LogUtil.writePayLog(AlipayPort.ALIPAY_TRADE_APP_PAY_NOTIFY, JSON.toJSONString(paramMap));
		// 更新订单表
		PayDBService payDBService = IocUtil.getBean(PayDBService.class);
		payDBService.updateTrade(outTradeNo, null, tradeNo, tradeStatus, gmtPayment);
	}

	/**
	 * 将double类型金额转换为int类型，以分为单位
	 * @return
	 * @throws Exception
	 */
	private Integer getTotalFee() throws Exception {
		Double totalAmount = MapUtils.getDouble(paramMap, "total_amount");
		totalAmount = (totalAmount != null ? totalAmount * 100 : null);
		return totalAmount != null ? totalAmount.intValue() : null;
	}
}