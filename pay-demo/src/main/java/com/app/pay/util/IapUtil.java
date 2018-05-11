package com.app.pay.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.alibaba.fastjson.JSON;
import com.app.pay.code.IapStatus;
import com.app.pay.code.PayConstant;
import com.app.pay.code.IapPort;
import com.app.pay.code.SwiftTradeStatus;
import com.app.pay.entity.dto.IapInAppReceipt;
import com.app.pay.entity.dto.IapRequest;
import com.app.pay.entity.dto.IapResponse;
import com.app.pay.entity.dto.PayBack;
import com.app.util.DateUtil;
import com.app.util.HttpUtil;

/**
 * <p>功 能：苹果内购二次验证工具类</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月23日 下午12:14:34</p>
 * @author 王建
 * @version 1.0
 */
public class IapUtil {
	/**
	 * 苹果内购服务器二次验证
	 * @param receiptData base64编码的收据数据
	 * @param password 仅用于包含自动续订订阅的收据
	 * @return
	 * @throws Exception
	 */
	public static PayBack verifyReceipt(final String outTradeNo, final String receiptData, final String password) throws Exception {
		// 组装请求参数
		IapRequest req = new IapRequest();
		req.setReceiptData(receiptData);
		req.setPassword(password);
		// 转换为json字符串
		String reqJson = JSON.toJSONString(req);
		// 发送验证请求
		IapResponse resp = doVerify(PayConstant.IOS_IAP_VERIFY_SANDBOX_URL, reqJson);
		// 转换请求结果
		return invertPayBack(outTradeNo, resp);
	}

	/**
	 * 发送验证请求
	 * @param url 请求地址
	 * @param json 请求JSON字符串参数
	 * @return
	 * @throws Exception
	 */
	private static IapResponse doVerify(final String url, final String json) throws Exception {
		IapResponse resp = null;
		String status = null;

		// 使用循环处理苹果内购支付环境与服务器校验环境不一致的问题
		do {
			// 获取转换后的请求地址
			String realUrl = getRealUrl(url, status);
			// 发送验证请求
			String result = doRequest(realUrl, json);
			// 转换为javabean
			resp = JSON.parseObject(result, IapResponse.class);
			// 获取校验请求状态
			status = resp.getStatus();
		} while (StringUtils.equalsAny(status, IapStatus.S21007, IapStatus.S21008));

		return resp;
	}

	/**
	 * 循环调用方式获取请求结果
	 * @param url
	 * @param json
	 * @return
	 * @throws Exception
	 */
	private static String doRequest(final String url, final String json) throws Exception {
		// 发送验证请求
		String result = null;

		// 使用循环请求苹果校验接口，直到获取结果为止，此处待完善
		do {
			result = HttpUtil.httpPostJson(url, json);
		} while (StringUtils.isBlank(result));

		return result;
	}

	/**
	 * 根据苹果校验请求状态转换请求地址
	 * @param url
	 * @param status
	 * @return
	 */
	private static String getRealUrl(final String url, final String status) {
		String realUrl = url;
		if (StringUtils.isNotBlank(status)) {
			if (StringUtils.equals(status, IapStatus.S21007))
				realUrl = PayConstant.IOS_IAP_VERIFY_SANDBOX_URL;
			else if (StringUtils.equals(status, IapStatus.S21008))
				realUrl = PayConstant.IOS_IAP_VERIFY_BUY_URL;
		}
		return realUrl;
	}

	/**
	 * 将IapResponse转换为PayBack
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	private static PayBack invertPayBack(final String outTradeNo, final IapResponse resp) throws Exception {
		PayBack back = null;
		String tradeStatus = resp.getStatus();
		// tradeStatus 为0表示支付成功，否则表示支付失败
		if (StringUtils.equals(IapStatus.S0, tradeStatus)) {
			// 应用内采购收据字段，此处需完善，因为 getInApp 中有可能不止一个元素
			IapInAppReceipt inApp = resp.getReceipt().getInApp().get(0);
			String productId = inApp.getProductId();
			String transactionId = inApp.getTransactionId();
			String quantity = inApp.getQuantity();
			Long purchaseDateMs = Long.valueOf(inApp.getPurchaseDateMs());
			String timeEnd = DateFormatUtils.format(purchaseDateMs, DateUtil.FMT_DATETIME);
			// 转换为payback返回
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("service", IapPort.IOS_IAP_TRADE_VERIFY);
			paramMap.put("outTradeNo", outTradeNo);
			paramMap.put("transactionId", transactionId);
			paramMap.put("body", productId);
			paramMap.put("totalFee", invertTotalFee(productId, quantity));
			paramMap.put("tradeState", SwiftTradeStatus.SUCCESS);
			paramMap.put("timeEnd", timeEnd);
			back = PayBackUtil.getSuccessBack(paramMap);
		} else {
			back = PayBackUtil.getErrorBack(tradeStatus, "苹果内购校验参数错误");
		}
		return back;
	}

	/**
	 * 根据苹果内购的商品数量与商品标识获取订单总额
	 * @param productId 商品标识
	 * @param quantity 商品数量
	 * @return
	 */
	private static Integer invertTotalFee(final String productId, final String quantity) {
		Integer totalFee = null;
		if (StringUtils.equalsAnyIgnoreCase(productId, IapPort.COM_APPS_YBF_6, IapPort.COM_APPS_YBF_CN_6))
			totalFee = 6;
		else if (StringUtils.equalsAnyIgnoreCase(productId, IapPort.COM_APPS_YBF_12, IapPort.COM_APPS_YBF_CN_12))
			totalFee = 12;
		else if (StringUtils.equalsAnyIgnoreCase(productId, IapPort.COM_APPS_YBF_18, IapPort.COM_APPS_YBF_CN_18))
			totalFee = 18;
		else if (StringUtils.equalsAnyIgnoreCase(productId, IapPort.COM_APPS_YBF_25, IapPort.COM_APPS_YBF_CN_25))
			totalFee = 25;
		else if (StringUtils.equalsAnyIgnoreCase(productId, IapPort.COM_APPS_YBF_30, IapPort.COM_APPS_YBF_CN_30))
			totalFee = 30;
		else if (StringUtils.equalsAnyIgnoreCase(productId, IapPort.COM_APPS_YBF_50, IapPort.COM_APPS_YBF_CN_50))
			totalFee = 50;
		else if (StringUtils.equalsAnyIgnoreCase(productId, IapPort.COM_APPS_YBF_60, IapPort.COM_APPS_YBF_CN_60))
			totalFee = 60;
		else if (StringUtils.equalsAnyIgnoreCase(productId, IapPort.COM_APPS_YBF_98, IapPort.COM_APPS_YBF_CN_98))
			totalFee = 98;
		return totalFee * 100 * Integer.valueOf(quantity);
	}
}