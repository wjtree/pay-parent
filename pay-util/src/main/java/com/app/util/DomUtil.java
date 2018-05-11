package com.app.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSON;

/**
 * <p>功 能：Dom4j操作工具类</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年3月24日 下午4:01:03</p>
 * @author 王建
 * @version 1.0
 */
public class DomUtil {
	/**
	 * 使用Dom4j将xml转换为json
	 * @param xmlStr
	 * @return
	 * @throws Exception
	 */
	public static String xmlToJson(String xmlStr) throws Exception {
		String jsonStr = null;
		if (StringUtils.isNotBlank(xmlStr)) {
			// 获取xml根节点
			Element root = DocumentHelper.parseText(xmlStr).getRootElement();
			// 返回类型未知，已知DOM结构的时候可以强制转换
			jsonStr = JSON.toJSONString(parse(root));
		}
		return jsonStr;
	}

	/**
	 * Dom4j节点解析
	 * @param root
	 * @return
	 * @throws Exception
	 */
	public static Object parse(Element root) throws Exception {
		List<?> elements = root.elements();
		if (elements.size() == 0) {
			// 没有子元素
			return root.getTextTrim();
		} else {
			// 有子元素
			String prev = null;
			boolean guess = true; // 默认按照数组处理

			Iterator<?> iterator = elements.iterator();
			while (iterator.hasNext()) {
				Element elem = (Element) iterator.next();
				String name = elem.getName();
				if (prev == null) {
					prev = name;
				} else {
					guess = name.equals(prev);
					break;
				}
			}
			iterator = elements.iterator();
			if (guess) {
				List<Object> data = new ArrayList<Object>();
				while (iterator.hasNext()) {
					Element elem = (Element) iterator.next();
					((List<Object>) data).add(parse(elem));
				}
				return data;
			} else {
				Map<String, Object> data = new HashMap<String, Object>();
				while (iterator.hasNext()) {
					Element elem = (Element) iterator.next();
					((Map<String, Object>) data).put(elem.getName(), parse(elem));
				}
				return data;
			}
		}
	}
}