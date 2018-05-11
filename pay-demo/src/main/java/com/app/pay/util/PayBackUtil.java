package com.app.pay.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.app.code.Status;
import com.app.pay.code.AlipayTradeStatus;
import com.app.pay.code.WxpayRefundStatus;
import com.app.pay.code.WxpayTradeStatus;
import com.app.pay.entity.dto.PayBack;
import com.app.pay.entity.dto.PayTradeClose;
import com.app.pay.entity.dto.PayTradeQuery;
import com.app.pay.entity.dto.PayTradeRefund;
import com.app.pay.entity.dto.PayTradeRefundQuery;
import com.app.pay.entity.dto.WxpayCloseOrderResponse;
import com.app.pay.entity.dto.WxpayOrderQueryResponse;
import com.app.pay.entity.dto.WxpayRefundQueryResponse;
import com.app.pay.entity.dto.WxpayRefundQuerySubResponse;
import com.app.pay.entity.dto.WxpayRefundResponse;
import com.app.pay.entity.dto.WxpayResponse;
import com.app.pay.entity.dto.WxpayUnifiedOrderAppRequest;
import com.app.pay.entity.dto.WxpayUnifiedOrderResponse;
import com.app.util.DateUtil;
import com.google.common.collect.Maps;

/**
 * <p>功 能：第三方支付返回结果校验工具类</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月17日 下午5:00:06</p>
 * @author 王建
 * @version 1.0
 */
public class PayBackUtil {
	/**
	 * @param resp
	 * @return
	 */
	public static PayBack alipayTradeAppPay(AlipayTradeAppPayResponse resp, String outTradeNo) throws Exception {
		resp.setOutTradeNo(outTradeNo);

		PayBack back = new PayBack();
		back.setErrCode(Status.SUCCESS.getStatus());
		back.setDataMap(getPayBackMap(resp));
		return back;
	}

	/**
	 * @param resp
	 * @param outTradeNo
	 * @return
	 * @throws Exception
	 */
	public static PayBack alipayTradeWapPay(AlipayTradeWapPayResponse resp, String outTradeNo) throws Exception {
		resp.setOutTradeNo(outTradeNo);

		PayBack back = new PayBack();
		back.setErrCode(Status.SUCCESS.getStatus());
		back.setDataMap(getPayBackMap(resp));
		return back;
	}

	/**
	 * @param resp
	 * @return
	 */
	public static PayBack alipayTradeQuery(AlipayTradeQueryResponse resp) throws Exception {
		PayBack back = checkAlipay(resp);
		if (StringUtils.equals(Status.SUCCESS.getStatus(), back.getErrCode())) {
			// 获取参数
			Integer totalFee = AlipayUtil.getFeeInt(resp.getTotalAmount());
			String timeEnd = DateUtil.getDateTime(resp.getSendPayDate());
			String tradeStatus = resp.getTradeStatus();
			tradeStatus = AlipayTradeStatus.getPayTradeStatus(tradeStatus);

			// 绑定参数
			PayTradeQuery model = new PayTradeQuery();
			model.setOutTradeNo(resp.getOutTradeNo());
			model.setTransactionId(resp.getTradeNo());
			model.setTotalFee(totalFee);
			model.setTradeState(tradeStatus);
			model.setTimeEnd(timeEnd);

			back.setDataMap(getPayBackMap(model));
		}
		return back;
	}

	/**
	 * @param resp
	 * @return
	 */
	public static PayBack alipayTradeClose(AlipayTradeCloseResponse resp) throws Exception {
		PayBack back = checkAlipay(resp);
		if (StringUtils.equals(Status.SUCCESS.getStatus(), back.getErrCode())) {
			PayTradeClose model = new PayTradeClose();
			model.setOutTradeNo(resp.getOutTradeNo());

			back.setDataMap(getPayBackMap(model));
		}
		return back;
	}

	/**
	 * @param resp
	 * @return
	 */
	public static PayBack alipayTradeRefund(AlipayTradeRefundResponse resp, String outRefundNo, Integer totalFee) throws Exception {
		PayBack back = checkAlipay(resp);
		if (StringUtils.equals(Status.SUCCESS.getStatus(), back.getErrCode())) {
			PayTradeRefund model = new PayTradeRefund();
			model.setOutTradeNo(resp.getOutTradeNo());
			model.setOutRefundNo(outRefundNo);
			model.setTotalFee(totalFee);
			model.setRefundFee(AlipayUtil.getFeeInt(resp.getRefundFee()));

			back.setDataMap(getPayBackMap(model));
		}
		return back;
	}

	/**
	 * @param resp
	 * @return
	 */
	public static PayBack alipayTradeFastpayRefundQuery(AlipayTradeFastpayRefundQueryResponse resp) throws Exception {
		PayBack back = checkAlipay(resp);
		if (StringUtils.equals(Status.SUCCESS.getStatus(), back.getErrCode())) {
			PayTradeRefundQuery model = new PayTradeRefundQuery();
			model.setOutTradeNo(resp.getOutTradeNo());
			model.setTotalFee(AlipayUtil.getFeeInt(resp.getTotalAmount()));
			model.setOutRefundNo(resp.getOutRequestNo());
			model.setRefundFee(AlipayUtil.getFeeInt(resp.getRefundAmount()));
			model.setRefundStatus(WxpayRefundStatus.SUCCESS);
			model.setRefundTime(DateUtil.getCurrentDateTime());

			back.setDataMap(getPayBackMap(model));
		}
		return back;
	}

	/**
	 * @param resp
	 * @return
	 */
	public static PayBack wxpayTradeAppPay(WxpayUnifiedOrderResponse resp, String outTradeNo) throws Exception {
		PayBack back = checkWxpay(resp);
		if (StringUtils.equals(Status.SUCCESS.getStatus(), back.getErrCode())) {
			// 封装APP端调起支付接口的参数
			WxpayUnifiedOrderAppRequest req = null;
			if (StringUtils.isNotBlank(resp.getPrepayId()))
				req = WxpayUtil.wxpayTradeAppPayParam(resp.getPrepayId());

			if (req != null) {
				req.setOutTradeNo(outTradeNo);
				back.setDataMap(getPayBackMap(req));
			}
		}
		return back;
	}

	/**
	 * @param resp
	 * @param outTradeNo
	 * @return
	 * @throws Exception
	 */
	public static PayBack wxpayTradeWapPay(WxpayUnifiedOrderResponse resp, String outTradeNo) throws Exception {
		PayBack back = checkWxpay(resp);
		if (StringUtils.equals(Status.SUCCESS.getStatus(), back.getErrCode())) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("outTradeNo", outTradeNo);
			map.put("mwebUrl", resp.getMwebUrl());
			back.setDataMap(map);
		}
		return back;
	}

	/**
	 * @param resp
	 * @return
	 */
	public static PayBack wxpayTradeQuery(WxpayOrderQueryResponse resp) throws Exception {
		PayBack back = checkWxpay(resp);
		if (StringUtils.equals(Status.SUCCESS.getStatus(), back.getErrCode())) {
			// 获取参数
			String tradeStatus = resp.getTradeState();
			tradeStatus = WxpayTradeStatus.getPayTradeStatus(tradeStatus);
			String timeEnd = WxpayUtil.formatWxDate(resp.getTimeEnd());

			// 绑定参数
			PayTradeQuery model = new PayTradeQuery();
			model.setOutTradeNo(resp.getOutTradeNo());
			model.setTransactionId(resp.getTransactionId());
			model.setTotalFee(resp.getTotalFee());
			model.setTradeState(tradeStatus);
			model.setTimeEnd(timeEnd);

			back.setDataMap(getPayBackMap(model));
		}
		return back;
	}

	/**
	 * @param resp
	 * @return
	 */
	public static PayBack wxpayTradeClose(WxpayCloseOrderResponse resp, String outTradeNo) throws Exception {
		PayBack back = checkWxpay(resp);
		if (StringUtils.equals(Status.SUCCESS.getStatus(), back.getErrCode())) {
			PayTradeClose model = new PayTradeClose();
			model.setOutTradeNo(outTradeNo);

			back.setDataMap(getPayBackMap(model));
		}
		return back;
	}

	/**
	 * @param resp
	 * @return
	 */
	public static PayBack wxpayTradeRefund(WxpayRefundResponse resp) throws Exception {
		PayBack back = checkWxpay(resp);
		if (StringUtils.equals(Status.SUCCESS.getStatus(), back.getErrCode())) {
			PayTradeRefund model = new PayTradeRefund();
			model.setOutTradeNo(resp.getOutTradeNo());
			model.setOutRefundNo(resp.getOutRefundNo());
			model.setTotalFee(resp.getTotalFee());
			model.setRefundFee(resp.getRefundFee());

			back.setDataMap(getPayBackMap(model));
		}
		return back;
	}

	/**
	 * @param resp
	 * @return
	 */
	public static PayBack wxpayTradeRefundQuery(WxpayRefundQueryResponse resp) throws Exception {
		PayBack back = checkWxpay(resp);
		if (StringUtils.equals(Status.SUCCESS.getStatus(), back.getErrCode())) {
			PayTradeRefundQuery model = new PayTradeRefundQuery();
			model.setOutTradeNo(resp.getOutTradeNo());
			model.setTotalFee(resp.getTotalFee());

			List<WxpayRefundQuerySubResponse> refunds = resp.getRefunds();
			if (CollectionUtils.isNotEmpty(refunds)) {
				WxpayRefundQuerySubResponse refund = refunds.get(0);
				// 转换状态为统一的退款状态
				String refundStatus = WxpayRefundStatus.getPayRefundStatus(refund.getRefundStatus());

				model.setOutRefundNo(refund.getOutRefundNo());
				model.setRefundFee(refund.getRefundFee());
				model.setRefundStatus(refundStatus);
				model.setRefundTime(refund.getRefundSuccessTime());
			}

			back.setDataMap(getPayBackMap(model));
		}
		return back;
	}

	/**
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public static <T extends AlipayResponse> PayBack checkAlipay(final T t) throws Exception {
		PayBack back = new PayBack();
		if (StringUtils.equalsIgnoreCase(t.getCode(), "10000")) {
			back.setErrCode(Status.SUCCESS.getStatus());
		} else {
			back.setErrCode(t.getSubCode());
			back.setErrMsg(t.getSubMsg());
		}
		return back;
	}

	/**
	 * @param t
	 * @return
	 */
	public static <T extends WxpayResponse> PayBack checkWxpay(final T t) throws Exception {
		PayBack back = new PayBack();
		// 先判断协议字段返回
		if (StringUtils.equalsIgnoreCase(t.getReturnCode(), Status.SUCCESS.name())) {
			// 再判断业务返回
			if (StringUtils.isBlank(t.getResultCode()) || StringUtils.equalsIgnoreCase(t.getResultCode(), Status.SUCCESS.name())) {
				// 标识返回结果正确并有效
				back.setErrCode(Status.SUCCESS.getStatus());
			} else {
				back.setErrCode(t.getErrCode());
				back.setErrMsg(t.getErrCodeDes());
			}
		} else {
			back.setErrCode(t.getReturnCode());
			back.setErrMsg(t.getReturnMsg());
		}
		return back;
	}

	/**
	 * 组装自定义的正确消息提示
	 * @param paramMap
	 * @return
	 */
	public static PayBack getSuccessBack(Map<String, Object> paramMap) throws Exception {
		// 组装参数
		PayBack back = new PayBack();
		back.setErrCode(Status.SUCCESS.getStatus());
		back.setDataMap(paramMap);
		return back;
	}

	/**
	 * 组装自定义的正确消息提示
	 * @param obj
	 * @return
	 */
	public static PayBack getSuccessBack(Object obj) throws Exception {
		// 组装参数
		PayBack back = new PayBack();
		back.setErrCode(Status.SUCCESS.getStatus());
		back.setDataMap(getPayBackMap(obj));
		return back;
	}

	/**
	 * 组装自定义的错误消息提示
	 * @param errMsg
	 * @return
	 */
	public static PayBack getErrorBack(String errMsg) {
		// 传入错误消息为空时使用默认错误提示
		errMsg = (StringUtils.isBlank(errMsg) ? "系统错误" : errMsg);
		// 组装参数
		PayBack back = new PayBack();
		back.setErrCode("-1");
		back.setErrMsg(errMsg);
		return back;
	}

	/**
	 * 组装自定义的错误消息提示
	 * @param errCode
	 * @param errMsg
	 * @return
	 */
	public static PayBack getErrorBack(String errCode, String errMsg) {
		// 传入错误消息为空时使用默认错误提示
		errCode = (StringUtils.isBlank(errCode) ? "-1" : errCode);
		errMsg = (StringUtils.isBlank(errMsg) ? "系统错误" : errMsg);
		// 组装参数
		PayBack back = new PayBack();
		back.setErrCode(errCode);
		back.setErrMsg(errMsg);
		return back;
	}

	/**
	 * 将javabean转换为map
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> getPayBackMap(Object obj) throws Exception {
		Map<String, Object> map = PropertyUtils.describe(obj);
		map.remove("class");
		return map;
	}
}