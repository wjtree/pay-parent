package com.app.pay.code;

/**
 * <p>功 能：数据字典-威富通退款状态</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月19日 上午11:33:01</p>
 * @author 王建
 * @version 1.0
 */
public class SwiftRefundStatus {
    /** 退款成功 */
    public static final String SUCCESS = "SUCCESS";
    /** 退款失败 */
    public static final String FAIL = "FAIL";
    /** 退款处理中 */
    public static final String PROCESSING = "PROCESSING";
    /** 未确定，需要商户原退款单号重新发起 */
    public static final String NOTSURE = "NOTSURE";
    /** 转入代发，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者威富通转账的方式进行退款 */
    public static final String CHANGE = "CHANGE";
}