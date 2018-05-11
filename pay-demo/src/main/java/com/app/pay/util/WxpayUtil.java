package com.app.pay.util;

import java.net.InetAddress;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.Assert;

import com.app.code.Status;
import com.app.code.Symbol;
import com.app.core.VariableHolder;
import com.app.pay.code.PayConstant;
import com.app.pay.code.WxpayTradeType;
import com.app.pay.entity.dto.WxpayCloseOrderRequest;
import com.app.pay.entity.dto.WxpayCloseOrderResponse;
import com.app.pay.entity.dto.WxpayDownloadBillRequest;
import com.app.pay.entity.dto.WxpayDownloadBillResponse;
import com.app.pay.entity.dto.WxpayOrderQueryRequest;
import com.app.pay.entity.dto.WxpayOrderQueryResponse;
import com.app.pay.entity.dto.WxpayRefundQueryRequest;
import com.app.pay.entity.dto.WxpayRefundQueryResponse;
import com.app.pay.entity.dto.WxpayRefundRequest;
import com.app.pay.entity.dto.WxpayRefundResponse;
import com.app.pay.entity.dto.WxpayReportRequest;
import com.app.pay.entity.dto.WxpayReportResponse;
import com.app.pay.entity.dto.WxpayRequest;
import com.app.pay.entity.dto.WxpayUnifiedOrderAppRequest;
import com.app.pay.entity.dto.WxpayUnifiedOrderRequest;
import com.app.pay.entity.dto.WxpayUnifiedOrderResponse;
import com.app.util.DateUtil;
import com.app.util.HttpUtil;
import com.app.util.StringUtil;
import com.app.util.XmlUtil;

/**
 * <p>功 能：微信支付接口调用工具类</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月15日 上午11:35:21</p>
 * @author 王建
 * @version 1.0
 */
public class WxpayUtil {
	/**
	 * 交易支付（APP）接口
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static WxpayUnifiedOrderResponse wxpayTradeAppPay(WxpayUnifiedOrderRequest request) throws Exception {
		// 组装接口业务参数
		request.setDeviceInfo(PayConstant.WXPAY_DEVICE_INFO);
		request.setSignType(PayConstant.WXPAY_SIGN_TYPE);
		request.setFeeType(PayConstant.WXPAY_FEE_TYPE);
		request.setSpbillCreateIp(InetAddress.getLocalHost().getHostAddress());
		request.setNotifyUrl(PayConstant.WXPAY_NOTIFY_URL);
		request.setTradeType(WxpayTradeType.APP);
		// 组装公共参数
		loadParameter(request);

		// 获取接口请求结果
		String result = HttpUtil.httpPostXml(PayConstant.WXPAY_SERVER_UNIFIEDORDER_URL, XmlUtil.toXmlWithCData(request));
		// 将结果字符串转换为JavaBean
		WxpayUnifiedOrderResponse response = XmlUtil.toBeanWithCData(result, WxpayUnifiedOrderResponse.class);
		// 校验签名，如果签名校验不通过，说明请求结果不是微信发的，忽略返回结果
		return checkSign(result) ? response : null;
	}

	/**
	 * 调起支付接口
	 * <p>APP端调用支付SDK时需传入的参数</p>
	 * @param prepayid 预支付交易会话ID
	 * @return
	 * @throws Exception
	 */
	public static WxpayUnifiedOrderAppRequest wxpayTradeAppPayParam(String prepayid) throws Exception {
		// 参数校验
		Assert.hasText(prepayid, "prepayid不能为空");
		// 组装参数
		WxpayUnifiedOrderAppRequest req = new WxpayUnifiedOrderAppRequest();
		req.setAppid(PayConstant.WXPAY_APP_ID);
		req.setPartnerid(PayConstant.WXPAY_MCH_ID);
		req.setPrepayid(prepayid);
		req.setPackages(PayConstant.WXPAY_PACKAGES);
		req.setNoncestr(StringUtil.getNonceStr(32));
		req.setTimestamp(DateUtil.getSecondTimestamp());
		req.setSign(getSign(req));
		return req;
	}

	/**
	 * 交易支付（Wap）接口
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static WxpayUnifiedOrderResponse wxpayTradeWapPay(WxpayUnifiedOrderRequest request) throws Exception {
		// 组装接口业务参数
		request.setDeviceInfo(PayConstant.WXPAY_DEVICE_INFO);
		request.setSignType(PayConstant.WXPAY_SIGN_TYPE);
		request.setFeeType(PayConstant.WXPAY_FEE_TYPE);
		request.setNotifyUrl(PayConstant.WXPAY_NOTIFY_URL);
		request.setTradeType(WxpayTradeType.MWEB);
		// 组装公共参数
		loadParameter(request);

		// 获取接口请求结果
		String result = HttpUtil.httpPostXml(PayConstant.WXPAY_SERVER_UNIFIEDORDER_URL, XmlUtil.toXmlWithCData(request));
		// 将结果字符串转换为JavaBean
		WxpayUnifiedOrderResponse response = XmlUtil.toBeanWithCData(result, WxpayUnifiedOrderResponse.class);
		// 校验签名，如果签名校验不通过，说明请求结果不是微信发的，忽略返回结果
		return checkSign(result) ? response : null;
	}

	/**
	 * 交易查询接口
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static WxpayOrderQueryResponse wxpayTradeQuery(WxpayOrderQueryRequest request) throws Exception {
		// 组装公共参数
		loadParameter(request);

		// 获取接口请求结果
		String result = HttpUtil.httpPostXml(PayConstant.WXPAY_SERVER_ORDERQUERY_URL, XmlUtil.toXmlWithCData(request));
		// 将结果字符串转换为JavaBean
		WxpayOrderQueryResponse response = XmlUtil.toBeanWithCData(result, WxpayOrderQueryResponse.class);
		// 校验签名，如果签名校验不通过，说明请求结果不是微信发的，忽略返回结果
		return checkSign(result) ? response : null;
	}

	/**
	 * 交易关闭接口
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static WxpayCloseOrderResponse wxpayTradeClose(WxpayCloseOrderRequest request) throws Exception {
		// 组装公共参数
		loadParameter(request);

		// 获取接口请求结果
		String result = HttpUtil.httpPostXml(PayConstant.WXPAY_SERVER_CLOSEORDER_URL, XmlUtil.toXmlWithCData(request));
		// 将结果字符串转换为JavaBean
		WxpayCloseOrderResponse response = XmlUtil.toBeanWithCData(result, WxpayCloseOrderResponse.class);
		// 校验签名，如果签名校验不通过，说明请求结果不是微信发的，忽略返回结果
		return checkSign(result) ? response : null;
	}

	/**
	 * 交易退款接口
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static WxpayRefundResponse wxpayTradeRefund(WxpayRefundRequest request) throws Exception {
		// 组装接口业务参数
		request.setDeviceInfo(PayConstant.WXPAY_DEVICE_INFO);
		request.setSignType(PayConstant.WXPAY_SIGN_TYPE);
		request.setRefundFeeType(PayConstant.WXPAY_FEE_TYPE);
		request.setOpUserId(PayConstant.WXPAY_MCH_ID);
		// 组装公共参数
		loadParameter(request);

		// 获取接口请求结果
		String result = HttpUtil.httpsPostXml(PayConstant.WXPAY_SERVER_REFUND_URL, XmlUtil.toXmlWithCData(request), PayConstant.WXPAY_CERT_PATH, PayConstant.WXPAY_MCH_ID);
		// 将结果字符串转换为JavaBean
		WxpayRefundResponse response = XmlUtil.toBeanWithCData(result, WxpayRefundResponse.class);
		// 校验签名，如果签名校验不通过，说明请求结果不是微信发的，忽略返回结果
		return checkSign(result) ? response : null;
	}

	/**
	 * 交易退款查询接口
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static WxpayRefundQueryResponse wxpayTradeRefundQuery(WxpayRefundQueryRequest request) throws Exception {
		// 组装接口业务参数
		request.setDeviceInfo(PayConstant.WXPAY_DEVICE_INFO);
		// 组装公共参数
		loadParameter(request);

		// 获取接口请求结果
		String result = HttpUtil.httpPostXml(PayConstant.WXPAY_SERVER_REFUNDQUERY_URL, XmlUtil.toXmlWithCData(request));
		// 将结果字符串转换为JavaBean
		WxpayRefundQueryResponse response = XmlUtil.toBeanWithCData(result, WxpayRefundQueryResponse.class);
		// 校验签名，如果签名校验不通过，说明请求结果不是微信发的，忽略返回结果
		return checkSign(result) ? response : null;
	}

	/**
	 * 下载对账单
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static WxpayDownloadBillResponse wxpayDownloadBill(WxpayDownloadBillRequest request) throws Exception {
		// 组装接口业务参数
		request.setDeviceInfo(PayConstant.WXPAY_DEVICE_INFO);
		request.setSignType(PayConstant.WXPAY_SIGN_TYPE);
		// 组装公共参数
		loadParameter(request);

		// 获取接口请求结果
		String result = HttpUtil.httpPostXml(PayConstant.WXPAY_SERVER_DOWNLOADBILL_URL, XmlUtil.toXmlWithCData(request));
		// 将结果字符串转换为JavaBean
		return XmlUtil.toBeanWithCData(result, WxpayDownloadBillResponse.class);
	}

	/**
	 * 交易保障接口
	 * <p>暂不启用</p>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static WxpayReportResponse wxpayDownloadReport(WxpayReportRequest request) throws Exception {
		// 组装接口业务参数
		request.setAppid(PayConstant.WXPAY_APP_ID);
		request.setMchId(PayConstant.WXPAY_MCH_ID);
		request.setDeviceInfo(PayConstant.WXPAY_DEVICE_INFO);
		request.setNonceStr(StringUtil.getNonceStr(32));
		request.setUserIp(InetAddress.getLocalHost().getHostAddress());
		request.setTime(DateUtil.getCurrentDateTime(DateUtil.FMT_DATETIME_SHORT));
		request.setSign(getSign(request));

		// 获取接口请求结果
		String result = HttpUtil.httpPostXml(PayConstant.WXPAY_SERVER_REPORT_URL, XmlUtil.toXmlWithCData(request));
		// 将结果字符串转换为JavaBean
		return XmlUtil.toBeanWithCData(result, WxpayReportResponse.class);
	}

	/**
	 * 加载公共参数
	 * @param t
	 */
	private static <T extends WxpayRequest> void loadParameter(T t) throws Exception {
		t.setAppid(PayConstant.WXPAY_APP_ID);
		t.setMchId(PayConstant.WXPAY_MCH_ID);
		t.setNonceStr(StringUtil.getNonceStr(32));
		t.setSign(getSign(t));
	}

	/**
	 * 获取商品描述
	 * <p>微信APP支付接口要求的"商品描述"参数格式为："应用市场上的APP名字-实际商品名称"</p>
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static String getBody(final String body) throws Exception {
		return VariableHolder.getStringBuilder().append(PayConstant.WXPAY_APP_NAME).append(Symbol.DASH.getSymbol()).append(body).toString();
	}

	/**
	 * 获取微信支付接口签名
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String getSign(final Object obj) throws Exception {
		return SignUtil.getSignature(XmlUtil.toXmlWithCData(obj), PayConstant.WXPAY_API_KEY);
	}

	/**
	 * 校验微信支付接口签名
	 * @param xmlStr
	 * @return
	 * @throws Exception
	 */
	public static boolean checkSign(final String xmlStr) throws Exception {
		return SignUtil.checkSignature(xmlStr, PayConstant.WXPAY_API_KEY);
	}

	/**
	 * 获取微信支付通知返回xml字符串
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public static String getNotifyBack(final String code) {
		String returnMsg = StringUtils.equals(Status.SUCCESS.name(), code) ? "OK" : "ERROR";
		WxpayDownloadBillResponse model = new WxpayDownloadBillResponse();
		model.setReturnCode(code);
		model.setReturnMsg(returnMsg);
		return XmlUtil.toXmlWithCData(model);
	}

	/**
	 * 将yyyyMMddHHmmss格式日期转换为yyyy-MM-dd HH:mm:ss格式
	 * @param dateStr
	 * @return
	 * @throws Exception
	 */
	public static String formatWxDate(final String dateStr) throws Exception {
		Date date = DateUtils.parseDate(dateStr, DateUtil.FMT_DATETIME_SHORT);
		return DateFormatUtils.format(date, DateUtil.FMT_DATETIME);
	}
}