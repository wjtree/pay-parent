package com.app.pay.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.app.pay.entity.dto.PayBack;
import com.app.pay.service.PayProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * <p>功 能：第三方支付接口Controller</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月9日 下午6:27:34</p>
 *
 * @author 王建
 * @version 1.0
 */
@Api(description = "第三方支付接口")
@RestController
@RequestMapping(value = "pay", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
public class PayController {
    private static final Logger LOG = LogManager.getLogger(PayController.class);

    @Autowired
    private PayProvider payProvider;

    /**
     * 交易支付接口
     *
     * @param modelMap
     * @param service        支付类型
     * @param outTradeNo     商户订单号
     * @param body           商品名称
     * @param totalFee       总金额
     * @param expireTime     订单有效期（分钟），默认120分钟
     * @param returnUrl      WAP支付回跳地址
     * @param spbillCreateIp 支付用户IP
     * @return
     */
    @ApiOperation(value = "交易支付接口")
    @PostMapping(value = "unifiedTradePay")
    public Object unifiedTradePay(ModelMap modelMap, @RequestParam(value = "service") String service, @RequestParam(value = "outTradeNo") String outTradeNo, @RequestParam(value = "body") String body, @RequestParam(value = "totalFee") Integer totalFee, @RequestParam(value = "expireTime", required = false, defaultValue = "120") Integer expireTime, @RequestParam(value = "returnUrl", required = false) String returnUrl, @RequestParam(value = "spbillCreateIp", required = false) String spbillCreateIp) {
        // 组装参数
        JSONObject json = new JSONObject();
        json.put("service", service);
        json.put("outTradeNo", outTradeNo);
        json.put("body", body);
        json.put("totalFee", totalFee);
        json.put("expireTime", expireTime);
        json.put("returnUrl", returnUrl);
        json.put("spbillCreateIp", spbillCreateIp);
        // 请求Dubbo接口
        PayBack obj = payProvider.unifiedTradePay(json.toString());
        if (LOG.isInfoEnabled())
            LOG.info("交易支付接口请求结果：{}", JSON.toJSONString(obj));
        return obj;
    }

    /**
     * 交易查询接口
     *
     * @param modelMap
     * @param outTradeNo 商户订单号
     * @return
     */
    @ApiOperation(value = "交易查询接口")
    @PostMapping(value = "unifiedTradeQuery")
    public Object unifiedTradeQuery(ModelMap modelMap, @RequestParam(value = "outTradeNo") String outTradeNo) {
        // 组装参数
        JSONObject json = new JSONObject();
        json.put("outTradeNo", outTradeNo);
        // 请求Dubbo接口
        PayBack obj = payProvider.unifiedTradeQuery(json.toString());
        if (LOG.isInfoEnabled())
            LOG.info("交易查询接口请求结果：{}", JSON.toJSONString(obj));
        return obj;
    }

    /**
     * 交易关闭接口
     *
     * @param modelMap
     * @param outTradeNo 商户订单号
     * @return
     */
    @ApiOperation(value = "交易关闭接口")
    @PostMapping(value = "unifiedTradeClose")
    public Object unifiedTradeClose(ModelMap modelMap, @RequestParam(value = "outTradeNo") String outTradeNo) {
        // 组装参数
        JSONObject json = new JSONObject();
        json.put("outTradeNo", outTradeNo);
        // 请求Dubbo接口
        PayBack obj = payProvider.unifiedTradeClose(json.toString());
        if (LOG.isInfoEnabled())
            LOG.info("交易关闭接口请求结果：{}", JSON.toJSONString(obj));
        return obj;
    }

    /**
     * 交易退款接口
     *
     * @param modelMap
     * @param outTradeNo  商户订单号
     * @param outRefundNo 商户退款单号
     * @param totalFee    订单金额
     * @param refundFee   退款金额
     * @return
     */
    @ApiOperation(value = "交易退款接口")
    @PostMapping(value = "unifiedTradeRefund")
    public Object unifiedTradeRefund(ModelMap modelMap, @RequestParam(value = "outTradeNo") String outTradeNo, @RequestParam(value = "outRefundNo") String outRefundNo, @RequestParam(value = "totalFee") Integer totalFee, @RequestParam(value = "refundFee") Integer refundFee) {
        // 组装参数
        JSONObject json = new JSONObject();
        json.put("outTradeNo", outTradeNo);
        json.put("outRefundNo", outRefundNo);
        json.put("totalFee", totalFee);
        json.put("refundFee", refundFee);
        // 请求Dubbo接口
        PayBack obj = payProvider.unifiedTradeRefund(json.toString());
        if (LOG.isInfoEnabled())
            LOG.info("交易退款接口请求结果：{}", JSON.toJSONString(obj));
        return obj;
    }

    /**
     * 交易退款查询接口
     *
     * @param modelMap
     * @param outTradeNo  商户订单号
     * @param outRefundNo 商户退款单号
     * @return
     */
    @ApiOperation(value = "交易退款查询接口")
    @PostMapping(value = "unifiedTradeRefundQuery")
    public Object unifiedTradeRefundQuery(ModelMap modelMap, @RequestParam(value = "outTradeNo") String outTradeNo, @RequestParam(value = "outRefundNo") String outRefundNo) {
        // 组装参数
        JSONObject json = new JSONObject();
        json.put("outTradeNo", outTradeNo);
        json.put("outRefundNo", outRefundNo);
        // 请求Dubbo接口
        PayBack obj = payProvider.unifiedTradeRefundQuery(json.toString());
        if (LOG.isInfoEnabled())
            LOG.info("交易退款查询接口请求结果：{}", JSON.toJSONString(obj));
        return obj;
    }

    /**
     * 下载对账单接口
     *
     * @param modelMap
     * @param billDate 账单日期
     * @param billType 账单类型
     * @return
     */
    @ApiOperation(value = "下载对账单接口")
    @PostMapping(value = "unifiedDownloadBill")
    public Object unifiedDownloadBill(ModelMap modelMap, @RequestParam(value = "billDate") String billDate, @RequestParam(value = "billType") String billType) {
        // 组装参数
        JSONObject json = new JSONObject();
        json.put("billDate", billDate);
        json.put("billType", billType);
        // 请求Dubbo接口
        Object obj = payProvider.unifiedDownloadBill(json.toString());
        if (LOG.isInfoEnabled())
            LOG.info("下载对账单接口请求结果：{}", JSON.toJSONString(obj));
        return obj;
    }
}