package com.app.pay.service.impl;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.app.code.Status;
import com.app.pay.code.PayPort;
import com.app.pay.code.SwiftTradeStatus;
import com.app.pay.entity.dto.PayBack;
import com.app.pay.thread.PayExecutor;
import com.app.pay.util.LogUtil;

/**
 * <p>功 能：第三方支付接口报文的持久化与文件IO流操作</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月12日 下午4:55:41</p>
 * @author 王建
 * @version 1.0
 */
@Service
public class PayIOService {
	private static final Logger LOG = LogManager.getLogger(PayIOService.class);

	@Autowired
	private PayCheckService payCheckService;
	@Autowired
	private PayDBService payDBService;

	/**
	 * 交易支付接口线程
	 * <p>处理数据持久化和日志记录</p>
	 * @param json 请求JSON字符串
	 * @param obj 返回结果对象
	 * @param service
	 * @param outTradeNo
	 * @param body
	 * @param totalFee
	 * @param expireTime
	 */
	public void unifiedTradePayThread(final String json, final PayBack obj, final String service, final String outTradeNo, final String body, final Integer totalFee,
			final Integer expireTime) {
		// 使用支付专用线程执行器，以匿名内部类的方式传入线程
		PayExecutor.execute(new Runnable() {
			public void run() {
				try {
					// 获取接口调用返回信息
					String errCode = obj.getErrCode();
					// 记录日志
					LogUtil.writePayLog(PayPort.YBF_TRADE_PAY, json, JSON.toJSONString(obj));
					// 保存订单支付信息
					if (!payCheckService.isExistTrade(outTradeNo) && StringUtils.equals(errCode, "0"))
						payDBService.saveTrade(service, outTradeNo, body, totalFee, expireTime);
				} catch (Exception e) {
					LOG.error("交易支付接口线程出错", e);
				}
			}
		});
	}

	/**
	 * 交易查询接口线程
	 * @param json
	 * @param obj
	 * @param outTradeNo
	 */
	public void unifiedTradeQueryThread(final String json, final PayBack obj, final String outTradeNo) {
		// 使用支付专用线程执行器，以匿名内部类的方式传入线程
		PayExecutor.execute(new Runnable() {
			public void run() {
				try {
					// 获取接口调用返回信息
					String errCode = obj.getErrCode();
					// 记录日志
					LogUtil.writePayLog(PayPort.YBF_TRADE_QUERY, json, JSON.toJSONString(obj));
					// 修改订单状态
					if (StringUtils.equals(errCode, Status.SUCCESS.getStatus())) {
						Map<String, Object> map = obj.getDataMap();
						String outTradeNo = null;
						String transactionId = null;
						String outTransactionId = null;
						String tradeState = null;
						String timeEnd = null;

						if (map.containsKey("trade_state")) {
							// 威富通支付
							outTradeNo = MapUtils.getString(map, "out_trade_no");
							transactionId = MapUtils.getString(map, "transaction_id");
							outTransactionId = MapUtils.getString(map, "out_transaction_id");
							tradeState = MapUtils.getString(map, "trade_state");
							timeEnd = MapUtils.getString(map, "time_end");
						} else {
							// 支付宝或微信APP支付
							outTradeNo = MapUtils.getString(map, "outTradeNo");
							outTransactionId = MapUtils.getString(map, "transactionId");
							tradeState = MapUtils.getString(map, "tradeState");
							timeEnd = MapUtils.getString(map, "timeEnd");
						}

						// 更新数据库
						payDBService.updateTrade(outTradeNo, transactionId, outTransactionId, tradeState, timeEnd);
					}
				} catch (Exception e) {
					LOG.error("交易查询接口线程出错", e);
				}
			}
		});
	}

	/**
	 * 交易关闭接口线程
	 * @param json
	 * @param obj
	 * @param outTradeNo
	 */
	public void unifiedTradeCloseThread(final String json, final PayBack obj, final String outTradeNo) {
		// 使用支付专用线程执行器，以匿名内部类的方式传入线程
		PayExecutor.execute(new Runnable() {
			public void run() {
				try {
					// 获取接口调用返回信息
					String errCode = obj.getErrCode();
					// 记录日志
					LogUtil.writePayLog(PayPort.YBF_TRADE_CLOSE, json, JSON.toJSONString(obj));
					// 修改订单状态
					if (StringUtils.equals(errCode, Status.SUCCESS.getStatus()))
						payDBService.updateTrade(outTradeNo, null, null, SwiftTradeStatus.CLOSED, null);
				} catch (Exception e) {
					LOG.error("交易关闭接口线程出错", e);
				}
			}
		});
	}

	/**
	 * 交易退款接口线程
	 * @param json
	 * @param obj
	 * @param outTradeNo
	 * @param outRefundNo
	 * @param totalFee
	 * @param refundFee
	 */
	public void unifiedTradeRefundThread(final String json, final PayBack obj, final String outTradeNo, final String outRefundNo, final Integer totalFee, final Integer refundFee) {
		// 使用支付专用线程执行器，以匿名内部类的方式传入线程
		PayExecutor.execute(new Runnable() {
			public void run() {
				try {
					// 获取接口调用返回信息
					String errCode = obj.getErrCode();
					// 记录日志
					LogUtil.writePayLog(PayPort.YBF_TRADE_REFUND, json, JSON.toJSONString(obj));
					// 保存订单退款信息
					if (!payCheckService.isExistRefund(outRefundNo) && StringUtils.equals(errCode, "0"))
						payDBService.saveRefund(outTradeNo, outRefundNo, refundFee);
				} catch (Exception e) {
					LOG.error("交易退款接口线程出错", e);
				}
			}
		});
	}

	/**
	 * 交易退款查询接口线程
	 * @param json
	 * @param obj
	 * @param outTradeNo
	 * @param outRefundNo
	 */
	public void unifiedTradeRefundQueryThread(final String json, final PayBack obj, final String outTradeNo, final String outRefundNo) {
		// 使用支付专用线程执行器，以匿名内部类的方式传入线程
		PayExecutor.execute(new Runnable() {
			public void run() {
				try {
					// 获取接口调用返回信息
					String errCode = obj.getErrCode();
					// 记录日志
					LogUtil.writePayLog(PayPort.YBF_TRADE_REFUND_QUERY, json, JSON.toJSONString(obj));
					// 修改订单状态
					if (StringUtils.equals(errCode, Status.SUCCESS.getStatus())) {
						Map<String, Object> map = obj.getDataMap();
						String outRefundNo = null;
						String refundId = null;
						String refundStatus = null;
						String refundTime = null;

						if (map.containsKey("out_refund_no")) {
							// 威富通支付
							outRefundNo = MapUtils.getString(map, "out_refund_no");
							refundId = MapUtils.getString(map, "refund_id");
							refundStatus = MapUtils.getString(map, "refund_status");
							refundTime = MapUtils.getString(map, "refund_time");
						} else {
							// 支付宝或微信APP支付
							outRefundNo = MapUtils.getString(map, "outRefundNo");
							refundStatus = MapUtils.getString(map, "refundStatus");
							refundTime = MapUtils.getString(map, "refundTime");
						}

						// 更新数据库
						payDBService.updateRefund(outRefundNo, refundId, refundStatus, refundTime);
					}
				} catch (Exception e) {
					LOG.error("交易退款查询接口线程出错", e);
				}
			}
		});
	}

	/**
	 * 下载对账单接口线程
	 * @param json
	 * @param obj
	 * @param billDate
	 * @param billType
	 */
	public void unifiedDownloadBillThread(final String json, final Object obj, final String billDate, final String billType) {
		// 使用支付专用线程执行器，以匿名内部类的方式传入线程
		PayExecutor.execute(new Runnable() {
			public void run() {
				try {
					// 记录日志
					LogUtil.writePayLog(PayPort.YBF_DOWNLOAD_BILL, json);
				} catch (Exception e) {
					LOG.error("下载对账单接口线程出错", e);
				}
			}
		});
	}
}