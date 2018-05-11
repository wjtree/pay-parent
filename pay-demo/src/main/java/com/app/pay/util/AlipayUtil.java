package com.app.pay.util;

import java.util.Map;

import org.springframework.util.Assert;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayDataDataserviceBillDownloadurlQueryModel;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.app.pay.code.PayConstant;

/**
 * <p>功 能：支付宝支付接口调用工具类</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月2日 下午3:43:25</p>
 * @author 王建
 * @version 1.0
 */
public class AlipayUtil {
	/** 支付宝接口调用实例 */
	private static AlipayClient alipayClient = new DefaultAlipayClient(PayConstant.ALIPAY_SERVER_URL, PayConstant.ALIPAY_APP_ID, PayConstant.ALIPAY_RSA2_PRIVATE_KEY,
			PayConstant.ALIPAY_FORMAT, PayConstant.ALIPAY_CHARSET, PayConstant.ALIPAY_RSA2_ALIPAY_PULIC_KEY, PayConstant.ALIPAY_SIGN_TYPE, PayConstant.ALIPAY_ENCRYPT_KEY,
			PayConstant.ALIPAY_ENCRYPT_TYPE);

	/**
	 * 交易支付（APP）接口
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public static AlipayTradeAppPayResponse alipayTradeAppPay(AlipayTradeAppPayModel model) throws Exception {
		Assert.notNull(model, "model参数不能为空");
		// 封装业务参数
		model.setProductCode(PayConstant.ALIPAY_PRODUCT_CODE);
		// 封装公共参数
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		request.setBizModel(model);
		request.setNotifyUrl(PayConstant.ALIPAY_NOTIFY_URL);

		// 获取请求结果
		return alipayClient.sdkExecute(request);
	}

	/**
	 * 交易支付（WAP）接口
	 * @param model
	 * @param returnUrl
	 * @return
	 * @throws Exception
	 */
	public static AlipayTradeWapPayResponse alipayTradeWapPay(AlipayTradeWapPayModel model, String returnUrl) throws Exception {
		Assert.notNull(model, "model参数不能为空");
		// 封装业务参数
		model.setProductCode(PayConstant.ALIPAY_PRODUCT_CODE);

		// 封装公共参数
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
		alipayRequest.setBizModel(model);
		// 设置回跳地址
		alipayRequest.setReturnUrl(returnUrl);
		// 设置通知地址
		alipayRequest.setNotifyUrl(PayConstant.ALIPAY_NOTIFY_URL);

		// 调用SDK生成表单
		return alipayClient.pageExecute(alipayRequest);
	}

	/**
	 * 交易查询接口
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public static AlipayTradeQueryResponse alipayTradeQuery(AlipayTradeQueryModel model) throws Exception {
		Assert.notNull(model, "model参数不能为空");
		// 封装公共参数
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizModel(model);

		// 获取请求结果
		return alipayClient.execute(request);
	}

	/**
	 * 交易关闭接口
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public static AlipayTradeCloseResponse alipayTradeClose(AlipayTradeCloseModel model) throws Exception {
		Assert.notNull(model, "model参数不能为空");
		// 封装公共参数
		AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
		request.setBizModel(model);

		// 获取请求结果
		return alipayClient.execute(request);
	}

	/**
	 * 交易退款接口
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public static AlipayTradeRefundResponse alipayTradeRefund(AlipayTradeRefundModel model) throws Exception {
		Assert.notNull(model, "model参数不能为空");
		// 封装公共参数
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizModel(model);

		// 获取请求结果
		return alipayClient.execute(request);
	}

	/**
	 * 交易退款查询接口
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public static AlipayTradeFastpayRefundQueryResponse alipayTradeFastpayRefundQuery(AlipayTradeFastpayRefundQueryModel model) throws Exception {
		Assert.notNull(model, "model参数不能为空");
		// 封装公共参数
		AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
		request.setBizModel(model);

		// 获取请求结果
		return alipayClient.execute(request);
	}

	/**
	 * 查询账单下载地址接口
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public static AlipayDataDataserviceBillDownloadurlQueryResponse alipayDataDataserviceBillDownloadurlQuery(AlipayDataDataserviceBillDownloadurlQueryModel model)
			throws Exception {
		Assert.notNull(model, "model参数不能为空");
		// 封装公共参数
		AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
		request.setBizModel(model);

		// 获取请求结果
		return alipayClient.execute(request);
	}

	/**
	 * 验证支付宝支付通知消息
	 * @param paramsMap
	 * @return
	 */
	public static boolean checkNotify(final Map<String, String> paramsMap) throws Exception {
		// 签名校验
		return AlipaySignature.rsaCheckV1(paramsMap, PayConstant.ALIPAY_RSA2_ALIPAY_PULIC_KEY, PayConstant.ALIPAY_CHARSET, PayConstant.ALIPAY_SIGN_TYPE);
	}

	/**
	 * 将double类型的金额转换为分为单位的整型数值
	 * @param totalAmount
	 * @return
	 */
	public static Integer getFeeInt(String totalAmount) {
		return (int) (Double.valueOf(totalAmount) * 100);
	}
}