package com.app.pay.entity.dto;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <p>功 能：苹果内购应用收据字段</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月27日 下午5:33:29</p>
 * @author 王建
 * @version 1.0
 */
public class IapAppReceipt implements Serializable {
    private static final long serialVersionUID = 1L;

    /** App Store用于唯一标识创建事务的应用程序的字符串 */
    @JSONField(name = "app_item_id")
    private Integer appItemId;

    @JSONField(name = "request_date_pst")
    private String requestDatePst;

    @JSONField(name = "receipt_type")
    private String receiptType;

    @JSONField(name = "original_purchase_date_ms")
    private String originalPurchaseDateMs;

    @JSONField(name = "request_date")
    private String requestDate;

    /** 原始采购日期 */
    @JSONField(name = "original_purchase_date")
    private String originalPurchaseDate;

    @JSONField(name = "adam_id")
    private Integer adamId;

    @JSONField(name = "receipt_creation_date")
    private String receiptCreationDate;

    @JSONField(name = "original_purchase_date_pst")
    private String originalPurchaseDatePst;

    /** 应用内采购收据 */
    @JSONField(name = "in_app")
    private List<IapInAppReceipt> inApp;

    @JSONField(name = "download_id")
    private Integer downloadId;

    @JSONField(name = "receipt_creation_date_pst")
    private String receiptCreationDatePst;

    /** 应用版本 */
    @JSONField(name = "application_version")
    private String applicationVersion;

    /** 唯一标识应用程序修订版本的任意数字 */
    @JSONField(name = "version_external_identifier")
    private Integer versionExternalIdentifier;

    /** 捆绑标识符 */
    @JSONField(name = "bundle_id")
    private String bundleId;

    /** 原始应用版本 */
    @JSONField(name = "original_application_version")
    private String originalApplicationVersion;

    @JSONField(name = "receipt_creation_date_ms")
    private String receiptCreationDateMs;

    @JSONField(name = "request_date_ms")
    private String requestDateMs;

    public Integer getAppItemId() {
        return appItemId;
    }

    public void setAppItemId(Integer appItemId) {
        this.appItemId = appItemId;
    }

    public String getRequestDatePst() {
        return requestDatePst;
    }

    public void setRequestDatePst(String requestDatePst) {
        this.requestDatePst = requestDatePst;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public String getOriginalPurchaseDateMs() {
        return originalPurchaseDateMs;
    }

    public void setOriginalPurchaseDateMs(String originalPurchaseDateMs) {
        this.originalPurchaseDateMs = originalPurchaseDateMs;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getOriginalPurchaseDate() {
        return originalPurchaseDate;
    }

    public void setOriginalPurchaseDate(String originalPurchaseDate) {
        this.originalPurchaseDate = originalPurchaseDate;
    }

    public Integer getAdamId() {
        return adamId;
    }

    public void setAdamId(Integer adamId) {
        this.adamId = adamId;
    }

    public String getReceiptCreationDate() {
        return receiptCreationDate;
    }

    public void setReceiptCreationDate(String receiptCreationDate) {
        this.receiptCreationDate = receiptCreationDate;
    }

    public String getOriginalPurchaseDatePst() {
        return originalPurchaseDatePst;
    }

    public void setOriginalPurchaseDatePst(String originalPurchaseDatePst) {
        this.originalPurchaseDatePst = originalPurchaseDatePst;
    }

    public List<IapInAppReceipt> getInApp() {
        return inApp;
    }

    public void setInApp(List<IapInAppReceipt> inApp) {
        this.inApp = inApp;
    }

    public Integer getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(Integer downloadId) {
        this.downloadId = downloadId;
    }

    public String getReceiptCreationDatePst() {
        return receiptCreationDatePst;
    }

    public void setReceiptCreationDatePst(String receiptCreationDatePst) {
        this.receiptCreationDatePst = receiptCreationDatePst;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public Integer getVersionExternalIdentifier() {
        return versionExternalIdentifier;
    }

    public void setVersionExternalIdentifier(Integer versionExternalIdentifier) {
        this.versionExternalIdentifier = versionExternalIdentifier;
    }

    public String getBundleId() {
        return bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public String getOriginalApplicationVersion() {
        return originalApplicationVersion;
    }

    public void setOriginalApplicationVersion(String originalApplicationVersion) {
        this.originalApplicationVersion = originalApplicationVersion;
    }

    public String getReceiptCreationDateMs() {
        return receiptCreationDateMs;
    }

    public void setReceiptCreationDateMs(String receiptCreationDateMs) {
        this.receiptCreationDateMs = receiptCreationDateMs;
    }

    public String getRequestDateMs() {
        return requestDateMs;
    }

    public void setRequestDateMs(String requestDateMs) {
        this.requestDateMs = requestDateMs;
    }
}