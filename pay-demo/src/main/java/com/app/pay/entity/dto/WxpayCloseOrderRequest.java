package com.app.pay.entity.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>功 能：关闭订单接口请求参数</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月15日 下午7:58:19</p>
 * @author 王建
 * @version 1.0
 */
@XStreamAlias("xml")
public class WxpayCloseOrderRequest extends WxpayRequest implements Serializable {

    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    private static final long serialVersionUID = 1L;

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }
}