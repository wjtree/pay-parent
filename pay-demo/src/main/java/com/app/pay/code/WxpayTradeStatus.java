package com.app.pay.code;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>功 能：数据字典-微信支付交易状态</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年3月26日 下午4:34:27</p>
 * @author 王建
 * @version 1.0
 */
public class WxpayTradeStatus {
    /** 支付成功 */
    public static final String SUCCESS = "SUCCESS";
    /** 转入退款 */
    public static final String REFUND = "REFUND";
    /** 未支付 */
    public static final String NOTPAY = "NOTPAY";
    /** 已关闭 */
    public static final String CLOSED = "CLOSED";
    /** 已撤销（刷卡支付）  */
    public static final String REVOKED = "REVOKED";
    /** 用户支付中  */
    public static final String USERPAYING = "USERPAYING";
    /** 支付失败(其他原因，如银行返回失败) */
    public static final String PAYERROR = "PAYERROR";

    /**
     * 将微信支付交易状态转换为威富通交易状态统一存储
     * @param tradeStatus
     * @return
     */
    public static String getPayTradeStatus(String tradeStatus) {
        String status = null;
        if (StringUtils.equalsIgnoreCase(SUCCESS, tradeStatus))
            status = SwiftTradeStatus.SUCCESS;
        else if (StringUtils.equalsIgnoreCase(REFUND, tradeStatus))
            status = SwiftTradeStatus.REFUND;
        else if (StringUtils.equalsIgnoreCase(NOTPAY, tradeStatus))
            status = SwiftTradeStatus.NOTPAY;
        else if (StringUtils.equalsIgnoreCase(CLOSED, tradeStatus))
            status = SwiftTradeStatus.CLOSED;
        else if (StringUtils.equalsIgnoreCase(REVOKED, tradeStatus))
            status = SwiftTradeStatus.CLOSED;
        else if (StringUtils.equalsIgnoreCase(USERPAYING, tradeStatus))
            status = SwiftTradeStatus.NOTPAY;
        else if (StringUtils.equalsIgnoreCase(PAYERROR, tradeStatus))
            status = SwiftTradeStatus.PAYERROR;
        return status;
    }
}