package com.app.pay.code;

/**
 * <p>功 能：支付宝接口名称常量类</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月3日 下午12:04:55</p>
 * @author 王建
 * @version 1.0
 */
public class AlipayPort {
	/** 统一收单交易创建接口 */
	public static final String ALIPAY_TRADE_CREATE = "alipay.trade.create";
	/** 统一收单线下交易预创建 */
	public static final String ALIPAY_TRADE_PRECREATE = "alipay.trade.precreate";

	/** 统一收单交易支付接口 */
	public static final String ALIPAY_TRADE_PAY = "alipay.trade.pay";
	/** 统一收单交易支付（APP）接口*/
	public static final String ALIPAY_TRADE_APP_PAY = "alipay.trade.app.pay";
	/** 统一收单交易支付（APP）通知接口*/
	public static final String ALIPAY_TRADE_APP_PAY_NOTIFY = "alipay.trade.app.pay.notify";
	/** 统一收单交易支付（WAP）接口*/
	public static final String ALIPAY_TRADE_WAP_PAY = "alipay.trade.wap.pay";
	/** 统一收单交易支付（WAP）通知接口*/
	public static final String ALIPAY_TRADE_WAP_PAY_NOTIFY = "alipay.trade.app.pay.notify";

	/** 统一收单线下交易查询 */
	public static final String ALIPAY_TRADE_QUERY = "alipay.trade.query";
	/** 统一收单交易关闭接口 */
	public static final String ALIPAY_TRADE_CLOSE = "alipay.trade.close";
	/** 统一收单交易撤销接口 */
	public static final String ALIPAY_TRADE_CANCEL = "alipay.trade.cancel";
	/** 统一收单交易退款接口 */
	public static final String ALIPAY_TRADE_REFUND = "alipay.trade.refund";
	/** 统一收单交易退款查询 */
	public static final String ALIPAY_TRADE_FASTPAY_REFUND_QUERY = "alipay.trade.fastpay.refund.query";
	/** 统一收单交易结算接口 */
	public static final String ALIPAY_TRADE_ORDER_SETTLE = "alipay.trade.order.settle";
	/** 查询对账单下载地址 */
	public static final String ALIPAY_DATA_DATASERVICE_BILL_DOWNLOADURL_QUERY = "alipay.data.dataservice.bill.downloadurl.query";

	/** 支付宝支持的支付类型数组 */
	public static final String[] PAY_ARRAY = { ALIPAY_TRADE_APP_PAY, ALIPAY_TRADE_WAP_PAY };
}