package com.app.pay.entity.dto;

import java.io.Serializable;
import java.util.List;

/**
 * <p>功 能：威富通对账单</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年3月28日 上午10:23:51</p>
 * @author 王建
 * @version 1.0
 */
public class SwiftBill implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 账单日期 */
	private String billDate;

	/** 账单类型 */
	private String billType;

	/** 账单明细标题 */
	private String[] bodyTitle;

	/** 账单明细内容 */
	private List<String[]> bodyContent;

	/** 账单汇总标题 */
	private String[] sumTitle;

	/** 账单汇总内容 */
	private String[] sumContent;

	/** 下载账单错误代码 */
	private String result;

	/** 下载账单错误原因 */
	private String message;

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

	public String[] getBodyTitle() {
		return bodyTitle;
	}

	public void setBodyTitle(String[] bodyTitle) {
		this.bodyTitle = bodyTitle;
	}

	public List<String[]> getBodyContent() {
		return bodyContent;
	}

	public void setBodyContent(List<String[]> bodyContent) {
		this.bodyContent = bodyContent;
	}

	public String[] getSumTitle() {
		return sumTitle;
	}

	public void setSumTitle(String[] sumTitle) {
		this.sumTitle = sumTitle;
	}

	public String[] getSumContent() {
		return sumContent;
	}

	public void setSumContent(String[] sumContent) {
		this.sumContent = sumContent;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}