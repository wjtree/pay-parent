package com.app.pay.util;

import com.app.code.Symbol;
import com.app.core.VariableHolder;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <p>功 能：加密与签名工具类</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月15日 下午3:48:38</p>
 * @author 王建
 * @version 1.0
 */
public class SignUtil {
	/**
	 * 获取签名
	 * @param xmlStr 签名对象XML
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String getSignature(final String xmlStr, final String key) throws Exception {
		// 1.将非空参数值的参数按照参数名ASCII码从小到大排序（字典序）
		return doSignature(xmlToSortedMap(xmlStr), key);
	}

	/**
	 * 比较签名
	 * @param xmlStr
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static boolean checkSignature(final String xmlStr, final String key) throws Exception {
		// 1.将非空参数值的参数按照参数名ASCII码从小到大排序（字典序）
		SortedMap<String, Object> map = xmlToSortedMap(xmlStr);
		// 2.获取响应或通知中的签名字符串
		String oldSign = MapUtils.getString(map, "sign");
		if (StringUtils.isBlank(oldSign))
			return true;
		// 3.获取本地重新计算的签名字符串
		String newSign = doSignature(map, key);
		// 4.比较收到的签名和重新计算的签名是否一致
		return StringUtils.equalsIgnoreCase(oldSign, newSign);
	}

	/**
	 * 获取签名
	 * @param sortMap
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static String doSignature(final Map<String, Object> sortMap, final String key) throws Exception {
		// 1.使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串
		String queryStr = mapToQueryString(sortMap, "sign");
		// 2.最后拼接上交易密钥key
		queryStr += ("&key=" + key);
		// 3.对最后获取的字符串进行MD5加密，并将结果转换为大写
		return DigestUtils.md5Hex(queryStr.getBytes(StandardCharsets.UTF_8.name())).toUpperCase();
	}

	/**
	 * 将XML转换为SortedMap
	 * @param xmlStr
	 * @return
	 */
	public static SortedMap<String, Object> xmlToSortedMap(final String xmlStr) throws Exception {
		// 参数检查
		Assert.hasText(xmlStr, "参数xmlStr不能为空");
		// 解析XML元素
		@SuppressWarnings("unchecked")
		List<Element> elements = DocumentHelper.parseText(xmlStr).getRootElement().elements();
		// 转换为MAP
		SortedMap<String, Object> sortMap = null;
		if (CollectionUtils.isNotEmpty(elements)) {
			// SortedMap 默认按字典序升序排列
			sortMap = new TreeMap<String, Object>();
			for (Element element : elements) {
				// 排除元素内容为空的字段
				if (StringUtils.isBlank(element.getTextTrim()))
					continue;
				sortMap.put(element.getName(), element.getTextTrim());
			}
		}
		return sortMap;
	}

	/**
	 * 将Map键值对拼接成QueryString字符串
	 * @param map
	 * @param delArr map集合中需要排除的字段
	 * @return
	 */
	public static String mapToQueryString(final Map<String, Object> map, final String... delArr) throws Exception {
		// 参数检查
		Assert.notEmpty(map, "参数map不能为空");
		// 获取StringBuilder
		StringBuilder sb = VariableHolder.getStringBuilder();
		// 组装QueryString
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			// 排除指定参数
			if (ArrayUtils.contains(delArr, entry.getKey()))
				continue;
			// 按自然排序拼接QueryString
			sb.append(entry.getKey()).append(Symbol.EQUALS.getSymbol()).append(entry.getValue()).append(Symbol.AMPERSAND.getSymbol());
		}

		// 转换StringBuilder
		String queryStr = null;
		if (sb.length() != 0) {
			// 移除最后一个“&”符号
			sb.setLength(sb.length() - 1);
			queryStr = sb.toString();
		}
		return queryStr;
	}
}