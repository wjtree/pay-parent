package com.app.pay.service;

import com.app.pay.entity.dto.PayBack;

/**
 * <p>功 能：第三方支付接口</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月9日 下午3:11:52</p>
 * @author 王建
 * @version 1.0
 */
public interface PayProvider {
	/**
	 * 交易支付接口
	 * <p>以下参数必填</p>
	 * <ol>
	 *  <li>接口类型 service</li>
	 *  <li>商户订单号 outTradeNo</li>
	 *  <li>商品名称 body</li>
	 *  <li>总金额 totalFee</li>
	 * </ol>
	 * @param json
	 * @return
	 */
	PayBack unifiedTradePay(String json);

	/**
	 * 交易查询接口
	 * <p>以下参数必填</p>
	 * <ol>
	 *  <li>商户订单号 outTradeNo</li>
	 * </ol>
	 * @param json
	 * @return
	 */
	PayBack unifiedTradeQuery(String json);

	/**
	 * 交易关闭接口
	 * <p>以下参数必填</p>
	 * <ol>
	 *  <li>商户订单号 outTradeNo</li>
	 * </ol>
	 * @param json
	 * @return
	 */
	PayBack unifiedTradeClose(String json);

	/**
	 * 交易退款接口
	 * <p>以下参数必填</p>
	 * <ol>
	 *  <li>商户订单号 outTradeNo</li>
	 *  <li>商户退款单号 outRefundNo</li>
	 *  <li>订单金额 totalFee</li>
	 *  <li>退款金额 refundFee</li>
	 * </ol>
	 * @param json
	 * @return
	 */
	PayBack unifiedTradeRefund(String json);

	/**
	 * 交易退款查询接口
	 * <p>以下参数必填</p>
	 * <ol>
	 *  <li>商户订单号 outTradeNo</li>
	 *  <li>商户退款单号 outRefundNo</li>
	 * </ol>
	 * @param json
	 * @return
	 */
	PayBack unifiedTradeRefundQuery(String json);

	/**
	 * 下载对账单接口
	 * <p>以下参数必填</p>
	 * <ol>
	 *  <li>账单日期 billDate</li>
	 *  <li>账单类型 billType</li>
	 * </ol>
	 * @param json
	 * @return
	 */
	Object unifiedDownloadBill(String json);
}