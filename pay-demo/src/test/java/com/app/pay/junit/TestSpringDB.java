package com.app.pay.junit;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.app.core.AbstractTranTestCase;
import com.app.pay.dao.PayTradeMapper;
import com.app.pay.entity.generate.PayTrade;
import com.github.pagehelper.PageHelper;

public class TestSpringDB extends AbstractTranTestCase {
	@Autowired
	private PayTradeMapper mapper;

	@Test
	public void test() throws Exception {
		PageHelper.startPage(0, 3);
		List<PayTrade> trades = mapper.selectByPage();
		System.out.println("========================");
		System.out.println(JSON.toJSONString(trades));
		System.out.println("========================");
	}
}