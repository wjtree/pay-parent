package com.app.pay.dao;

import java.util.List;

import com.app.pay.entity.generate.PayTrade;

public interface PayTradeMapper {
	int deleteByPrimaryKey(String outTradeNo);

	int insert(PayTrade record);

	int insertSelective(PayTrade record);

	PayTrade selectByPrimaryKey(String outTradeNo);

	List<PayTrade> selectByPage();

	int updateByPrimaryKeySelective(PayTrade record);

	int updateByPrimaryKey(PayTrade record);
}