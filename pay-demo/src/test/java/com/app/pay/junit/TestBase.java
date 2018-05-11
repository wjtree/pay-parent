package com.app.pay.junit;

import com.alibaba.fastjson.JSON;
import com.app.pay.entity.dto.WxpayRefundRequest;
import com.app.pay.entity.dto.WxpayRefundResponse;
import com.app.pay.util.WxpayUtil;
import org.junit.Test;

public class TestBase {
    @Test
    public void test() throws Exception {
        WxpayRefundRequest model = new WxpayRefundRequest();
        model.setOutTradeNo("today112");
        model.setOutRefundNo("today112ref");
        model.setTotalFee(1);
        model.setRefundFee(1);

        WxpayRefundResponse resp = WxpayUtil.wxpayTradeRefund(model);
        System.out.println("==========================");
        System.out.println(JSON.toJSONString(resp));
        System.out.println("==========================");
    }
}