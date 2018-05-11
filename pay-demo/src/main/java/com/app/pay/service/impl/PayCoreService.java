package com.app.pay.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.app.pay.code.AlipayPort;
import com.app.pay.code.SwiftPort;
import com.app.pay.code.WxpayPort;
import com.app.pay.entity.dto.PayBack;
import com.app.pay.entity.dto.SwiftRequest;
import com.app.pay.entity.dto.WxpayCloseOrderRequest;
import com.app.pay.entity.dto.WxpayCloseOrderResponse;
import com.app.pay.entity.dto.WxpayOrderQueryRequest;
import com.app.pay.entity.dto.WxpayOrderQueryResponse;
import com.app.pay.entity.dto.WxpayRefundQueryRequest;
import com.app.pay.entity.dto.WxpayRefundQueryResponse;
import com.app.pay.entity.dto.WxpayRefundRequest;
import com.app.pay.entity.dto.WxpayRefundResponse;
import com.app.pay.entity.dto.WxpayUnifiedOrderRequest;
import com.app.pay.entity.dto.WxpayUnifiedOrderResponse;
import com.app.pay.util.PayBackUtil;
import com.app.pay.util.PayBindUtil;

/**
 * <p>功 能：第三方支付请求转发</p>
 * <p>描述：将支付请求转发到实际的支付服务商接口，如威富通，微信，支付宝</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月10日 下午2:38:32</p>
 * @author 王建
 * @version 1.0
 */
@Service
public class PayCoreService {
	@Autowired
	private PayCheckService payCheckService;
	@Autowired
	private SwiftService swiftService;
	@Autowired
	private AlipayService alipayService;
	@Autowired
	private WxpayService wxpayService;

	/**
	 * 交易支付接口选择
	 * @param service 支付类型
	 * @param outTradeNo 商户订单号
	 * @param body 商品名称
	 * @param totalFee 总金额
	 * @param expireTime 订单有效期，单位：分钟
	 * @param returnUrl WAP支付回跳地址
	 * @param spbillCreateIp 用户端IP，微信H5支付必填
	 * @return
	 * @throws Exception
	 */
	public PayBack unifiedTradePayProcess(String service, String outTradeNo, String body, Integer totalFee, Integer expireTime, String returnUrl, String spbillCreateIp)
			throws Exception {
		PayBack obj = null;
		if (StringUtils.equalsAnyIgnoreCase(service, SwiftPort.PAY_PC_ARRAY)) {
			// <威富通>支付宝或微信扫码支付
			SwiftRequest model = PayBindUtil.swiftTradePay(service, outTradeNo, body, totalFee, expireTime);
			obj = swiftService.swiftTradePay(model);
		} else if (StringUtils.equalsIgnoreCase(service, AlipayPort.ALIPAY_TRADE_APP_PAY)) {
			// <支付宝>支付宝APP支付
			AlipayTradeAppPayModel model = PayBindUtil.alipayTradeAppPay(outTradeNo, body, totalFee, expireTime);
			AlipayTradeAppPayResponse resp = alipayService.alipayTradeAppPay(model);
			obj = PayBackUtil.alipayTradeAppPay(resp, outTradeNo);
		} else if (StringUtils.equalsIgnoreCase(service, AlipayPort.ALIPAY_TRADE_WAP_PAY)) {
			// <支付宝>支付宝WAP支付
			AlipayTradeWapPayModel model = PayBindUtil.alipayTradeWapPay(outTradeNo, body, totalFee, expireTime);
			AlipayTradeWapPayResponse resp = alipayService.alipayTradeWapPay(model, returnUrl);
			obj = PayBackUtil.alipayTradeWapPay(resp, outTradeNo);
		} else if (StringUtils.equalsIgnoreCase(service, WxpayPort.WXPAY_TRADE_APP_PAY)) {
			// <微信>微信APP支付
			WxpayUnifiedOrderRequest model = PayBindUtil.wxpayTradeAppPay(outTradeNo, body, totalFee, expireTime);
			WxpayUnifiedOrderResponse resp = wxpayService.wxpayTradeAppPay(model);
			obj = PayBackUtil.wxpayTradeAppPay(resp, outTradeNo);
		} else if (StringUtils.equalsIgnoreCase(service, WxpayPort.WXPAY_TRADE_WAP_PAY)) {
			// <微信>微信WAP支付
			WxpayUnifiedOrderRequest model = PayBindUtil.wxpayTradeWapPay(outTradeNo, body, totalFee, expireTime, returnUrl, spbillCreateIp);
			WxpayUnifiedOrderResponse resp = wxpayService.wxpayTradeWapPay(model);
			obj = PayBackUtil.wxpayTradeWapPay(resp, outTradeNo);
		} else {
			// 未找到匹配的接口名称
			obj = PayBackUtil.getErrorBack("<service>参数无效");
		}
		return obj;
	}

	/**
	 * 交易查询接口选择
	 * @param outTradeNo 商户订单号
	 * @return
	 * @throws Exception
	 */
	public PayBack unifiedTradeQueryProcess(String outTradeNo) throws Exception {
		PayBack obj = null;
		// 查询数据库获取支付订单交易类型
		String service = payCheckService.getTradeType(outTradeNo);
		if (StringUtils.equalsAnyIgnoreCase(service, SwiftPort.PAY_PC_ARRAY)) {
			// <威富通>支付宝或微信扫码支付
			SwiftRequest model = PayBindUtil.swiftTradeQuery(outTradeNo);
			obj = swiftService.swiftTradeQuery(model);
		} else if (StringUtils.equalsAnyIgnoreCase(service, AlipayPort.PAY_ARRAY)) {
			// <支付宝>支付宝APP支付
			AlipayTradeQueryModel model = PayBindUtil.alipayTradeQuery(outTradeNo);
			AlipayTradeQueryResponse resp = alipayService.alipayTradeQuery(model);
			obj = PayBackUtil.alipayTradeQuery(resp);
		} else if (StringUtils.equalsAnyIgnoreCase(service, WxpayPort.PAY_ARRAY)) {
			// <微信>微信APP支付
			WxpayOrderQueryRequest model = PayBindUtil.wxpayTradeQuery(outTradeNo);
			WxpayOrderQueryResponse resp = wxpayService.wxpayTradeQuery(model);
			obj = PayBackUtil.wxpayTradeQuery(resp);
		} else {
			// 订单号不存在
			obj = PayBackUtil.getErrorBack("<service>参数无效或订单号不存在");
		}
		return obj;
	}

	/**
	 * 交易关闭接口选择
	 * @param outTradeNo 商户订单号
	 * @return
	 * @throws Exception
	 */
	public PayBack unifiedTradeCloseProcess(String outTradeNo) throws Exception {
		PayBack obj = null;
		// 查询数据库获取支付订单交易类型
		String service = payCheckService.getTradeType(outTradeNo);
		if (StringUtils.equalsAnyIgnoreCase(service, SwiftPort.PAY_PC_ARRAY)) {
			// <威富通>支付宝或微信扫码支付
			SwiftRequest model = PayBindUtil.swiftTradeClose(outTradeNo);
			obj = swiftService.swiftTradeClose(model);
		} else if (StringUtils.equalsAnyIgnoreCase(service, AlipayPort.PAY_ARRAY)) {
			// <支付宝>支付宝APP支付
			AlipayTradeCloseModel model = PayBindUtil.alipayTradeClose(outTradeNo);
			AlipayTradeCloseResponse resp = alipayService.alipayTradeClose(model);
			obj = PayBackUtil.alipayTradeClose(resp);
		} else if (StringUtils.equalsAnyIgnoreCase(service, WxpayPort.PAY_ARRAY)) {
			// <微信>微信APP支付
			WxpayCloseOrderRequest model = PayBindUtil.wxpayTradeClose(outTradeNo);
			WxpayCloseOrderResponse resp = wxpayService.wxpayTradeClose(model);
			obj = PayBackUtil.wxpayTradeClose(resp, outTradeNo);
		} else {
			// 订单号不存在
			obj = PayBackUtil.getErrorBack("<service>参数无效或订单号不存在");
		}
		return obj;
	}

	/**
	 * 交易退款接口选择
	 * @param outTradeNo 商户订单号
	 * @param outRefundNo 商户退款单号
	 * @param totalFee 订单金额
	 * @param refundFee 退款金额
	 * @return
	 * @throws Exception
	 */
	public PayBack unifiedTradeRefundProcess(String outTradeNo, String outRefundNo, Integer totalFee, Integer refundFee) throws Exception {
		PayBack obj = null;
		// 查询数据库获取支付订单交易类型
		String service = payCheckService.getTradeType(outTradeNo);
		if (StringUtils.equalsAnyIgnoreCase(service, SwiftPort.PAY_PC_ARRAY)) {
			// <威富通>支付宝或微信扫码支付
			SwiftRequest model = PayBindUtil.swiftTradeRefund(outTradeNo, outRefundNo, totalFee, refundFee);
			obj = swiftService.swiftTradeRefund(model);
		} else if (StringUtils.equalsAnyIgnoreCase(service, AlipayPort.PAY_ARRAY)) {
			// <支付宝>支付宝APP支付
			AlipayTradeRefundModel model = PayBindUtil.alipayTradeRefund(outTradeNo, outRefundNo, refundFee);
			AlipayTradeRefundResponse resp = alipayService.alipayTradeRefund(model);
			obj = PayBackUtil.alipayTradeRefund(resp, outRefundNo, totalFee);
		} else if (StringUtils.equalsAnyIgnoreCase(service, WxpayPort.PAY_ARRAY)) {
			// <微信>微信APP支付
			WxpayRefundRequest model = PayBindUtil.wxpayTradeRefund(outTradeNo, outRefundNo, totalFee, refundFee);
			WxpayRefundResponse resp = wxpayService.wxpayTradeRefund(model);
			obj = PayBackUtil.wxpayTradeRefund(resp);
		} else {
			// 订单号不存在
			obj = PayBackUtil.getErrorBack("<service>参数无效或订单号不存在");
		}
		return obj;
	}

	/**
	 * 交易退款查询接口选择
	 * @param outTradeNo 商户订单号
	 * @param outRefundNo 商户退款单号
	 * @return
	 * @throws Exception
	 */
	public PayBack unifiedTradeRefundQueryProcess(String outTradeNo, String outRefundNo) throws Exception {
		PayBack obj = null;
		// 查询数据库获取支付订单交易类型
		String service = payCheckService.getTradeType(outTradeNo);
		if (StringUtils.equalsAnyIgnoreCase(service, SwiftPort.PAY_PC_ARRAY)) {
			// <威富通>支付宝或微信扫码支付
			SwiftRequest model = PayBindUtil.swiftTradeRefundQuery(outTradeNo, outRefundNo);
			obj = swiftService.swiftTradeRefundQuery(model);
		} else if (StringUtils.equalsAnyIgnoreCase(service, AlipayPort.PAY_ARRAY)) {
			// <支付宝>支付宝APP支付
			AlipayTradeFastpayRefundQueryModel model = PayBindUtil.alipayTradeFastpayRefundQuery(outTradeNo, outRefundNo);
			AlipayTradeFastpayRefundQueryResponse resp = alipayService.alipayTradeFastpayRefundQuery(model);
			obj = PayBackUtil.alipayTradeFastpayRefundQuery(resp);
		} else if (StringUtils.equalsAnyIgnoreCase(service, WxpayPort.PAY_ARRAY)) {
			// <微信>微信APP支付
			WxpayRefundQueryRequest model = PayBindUtil.wxpayTradeRefundQuery(outTradeNo, outRefundNo);
			WxpayRefundQueryResponse resp = wxpayService.wxpayTradeRefundQuery(model);
			obj = PayBackUtil.wxpayTradeRefundQuery(resp);
		} else {
			// 订单号不存在
			obj = PayBackUtil.getErrorBack("<service>参数无效或订单号不存在");
		}
		return obj;
	}

	/**
	 * 下载对账单接口选择
	 * <p>暂时只提供威富通支付的对账单下载</p>
	 * @param billDate 账单日期
	 * @param billType 账单类型
	 * @return
	 * @throws Exception
	 */
	public Object unifiedDownloadBillProcess(String billDate, String billType) throws Exception {
		// 威富通支付的对账单下载
		SwiftRequest model = PayBindUtil.swiftDownloadBill(billDate, billType);
		return swiftService.swiftDownloadBill(model);
	}
}