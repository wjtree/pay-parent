package com.app.util;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>功 能：JAVABEAN操作工具类</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年4月11日 下午6:48:51</p>
 * @author 王建
 * @version 1.0
 */
public class BeanUtil {
	/**
	 * 将Map中的键值对按照驼峰规则转换后填充到JavaBean属性中
	 * @param bean
	 * @param map
	 * @throws Exception
	 */
	public static void populateCamel(Object bean, Map<String, ? extends Object> map) throws Exception {
		// 迭代填充Map键值对到JavaBean中
		for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
			// 因为Map中可以有一个Key名称为空，所以要加判断
			if (StringUtils.isNotBlank(entry.getKey()))
				BeanUtils.setProperty(bean, StringUtil.convertCamel(entry.getKey()), entry.getValue());
		}
	}

	/**
	 * 将指定JAVABEAN的属性加载到目标JavaBean中
	 * @param source
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public static <T> T convertBean(Object source, Class<T> target) throws Exception {
		T t = target.newInstance();
		BeanUtils.copyProperties(t, source);
		return t;
	}
}