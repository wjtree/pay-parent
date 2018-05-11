package com.app.pay.entity.dto;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <p>功 能：苹果内购服务端二次验证请求对象</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月23日 下午2:13:59</p>
 * @author 王建
 * @version 1.0
 */
public class IapRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /** base64编码的收据数据 */
    @JSONField(name = "receipt-data")
    private String receiptData;

    /** 仅用于包含自动续订订阅的收据，应用程式的共享秘密（十六进制字符串） */
    private String password;

    public String getReceiptData() {
        return receiptData;
    }

    public void setReceiptData(String receiptData) {
        this.receiptData = receiptData;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}