package com.app.pay.entity.dto;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>功 能：申请退款接口响应参数</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月15日 下午8:03:32</p>
 * @author 王建
 * @version 1.0
 */
@XStreamAlias("xml")
public class WxpayRefundResponse extends WxpayResponse implements Serializable {

    @XStreamAlias("transaction_id")
    private String transactionId;

    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    @XStreamAlias("out_refund_no")
    private String outRefundNo;

    @XStreamAlias("refund_id")
    private String refundId;

    @XStreamAlias("refund_fee")
    private Integer refundFee;

    @XStreamAlias("settlement_refund_fee")
    private Integer settlementRefundFee;

    @XStreamAlias("total_fee")
    private Integer totalFee;

    @XStreamAlias("settlement_total_fee")
    private Integer settlementTotalFee;

    @XStreamAlias("fee_type")
    private String feeType;

    @XStreamAlias("cash_fee")
    private Integer cashFee;

    @XStreamAlias("cash_fee_type")
    private String cashFeeType;

    @XStreamAlias("cash_refund_fee")
    private Integer cashRefundFee;

    @XStreamAlias("coupon_refund_fee")
    private Integer couponRefundFee;

    @XStreamAlias("coupon_refund_count")
    private Integer couponRefundCount;

    private List<WxpayRefundCouponResponse> coupons;

    private static final long serialVersionUID = 1L;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }

    public Integer getSettlementRefundFee() {
        return settlementRefundFee;
    }

    public void setSettlementRefundFee(Integer settlementRefundFee) {
        this.settlementRefundFee = settlementRefundFee;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setSettlementTotalFee(Integer settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getCashFee() {
        return cashFee;
    }

    public void setCashFee(Integer cashFee) {
        this.cashFee = cashFee;
    }

    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    public Integer getCashRefundFee() {
        return cashRefundFee;
    }

    public void setCashRefundFee(Integer cashRefundFee) {
        this.cashRefundFee = cashRefundFee;
    }

    public Integer getCouponRefundFee() {
        return couponRefundFee;
    }

    public void setCouponRefundFee(Integer couponRefundFee) {
        this.couponRefundFee = couponRefundFee;
    }

    public Integer getCouponRefundCount() {
        return couponRefundCount;
    }

    public void setCouponRefundCount(Integer couponRefundCount) {
        this.couponRefundCount = couponRefundCount;
    }

    public List<WxpayRefundCouponResponse> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<WxpayRefundCouponResponse> coupons) {
        this.coupons = coupons;
    }
}