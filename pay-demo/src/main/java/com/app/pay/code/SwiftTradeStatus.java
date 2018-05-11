package com.app.pay.code;

/**
 * <p>功 能：数据字典-威富通交易状态</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年3月26日 下午4:34:27</p>
 * @author 王建
 * @version 1.0
 */
public class SwiftTradeStatus {
    /** 支付成功 */
    public static final String SUCCESS = "SUCCESS";
    /** 转入退款 */
    public static final String REFUND = "REFUND";
    /** 未支付 */
    public static final String NOTPAY = "NOTPAY";
    /** 已关闭 */
    public static final String CLOSED = "CLOSED";
    /** 支付失败(其他原因，如银行返回失败) */
    public static final String PAYERROR = "PAYERROR";
}