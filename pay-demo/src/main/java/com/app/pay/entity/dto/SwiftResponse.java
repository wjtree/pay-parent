package com.app.pay.entity.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>功 能：威富通接口返回报文</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年3月23日 下午4:26:58</p>
 * @author 王建
 * @version 1.0
 */
@XStreamAlias("xml")
public class SwiftResponse extends SwiftCommon implements Serializable {
	/** 返回状态码 */
	private String status;

	/** 返回信息 */
	private String message;

	/** 业务结果 */
	@XStreamAlias("result_code")
	private String resultCode;

	/** 错误代码 */
	@XStreamAlias("err_code")
	private String errCode;

	/** 错误代码描述 */
	@XStreamAlias("err_msg")
	private String errMsg;

	/** 二维码唯一标识 */
	private String uuid;

	/** 二维码链接 */
	@XStreamAlias("code_url")
	private String codeUrl;

	/** 二维码图片 */
	@XStreamAlias("code_img_url")
	private String codeImgUrl;

	/** 支付结果 */
	@XStreamAlias("pay_result")
	private Integer payResult;

	/** 支付结果信息 */
	@XStreamAlias("pay_info")
	private String payInfo;

	/** 交易状态 */
	@XStreamAlias("trade_state")
	private String tradeState;

	/** 交易类型 */
	@XStreamAlias("trade_type")
	private String tradeType;

	/** 用户标识 */
	private String openid;

	/** 是否关注公众账号 */
	@XStreamAlias("is_subscribe")
	private String isSubscribe;

	/** 用户openid */
	@XStreamAlias("sub_openid")
	private String subOpenid;

	/** 是否关注商户公众号 */
	@XStreamAlias("sub_is_subscribe")
	private String subIsSubscribe;

	/** 第三方订单号 */
	@XStreamAlias("out_transaction_id")
	private String outTransactionId;

	/** 现金券金额 */
	@XStreamAlias("coupon_fee")
	private Integer couponFee;

	/** 货币种类 */
	@XStreamAlias("fee_type")
	private String feeType;

	/** 付款银行 */
	@XStreamAlias("bank_type")
	private String bankType;

	/** 银行订单号 */
	@XStreamAlias("bank_billno")
	private String bankBillno;

	/** 支付完成时间，格式为yyyyMMddHHmmss */
	@XStreamAlias("time_end")
	private String timeEnd;

	/** 现金券退款金额 */
	@XStreamAlias("coupon_refund_fee")
	private Integer couponRefundFee;

	/** 退款笔数 */
	@XStreamAlias("refund_count")
	private String refundCount;

	/** 退款时间 */
	@XStreamAlias("refund_time")
	private String refundTime;

	/** 退款状态 */
	@XStreamAlias("refund_status")
	private String refundStatus;

	/** 支持的支付类型 */
	private String services;

	/** 授权码 */
	@XStreamAlias("token_id")
	private String tokenId;

	private static final long serialVersionUID = 1L;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message == null ? null : message.trim();
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode == null ? null : resultCode.trim();
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode == null ? null : errCode.trim();
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg == null ? null : errMsg.trim();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid == null ? null : uuid.trim();
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl == null ? null : codeUrl.trim();
	}

	public String getCodeImgUrl() {
		return codeImgUrl;
	}

	public void setCodeImgUrl(String codeImgUrl) {
		this.codeImgUrl = codeImgUrl == null ? null : codeImgUrl.trim();
	}

	public Integer getPayResult() {
		return payResult;
	}

	public void setPayResult(Integer payResult) {
		this.payResult = payResult;
	}

	public String getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo == null ? null : payInfo.trim();
	}

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState == null ? null : tradeState.trim();
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType == null ? null : tradeType.trim();
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid == null ? null : openid.trim();
	}

	public String getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe == null ? null : isSubscribe.trim();
	}

	public String getSubOpenid() {
		return subOpenid;
	}

	public void setSubOpenid(String subOpenid) {
		this.subOpenid = subOpenid == null ? null : subOpenid.trim();
	}

	public String getSubIsSubscribe() {
		return subIsSubscribe;
	}

	public void setSubIsSubscribe(String subIsSubscribe) {
		this.subIsSubscribe = subIsSubscribe == null ? null : subIsSubscribe.trim();
	}

	public String getOutTransactionId() {
		return outTransactionId;
	}

	public void setOutTransactionId(String outTransactionId) {
		this.outTransactionId = outTransactionId == null ? null : outTransactionId.trim();
	}

	public Integer getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(Integer couponFee) {
		this.couponFee = couponFee;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType == null ? null : feeType.trim();
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType == null ? null : bankType.trim();
	}

	public String getBankBillno() {
		return bankBillno;
	}

	public void setBankBillno(String bankBillno) {
		this.bankBillno = bankBillno == null ? null : bankBillno.trim();
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd == null ? null : timeEnd.trim();
	}

	public Integer getCouponRefundFee() {
		return couponRefundFee;
	}

	public void setCouponRefundFee(Integer couponRefundFee) {
		this.couponRefundFee = couponRefundFee;
	}

	public String getRefundCount() {
		return refundCount;
	}

	public void setRefundCount(String refundCount) {
		this.refundCount = refundCount == null ? null : refundCount.trim();
	}

	public String getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime == null ? null : refundTime.trim();
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus == null ? null : refundStatus.trim();
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
}