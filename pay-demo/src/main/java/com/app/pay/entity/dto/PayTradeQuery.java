package com.app.pay.entity.dto;

import java.io.Serializable;

/**
 * <p>功 能：统一订单查询实体类</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月17日 下午7:22:39</p>
 * @author 王建
 * @version 1.0
 */
public class PayTradeQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    private String outTradeNo;

    private String transactionId;

    private Integer totalFee;

    private String tradeState;

    private String timeEnd;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}