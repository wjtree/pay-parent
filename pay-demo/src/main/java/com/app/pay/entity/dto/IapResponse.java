package com.app.pay.entity.dto;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <p>功 能：苹果内购服务端二次验证响应对象</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月23日 下午2:13:59</p>
 * @author 王建
 * @version 1.0
 */
public class IapResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 整个应用收据的状态 */
    private String status;

    /** 发送验证的收据的JSON表示 */
    private IapAppReceipt receipt;

    /** 仅适用于iOS 6风格的交易收据，用于自动续订订阅 */
    @JSONField(name = "latest_receipt")
    private String latestReceipt;

    /** 仅适用于iOS 6风格的交易收据，用于自动续订订阅 */
    @JSONField(name = "latest_receipt_info")
    private String latestReceiptInfo;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public IapAppReceipt getReceipt() {
        return receipt;
    }

    public void setReceipt(IapAppReceipt receipt) {
        this.receipt = receipt;
    }

    public String getLatestReceipt() {
        return latestReceipt;
    }

    public void setLatestReceipt(String latestReceipt) {
        this.latestReceipt = latestReceipt;
    }

    public String getLatestReceiptInfo() {
        return latestReceiptInfo;
    }

    public void setLatestReceiptInfo(String latestReceiptInfo) {
        this.latestReceiptInfo = latestReceiptInfo;
    }
}