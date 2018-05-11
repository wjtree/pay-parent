package com.app.pay.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

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
import com.app.pay.entity.dto.WxpayUnifiedOrderRequest;
import com.app.pay.entity.dto.WxpayUnifiedOrderResponse;
import com.app.pay.util.WxpayUtil;

/**
 * <p>功 能：微信支付接口逻辑处理类</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月10日 下午4:27:30</p>
 * @author 王建
 * @version 1.0
 */
@Service
public class WxpayService {
	private final Logger LOG = LogManager.getLogger(WxpayService.class);

	/**
	 * 交易支付（APP）接口
	 * @param request
	 * @return
	 */
	public WxpayUnifiedOrderResponse wxpayTradeAppPay(WxpayUnifiedOrderRequest request) {
		WxpayUnifiedOrderResponse response = null;
		try {
			response = WxpayUtil.wxpayTradeAppPay(request);
		} catch (Exception e) {
			LOG.error("交易支付（APP）接口出错", e);
		}
		return response;
	}

	/**
	 * 交易支付（WAP）接口
	 * @param request
	 * @return
	 */
	public WxpayUnifiedOrderResponse wxpayTradeWapPay(WxpayUnifiedOrderRequest request) {
		WxpayUnifiedOrderResponse response = null;
		try {
			response = WxpayUtil.wxpayTradeWapPay(request);
		} catch (Exception e) {
			LOG.error("交易支付（WAP）接口出错", e);
		}
		return response;
	}

	/**
	 * 交易查询接口
	 * @param request
	 * @return
	 */
	public WxpayOrderQueryResponse wxpayTradeQuery(WxpayOrderQueryRequest request) {
		WxpayOrderQueryResponse response = null;
		try {
			response = WxpayUtil.wxpayTradeQuery(request);
		} catch (Exception e) {
			LOG.error("交易查询接口出错", e);
		}
		return response;
	}

	/**
	 * 交易关闭接口
	 * @param request
	 * @return
	 */
	public WxpayCloseOrderResponse wxpayTradeClose(WxpayCloseOrderRequest request) {
		WxpayCloseOrderResponse response = null;
		try {
			response = WxpayUtil.wxpayTradeClose(request);
		} catch (Exception e) {
			LOG.error("交易关闭接口出错", e);
		}
		return response;
	}

	/**
	 * 交易退款接口
	 * @param request
	 * @return
	 */
	public WxpayRefundResponse wxpayTradeRefund(WxpayRefundRequest request) {
		WxpayRefundResponse response = null;
		try {
			response = WxpayUtil.wxpayTradeRefund(request);
		} catch (Exception e) {
			LOG.error("交易退款接口出错", e);
		}
		return response;
	}

	/**
	 * 交易退款查询接口
	 * @param request
	 * @return
	 */
	public WxpayRefundQueryResponse wxpayTradeRefundQuery(WxpayRefundQueryRequest request) {
		WxpayRefundQueryResponse response = null;
		try {
			response = WxpayUtil.wxpayTradeRefundQuery(request);
		} catch (Exception e) {
			LOG.error("交易退款查询接口出错", e);
		}
		return response;
	}

	/**
	 * 下载对账单
	 * @param request
	 * @return
	 */
	public WxpayDownloadBillResponse wxpayDownloadBill(WxpayDownloadBillRequest request) {
		WxpayDownloadBillResponse response = null;
		try {
			response = WxpayUtil.wxpayDownloadBill(request);
		} catch (Exception e) {
			LOG.error("下载对账单接口出错", e);
		}
		return response;
	}

	/**
	 * 交易保障接口
	 * @param request
	 * @return
	 */
	@Deprecated
	public WxpayReportResponse wxpayDownloadReport(WxpayReportRequest request) {
		WxpayReportResponse response = null;
		try {
			response = WxpayUtil.wxpayDownloadReport(request);
		} catch (Exception e) {
			LOG.error("交易保障接口出错", e);
		}
		return response;
	}
}