package com.app.pay.service.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.app.code.Status;
import com.app.pay.entity.dto.PayBack;
import com.app.pay.service.PayNotifyProvider;
import com.app.pay.thread.AlipayNotifyRunnable;
import com.app.pay.thread.PayExecutor;
import com.app.pay.thread.SwiftNotifyRunnable;
import com.app.pay.thread.WxpayNotifyRunnable;
import com.app.pay.util.AlipayUtil;
import com.app.pay.util.SwiftUtil;
import com.app.pay.util.WxpayUtil;
import com.app.util.IocUtil;
import com.app.util.MapUtil;

/**
 * <p>功 能：第三方支付接口支付通知回调地址</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月20日 下午9:22:01</p>
 * @author 王建
 * @version 1.0
 */
@Service
public class PayNotifyProviderImpl implements PayNotifyProvider {
	private static final Logger LOG = LogManager.getLogger(PayNotifyProviderImpl.class);

	public String swiftNotify(String param) {
		String result = Status.FAIL.name();
		try {
			// 校验成功
			if (SwiftUtil.checkSign(param)) {
				// 返回 success 标识给威富通
				result = Status.SUCCESS.name();
				// 将通知内容发送到线程执行器处理
				PayExecutor.execute(new SwiftNotifyRunnable(param));
			}
		} catch (Exception e) {
			LOG.error("威富通支付通知出错", e);
		}
		return result;
	}

	public String wxpayNotify(String param) {
		String result = WxpayUtil.getNotifyBack(Status.FAIL.name());
		try {
			// 签名校验
			if (WxpayUtil.checkSign(param)) {
				result = WxpayUtil.getNotifyBack(Status.SUCCESS.name());
				// 将通知内容发送到线程执行器处理
				PayExecutor.execute(new WxpayNotifyRunnable(param));
			}
		} catch (Exception e) {
			LOG.error("微信支付通知出错", e);
		}
		return result;
	}

	public String alipayNotify(Map<String, String[]> paramMap) {
		String result = Status.FAIL.name();
		try {
			// 获取并转换请求参数
			Map<String, String> map = MapUtil.arrayToString(paramMap);
			// 校验成功
			if (AlipayUtil.checkNotify(map)) {
				// 返回 success 标识给支付宝
				result = Status.SUCCESS.name();
				// 将通知内容发送到线程执行器处理
				PayExecutor.execute(new AlipayNotifyRunnable(map));
			}
		} catch (Exception e) {
			LOG.error("支付宝支付通知出错", e);
		}
		return result;
	}

	public PayBack iosIapVerify(String outTradeNo, String receiptData, String password) {
		PayBack obj = null;
		try {
			IapService iap = IocUtil.getBean(IapService.class);
			obj = iap.verifyReceipt(outTradeNo, receiptData, password);
			if (LOG.isInfoEnabled())
				LOG.info("苹果内购服务器校验返回结果：{}", JSON.toJSONString(obj));
		} catch (Exception e) {
			LOG.error("苹果内购服务器二次验证出错", e);
		}
		return obj;
	}
}