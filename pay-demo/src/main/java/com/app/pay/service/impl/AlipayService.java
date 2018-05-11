package com.app.pay.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alipay.api.domain.AlipayDataDataserviceBillDownloadurlQueryModel;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.app.pay.util.AlipayUtil;

/**
 * <p>功 能：支付宝支付接口逻辑处理类</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月10日 下午4:26:29</p>
 * @author 王建
 * @version 1.0
 */
@Service
public class AlipayService {
	private static final Logger LOG = LogManager.getLogger(AlipayService.class);

	/**
	 * 交易支付（APP）接口
	 * @param model
	 * @return
	 */
	public AlipayTradeAppPayResponse alipayTradeAppPay(AlipayTradeAppPayModel model) {
		AlipayTradeAppPayResponse result = null;
		try {
			result = AlipayUtil.alipayTradeAppPay(model);
		} catch (Exception e) {
			LOG.error("交易支付（APP）接口出错", e);
		}
		return result;
	}

	/**
	 * 交易支付（WAP）接口
	 * @param model
	 * @param returnUrl
	 * @return
	 */
	public AlipayTradeWapPayResponse alipayTradeWapPay(AlipayTradeWapPayModel model, String returnUrl) {
		AlipayTradeWapPayResponse result = null;
		try {
			result = AlipayUtil.alipayTradeWapPay(model, returnUrl);
		} catch (Exception e) {
			LOG.error("交易支付（WAP）接口出错", e);
		}
		return result;
	}

	/**
	 * 交易查询接口
	 * @param model
	 * @return
	 */
	public AlipayTradeQueryResponse alipayTradeQuery(AlipayTradeQueryModel model) {
		AlipayTradeQueryResponse result = null;
		try {
			result = AlipayUtil.alipayTradeQuery(model);
		} catch (Exception e) {
			LOG.error("交易查询接口出错", e);
		}
		return result;
	}

	/**
	 * 交易关闭接口
	 * @param model
	 * @return
	 */
	public AlipayTradeCloseResponse alipayTradeClose(AlipayTradeCloseModel model) {
		AlipayTradeCloseResponse result = null;
		try {
			result = AlipayUtil.alipayTradeClose(model);
		} catch (Exception e) {
			LOG.error("交易关闭接口出错", e);
		}
		return result;
	}

	/**
	 * 交易退款接口
	 * @param model
	 * @return
	 */
	public AlipayTradeRefundResponse alipayTradeRefund(AlipayTradeRefundModel model) {
		AlipayTradeRefundResponse result = null;
		try {
			result = AlipayUtil.alipayTradeRefund(model);
		} catch (Exception e) {
			LOG.error("交易退款接口出错", e);
		}
		return result;
	}

	/**
	 * 交易退款查询接口
	 * @param model
	 * @return
	 */
	public AlipayTradeFastpayRefundQueryResponse alipayTradeFastpayRefundQuery(AlipayTradeFastpayRefundQueryModel model) {
		AlipayTradeFastpayRefundQueryResponse result = null;
		try {
			result = AlipayUtil.alipayTradeFastpayRefundQuery(model);
		} catch (Exception e) {
			LOG.error("交易退款查询接口出错", e);
		}
		return result;
	}

	/**
	 * 查询账单下载地址接口
	 * @param model
	 * @return
	 */
	public AlipayDataDataserviceBillDownloadurlQueryResponse alipayDataDataserviceBillDownloadurlQuery(AlipayDataDataserviceBillDownloadurlQueryModel model) {
		AlipayDataDataserviceBillDownloadurlQueryResponse result = null;
		try {
			result = AlipayUtil.alipayDataDataserviceBillDownloadurlQuery(model);
		} catch (Exception e) {
			LOG.error("查询账单下载地址接口出错", e);
		}
		return result;
	}
}