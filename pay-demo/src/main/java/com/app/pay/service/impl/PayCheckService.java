package com.app.pay.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pay.code.SwiftTradeStatus;
import com.app.pay.entity.generate.PayRefund;
import com.app.pay.entity.generate.PayTrade;

/**
 * <p>功 能：第三方支付接口请求与响应参数检查</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月13日 下午12:25:00</p>
 * @author 王建
 * @version 1.0
 */
@Service
public class PayCheckService {
	@Autowired
	private PayDBService payDBService;

	/**
	 * 获取订单的交易类型
	 * <p>比如："pay.weixin.native" 表示 "微信扫码支付"</p>
	 * @param outTradeNo 商户订单号
	 * @return
	 * @throws Exception
	 */
	public String getTradeType(String outTradeNo) throws Exception {
		PayTrade payTrade = payDBService.searchTrade(outTradeNo);
		return payTrade != null ? payTrade.getTradeType() : null;
	}

	/**
	 * 获取订单的交易状态
	 * <p>比如："SUCCESS" 表示 "支付成功"</p>
	 * @param outTradeNo 商户订单号
	 * @return
	 * @throws Exception
	 */
	public String getTradeState(String outTradeNo) throws Exception {
		PayTrade payTrade = payDBService.searchTrade(outTradeNo);
		return payTrade != null ? payTrade.getTradeState() : null;
	}

	/**
	 * 查询订单是否支付成功
	 * @param outTradeNo 商户订单号
	 * @return
	 */
	public boolean isTradeSuccess(String outTradeNo) throws Exception {
		return StringUtils.equalsIgnoreCase(getTradeState(outTradeNo), SwiftTradeStatus.SUCCESS);
	}

	/**
	 * 查询是否存在指定订单号，并且订单号和订单金额都匹配的订单记录
	 * @param outTradeNo
	 * @param totalFee
	 * @return
	 */
	public boolean isMatchTradeAndFee(String outTradeNo, Integer totalFee) throws Exception {
		boolean isMatch = false;
		PayTrade payTrade = payDBService.searchTrade(outTradeNo);
		if (payTrade != null && payTrade.getTotalFee().longValue() == totalFee.longValue())
			isMatch = true;
		return isMatch;
	}

	/**
	 * 查询是否存在指定商户订单号，但是支付类型与参数不一致的订单记录
	 * @param outTradeNo 商户订单号
	 * @param service 接口类型
	 * @return
	 */
	public boolean isNotMatchTradeAndType(String outTradeNo, String service) {
		boolean isNotMatch = false;
		PayTrade payTrade = payDBService.searchTrade(outTradeNo);
		if (payTrade != null && !StringUtils.equalsIgnoreCase(service, payTrade.getTradeType()))
			isNotMatch = true;
		return isNotMatch;
	}

	/**
	 * 查询是否存在订单支付信息
	 * @param outTradeNo 商户订单号
	 * @return
	 * @throws Exception
	 */
	public boolean isExistTrade(String outTradeNo) throws Exception {
		return payDBService.searchTrade(outTradeNo) != null;
	}

	/**
	 * 查询是否存在指定退款单号，但是订单号与参数不一致的订单退款记录
	 * @param outTradeNo 商户订单号
	 * @param outRefundNo 商户退款单号
	 * @return
	 * @throws Exception
	 */
	public boolean isNotMatchTradeAndRefund(String outTradeNo, String outRefundNo) throws Exception {
		boolean isNotMatch = false;
		PayRefund payRefund = payDBService.searchRefund(outRefundNo);
		if (payRefund != null && !StringUtils.equalsIgnoreCase(payRefund.getOutTradeNo(), outTradeNo))
			isNotMatch = true;
		return isNotMatch;
	}

	/**
	 * 查询是否存在订单退款信息
	 * @param outRefundNo 商户退款单号
	 * @return
	 * @throws Exception
	 */
	public boolean isExistRefund(String outRefundNo) throws Exception {
		return payDBService.searchRefund(outRefundNo) != null;
	}
}