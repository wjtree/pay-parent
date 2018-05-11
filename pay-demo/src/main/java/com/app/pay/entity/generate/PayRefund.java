package com.app.pay.entity.generate;

import java.io.Serializable;

public class PayRefund implements Serializable {
	private String outRefundNo;

	private String outTradeNo;

	private String refundId;

	private Long refundFee;

	private String refundChannel;

	private String refundStatus;

	private String refundTime;

	private String createTime;

	private String updateTime;

	private static final long serialVersionUID = 1L;

	public String getOutRefundNo() {
		return outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo == null ? null : outRefundNo.trim();
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
	}

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId == null ? null : refundId.trim();
	}

	public Long getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(Long refundFee) {
		this.refundFee = refundFee;
	}

	public String getRefundChannel() {
		return refundChannel;
	}

	public void setRefundChannel(String refundChannel) {
		this.refundChannel = refundChannel == null ? null : refundChannel.trim();
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus == null ? null : refundStatus.trim();
	}

	public String getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime == null ? null : refundTime.trim();
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
		sb.append(", outRefundNo=").append(outRefundNo);
		sb.append(", outTradeNo=").append(outTradeNo);
		sb.append(", refundId=").append(refundId);
		sb.append(", refundFee=").append(refundFee);
		sb.append(", refundChannel=").append(refundChannel);
		sb.append(", refundStatus=").append(refundStatus);
		sb.append(", refundTime=").append(refundTime);
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
		PayRefund other = (PayRefund) that;
		return (this.getOutRefundNo() == null ? other.getOutRefundNo() == null : this.getOutRefundNo().equals(other.getOutRefundNo()))
				&& (this.getOutTradeNo() == null ? other.getOutTradeNo() == null : this.getOutTradeNo().equals(other.getOutTradeNo()))
				&& (this.getRefundId() == null ? other.getRefundId() == null : this.getRefundId().equals(other.getRefundId()))
				&& (this.getRefundFee() == null ? other.getRefundFee() == null : this.getRefundFee().equals(other.getRefundFee()))
				&& (this.getRefundChannel() == null ? other.getRefundChannel() == null : this.getRefundChannel().equals(other.getRefundChannel()))
				&& (this.getRefundStatus() == null ? other.getRefundStatus() == null : this.getRefundStatus().equals(other.getRefundStatus()))
				&& (this.getRefundTime() == null ? other.getRefundTime() == null : this.getRefundTime().equals(other.getRefundTime()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getOutRefundNo() == null) ? 0 : getOutRefundNo().hashCode());
		result = prime * result + ((getOutTradeNo() == null) ? 0 : getOutTradeNo().hashCode());
		result = prime * result + ((getRefundId() == null) ? 0 : getRefundId().hashCode());
		result = prime * result + ((getRefundFee() == null) ? 0 : getRefundFee().hashCode());
		result = prime * result + ((getRefundChannel() == null) ? 0 : getRefundChannel().hashCode());
		result = prime * result + ((getRefundStatus() == null) ? 0 : getRefundStatus().hashCode());
		result = prime * result + ((getRefundTime() == null) ? 0 : getRefundTime().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
		return result;
	}
}