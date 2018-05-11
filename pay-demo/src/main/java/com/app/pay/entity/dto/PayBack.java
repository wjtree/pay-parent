package com.app.pay.entity.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>功 能：威富通接口返回信息对象</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年3月24日 下午4:50:54</p>
 * @author 王建
 * @version 1.0
 */
public class PayBack implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 错误代码 */
	private String errCode;

	/** 错误消息 */
	private String errMsg;

	/** 正常返回结果 */
	private Map<String, Object> dataMap;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
}