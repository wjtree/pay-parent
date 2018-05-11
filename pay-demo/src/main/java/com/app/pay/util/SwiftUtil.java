package com.app.pay.util;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.app.code.Extension;
import com.app.code.Status;
import com.app.code.Symbol;
import com.app.pay.code.PayConstant;
import com.app.pay.code.SwiftPort;
import com.app.pay.entity.dto.PayBack;
import com.app.pay.entity.dto.SwiftBill;
import com.app.pay.entity.dto.SwiftRequest;
import com.app.pay.entity.dto.SwiftResponse;
import com.app.util.ArrayUtil;
import com.app.util.CsvUtil;
import com.app.util.HttpUtil;
import com.app.util.IocUtil;
import com.app.util.StringUtil;
import com.app.util.XmlUtil;

/**
 * <p>功 能：威富通接口调用工具类</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年3月16日 下午12:06:27</p>
 * @author 王建
 * @version 1.0
 */
public class SwiftUtil {
	/**
	 * 威富通扫码支付接口请求
	 * @param service
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static PayBack process(String service, SwiftRequest request) throws Exception {
		PayBack back = null;
		// 获取接口请求接口
		String result = doRequest(service, request, PayConstant.SWIFT_PAY_URL);
		if (StringUtils.isNotBlank(result))
			back = getWftBack(service, result, request.getOutTradeNo());
		return back;
	}

	/**
	 * 威富通账单下载接口请求
	 * @param service
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static SwiftBill processBill(String service, SwiftRequest request) throws Exception {
		SwiftBill wftBill = null;
		// 获取接口请求接口
		String result = doRequest(service, request, PayConstant.SWIFT_DOWNLOAD_URL);
		if (StringUtils.isNotBlank(result))
			wftBill = ConvertBill(request, result);
		return wftBill;
	}

	/**
	 * 执行威富通接口请求过程
	 * @param service 接口名称
	 * @param request 接口请求参数
	 * @param reqUrl
	 * @return
	 * @throws Exception
	 */
	private static String doRequest(String service, SwiftRequest request, String reqUrl) throws Exception {
		// 装载威富通接口请求的公共参数
		loadParameter(service, request);
		// 获取请求结果
		return HttpUtil.httpPostXml(reqUrl, XmlUtil.toXmlWithCData(request));
	}

	/**
	 * 装载威富通接口请求的公共参数
	 * @param service 接口名称
	 * @param request 接口请求参数
	 * @throws Exception
	 */
	private static void loadParameter(String service, SwiftRequest request) throws Exception {
		if (StringUtils.equalsAnyIgnoreCase(service, SwiftPort.PAY_ARRAY)) {
			// 如果请求的接口是“统一下单”，需要另外添加"终端IP"和"通知地址"参数
			request.setMchCreateIp(InetAddress.getLocalHost().getHostAddress());
			request.setNotifyUrl(PayConstant.SWIFT_NOTIFY_URL);
		} else if (SwiftPort.UNIFIED_TRADE_REFUND.equalsIgnoreCase(service)) {
			// 如果请求的接口是“申请退款”，需要另外添加"操作员"和"退款渠道"参数，默认ORIGINAL "原路退款"
			request.setOpUserId(PayConstant.SWIFT_MCH_ID);
			request.setRefundChannel("ORIGINAL");
		}

		// 接口名称
		request.setService(service);
		// 版本号
		request.setVersion(PayConstant.SWIFT_VERSION);
		// 字符集
		request.setCharset(PayConstant.SWIFT_CHARSET);
		// 签名方式
		request.setSignType(PayConstant.SWIFT_SIGN_TYPE);
		// 商户号
		request.setMchId(PayConstant.SWIFT_MCH_ID);
		// 随机字符串
		request.setNonceStr(String.valueOf(new Date().getTime()));
		// 对以上请求参数进行签名
		String sign = SignUtil.getSignature(XmlUtil.toXmlWithCData(request), PayConstant.SWIFT_KEY);
		request.setSign(sign);
	}

	/**
	 * 转换对账单
	 * @param request
	 * @param result
	 * @return
	 * @throws Exception
	 */
	private static SwiftBill ConvertBill(final SwiftRequest request, final String result) throws Exception {
		SwiftBill wftBill = null;
		if (!StringUtil.isJson(result)) {
			wftBill = IocUtil.getBean(SwiftBill.class);
			// 读取账单文本中的每一行记录
			String[] totals = StringUtils.split(result, Symbol.LINE_BREAK.getSymbol());
			int len = totals.length;
			if (len > 2) {
				// 读取账单明细记录
				String[] sumTitle = StringUtils.split(totals[len - 2], Symbol.COMMA.getSymbol());
				String[] sumContent = ArrayUtil.removeChar(StringUtils.split(totals[len - 1], Symbol.COMMA.getSymbol()), Symbol.ACCENT.getSymbol());
				String[] bodyTitle = StringUtils.split(totals[0], Symbol.COMMA.getSymbol());
				List<String[]> bodyContent = new ArrayList<String[]>();
				String[] bodys = ArrayUtils.subarray(totals, 1, len - 2);
				String[] body = null;
				for (String str : bodys) {
					body = ArrayUtil.removeChar(StringUtils.split(str, Symbol.COMMA.getSymbol()), Symbol.ACCENT.getSymbol());
					bodyContent.add(body);
				}

				// 装载返回对象属性
				wftBill.setBillDate(request.getBillDate());
				wftBill.setBillType(request.getBillType());
				wftBill.setBodyTitle(bodyTitle);
				wftBill.setBodyContent(bodyContent);
				wftBill.setSumTitle(sumTitle);
				wftBill.setSumContent(sumContent);

				// 将对账单写入CSV文件中
				List<String[]> csvData = new ArrayList<String[]>();
				csvData.add(bodyTitle);
				csvData.addAll(bodyContent);
				csvData.add(sumTitle);
				csvData.add(sumContent);
				String csvName = StringUtils.join(PayConstant.BILL_LOG, request.getBillDate(), Symbol.UNDERSCORE.getSymbol(), request.getBillType(), Extension.CSV.getExtension());
				CsvUtil.writeCSV(csvName, csvData);
			} else {
				wftBill.setResult("-1");
				wftBill.setMessage("威富通下载对账单接口未知错误");
			}
		}
		return wftBill;
	}

	/**
	 * 将威富通的接口返回进行封装
	 * @param service
	 * @param result
	 * @param outTradeNo
	 * @return
	 * @throws Exception
	 */
	public static PayBack getWftBack(final String service, final String result, final String outTradeNo) throws Exception {
		PayBack back = new PayBack();
		// 将结果字符串转换为JAVABEAN
		SwiftResponse response = XmlUtil.toBeanWithCData(result, SwiftResponse.class);
		// 先判断协议字段返回
		if (StringUtils.equals(response.getStatus(), Status.SUCCESS.getStatus())) {
			// 签名校验
			if (SignUtil.checkSignature(result, PayConstant.SWIFT_KEY)) {
				// 再判断业务返回
				if (StringUtils.isBlank(response.getResultCode()) || StringUtils.equals(response.getResultCode(), Status.SUCCESS.getStatus())) {
					back.setErrCode(Status.SUCCESS.getStatus());
					back.setDataMap(getWftBackData(result, outTradeNo, service));
				} else {
					// 业务返回失败
					back.setErrCode(response.getErrCode());
					back.setErrMsg(response.getErrMsg());
					back.setDataMap(getWftBackErrData(response, outTradeNo));
				}
			} else {
				// 签名校验失败
				back.setErrCode("签名校验失败");
				back.setErrMsg("威富通接口返回信息签名不一致，返回数据被忽略");
				back.setDataMap(getWftBackErrData(response, outTradeNo));
			}
		} else {
			// 签名失败参数格式校验错误
			back.setErrCode(response.getStatus());
			back.setErrMsg(response.getMessage());
			back.setDataMap(getWftBackErrData(response, outTradeNo));
		}
		return back;
	}

	/**
	 * 移除不需要返回的属性
	 * @param result
	 * @param outTradeNo
	 * @param service
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, Object> getWftBackData(final String result, final String outTradeNo, final String service) throws Exception {
		Map<String, Object> map = SignUtil.xmlToSortedMap(result);
		// 公共返回
		map.remove("class");
		map.remove("version");
		map.remove("charset");
		map.remove("sign_type");
		map.remove("status");
		map.remove("message");
		map.remove("result_code");
		map.remove("mch_id");
		map.remove("device_info");
		map.remove("nonce_str");
		map.remove("err_code");
		map.remove("err_msg");
		map.remove("sign");
		// 统一下单
		map.remove("uuid");
		map.remove("code_url");
		// 查询订单
		map.remove("appid");
		map.remove("is_subscribe");
		map.remove("openid");
		map.remove("sub_appid");
		map.remove("sub_is_subscribe");
		map.remove("sub_openid");
		map.remove("trade_type");

		// 如果是查询退款接口，移除返回结果中部分字段的后缀“_0”
		if (StringUtils.equalsIgnoreCase(SwiftPort.UNIFIED_TRADE_REFUNDQUERY, service)) {
			// 添加移除“_0”后的键
			map.put("out_refund_no", map.get("out_refund_no_0"));
			map.put("refund_id", map.get("refund_id_0"));
			map.put("refund_channel", map.get("refund_channel_0"));
			map.put("refund_fee", map.get("refund_fee_0"));
			map.put("coupon_refund_fee", map.get("coupon_refund_fee_0"));
			map.put("refund_time", map.get("refund_time_0"));
			map.put("refund_status", map.get("refund_status_0"));
			// 移除以“_0”结尾的键
			map.remove("out_refund_no_0");
			map.remove("refund_id_0");
			map.remove("refund_channel_0");
			map.remove("refund_fee_0");
			map.remove("coupon_refund_fee_0");
			map.remove("refund_time_0");
			map.remove("refund_status_0");
		} else if (StringUtils.equalsIgnoreCase(SwiftPort.PAY_WEIXIN_RAW_APP, service)) {
			// 因为安卓端的package是关键字，所以此处换成packages
			String payInfo = MapUtils.getString(map, "pay_info");
			Map<String, String> payMap = JSON.parseObject(payInfo, HashMap.class);
			payMap.put("packages", MapUtils.getString(payMap, "package"));
			payMap.remove("package");
			map.put("pay_info", payMap);
		}

		// 加入商户订单号返回客户端
		if (!map.containsKey("out_trade_no"))
			map.put("out_trade_no", outTradeNo);
		return map;
	}

	/**
	 * 在发送错误时，原样返回客户端的数据
	 * @param response
	 * @param outTradeNo
	 * @return
	 * @throws Exception
	 */
	private static Map<String, Object> getWftBackErrData(final SwiftResponse response, final String outTradeNo) throws Exception {
		// 获取商户订单号
		String outTradeNoNew = (StringUtils.isNotBlank(outTradeNo) ? outTradeNo : response.getOutTradeNo());
		// 在发送错误时，原样返回客户端的数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("out_trade_no", outTradeNoNew);
		return map;
	}

	/**
	 * 校验威富通支付接口签名
	 * @param xmlStr
	 * @return
	 * @throws Exception
	 */
	public static boolean checkSign(final String xmlStr) throws Exception {
		return SignUtil.checkSignature(xmlStr, PayConstant.SWIFT_KEY);
	}
}