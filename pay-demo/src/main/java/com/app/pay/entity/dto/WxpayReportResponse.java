package com.app.pay.entity.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>功 能：交易保障接口响应参数</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月15日 下午8:04:25</p>
 * @author 王建
 * @version 1.0
 */
@XStreamAlias("xml")
public class WxpayReportResponse extends WxpayResponse implements Serializable {

    private static final long serialVersionUID = 1L;
}