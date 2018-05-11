package com.app.pay.util;

import com.alipay.api.domain.AlipayDataDataserviceBillDownloadurlQueryModel;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.app.pay.code.WxpaySceneType;
import com.app.pay.entity.dto.SwiftRequest;
import com.app.pay.entity.dto.WxpayCloseOrderRequest;
import com.app.pay.entity.dto.WxpayDownloadBillRequest;
import com.app.pay.entity.dto.WxpayOrderQueryRequest;
import com.app.pay.entity.dto.WxpayRefundQueryRequest;
import com.app.pay.entity.dto.WxpayRefundRequest;
import com.app.pay.entity.dto.WxpayUnifiedOrderRequest;
import com.app.pay.entity.dto.WxpayUnifiedOrderWapInfo;
import com.app.pay.entity.dto.WxpayUnifiedOrderWapSceneInfo;
import com.app.util.DateUtil;

/**
 * <p>功 能：第三方支付请求参数处理</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月10日 下午5:54:24</p>
 * @author 王建
 * @version 1.0
 */
public class PayBindUtil {
	/**
	 * 组装威富通交易支付接口请求参数
	 * @param service
	 * @param outTradeNo
	 * @param body
	 * @param totalFee
	 * @param expireTime
	 * @return
	 * @throws Exception
	 */
	public static SwiftRequest swiftTradePay(String service, String outTradeNo, String body, Integer totalFee, Integer expireTime) throws Exception {
		SwiftRequest model = new SwiftRequest();
		model.setService(service);
		model.setOutTradeNo(outTradeNo);
		model.setBody(body);
		model.setTotalFee(totalFee);
		// 订单创建时间
		model.setTimeStart(DateUtil.getCurrentDateTime(DateUtil.FMT_DATETIME_SHORT));
		// 订单失效时间
		model.setTimeExpire(DateUtil.getDateTimeByNow(expireTime, DateUtil.FMT_DATETIME_SHORT));
		return model;
	}

	/**
	 * 组装威富通交易查询接口请求参数
	 * @param outTradeNo
	 * @return
	 * @throws Exception
	 */
	public static SwiftRequest swiftTradeQuery(String outTradeNo) throws Exception {
		SwiftRequest model = new SwiftRequest();
		model.setOutTradeNo(outTradeNo);
		return model;
	}

	/**
	 * 组装威富通交易关闭接口请求参数
	 * @param outTradeNo
	 * @return
	 * @throws Exception
	 */
	public static SwiftRequest swiftTradeClose(String outTradeNo) throws Exception {
		SwiftRequest model = new SwiftRequest();
		model.setOutTradeNo(outTradeNo);
		return model;
	}

	/**
	 * 组装威富通交易退款接口请求参数
	 * @param outTradeNo
	 * @param outRefundNo
	 * @param totalFee
	 * @param refundFee
	 * @return
	 * @throws Exception
	 */
	public static SwiftRequest swiftTradeRefund(String outTradeNo, String outRefundNo, Integer totalFee, Integer refundFee) throws Exception {
		SwiftRequest model = new SwiftRequest();
		model.setOutTradeNo(outTradeNo);
		model.setOutRefundNo(outRefundNo);
		model.setTotalFee(totalFee);
		model.setRefundFee(refundFee);
		return model;
	}

	/**
	 * 组装威富通交易查询接口请求参数
	 * @param outTradeNo
	 * @param outRefundNo
	 * @return
	 * @throws Exception
	 */
	public static SwiftRequest swiftTradeRefundQuery(String outTradeNo, String outRefundNo) throws Exception {
		SwiftRequest model = new SwiftRequest();
		model.setOutTradeNo(outTradeNo);
		model.setOutRefundNo(outRefundNo);
		return model;
	}

	/**
	 * 组装威富通交易退款查询接口请求参数
	 * @param billDate
	 * @param billType
	 * @return
	 * @throws Exception
	 */
	public static SwiftRequest swiftDownloadBill(String billDate, String billType) throws Exception {
		SwiftRequest model = new SwiftRequest();
		model.setBillDate(billDate);
		model.setBillType(billType);
		return model;
	}

	/**
	 * 组装支付宝交易APP支付接口请求参数
	 * @param outTradeNo
	 * @param body
	 * @param totalFee
	 * @param expireTime
	 * @return
	 * @throws Exception
	 */
	public static AlipayTradeAppPayModel alipayTradeAppPay(String outTradeNo, String body, Integer totalFee, Integer expireTime) throws Exception {
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setOutTradeNo(outTradeNo);
		model.setSubject(body);
		// 此处转换为小数形式
		model.setTotalAmount(String.valueOf(totalFee / 100.0));
		// 此处转换为分钟形式
		model.setTimeoutExpress(expireTime + "m");
		return model;
	}

	/**
	 * 组装支付宝交易WAP支付接口请求参数
	 * @param outTradeNo
	 * @param body
	 * @param totalFee
	 * @param expireTime
	 * @return
	 * @throws Exception
	 */
	public static AlipayTradeWapPayModel alipayTradeWapPay(String outTradeNo, String body, Integer totalFee, Integer expireTime) throws Exception {
		AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
		model.setOutTradeNo(outTradeNo);
		model.setSubject(body);
		// 此处转换为小数形式
		model.setTotalAmount(String.valueOf(totalFee / 100.0));
		// 此处转换为分钟形式
		model.setTimeoutExpress(expireTime + "m");
		return model;
	}

	/**
	 * 组装支付宝交易查询接口请求参数
	 * @param outTradeNo
	 * @return
	 * @throws Exception
	 */
	public static AlipayTradeQueryModel alipayTradeQuery(String outTradeNo) throws Exception {
		AlipayTradeQueryModel model = new AlipayTradeQueryModel();
		model.setOutTradeNo(outTradeNo);
		return model;
	}

	/**
	 * 组装支付宝交易关闭接口请求参数
	 * @param outTradeNo
	 * @return
	 * @throws Exception
	 */
	public static AlipayTradeCloseModel alipayTradeClose(String outTradeNo) throws Exception {
		AlipayTradeCloseModel model = new AlipayTradeCloseModel();
		model.setOutTradeNo(outTradeNo);
		return model;
	}

	/**
	 * 组装支付宝交易退款接口请求参数
	 * @param outTradeNo
	 * @param outRefundNo
	 * @param refundFee
	 * @return
	 * @throws Exception
	 */
	public static AlipayTradeRefundModel alipayTradeRefund(String outTradeNo, String outRefundNo, Integer refundFee) throws Exception {
		AlipayTradeRefundModel model = new AlipayTradeRefundModel();
		model.setOutTradeNo(outTradeNo);
		model.setOutRequestNo(outRefundNo);
		model.setRefundAmount(refundFee.toString());
		return model;
	}

	/**
	 * 组装支付宝交易退款查询接口请求参数
	 * @param outTradeNo
	 * @param outRefundNo
	 * @return
	 * @throws Exception
	 */
	public static AlipayTradeFastpayRefundQueryModel alipayTradeFastpayRefundQuery(String outTradeNo, String outRefundNo) throws Exception {
		AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
		model.setOutTradeNo(outTradeNo);
		model.setOutRequestNo(outRefundNo);
		return model;
	}

	/**
	 * 组装支付宝查询账单下载地址接口请求参数
	 * @param billDate
	 * @param billType
	 * @return
	 * @throws Exception
	 */
	public static AlipayDataDataserviceBillDownloadurlQueryModel alipayDataDataserviceBillDownloadurlQuery(String billDate, String billType) throws Exception {
		AlipayDataDataserviceBillDownloadurlQueryModel model = new AlipayDataDataserviceBillDownloadurlQueryModel();
		model.setBillDate(billDate);
		model.setBillType(billType);
		return model;
	}

	/**
	 * 交易支付（APP）接口
	 * @param outTradeNo
	 * @param body
	 * @param totalFee
	 * @param expireTime
	 * @return
	 * @throws Exception
	 */
	public static WxpayUnifiedOrderRequest wxpayTradeAppPay(String outTradeNo, String body, Integer totalFee, Integer expireTime) throws Exception {
		WxpayUnifiedOrderRequest model = new WxpayUnifiedOrderRequest();
		model.setOutTradeNo(outTradeNo);
		model.setBody(WxpayUtil.getBody(body));
		model.setTotalFee(totalFee);
		model.setTimeStart(DateUtil.getCurrentDateTime(DateUtil.FMT_DATETIME_SHORT));
		model.setTimeExpire(DateUtil.getDateTimeByNow(expireTime, DateUtil.FMT_DATETIME_SHORT));
		return model;
	}

	/**
	 * 交易支付（WAP）接口
	 * @param outTradeNo
	 * @param body
	 * @param totalFee
	 * @param expireTime
	 * @param returnUrl
	 * @param spbillCreateIp
	 * @return
	 * @throws Exception
	 */
	public static WxpayUnifiedOrderRequest wxpayTradeWapPay(String outTradeNo, String body, Integer totalFee, Integer expireTime, String returnUrl, String spbillCreateIp)
			throws Exception {
		WxpayUnifiedOrderRequest model = new WxpayUnifiedOrderRequest();
		model.setOutTradeNo(outTradeNo);
		model.setBody(body);
		model.setTotalFee(totalFee);
		model.setTimeStart(DateUtil.getCurrentDateTime(DateUtil.FMT_DATETIME_SHORT));
		model.setTimeExpire(DateUtil.getDateTimeByNow(expireTime, DateUtil.FMT_DATETIME_SHORT));
		model.setSpbillCreateIp(spbillCreateIp);
		model.setSceneInfo(getSceneInfo(body, returnUrl));
		return model;
	}

	/**
	 * 组装微信H5支付的场景信息
	 * @param body
	 * @param returnUrl
	 * @return
	 */
	public static WxpayUnifiedOrderWapSceneInfo getSceneInfo(String body, String returnUrl) {
		WxpayUnifiedOrderWapInfo info = new WxpayUnifiedOrderWapInfo(WxpaySceneType.WAP);
		info.setWapName(body);
		info.setWapUrl(returnUrl);
		return new WxpayUnifiedOrderWapSceneInfo(info);
	}

	/**
	 * 交易查询接口
	 * @param outTradeNo
	 * @return
	 * @throws Exception
	 */
	public static WxpayOrderQueryRequest wxpayTradeQuery(String outTradeNo) throws Exception {
		WxpayOrderQueryRequest model = new WxpayOrderQueryRequest();
		model.setOutTradeNo(outTradeNo);
		return model;
	}

	/**
	 * 交易关闭接口
	 * @param outTradeNo
	 * @return
	 * @throws Exception
	 */
	public static WxpayCloseOrderRequest wxpayTradeClose(String outTradeNo) throws Exception {
		WxpayCloseOrderRequest model = new WxpayCloseOrderRequest();
		model.setOutTradeNo(outTradeNo);
		return model;
	}

	/**
	 * 交易退款接口
	 * @param outTradeNo
	 * @param outRefundNo
	 * @param totalFee
	 * @param refundFee
	 * @return
	 * @throws Exception
	 */
	public static WxpayRefundRequest wxpayTradeRefund(String outTradeNo, String outRefundNo, Integer totalFee, Integer refundFee) throws Exception {
		WxpayRefundRequest model = new WxpayRefundRequest();
		model.setOutTradeNo(outTradeNo);
		model.setOutRefundNo(outRefundNo);
		model.setTotalFee(totalFee);
		model.setRefundFee(refundFee);
		return model;
	}

	/**
	 * 交易退款查询接口
	 * @param outTradeNo
	 * @param outRefundNo
	 * @return
	 * @throws Exception
	 */
	public static WxpayRefundQueryRequest wxpayTradeRefundQuery(String outTradeNo, String outRefundNo) throws Exception {
		WxpayRefundQueryRequest model = new WxpayRefundQueryRequest();
		model.setOutTradeNo(outTradeNo);
		model.setOutRefundNo(outRefundNo);
		return model;
	}

	/**
	 * 下载对账单
	 * @param billDate
	 * @param billType
	 * @return
	 * @throws Exception
	 */
	public static WxpayDownloadBillRequest wxpayDownloadBill(String billDate, String billType) throws Exception {
		WxpayDownloadBillRequest model = new WxpayDownloadBillRequest();
		model.setBillDate(billDate);
		model.setBillType(billType);
		return model;
	}
}