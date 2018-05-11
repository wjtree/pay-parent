package com.app.pay.entity.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>功 能：威富通接口请求报文</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年3月23日 下午4:26:54</p>
 * @author 王建
 * @version 1.0
 */
@XStreamAlias("xml")
public class SwiftRequest extends SwiftCommon implements Serializable {
	/** 接口类型 */
	private String service;

	/** 商品描述 */
	private String body;

	/** 终端IP */
	@XStreamAlias("mch_create_ip")
	private String mchCreateIp;

	/** 通知地址 */
	@XStreamAlias("notify_url")
	private String notifyUrl;

	/** 订单生成时间 */
	@XStreamAlias("time_start")
	private String timeStart;

	/** 订单超时时间 */
	@XStreamAlias("time_expire")
	private String timeExpire;

	/** 操作员 */
	@XStreamAlias("op_user_id")
	private String opUserId;

	/** 门店编号 */
	@XStreamAlias("op_shop_id")
	private String opShopId;

	/** 商品标记 */
	@XStreamAlias("goods_tag")
	private String goodsTag;

	/** 商品 ID */
	@XStreamAlias("product_id")
	private String productId;

	/** 是否限制信用卡 */
	@XStreamAlias("limit_credit_pay")
	private String limitCreditPay;

	/** 账单日期，格式：yyyyMMdd */
	@XStreamAlias("bill_date")
	private String billDate;

	/** 账单类型 */
	@XStreamAlias("bill_type")
	private String billType;

	private static final long serialVersionUID = 1L;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service == null ? null : service.trim();
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body == null ? null : body.trim();
	}

	public String getMchCreateIp() {
		return mchCreateIp;
	}

	public void setMchCreateIp(String mchCreateIp) {
		this.mchCreateIp = mchCreateIp == null ? null : mchCreateIp.trim();
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart == null ? null : timeStart.trim();
	}

	public String getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire == null ? null : timeExpire.trim();
	}

	public String getOpUserId() {
		return opUserId;
	}

	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId == null ? null : opUserId.trim();
	}

	public String getOpShopId() {
		return opShopId;
	}

	public void setOpShopId(String opShopId) {
		this.opShopId = opShopId;
	}

	public String getGoodsTag() {
		return goodsTag;
	}

	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag == null ? null : goodsTag.trim();
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId == null ? null : productId.trim();
	}

	public String getLimitCreditPay() {
		return limitCreditPay;
	}

	public void setLimitCreditPay(String limitCreditPay) {
		this.limitCreditPay = limitCreditPay == null ? null : limitCreditPay.trim();
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}
}