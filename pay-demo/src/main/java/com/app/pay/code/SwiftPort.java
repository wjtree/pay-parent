package com.app.pay.code;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>功 能：威富通接口名称常量类</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年3月16日 下午12:46:50</p>
 * @author 王建
 * @version 1.0
 */
public class SwiftPort {
    /** 微信扫码支付-统一下单接口 */
    public static final String PAY_WEIXIN_NATIVE = "pay.weixin.native";
    /** 微信扫码支付-统一下单通知 */
    public static final String PAY_WEIXIN_NATIVE_NOTIFY = "pay.weixin.native.notify";

    /** 支付宝扫码支付-统一下单接口 */
    public static final String PAY_ALIPAY_NATIVE = "pay.alipay.native";
    /** 支付宝扫码支付-统一下单通知 */
    public static final String PAY_ALIPAY_NATIVE_NOTIFY = "pay.alipay.native.notify";

    /** APP支付-非原生态预下单API */
    public static final String UNIFIED_TRADE_PAY = "unified.trade.pay";
    /** APP支付-原生态预下单API */
    public static final String PAY_WEIXIN_RAW_APP = "pay.weixin.raw.app";
    /** 微信APP支付通知 */
    public static final String PAY_WEIXIN_APP = "pay.weixin.app";
    /** APP支付-统一下单通知 */
    public static final String PAY_WEIXIN_APP_NOTIFY = "pay.weixin.app.notify";

    /** 查询订单接口 */
    public static final String UNIFIED_TRADE_QUERY = "unified.trade.query";
    /** 关闭订单接口 */
    public static final String UNIFIED_TRADE_CLOSE = "unified.trade.close";
    /** 申请退款接口 */
    public static final String UNIFIED_TRADE_REFUND = "unified.trade.refund";
    /** 查询退款接口 */
    public static final String UNIFIED_TRADE_REFUNDQUERY = "unified.trade.refundquery";

    /** 账单下载-下载单个商户时的对账单 */
    public static final String PAY_BILL_MERCHANT = "pay.bill.merchant";
    /** 账单下载-下载大商户下所有子商户的对账单 */
    public static final String PAY_BILL_BIGMERCHANT = "pay.bill.bigMerchant";
    /** 账单下载-下载某渠道下所有商户的对账单 */
    public static final String PAY_BILL_AGENT = "pay.bill.agent";

    /** 威富通支持的支付类型数组 */
    public static final String[] PAY_ARRAY = { PAY_WEIXIN_NATIVE, PAY_ALIPAY_NATIVE, UNIFIED_TRADE_PAY, PAY_WEIXIN_RAW_APP };
    /** 网页二维码支付类型数组 */
    public static final String[] PAY_PC_ARRAY = { PAY_WEIXIN_NATIVE, PAY_ALIPAY_NATIVE };
    /** APP支付类型数组 */
    public static final String[] PAY_APP_ARRAY = { UNIFIED_TRADE_PAY, PAY_WEIXIN_RAW_APP };
    /** 威富通支付结果通知所属的支付类型数组 */
    public static final String[] PAY_NOTIFY_ARRAY = { PAY_WEIXIN_NATIVE_NOTIFY, PAY_ALIPAY_NATIVE_NOTIFY, PAY_WEIXIN_APP_NOTIFY };

    /**
     * 根据支付通知中的交易类型获取对应的接口名称
     * @param tradeType 交易类型
     * @return
     * @throws Exception
     */
    public static String getNotifyType(final String tradeType) throws Exception {
        String type = null;
        // 微信扫码支付通知
        if (StringUtils.startsWithIgnoreCase(tradeType, PAY_WEIXIN_NATIVE))
            type = SwiftPort.PAY_WEIXIN_NATIVE_NOTIFY;
        // 支付宝扫码通知
        else if (StringUtils.startsWithIgnoreCase(tradeType, PAY_ALIPAY_NATIVE))
            type = SwiftPort.PAY_ALIPAY_NATIVE_NOTIFY;
        // 微信APP支付通知
        else if (StringUtils.startsWithIgnoreCase(tradeType, PAY_WEIXIN_APP))
            type = SwiftPort.PAY_WEIXIN_APP_NOTIFY;
        return type;
    }
}