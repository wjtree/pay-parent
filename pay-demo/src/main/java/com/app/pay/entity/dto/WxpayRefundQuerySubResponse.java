package com.app.pay.entity.dto;

import java.io.Serializable;
import java.util.List;

/**
 * <p>功 能：查询退款接口响应参数</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月15日 下午8:02:07</p>
 * @author 王建
 * @version 1.0
 */
public class WxpayRefundQuerySubResponse implements Serializable {

    private String outRefundNo;

    private String refundId;

    private String refundChannel;

    private Integer refundFee;

    private Integer couponRefundFee;

    private Integer couponRefundCount;

    private List<WxpayRefundCouponResponse> couponRefunds;

    private String refundStatus;

    private String refundAccount;

    private String refundRecvAccout;

    private String refundSuccessTime;

    private static final long serialVersionUID = 1L;

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

    public String getRefundChannel() {
        return refundChannel;
    }

    public void setRefundChannel(String refundChannel) {
        this.refundChannel = refundChannel;
    }

    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
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

    public List<WxpayRefundCouponResponse> getCouponRefunds() {
        return couponRefunds;
    }

    public void setCouponRefunds(List<WxpayRefundCouponResponse> couponRefunds) {
        this.couponRefunds = couponRefunds;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }

    public String getRefundRecvAccout() {
        return refundRecvAccout;
    }

    public void setRefundRecvAccout(String refundRecvAccout) {
        this.refundRecvAccout = refundRecvAccout;
    }

    public String getRefundSuccessTime() {
        return refundSuccessTime;
    }

    public void setRefundSuccessTime(String refundSuccessTime) {
        this.refundSuccessTime = refundSuccessTime;
    }
}