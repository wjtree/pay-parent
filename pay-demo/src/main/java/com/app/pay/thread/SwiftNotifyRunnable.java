package com.app.pay.thread;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.app.pay.code.SwiftPort;
import com.app.pay.code.SwiftTradeStatus;
import com.app.pay.entity.dto.PayBack;
import com.app.pay.entity.dto.SwiftResponse;
import com.app.pay.service.impl.PayCheckService;
import com.app.pay.service.impl.PayDBService;
import com.app.pay.util.LogUtil;
import com.app.pay.util.SwiftUtil;
import com.app.util.DomUtil;
import com.app.util.IocUtil;
import com.app.util.JmsUtil;
import com.app.util.StringUtil;
import com.app.util.XmlUtil;

/**
 * <p>功 能：威富通支付通知处理线程</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年4月20日 下午3:31:51</p>
 * @author 王建
 * @version 1.0
 */
public class SwiftNotifyRunnable implements Runnable {
	private static final Logger LOG = LogManager.getLogger(SwiftNotifyRunnable.class);

	private String input = null;

	public SwiftNotifyRunnable() {
	}

	public SwiftNotifyRunnable(String input) {
		this.input = input;
	}

	public void run() {
		try {
			if (StringUtils.isNotBlank(input) && StringUtil.isXml(input)) {
				if (LOG.isInfoEnabled())
					LOG.info("收到威富通支付通知：{}", input);
				// 处理通知
				process(input);
			}
		} catch (Exception e) {
			LOG.error("统一下单接口支付通知处理出错", e);
		}
	}

	/**
	 * 处理支付通知
	 * @param input 通知内容
	 * @return
	 * @throws Exception
	 */
	private void process(final String input) throws Exception {
		// 将输入流字符串转换为JavaBean
		SwiftResponse response = XmlUtil.toBeanWithCData(input, SwiftResponse.class);
		// 如果通知内容中不包含out_trade_no，说明通知出现异常，不进行业务处理
		if (StringUtils.isNotBlank(response.getOutTradeNo())) {
			// 获取参数
			String outTradeNo = response.getOutTradeNo();
			String transactionId = response.getTransactionId();
			String outTransactionId = response.getOutTransactionId();
			String tradeState = (response.getPayResult() == 0 ? SwiftTradeStatus.SUCCESS : SwiftTradeStatus.PAYERROR);
			String timeEnd = response.getTimeEnd();
			String service = SwiftPort.getNotifyType(response.getTradeType());

			// 如果数据库中已记录支付结果字段，说明该通知是重复发送的，不进行后续处理
			PayCheckService payCheckService = IocUtil.getBean(PayCheckService.class);
			if (payCheckService.isTradeSuccess(outTradeNo))
				return;

			// 获取支付通知结果
			PayBack wftBack = SwiftUtil.getWftBack(service, input, outTradeNo);
			String jsonStr = JSON.toJSONString(wftBack);
			// 发送订单支付结果给客户端
			JmsUtil.sendQueueMessage(jsonStr);
			if (LOG.isInfoEnabled())
				LOG.info("威富通支付通知接口推送MQ消息：{}", jsonStr);

			// 记录通知报文
			LogUtil.writePayLog(service, DomUtil.xmlToJson(input));
			// 更新订单表
			PayDBService payDBService = IocUtil.getBean(PayDBService.class);
			payDBService.updateTrade(outTradeNo, transactionId, outTransactionId, tradeState, timeEnd);
		} else {
			if (LOG.isInfoEnabled())
				LOG.info("威富通支付通知异常，错误代码：{}，错误描述：{}", response.getErrCode(), response.getErrMsg());
		}
	}
}