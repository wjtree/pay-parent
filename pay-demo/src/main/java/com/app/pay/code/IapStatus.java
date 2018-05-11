package com.app.pay.code;

/**
 * <p>功 能：数据字典-苹果内购服务器二次验证返回状态</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月23日 下午2:25:10</p>
 * @author 王建
 * @version 1.0
 */
public class IapStatus {
    /** 支付成功 */
    public static final String S0 = "0";

    /** App Store无法读取您提供的JSON对象 */
    public static final String S21000 = "21000";

    /** receipt-data属性中的数据格式错误或丢失 */
    public static final String S21002 = "21002";

    /** 收据无法验证 */
    public static final String S21003 = "21003";

    /** 您提供的共享密码与您帐户的文件共享密码不匹配 */
    public static final String S21004 = "21004";

    /** 收据服务器当前不可用 */
    public static final String S21005 = "21005";

    /** 此收据有效，但订阅已过期 */
    public static final String S21006 = "21006";

    /** 此收据来自测试环境，但已发送到生产环境进行验证。将其发送到测试环境 */
    public static final String S21007 = "21007";

    /** 此收据来自生产环境，但已发送到测试环境进行验证。将其发送到生产环境 */
    public static final String S21008 = "21008";
}