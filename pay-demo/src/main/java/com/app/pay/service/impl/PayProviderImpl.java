package com.app.pay.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.app.pay.code.PayConstant;
import com.app.pay.entity.dto.PayBack;
import com.app.pay.service.PayProvider;
import com.app.pay.util.PayBackUtil;

/**
 * <p>功 能：第三方支付接口实现类</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月9日 下午4:43:02</p>
 * @author 王建
 * @version 1.0
 */
@Service
public class PayProviderImpl implements PayProvider {
	private static final Logger LOG = LogManager.getLogger(PayProviderImpl.class);

	@Autowired
	private PayCheckService payCheckService;
	@Autowired
	private PayCoreService payCoreService;
	@Autowired
	private PayIOService payIOService;

	public PayBack unifiedTradePay(String json) {
		PayBack obj = null;
		try {
			// 参数检查
			Assert.hasText(json, "交易支付接口请求参数不能为空");
			if (LOG.isInfoEnabled())
				LOG.info("交易支付接口请求参数：{}", json);
			// 获取请求参数
			JSONObject jsonObj = JSON.parseObject(json);
			String service = jsonObj.getString("service");
			String outTradeNo = jsonObj.getString("outTradeNo");
			String body = jsonObj.getString("body");
			Integer totalFee = jsonObj.getInteger("totalFee");
			String returnUrl = jsonObj.getString("returnUrl");
			String spbillCreateIp = jsonObj.getString("spbillCreateIp");
			Integer expireTime = MapUtils.getInteger(jsonObj, "expireTime", PayConstant.PAY_EXPIRE);
			// 微信支付规定最短失效时间间隔必须大于5分钟
			expireTime = (expireTime > 5 ? expireTime : PayConstant.PAY_EXPIRE);

			// 判断订单号是否存在
			if (payCheckService.isNotMatchTradeAndType(outTradeNo, service))
				return PayBackUtil.getErrorBack("商户订单号已存在");

			// 选择实际交易支付接口
			obj = payCoreService.unifiedTradePayProcess(service, outTradeNo, body, totalFee, expireTime, returnUrl, spbillCreateIp);
			// 使用单独线程处理接口报文数据
			payIOService.unifiedTradePayThread(json, obj, service, outTradeNo, body, totalFee, expireTime);
		} catch (Exception e) {
			LOG.error("交易支付接口出错", e);
		}
		return obj;
	}

	public PayBack unifiedTradeQuery(String json) {
		PayBack obj = null;
		try {
			// 参数检查
			Assert.hasText(json, "交易查询接口请求参数不能为空");
			if (LOG.isInfoEnabled())
				LOG.info("交易查询接口请求参数：{}", json);
			// 获取请求参数
			JSONObject jsonObj = JSON.parseObject(json);
			String outTradeNo = jsonObj.getString("outTradeNo");

			// 选择实际交易查询接口
			obj = payCoreService.unifiedTradeQueryProcess(outTradeNo);
			// 使用单独线程处理接口报文数据
			payIOService.unifiedTradeQueryThread(json, obj, outTradeNo);
		} catch (Exception e) {
			LOG.error("交易查询接口出错", e);
		}
		return obj;
	}

	public PayBack unifiedTradeClose(String json) {
		PayBack obj = null;
		try {
			// 参数检查
			Assert.hasText(json, "交易关闭接口请求参数不能为空");
			if (LOG.isInfoEnabled())
				LOG.info("交易关闭接口请求参数：{}", json);
			// 获取请求参数
			JSONObject jsonObj = JSON.parseObject(json);
			String outTradeNo = jsonObj.getString("outTradeNo");

			// 选择实际交易关闭接口
			obj = payCoreService.unifiedTradeCloseProcess(outTradeNo);
			// 使用单独线程处理接口报文数据
			payIOService.unifiedTradeCloseThread(json, obj, outTradeNo);
		} catch (Exception e) {
			LOG.error("交易关闭接口出错", e);
		}
		return obj;
	}

	public PayBack unifiedTradeRefund(String json) {
		PayBack obj = null;
		try {
			// 参数检查
			Assert.hasText(json, "交易退款接口请求参数不能为空");
			if (LOG.isInfoEnabled())
				LOG.info("交易退款接口请求参数：{}", json);
			// 获取请求参数
			JSONObject jsonObj = JSON.parseObject(json);
			String outTradeNo = jsonObj.getString("outTradeNo");
			String outRefundNo = jsonObj.getString("outRefundNo");
			Integer totalFee = jsonObj.getInteger("totalFee");
			Integer refundFee = jsonObj.getInteger("refundFee");

			// 判断是否该退款单号已存在，但传入的订单号与已存在的该退款单号匹配的订单号不一致
			if (payCheckService.isNotMatchTradeAndRefund(outTradeNo, outRefundNo))
				return PayBackUtil.getErrorBack("商户退款单号已存在");

			// 选择实际交易退款接口
			obj = payCoreService.unifiedTradeRefundProcess(outTradeNo, outRefundNo, totalFee, refundFee);
			// 使用单独线程处理接口报文数据
			payIOService.unifiedTradeRefundThread(json, obj, outTradeNo, outRefundNo, totalFee, refundFee);
		} catch (Exception e) {
			LOG.error("交易退款接口出错", e);
		}
		return obj;
	}

	public PayBack unifiedTradeRefundQuery(String json) {
		PayBack obj = null;
		try {
			// 参数检查
			Assert.hasText(json, "交易退款查询接口请求参数不能为空");
			if (LOG.isInfoEnabled())
				LOG.info("交易退款查询接口请求参数：{}", json);
			// 获取请求参数
			JSONObject jsonObj = JSON.parseObject(json);
			String outTradeNo = jsonObj.getString("outTradeNo");
			String outRefundNo = jsonObj.getString("outRefundNo");

			// 选择实际交易退款接口
			obj = payCoreService.unifiedTradeRefundQueryProcess(outTradeNo, outRefundNo);
			// 使用单独线程处理接口报文数据
			payIOService.unifiedTradeRefundQueryThread(json, obj, outTradeNo, outRefundNo);
		} catch (Exception e) {
			LOG.error("交易退款查询接口出错", e);
		}
		return obj;
	}

	public Object unifiedDownloadBill(String json) {
		Object obj = null;
		try {
			// 参数检查
			Assert.hasText(json, "下载对账单接口请求参数不能为空");
			if (LOG.isInfoEnabled())
				LOG.info("下载对账单接口请求参数：{}", json);
			// 获取请求参数
			JSONObject jsonObj = JSON.parseObject(json);
			String billDate = jsonObj.getString("billDate");
			String billType = jsonObj.getString("billType");

			// 选择实际交易退款接口
			obj = payCoreService.unifiedDownloadBillProcess(billDate, billType);
			// 使用单独线程处理接口报文数据
			payIOService.unifiedDownloadBillThread(json, obj, billDate, billType);
		} catch (Exception e) {
			LOG.error("下载对账单接口出错", e);
		}
		return obj;
	}
}