package com.app.pay.code;

import com.alipay.api.AlipayConstants;
import com.app.util.PropUtil;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * <p>功 能：第三方支付接口配置信息</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月10日 上午9:14:54</p>
 * @author 王建
 * @version 1.0
 */
public class PayConstant {
	/** <公共参数>订单有效期，单位：分钟 */
	public static final Integer PAY_EXPIRE = PropUtil.getInteger("pay.expire", 120);
	/** <公共参数>文件大小，单位：MB */
	public static final Integer PAY_LOG_SIZE = PropUtil.getInteger("pay.log.size", 10);
	/** <公共参数>文件根路径（存放支付接口请求与响应的报文） */
	public static final String PAY_LOG_ROOT = PropUtil.getString("pay.log.root");
	/** <公共参数>支付订单最小有效期，不能低于此时间，单位：分钟 */
	public static final Integer PAY_MIN_EXPIRE = 5;

	/** <公共参数>文件目录 */
	public static final String PAY_LOG_PATH = PAY_LOG_ROOT + "payLog" + File.separator;
	/** <公共参数>对账单存放路径*/
	public static final String BILL_LOG = PAY_LOG_PATH + "bill" + File.separator;
	/** <公共参数>第三方支付接口报文存放路径*/
	public static final String PAY_LOG = PAY_LOG_PATH + "log" + File.separator;

	/** <威富通参数>版本号 */
	public static final String SWIFT_VERSION = "2.0";
	/** <威富通参数>字符集 */
	public static final String SWIFT_CHARSET = StandardCharsets.UTF_8.name();
	/** <威富通参数>签名方式 */
	public static final String SWIFT_SIGN_TYPE = "MD5";
	/** <威富通参数>支付请求url */
	public static final String SWIFT_PAY_URL = PropUtil.getString("swift.pay.url");
	/** <威富通参数>对账单请求url */
	public static final String SWIFT_DOWNLOAD_URL = PropUtil.getString("swift.download.url");
	/** <威富通参数>威富通商户号 */
	public static final String SWIFT_MCH_ID = PropUtil.getString("swift.mch.id");
	/** <威富通参数>威富通交易密钥 */
	public static final String SWIFT_KEY = PropUtil.getString("swift.key");
	/** <威富通参数>威富通支付通知地址 */
	public static final String SWIFT_NOTIFY_URL = PropUtil.getString("swift.notify.url");

	/** <Ping支付参数>Ping++ TEST Secret key，测试环境使用 */
	public static final String PINGPP_TEST_SECRET_KEY = PropUtil.getString("pingpp.test.secret.key");
	/** <Ping支付参数>Ping++ LIVE Secret key，生产环境使用 */
	public static final String PINGPP_LIVE_SECRET_KEY = PropUtil.getString("pingpp.live.secret.key");
	/** <Ping支付参数>Ping++公钥存放目录 */
	public static final String PINGPP_PING_RSA_PUBLIC_KEY_PATH = PropUtil.getString("pingpp.ping.rsa.public.key.path");
	/** <Ping支付参数>商户私钥存放目录 */
	public static final String PINGPP_YBF_RSA_PRIVATE_KEY_PATH = PropUtil.getString("pingpp.ybf.rsa.private.key.path");
	/** <Ping支付参数>Ping++ AppID */
	public static final String PINGPP_APPID = PropUtil.getString("pingpp.appid");

	/** <支付宝参数>支付宝网关 */
	public static final String ALIPAY_SERVER_URL = PropUtil.getString("alipay.server.url");
	/** <支付宝参数>数据交互格式 */
	public static final String ALIPAY_FORMAT = AlipayConstants.FORMAT_JSON;
	/** <支付宝参数>销售产品码 */
	public static final String ALIPAY_PRODUCT_CODE = "QUICK_MSECURITY_PAY";
	/** <支付宝参数>编码格式 */
	public static final String ALIPAY_CHARSET = AlipayConstants.CHARSET_UTF8;
	/** <支付宝参数>签名算法类型 */
	public static final String ALIPAY_SIGN_TYPE = AlipayConstants.SIGN_TYPE_RSA2;
	/** <支付宝参数>数据加密类型 */
	public static final String ALIPAY_ENCRYPT_TYPE = AlipayConstants.ENCRYPT_TYPE_AES;
	/** <支付宝参数>应用APPID */
	public static final String ALIPAY_APP_ID = PropUtil.getString("alipay.app.id");
	/** <支付宝参数>商户签名私钥 */
	public static final String ALIPAY_RSA2_PRIVATE_KEY = PropUtil.getString("alipay.rsa2.private.key");
	/** <支付宝参数>支付宝签名公钥 */
	public static final String ALIPAY_RSA2_ALIPAY_PULIC_KEY = PropUtil.getString("alipay.rsa2.alipay.pulic.key");
	/** <支付宝参数>数据加密密钥 */
	public static final String ALIPAY_ENCRYPT_KEY = PropUtil.getString("alipay.encrypt.key");
	/** <支付宝参数>支付宝支付通知地址 */
	public static final String ALIPAY_NOTIFY_URL = PropUtil.getString("alipay.notify.url");

	/** <微信支付参数>APP统一下单接口地址 */
	public static final String WXPAY_SERVER_UNIFIEDORDER_URL = PropUtil.getString("wxpay.server.unifiedorder.url");
	/** <微信支付参数>查询订单接口地址 */
	public static final String WXPAY_SERVER_ORDERQUERY_URL = PropUtil.getString("wxpay.server.orderquery.url");
	/** <微信支付参数>关闭订单接口地址 */
	public static final String WXPAY_SERVER_CLOSEORDER_URL = PropUtil.getString("wxpay.server.closeorder.url");
	/** <微信支付参数>申请退款接口地址 */
	public static final String WXPAY_SERVER_REFUND_URL = PropUtil.getString("wxpay.server.refund.url");
	/** <微信支付参数>查询退款接口地址 */
	public static final String WXPAY_SERVER_REFUNDQUERY_URL = PropUtil.getString("wxpay.server.refundquery.url");
	/** <微信支付参数>下载对账单接口地址 */
	public static final String WXPAY_SERVER_DOWNLOADBILL_URL = PropUtil.getString("wxpay.server.downloadbill.url");
	/** <微信支付参数>交易保障接口地址 */
	public static final String WXPAY_SERVER_REPORT_URL = PropUtil.getString("wxpay.server.report.url");
	/** <微信支付参数>设备号 */
	public static final String WXPAY_DEVICE_INFO = "WEB";
	/** <微信支付参数>签名类型 */
	public static final String WXPAY_SIGN_TYPE = "MD5";
	/** <微信支付参数>默认货币类型 */
	public static final String WXPAY_FEE_TYPE = "CNY";
	/** <微信支付参数>商户号 */
	public static final String WXPAY_MCH_ID = PropUtil.getString("wxpay.mch.id");
	/** <微信支付参数>API密钥 */
	public static final String WXPAY_API_KEY = PropUtil.getString("wxpay.api.key");
	/** <微信支付参数>一比分 AppID */
	public static final String WXPAY_APP_ID = PropUtil.getString("wxpay.app.id");
	/** <微信支付参数>一比分 AppSecret */
	public static final String WXPAY_APP_SECRET = PropUtil.getString("wxpay.app.secret");
	/** <微信支付参数>应用市场上的APP名字 */
	public static final String WXPAY_APP_NAME = PropUtil.getString("wxpay.app.name");
	/** <微信支付参数>微信支付证书地址 */
	public static final String WXPAY_CERT_PATH = PropUtil.getString("wxpay.cert.path");
	/** <微信支付参数>微信支付通知地址 */
	public static final String WXPAY_NOTIFY_URL = PropUtil.getString("wxpay.notify.url");
	/** <微信支付参数>调起支付接口-扩展字段固定值 */
	public static final String WXPAY_PACKAGES = "Sign=WXPay";

	/** <苹果内购参数>沙箱验证地址 */
	public static final String IOS_IAP_VERIFY_SANDBOX_URL = PropUtil.getString("ios.iap.verify.sandbox.url");
	/** <苹果内购参数>正式验证地址 */
	public static final String IOS_IAP_VERIFY_BUY_URL = PropUtil.getString("ios.iap.verify.buy.url");
}