package com.app.pay.entity.dto;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <p>功 能：微信Wap支付场景信息</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月15日 下午8:04:59</p>
 * @author 王建
 * @version 1.0
 */
public class WxpayUnifiedOrderWapInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 场景类型 */
	private String type;

	/** WAP网站URL地址 */
	@JSONField(name = "wap_url")
	private String wapUrl;

	/** WAP网站名 */
	@JSONField(name = "wap_name")
	private String wapName;

	public WxpayUnifiedOrderWapInfo() {
	}

	public WxpayUnifiedOrderWapInfo(String type) {
		this.type = type;
	}

	public WxpayUnifiedOrderWapInfo(String type, String wapName) {
		this.type = type;
		this.wapName = wapName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWapUrl() {
		return wapUrl;
	}

	public void setWapUrl(String wapUrl) {
		this.wapUrl = wapUrl;
	}

	public String getWapName() {
		return wapName;
	}

	public void setWapName(String wapName) {
		this.wapName = wapName;
	}
}