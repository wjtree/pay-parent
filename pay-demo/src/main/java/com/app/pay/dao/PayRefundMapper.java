package com.app.pay.dao;

import com.app.pay.entity.generate.PayRefund;

public interface PayRefundMapper {
	int deleteByPrimaryKey(String outRefundNo);

	int insert(PayRefund record);

	int insertSelective(PayRefund record);

	PayRefund selectByPrimaryKey(String outRefundNo);

	int updateByPrimaryKeySelective(PayRefund record);

	int updateByPrimaryKey(PayRefund record);
}