package com.app.pay.code;

/**
 * <p>功 能：第三方支付接口名称常量类</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月10日 下午7:54:57</p>
 * @author 王建
 * @version 1.0
 */
public class PayPort {
	/** 交易支付接口 */
	public static final String YBF_TRADE_PAY = "ybf.trade.pay";
	/** 交易查询接口 */
	public static final String YBF_TRADE_QUERY = "ybf.trade.query";
	/** 交易关闭接口 */
	public static final String YBF_TRADE_CLOSE = "ybf.trade.close";
	/** 交易退款接口 */
	public static final String YBF_TRADE_REFUND = "ybf.trade.refund";
	/** 交易退款查询接口 */
	public static final String YBF_TRADE_REFUND_QUERY = "ybf.trade.refund.query";
	/** 下载对账单接口 */
	public static final String YBF_DOWNLOAD_BILL = "ybf.download.bill";
}