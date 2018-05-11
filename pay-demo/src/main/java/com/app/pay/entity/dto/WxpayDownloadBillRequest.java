package com.app.pay.entity.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>功 能：下载对账单接口请求参数</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月15日 下午7:59:37</p>
 * @author 王建
 * @version 1.0
 */
@XStreamAlias("xml")
public class WxpayDownloadBillRequest extends WxpayRequest implements Serializable {

    @XStreamAlias("device_info")
    private String deviceInfo;

    @XStreamAlias("sign_type")
    private String signType;

    @XStreamAlias("bill_date")
    private String billDate;

    @XStreamAlias("bill_type")
    private String billType;

    @XStreamAlias("tar_type")
    private String tarType;

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

    public String getTarType() {
        return tarType;
    }

    public void setTarType(String tarType) {
        this.tarType = tarType;
    }
}