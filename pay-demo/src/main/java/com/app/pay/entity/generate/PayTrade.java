package com.app.pay.entity.generate;

import java.io.Serializable;

public class PayTrade implements Serializable {
	private String outTradeNo;

	private String transactionId;

	private String outTransactionId;

	private String body;

	private Long totalFee;

	private String tradeType;

	private String tradeState;

	private String timeStart;

	private String timeExpire;

	private String timeEnd;

	private String createTime;

	private String updateTime;

	private static final long serialVersionUID = 1L;

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

	public String getOutTransactionId() {
		return outTransactionId;
	}

	public void setOutTransactionId(String outTransactionId) {
		this.outTransactionId = outTransactionId == null ? null : outTransactionId.trim();
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body == null ? null : body.trim();
	}

	public Long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType == null ? null : tradeType.trim();
	}

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState == null ? null : tradeState.trim();
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

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd == null ? null : timeEnd.trim();
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime == null ? null : createTime.trim();
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime == null ? null : updateTime.trim();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", outTradeNo=").append(outTradeNo);
		sb.append(", transactionId=").append(transactionId);
		sb.append(", outTransactionId=").append(outTransactionId);
		sb.append(", body=").append(body);
		sb.append(", totalFee=").append(totalFee);
		sb.append(", tradeType=").append(tradeType);
		sb.append(", tradeState=").append(tradeState);
		sb.append(", timeStart=").append(timeStart);
		sb.append(", timeExpire=").append(timeExpire);
		sb.append(", timeEnd=").append(timeEnd);
		sb.append(", createTime=").append(createTime);
		sb.append(", updateTime=").append(updateTime);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		PayTrade other = (PayTrade) that;
		return (this.getOutTradeNo() == null ? other.getOutTradeNo() == null : this.getOutTradeNo().equals(other.getOutTradeNo()))
				&& (this.getTransactionId() == null ? other.getTransactionId() == null : this.getTransactionId().equals(other.getTransactionId()))
				&& (this.getOutTransactionId() == null ? other.getOutTransactionId() == null : this.getOutTransactionId().equals(other.getOutTransactionId()))
				&& (this.getBody() == null ? other.getBody() == null : this.getBody().equals(other.getBody()))
				&& (this.getTotalFee() == null ? other.getTotalFee() == null : this.getTotalFee().equals(other.getTotalFee()))
				&& (this.getTradeType() == null ? other.getTradeType() == null : this.getTradeType().equals(other.getTradeType()))
				&& (this.getTradeState() == null ? other.getTradeState() == null : this.getTradeState().equals(other.getTradeState()))
				&& (this.getTimeStart() == null ? other.getTimeStart() == null : this.getTimeStart().equals(other.getTimeStart()))
				&& (this.getTimeExpire() == null ? other.getTimeExpire() == null : this.getTimeExpire().equals(other.getTimeExpire()))
				&& (this.getTimeEnd() == null ? other.getTimeEnd() == null : this.getTimeEnd().equals(other.getTimeEnd()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getOutTradeNo() == null) ? 0 : getOutTradeNo().hashCode());
		result = prime * result + ((getTransactionId() == null) ? 0 : getTransactionId().hashCode());
		result = prime * result + ((getOutTransactionId() == null) ? 0 : getOutTransactionId().hashCode());
		result = prime * result + ((getBody() == null) ? 0 : getBody().hashCode());
		result = prime * result + ((getTotalFee() == null) ? 0 : getTotalFee().hashCode());
		result = prime * result + ((getTradeType() == null) ? 0 : getTradeType().hashCode());
		result = prime * result + ((getTradeState() == null) ? 0 : getTradeState().hashCode());
		result = prime * result + ((getTimeStart() == null) ? 0 : getTimeStart().hashCode());
		result = prime * result + ((getTimeExpire() == null) ? 0 : getTimeExpire().hashCode());
		result = prime * result + ((getTimeEnd() == null) ? 0 : getTimeEnd().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
		return result;
	}
}