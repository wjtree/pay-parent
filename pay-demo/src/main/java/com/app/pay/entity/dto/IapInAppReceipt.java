package com.app.pay.entity.dto;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <p>功 能：苹果内购应用内采购收据字段</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月27日 下午5:33:43</p>
 * @author 王建
 * @version 1.0
 */
public class IapInAppReceipt implements Serializable {
    private static final long serialVersionUID = 1L;

    @JSONField(name = "original_purchase_date_pst")
    private String originalPurchaseDatePst;

    /** 原始交易标识符 */
    @JSONField(name = "original_transaction_id")
    private String originalTransactionId;

    @JSONField(name = "is_trial_period")
    private String isTrialPeriod;

    /** 所购商品的商品标识 */
    @JSONField(name = "product_id")
    private String productId;

    @JSONField(name = "original_purchase_date_ms")
    private String originalPurchaseDateMs;

    /** 原始采购日期 */
    @JSONField(name = "original_purchase_date")
    private String originalPurchaseDate;

    @JSONField(name = "purchase_date_pst")
    private String purchaseDatePst;

    @JSONField(name = "purchase_date_ms")
    private String purchaseDateMs;

    /** 购买的商品数量 */
    private String quantity;

    /** 购买日期 */
    @JSONField(name = "purchase_date")
    private String purchaseDate;

    /** 所购买项目的交易标识符 */
    @JSONField(name = "transaction_id")
    private String transactionId;

    public void setOriginalPurchaseDatePst(String originalPurchaseDatePst) {
        this.originalPurchaseDatePst = originalPurchaseDatePst;
    }

    public String getOriginalPurchaseDatePst() {
        return originalPurchaseDatePst;
    }

    public void setOriginalTransactionId(String originalTransactionId) {
        this.originalTransactionId = originalTransactionId;
    }

    public String getOriginalTransactionId() {
        return originalTransactionId;
    }

    public void setIsTrialPeriod(String isTrialPeriod) {
        this.isTrialPeriod = isTrialPeriod;
    }

    public String getIsTrialPeriod() {
        return isTrialPeriod;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setOriginalPurchaseDateMs(String originalPurchaseDateMs) {
        this.originalPurchaseDateMs = originalPurchaseDateMs;
    }

    public String getOriginalPurchaseDateMs() {
        return originalPurchaseDateMs;
    }

    public void setOriginalPurchaseDate(String originalPurchaseDate) {
        this.originalPurchaseDate = originalPurchaseDate;
    }

    public String getOriginalPurchaseDate() {
        return originalPurchaseDate;
    }

    public void setPurchaseDatePst(String purchaseDatePst) {
        this.purchaseDatePst = purchaseDatePst;
    }

    public String getPurchaseDatePst() {
        return purchaseDatePst;
    }

    public void setPurchaseDateMs(String purchaseDateMs) {
        this.purchaseDateMs = purchaseDateMs;
    }

    public String getPurchaseDateMs() {
        return purchaseDateMs;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }
}