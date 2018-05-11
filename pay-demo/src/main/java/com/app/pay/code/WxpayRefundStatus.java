package com.app.pay.code;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>功 能：数据字典-微信支付退款状态</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年3月26日 下午4:34:27</p>
 * @author 王建
 * @version 1.0
 */
public class WxpayRefundStatus {
    /** 退款成功 */
    public static final String SUCCESS = "SUCCESS";
    /** 退款关闭 */
    public static final String REFUNDCLOSE = "REFUNDCLOSE";
    /** 未确定，需要商户用原退款单号重新发起退款申请 */
    public static final String NOTSURE = "NOTSURE";
    /** 退款处理中 */
    public static final String PROCESSING = "PROCESSING";
    /** 退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，商户可以发起异常退款处理的申请，可以退款到用户的新卡或者退款商户，商户自行处理 */
    public static final String CHANGE = "CHANGE";

    /**
     * 将支付宝交易状态转换为威富通交易状态统一存储
     * @param refundStatus
     * @return
     */
    public static String getPayRefundStatus(String refundStatus) {
        String status = null;
        if (StringUtils.equalsIgnoreCase(SUCCESS, refundStatus))
            status = SwiftRefundStatus.SUCCESS;
        else if (StringUtils.equalsIgnoreCase(REFUNDCLOSE, refundStatus))
            status = SwiftRefundStatus.FAIL;
        else if (StringUtils.equalsIgnoreCase(NOTSURE, refundStatus))
            status = SwiftRefundStatus.NOTSURE;
        else if (StringUtils.equalsIgnoreCase(PROCESSING, refundStatus))
            status = SwiftRefundStatus.PROCESSING;
        else if (StringUtils.equalsIgnoreCase(CHANGE, refundStatus))
            status = SwiftRefundStatus.CHANGE;
        return status;
    }
}