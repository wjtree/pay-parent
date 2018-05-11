package com.app.pay.code;

/**
 * <p>功 能：数据字典-微信支付交易类型</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年6月12日 下午2:17:44</p>
 * @author 王建
 * @version 1.0
 */
public class WxpayTradeType {
	/** 公众号支付 */
	public static final String JSAPI = "JSAPI";
	/** 原生扫码支付 */
	public static final String NATIVE = "NATIVE";
	/** app支付 */
	public static final String APP = "APP";
	/** H5支付 */
	public static final String MWEB = "MWEB";
	/** 刷卡支付 */
	public static final String MICROPAY = "MICROPAY";
}