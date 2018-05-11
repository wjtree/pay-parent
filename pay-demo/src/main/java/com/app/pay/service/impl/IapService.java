package com.app.pay.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.code.Status;
import com.app.pay.entity.dto.PayBack;
import com.app.pay.thread.PayExecutor;
import com.app.pay.util.IapUtil;

/**
 * <p>功 能：苹果内购服务器二次验证业务逻辑处理类</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月23日 下午2:46:19</p>
 * @author 王建
 * @version 1.0
 */
@Service
public class IapService {
	private static final Logger LOG = LogManager.getLogger(IapService.class);

	@Autowired
	private PayDBService payDBService;
	@Autowired
	private PayCheckService payCheckService;

	/**
	 * 苹果内购服务器二次验证
	 * @param receiptData
	 * @param password
	 * @return
	 */
	public PayBack verifyReceipt(String outTradeNo, String receiptData, String password) {
		PayBack model = null;
		try {
			model = IapUtil.verifyReceipt(outTradeNo, receiptData, password);
			// 保存苹果内购订单记录
			saveIapTrade(outTradeNo, model);
		} catch (Exception e) {
			LOG.error("苹果内购服务器二次验证出错", e);
		}
		return model;
	}

	/**
	 * 保存苹果内购订单记录
	 * @param outTradeNo
	 * @param back
	 */
	private void saveIapTrade(final String outTradeNo, final PayBack back) {
		PayExecutor.execute(new Runnable() {
			public void run() {
				try {
					// 如果校验支付成功
					if (StringUtils.equals(Status.SUCCESS.getStatus(), back.getErrCode())) {
						String transactionId = MapUtils.getString(back.getDataMap(), "transactionId");
						String body = MapUtils.getString(back.getDataMap(), "body");
						Integer totalFee = MapUtils.getInteger(back.getDataMap(), "totalFee");
						String timeEnd = MapUtils.getString(back.getDataMap(), "timeEnd");

						if (!payCheckService.isExistTrade(outTradeNo))
							payDBService.saveIapTrade(outTradeNo, transactionId, body, totalFee, timeEnd);
					}
				} catch (Exception e) {
					LOG.error("交易支付接口线程出错", e);
				}
			}
		});
	}
}