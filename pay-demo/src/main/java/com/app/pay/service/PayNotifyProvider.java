package com.app.pay.service;

import java.util.Map;

import com.app.pay.entity.dto.PayBack;

/**
 * <p>功 能：第三方支付接口支付通知回调地址</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月9日 下午3:11:52</p>
 * @author 王建
 * @version 1.0
 */
public interface PayNotifyProvider {
	/**
	 * 威富通支付通知回调地址
	 * @param param
	 * @return
	 */
	String swiftNotify(String param);

	/**
	 * 微信支付通知回调地址
	 * @param param
	 * @return
	 */
	String wxpayNotify(String param);

	/**
	 * 支付宝支付通知回调地址
	 * @param paramMap
	 * @return
	 */
	String alipayNotify(Map<String, String[]> paramMap);

	/**
	 * 苹果内购服务器二次验证
	 * @param outTradeNo 
	 * @param receiptData 二次验证收据
	 * @param password
	 * @return
	 */
	PayBack iosIapVerify(String outTradeNo, String receiptData, String password);
}