package com.app.pay.entity.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>功 能：统一下单接口响应参数</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月15日 下午8:05:09</p>
 * @author 王建
 * @version 1.0
 */
@XStreamAlias("xml")
public class WxpayUnifiedOrderResponse extends WxpayResponse implements Serializable {

	@XStreamAlias("trade_type")
	private String tradeType;

	@XStreamAlias("prepay_id")
	private String prepayId;

	@XStreamAlias("mweb_url")
	private String mwebUrl;

	private static final long serialVersionUID = 1L;

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getMwebUrl() {
		return mwebUrl;
	}

	public void setMwebUrl(String mwebUrl) {
		this.mwebUrl = mwebUrl;
	}
}