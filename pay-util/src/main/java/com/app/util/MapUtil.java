package com.app.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;
import com.app.code.Symbol;

/**
 * <p>功 能：Map操作工具类</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年3月16日 上午11:13:47</p>
 * @author 王建
 * @version 1.0
 */
public class MapUtil {
	/**
	 * 将 Map<String, String[]>转换为Map<String, String>，用逗号分隔
	 * @param paramMap
	 * @return
	 */
	public static Map<String, String> arrayToString(final Map<String, String[]> paramMap) throws Exception {
		// 参数检查
		Assert.notEmpty(paramMap, "参数Map<String, String[]>不能为空");
		// 循环转换每个键值对
		Map<String, String> rsMap = new HashMap<String, String>();
		for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
			// 跳过值为空的元素
			if (ArrayUtils.isNotEmpty(entry.getValue()))
				rsMap.put(entry.getKey(), StringUtils.join(entry.getValue(), Symbol.COMMA.getSymbol()));
		}
		return rsMap;
	}

	/**
	 * 将 Map<String, String[]>转换为JSONObject
	 * @param paramMap
	 * @return
	 */
	public static JSONObject arrayToJson(final Map<String, String[]> paramMap) throws Exception {
		// 参数检查
		if (MapUtils.isEmpty(paramMap))
			return null;

		// 循环转换每个键值对
		JSONObject jsonObj = new JSONObject();
		for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
			// 跳过值为空的元素
			if (ArrayUtils.isNotEmpty(entry.getValue()))
				jsonObj.put(entry.getKey(), StringUtils.join(entry.getValue(), Symbol.COMMA.getSymbol()));
		}
		return jsonObj;
	}

	/**
	 * 将 Map<String, String[]>转换为JavaBean
	 * @param paramMap
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T arrayToBean(final Map<String, String[]> paramMap, Class<T> clazz) throws Exception {
		// 将Map转换为JSON
		JSONObject jsonObj = arrayToJson(paramMap);
		// 将Json转换为JavaBean
		return jsonObj != null ? jsonObj.toJavaObject(clazz) : null;
	}
}