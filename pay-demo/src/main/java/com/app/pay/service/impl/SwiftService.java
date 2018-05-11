package com.app.pay.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.app.pay.code.SwiftPort;
import com.app.pay.entity.dto.PayBack;
import com.app.pay.entity.dto.SwiftBill;
import com.app.pay.entity.dto.SwiftRequest;
import com.app.pay.util.SwiftUtil;

/**
 * <p>功 能：威富通支付接口逻辑处理类</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月10日 下午4:26:29</p>
 * @author 王建
 * @version 1.0
 */
@Service
public class SwiftService {
	private static final Logger LOG = LogManager.getLogger(SwiftService.class);

	/**
	 * 交易支付接口
	 * <p>以下参数必填</p>
	 * <ol>
	 *  <li>接口类型 service</li>
	 *  <li>商户订单号 outTradeNo</li>
	 *  <li>商品描述 body</li>
	 *  <li>总金额 totalFee</li>
	 * </ol>
	 * @param wftRequest
	 * @return
	 */
	public PayBack swiftTradePay(SwiftRequest wftRequest) {
		PayBack wftBack = null;
		try {
			wftBack = SwiftUtil.process(wftRequest.getService(), wftRequest);
		} catch (Exception e) {
			LOG.error("交易支付接口出错", e);
		}
		return wftBack;
	}

	/**
	 * 交易查询接口
	 * <p>以下参数必填</p>
	 * <ol>
	 *  <li>商户订单号 outTradeNo</li>
	 * </ol>
	 * @param wftRequest
	 * @return
	 */
	public PayBack swiftTradeQuery(SwiftRequest wftRequest) {
		PayBack wftBack = null;
		try {
			wftBack = SwiftUtil.process(SwiftPort.UNIFIED_TRADE_QUERY, wftRequest);
		} catch (Exception e) {
			LOG.error("交易查询接口出错", e);
		}
		return wftBack;
	}

	/**
	 * 交易关闭接口
	 * <p>以下参数必填</p>
	 * <ol>
	 *  <li>商户订单号 outTradeNo</li>
	 * </ol>
	 * @param wftRequest
	 * @return
	 */
	public PayBack swiftTradeClose(SwiftRequest wftRequest) {
		PayBack wftBack = null;
		try {
			wftBack = SwiftUtil.process(SwiftPort.UNIFIED_TRADE_CLOSE, wftRequest);
		} catch (Exception e) {
			LOG.error("交易关闭接口出错", e);
		}
		return wftBack;
	}

	/**
	 * 交易退款接口
	 * <p>以下参数必填</p>
	 * <ol>
	 *  <li>商户订单号 outTradeNo</li>
	 *  <li>商户退款单号 outRefundNo</li>
	 *  <li>总金额 totalFee</li>
	 *  <li>退款金额 refundFee</li>
	 * </ol>
	 * @param wftRequest
	 * @return
	 */
	public PayBack swiftTradeRefund(SwiftRequest wftRequest) {
		PayBack wftBack = null;
		try {
			wftBack = SwiftUtil.process(SwiftPort.UNIFIED_TRADE_REFUND, wftRequest);
		} catch (Exception e) {
			LOG.error("交易退款接口出错", e);
		}
		return wftBack;
	}

	/**
	 * 交易退款查询接口
	 * <p>以下参数必填</p>
	 * <ol>
	 *  <li>商户退款单号 outRefundNo</li>
	 * </ol>
	 * @param wftRequest
	 * @return
	 */
	public PayBack swiftTradeRefundQuery(SwiftRequest wftRequest) {
		PayBack wftBack = null;
		try {
			wftBack = SwiftUtil.process(SwiftPort.UNIFIED_TRADE_REFUNDQUERY, wftRequest);
		} catch (Exception e) {
			LOG.error("交易退款查询接口出错", e);
		}
		return wftBack;
	}

	/**
	 * 下载对账单接口
	 * <p>以下参数必填</p>
	 * <ol>
	 *  <li>账单日期 billDate</li>
	 *  <li>账单类型 billType</li>
	 * </ol>
	 * @param wftRequest
	 * @return
	 */
	public SwiftBill swiftDownloadBill(SwiftRequest wftRequest) {
		SwiftBill wftBill = null;
		try {
			wftBill = SwiftUtil.processBill(SwiftPort.PAY_BILL_MERCHANT, wftRequest);
		} catch (Exception e) {
			LOG.error("下载对账单接口出错", e);
		}
		return wftBill;
	}
}