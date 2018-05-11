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
public class WxpayUnifiedOrderWapSceneInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@JSONField(name = "h5_info")
	private WxpayUnifiedOrderWapInfo wapInfo;

	public WxpayUnifiedOrderWapSceneInfo() {
	}

	public WxpayUnifiedOrderWapSceneInfo(WxpayUnifiedOrderWapInfo wapInfo) {
		this.wapInfo = wapInfo;
	}

	public WxpayUnifiedOrderWapInfo getWapInfo() {
		return wapInfo;
	}

	public void setWapInfo(WxpayUnifiedOrderWapInfo wapInfo) {
		this.wapInfo = wapInfo;
	}
}