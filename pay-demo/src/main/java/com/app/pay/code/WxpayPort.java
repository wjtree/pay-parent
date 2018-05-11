package com.app.pay.code;

/**
 * <p>功 能：微信支付接口名称常量类</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月10日 下午3:21:13</p>
 * @author 王建
 * @version 1.0
 */
public class WxpayPort {
	/** 交易（APP）支付接口*/
	public static final String WXPAY_TRADE_APP_PAY = "wxpay.trade.app.pay";
	/** 交易（APP）支付通知接口*/
	public static final String WXPAY_TRADE_APP_PAY_NOTIFY = "wxpay.trade.app.pay.notify";
	/** 交易（WAP）支付接口*/
	public static final String WXPAY_TRADE_WAP_PAY = "wxpay.trade.wap.pay";
	/** 交易（WAP）支付通知接口*/
	public static final String WXPAY_TRADE_WAP_PAY_NOTIFY = "wxpay.trade.wap.pay.notify";
	/** 交易查询接口 */
	public static final String WXPAY_TRADE_QUERY = "wxpay.trade.query";
	/** 交易关闭接口 */
	public static final String WXPAY_TRADE_CLOSE = "wxpay.trade.close";
	/** 交易退款接口 */
	public static final String WXPAY_TRADE_REFUND = "wxpay.trade.refund";
	/** 交易退款查询接口 */
	public static final String WXPAY_TRADE_REFUND_QUERY = "wxpay.trade.refund.query";
	/** 下载对账单接口 */
	public static final String WXPAY_DOWNLOAD_BILL = "wxpay.download.bill";
	/** 下载服务器报表接口 */
	public static final String WXPAY_DOWNLOAD_REPORT = "wxpay.download.report";

	/** 微信支持的支付类型数组 */
	public static final String[] PAY_ARRAY = { WXPAY_TRADE_APP_PAY, WXPAY_TRADE_WAP_PAY };
}