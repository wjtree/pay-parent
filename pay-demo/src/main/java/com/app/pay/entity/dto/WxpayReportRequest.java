package com.app.pay.entity.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>功 能：交易保障接口请求参数</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月15日 下午8:04:04</p>
 * @author 王建
 * @version 1.0
 */
@XStreamAlias("xml")
public class WxpayReportRequest extends WxpayResponse implements Serializable {

    @XStreamAlias("interface_url")
    private String interfaceUrl;

    @XStreamAlias("execute_time_")
    private Integer executeTime;

    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    @XStreamAlias("user_ip")
    private String userIp;

    private String time;

    private static final long serialVersionUID = 1L;

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
    }

    public Integer getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Integer executeTime) {
        this.executeTime = executeTime;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}