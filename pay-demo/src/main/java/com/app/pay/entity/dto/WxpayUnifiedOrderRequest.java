package com.app.pay.entity.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>功 能：统一下单接口请求参数</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月15日 下午8:04:59</p>
 * @author 王建
 * @version 1.0
 */
@XStreamAlias("xml")
public class WxpayUnifiedOrderRequest extends WxpayRequest implements Serializable {

	@XStreamAlias("device_info")
	private String deviceInfo;

	@XStreamAlias("sign_type")
	private String signType;

	private String body;

	private String detail;

	private String attach;

	@XStreamAlias("out_trade_no")
	private String outTradeNo;

	@XStreamAlias("fee_type")
	private String feeType;

	@XStreamAlias("total_fee")
	private Integer totalFee;

	@XStreamAlias("spbill_create_ip")
	private String spbillCreateIp;

	@XStreamAlias("time_start")
	private String timeStart;

	@XStreamAlias("time_expire")
	private String timeExpire;

	@XStreamAlias("goods_tag")
	private String goodsTag;

	@XStreamAlias("notify_url")
	private String notifyUrl;

	@XStreamAlias("trade_type")
	private String tradeType;

	@XStreamAlias("product_id")
	private String productId;

	@XStreamAlias("limit_pay")
	private String limitPay;

	@XStreamAlias("openid")
	private String openid;

	@XStreamAlias("scene_info")
	private WxpayUnifiedOrderWapSceneInfo sceneInfo;

	private static final long serialVersionUID = 1L;

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}

	public String getGoodsTag() {
		return goodsTag;
	}

	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getLimitPay() {
		return limitPay;
	}

	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public WxpayUnifiedOrderWapSceneInfo getSceneInfo() {
		return sceneInfo;
	}

	public void setSceneInfo(WxpayUnifiedOrderWapSceneInfo sceneInfo) {
		this.sceneInfo = sceneInfo;
	}
}