package com.app.pay.entity.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>功 能：威富通接口请求和返回报文共有属性</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年3月23日 下午4:26:31</p>
 * @author 王建
 * @version 1.0
 */
public class SwiftCommon {
	/** 版本号 */
	private String version;

	/** 字符集 */
	private String charset;

	/** 签名方式 */
	@XStreamAlias("sign_type")
	private String signType;

	/** 商户号 */
	@XStreamAlias("mch_id")
	private String mchId;

	/** 大商户编号 */
	private String groupno;

	/** 授权渠道编号 */
	@XStreamAlias("sign_agentno")
	private String signAgentno;

	/** 设备号 */
	@XStreamAlias("device_info")
	private String deviceInfo;

	/** 附加信息 */
	private String attach;

	/** 商户订单号 */
	@XStreamAlias("out_trade_no")
	private String outTradeNo;

	/** 平台订单号 */
	@XStreamAlias("transaction_id")
	private String transactionId;

	/** 商户退款单号 */
	@XStreamAlias("out_refund_no")
	private String outRefundNo;

	/** 平台退款单号 */
	@XStreamAlias("refund_id")
	private String refundId;

	/** 总金额 */
	@XStreamAlias("total_fee")
	private Integer totalFee;

	/** 退款金额 */
	@XStreamAlias("refund_fee")
	private Integer refundFee;

	/** 退款渠道 */
	@XStreamAlias("refund_channel")
	private String refundChannel;

	/** 随机字符串 */
	@XStreamAlias("nonce_str")
	private String nonceStr;

	/** 签名 */
	private String sign;

	/** 公众号appid */
	private String appid;

	/** 商户appid */
	@XStreamAlias("sub_appid")
	private String subAppid;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version == null ? null : version.trim();
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset == null ? null : charset.trim();
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType == null ? null : signType.trim();
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId == null ? null : mchId.trim();
	}

	public String getGroupno() {
		return groupno;
	}

	public void setGroupno(String groupno) {
		this.groupno = groupno;
	}

	public String getSignAgentno() {
		return signAgentno;
	}

	public void setSignAgentno(String signAgentno) {
		this.signAgentno = signAgentno;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo == null ? null : deviceInfo.trim();
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach == null ? null : attach.trim();
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId == null ? null : transactionId.trim();
	}

	public String getOutRefundNo() {
		return outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo == null ? null : outRefundNo.trim();
	}

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId == null ? null : refundId.trim();
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public Integer getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(Integer refundFee) {
		this.refundFee = refundFee;
	}

	public String getRefundChannel() {
		return refundChannel;
	}

	public void setRefundChannel(String refundChannel) {
		this.refundChannel = refundChannel == null ? null : refundChannel.trim();
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr == null ? null : nonceStr.trim();
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign == null ? null : sign.trim();
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid == null ? null : appid.trim();
	}

	public String getSubAppid() {
		return subAppid;
	}

	public void setSubAppid(String subAppid) {
		this.subAppid = subAppid == null ? null : subAppid.trim();
	}
}