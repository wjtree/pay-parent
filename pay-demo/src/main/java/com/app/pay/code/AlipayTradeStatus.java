package com.app.pay.code;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>功 能：数据字典-支付宝交易类型</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月19日 上午9:45:54</p>
 * @author 王建
 * @version 1.0
 */
public class AlipayTradeStatus {
    /** 交易创建，等待买家付款 */
    public static final String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
    /** 未付款交易超时关闭，或支付完成后全额退款 */
    public static final String TRADE_CLOSED = "TRADE_CLOSED";
    /** 交易支付成功 */
    public static final String TRADE_SUCCESS = "TRADE_SUCCESS";
    /** 交易结束，不可退款 */
    public static final String TRADE_FINISHED = "TRADE_FINISHED";

    /**
     * 将支付宝交易状态转换为威富通交易状态统一存储
     * @param tradeStatus
     * @return
     */
    public static String getPayTradeStatus(String tradeStatus) {
        String status = null;
        if (StringUtils.equalsIgnoreCase(WAIT_BUYER_PAY, tradeStatus))
            status = SwiftTradeStatus.NOTPAY;
        else if (StringUtils.equalsIgnoreCase(TRADE_SUCCESS, tradeStatus))
            status = SwiftTradeStatus.SUCCESS;
        else if (StringUtils.equalsIgnoreCase(TRADE_FINISHED, tradeStatus))
            status = SwiftTradeStatus.SUCCESS;
        else if (StringUtils.equalsIgnoreCase(TRADE_CLOSED, tradeStatus))
            status = SwiftTradeStatus.CLOSED;
        return status;
    }
}