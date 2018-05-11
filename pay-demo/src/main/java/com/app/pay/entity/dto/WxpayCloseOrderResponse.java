package com.app.pay.entity.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>功 能：关闭订单接口响应参数</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月15日 下午7:59:15</p>
 * @author 王建
 * @version 1.0
 */
@XStreamAlias("xml")
public class WxpayCloseOrderResponse extends WxpayResponse implements Serializable {

    private static final long serialVersionUID = 1L;
}