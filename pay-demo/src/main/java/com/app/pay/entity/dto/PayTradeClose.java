package com.app.pay.entity.dto;

import java.io.Serializable;

/**
 * <p>功 能：统一订单关闭实体类</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月17日 下午7:22:47</p>
 * @author 王建
 * @version 1.0
 */
public class PayTradeClose implements Serializable {
    private static final long serialVersionUID = 1L;

    private String outTradeNo;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}