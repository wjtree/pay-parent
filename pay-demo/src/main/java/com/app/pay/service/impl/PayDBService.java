package com.app.pay.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.code.Status;
import com.app.pay.code.IapPort;
import com.app.pay.code.WxapyRefundChannel;
import com.app.pay.dao.PayRefundMapper;
import com.app.pay.dao.PayTradeMapper;
import com.app.pay.entity.generate.PayRefund;
import com.app.pay.entity.generate.PayTrade;
import com.app.util.DateUtil;

/**
 * <p>功 能：第三方支付请求记录</p>
 * <p>描述：记录支付请求与响应的报文，存入数据库或缓存</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月11日 下午2:38:16</p>
 * @author 王建
 * @version 1.0
 */
@Service
@Transactional
public class PayDBService {
	private static final Logger LOG = LogManager.getLogger(PayDBService.class);

	@Autowired
	private PayTradeMapper payTradeMapper;
	@Autowired
	private PayRefundMapper payRefundMapper;

	/**
	 * 查询订单支付信息
	 * @param outTradeNo 商户订单号
	 * @return
	 */
	public PayTrade searchTrade(String outTradeNo) {
		PayTrade payTrade = null;
		try {
			// 查询数据库获取订单信息，该查询语句已使用MyBatis3缓存
			payTrade = payTradeMapper.selectByPrimaryKey(outTradeNo);
		} catch (Exception e) {
			LOG.error("查询订单支付信息出错", e);
		}
		return payTrade;
	}

	/**
	 * 保存订单支付信息
	 * @param service 支付类型
	 * @param outTradeNo 商户订单号
	 * @param body 商品名称
	 * @param totalFee 总金额
	 * @param expireTime 订单有效期，单位：分钟
	 */
	public void saveTrade(String service, String outTradeNo, String body, Integer totalFee, Integer expireTime) {
		try {
			// 装载参数
			PayTrade payTrade = new PayTrade();
			payTrade.setTradeType(service);
			payTrade.setOutTradeNo(outTradeNo);
			payTrade.setBody(body);
			payTrade.setTotalFee(totalFee.longValue());
			payTrade.setTimeStart(DateUtil.getCurrentDateTime());
			payTrade.setTimeExpire(DateUtil.getDateTimeByNow(expireTime, DateUtil.FMT_DATETIME));
			payTrade.setCreateTime(DateUtil.getCurrentDateTime());
			// 插入数据库
			payTradeMapper.insertSelective(payTrade);
		} catch (Exception e) {
			LOG.error("保存订单支付信息出错", e);
		}
	}

	/**
	 * 保存苹果内购订单支付信息
	 * @param outTradeNo
	 * @param transactionId
	 * @param body
	 * @param totalFee
	 * @param timeEnd
	 */
	public void saveIapTrade(String outTradeNo, String transactionId, String body, Integer totalFee, String timeEnd) {
		try {
			// 装载参数
			PayTrade payTrade = new PayTrade();
			payTrade.setTradeType(IapPort.IOS_IAP_TRADE_VERIFY);
			payTrade.setOutTradeNo(outTradeNo);
			payTrade.setTransactionId(transactionId);
			payTrade.setBody(body);
			payTrade.setTotalFee(totalFee.longValue());
			payTrade.setTradeState(Status.SUCCESS.name());
			payTrade.setTimeEnd(timeEnd);
			payTrade.setCreateTime(DateUtil.getCurrentDateTime());
			// 插入数据库
			payTradeMapper.insertSelective(payTrade);
		} catch (Exception e) {
			LOG.error("保存苹果内购订单支付信息出错", e);
		}
	}

	/**
	 * 更新订单支付信息
	 * @param outTradeNo 商户订单号
	 * @param transactionId 威富通订单号
	 * @param outTransactionId 第三方订单号
	 * @param tradeState 交易状态
	 * @param timeEnd 订单完成时间
	 */
	public void updateTrade(String outTradeNo, String transactionId, String outTransactionId, String tradeState, String timeEnd) {
		try {
			// 装载参数
			PayTrade payTrade = new PayTrade();
			payTrade.setOutTradeNo(outTradeNo);
			payTrade.setTransactionId(transactionId);
			payTrade.setOutTransactionId(outTransactionId);
			payTrade.setTradeState(tradeState);
			payTrade.setTimeEnd(timeEnd);
			payTrade.setUpdateTime(DateUtil.getCurrentDateTime());
			// 插入数据库
			payTradeMapper.updateByPrimaryKeySelective(payTrade);
		} catch (Exception e) {
			LOG.error("更新订单支付信息出错", e);
		}
	}

	/**
	 * 查询订单退款信息
	 * @param outRefundNo 商户退款单号
	 * @return
	 */
	public PayRefund searchRefund(String outRefundNo) {
		PayRefund payRefund = null;
		try {
			// 查询数据库获取订单信息，该查询语句已使用MyBatis3缓存
			payRefund = payRefundMapper.selectByPrimaryKey(outRefundNo);
		} catch (Exception e) {
			LOG.error("查询订单退款信息出错", e);
		}
		return payRefund;
	}

	/**
	 * 保存订单退款信息
	 * @param outTradeNo 商户订单号
	 * @param outRefundNo 商户退款单号
	 * @param refundFee 退款金额
	 */
	public void saveRefund(String outTradeNo, String outRefundNo, Integer refundFee) {
		try {
			// 装载参数
			PayRefund payRefund = new PayRefund();
			payRefund.setOutRefundNo(outRefundNo);
			payRefund.setOutTradeNo(outTradeNo);
			payRefund.setRefundFee(refundFee.longValue());
			payRefund.setRefundChannel(WxapyRefundChannel.ORIGINAL);
			payRefund.setCreateTime(DateUtil.getCurrentDateTime());
			// 插入数据库
			payRefundMapper.insertSelective(payRefund);
		} catch (Exception e) {
			LOG.error("保存订单退款信息出错", e);
		}
	}

	/**
	 * 更新订单退款信息
	 * @param outRefundNo 商户退款单号
	 * @param refundId 威富通退款单号
	 * @param refundStatus 退款状态
	 * @param refundTime 退款时间
	 */
	public void updateRefund(String outRefundNo, String refundId, String refundStatus, String refundTime) {
		try {
			// 装载参数
			PayRefund payRefund = new PayRefund();
			payRefund.setOutRefundNo(outRefundNo);
			payRefund.setRefundId(refundId);
			payRefund.setRefundStatus(refundStatus);
			payRefund.setRefundTime(refundTime);
			payRefund.setUpdateTime(DateUtil.getCurrentDateTime());
			// 插入数据库
			payRefundMapper.updateByPrimaryKeySelective(payRefund);
		} catch (Exception e) {
			LOG.error("更新订单退款信息出错", e);
		}
	}
}