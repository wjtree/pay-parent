package com.app.pay.entity.dto;

import java.io.Serializable;

/**
 * <p>功 能：代金券退款响应参数</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月15日 下午8:03:32</p>
 * @author 王建
 * @version 1.0
 */
public class WxpayRefundCouponResponse implements Serializable {

    private String couponRefundId;

    private Integer couponRefundFee;

    private String couponType;

    private static final long serialVersionUID = 1L;

    public String getCouponRefundId() {
        return couponRefundId;
    }

    public void setCouponRefundId(String couponRefundId) {
        this.couponRefundId = couponRefundId;
    }

    public Integer getCouponRefundFee() {
        return couponRefundFee;
    }

    public void setCouponRefundFee(Integer couponRefundFee) {
        this.couponRefundFee = couponRefundFee;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }
}