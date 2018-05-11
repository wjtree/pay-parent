package com.app.pay.controller;

import com.app.pay.service.PayNotifyProvider;
import io.swagger.annotations.Api;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * <p>功 能：第三方支付接口支付通知回调地址</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月20日 下午8:38:08</p>
 * @author 王建
 * @version 1.0
 */
@Api
@RestController
@RequestMapping(value = "payNotify", method = RequestMethod.POST)
public class PayNotifyController {
	private static final Logger LOG = LogManager.getLogger(PayNotifyController.class);

	@Autowired
	private PayNotifyProvider payNotifyProvider;

	/**
	 * 威富通支付回调通知
	 * @param request
	 * @return
	 */
	@PostMapping(value = "swift", produces = "text/plain; charset=UTF-8")
	public String swiftNotify(HttpServletRequest request) {
		String result = null;
		try {
			// 将通知输入流转换为字符串
			String input = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8.name());
			// URL解码
			input = URLDecoder.decode(input, StandardCharsets.UTF_8.name());
			// 调用接口
			result = payNotifyProvider.swiftNotify(input);
		} catch (Exception e) {
			LOG.error("威富通支付通知输入流处理出错", e);
		}
		return result;
	}

	/**
	 * Ping++支付回调通知
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping(value = "ping")
	public String pingNotify(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	/**
	 * 微信支付回调通知
	 * @param request
	 * @return
	 */
	@PostMapping(value = "wxpay", produces = "text/xml; charset=UTF-8")
	public String wxpayNotify(HttpServletRequest request) {
		String result = null;
		try {
			// 将通知输入流转换为字符串
			String input = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8.name());
			// URL解码
			input = URLDecoder.decode(input, StandardCharsets.UTF_8.name());
			// 调用接口
			result = payNotifyProvider.wxpayNotify(input);
		} catch (Exception e) {
			LOG.error("微信支付通知输入流处理出错", e);
		}
		return result;
	}

	/**
	 * 支付宝回调通知
	 * @param request
	 * @return
	 */
	@PostMapping(value = "alipay", produces = "text/plain; charset=UTF-8")
	public String alipayNotify(HttpServletRequest request) {
		// 调用接口，传入请求参数
		return payNotifyProvider.alipayNotify(request.getParameterMap());
	}

	/**
	 * 苹果内购服务器校验
	 * @param modelMap
	 * @param receiptData
	 * @param password
	 * @return
	 */
	@PostMapping(value = "iapVerify")
	public Object iosIapVerify(ModelMap modelMap, @RequestParam(value = "outTradeNo") String outTradeNo, @RequestParam(value = "receiptData") String receiptData,
			@RequestParam(value = "password", required = false) String password) {
		if (LOG.isInfoEnabled())
			LOG.info("苹果内购服务器校验参数 receiptData ：{}", receiptData);
		Object obj = payNotifyProvider.iosIapVerify(outTradeNo, receiptData, password);
		return obj;
	}
}