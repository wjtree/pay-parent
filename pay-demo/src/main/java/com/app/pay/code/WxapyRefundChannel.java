package com.app.pay.code;

/**
 * <p>功 能：数据字典-微信支付退款渠道</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月11日 下午4:56:14</p>
 * @author 王建
 * @version 1.0
 */
public class WxapyRefundChannel {
    /** 原路退款 */
    public static final String ORIGINAL = "ORIGINAL";
    /** 退回到余额 */
    public static final String BALANCE = "BALANCE";
    /** 原账户异常退到其他余额账户 */
    public static final String OTHER_BALANCE = "OTHER_BALANCE";
    /** 原银行卡异常退到其他银行卡 */
    public static final String OTHER_BANKCARD = "OTHER_BANKCARD";
}