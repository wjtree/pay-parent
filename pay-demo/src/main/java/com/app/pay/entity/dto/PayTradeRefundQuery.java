package com.app.pay.entity.dto;

import java.io.Serializable;

/**
 * <p>功 能：统一订单退款查询实体类</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月17日 下午7:22:59</p>
 * @author 王建
 * @version 1.0
 */
public class PayTradeRefundQuery extends PayTradeRefund implements Serializable {
    private static final long serialVersionUID = 1L;

    private String refundStatus;

    private String refundTime;

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }
}