package com.app.util;

import com.app.code.Extension;
import com.app.code.Symbol;
import com.google.common.collect.Maps;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.BuilderParameters;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>功 能：属性文件读写工具类</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年6月5日 下午3:52:29</p>
 * @author 王建
 * @version 1.0
 */
public class PropUtil {
	private static final Logger LOG = LogManager.getLogger(PropUtil.class);

	/** 属性文件相对路径 */
	private static final String DEFAULT_PROP_ROOT = "prop";
	/** 属性文件名称，移除后缀名".properties" */
	private static final String DEFAULT_PROP_NAME = "config";

	/** 存放属性文件集合 */
	private static ConcurrentMap<String, Configuration> propMap = Maps.newConcurrentMap();

	/**
	 * 获取属性文件完整路径
	 * @param propRoot 存放属性文件的目录
	 * @param propName 属性文件名称
	 * @return
	 * @throws Exception
	 */
	private static URL getPropUrl(final String propRoot, final String propName) {
		String path = null;

		if (StringUtils.isBlank(propRoot))
			path = StringUtils.join(propName, Extension.PROPERTIES.getExtension());
		else
			path = StringUtils.join(propRoot, Symbol.SLASH.getSymbol(), propName, Extension.PROPERTIES.getExtension());

		return PropUtil.class.getClassLoader().getResource(path);
	}

	/**
	 * 通过Apache Configura组件读取属性文件
	 * @param propRoot 存放属性文件的目录
	 * @param propName 属性文件名称
	 * @return
	 * @throws Exception
	 */
	private static Configuration processConfig(final String propRoot, final String propName) {
		Configuration config = null;
		try {
			// 获取属性文件完整路径
			URL url = getPropUrl(propRoot, propName);
			// 读取属性文件的配置
			BuilderParameters buildParam = new Parameters().properties().setURL(url).setEncoding(StandardCharsets.UTF_8.name());
			// 加载属性文件
			FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
					.configure(buildParam);
			// 获取属性集合
			config = builder.getConfiguration();
		} catch (Exception e) {
			LOG.error("读取属性文件出错", e);
		}
		return config;
	}

	/**
	 * 获取属性文件集合
	 * @param propRoot 存放属性文件的目录
	 * @param propName 属性文件名称
	 * @return
	 * @throws Exception
	 */
	private static Configuration getConfig(final String propRoot, final String propName) {
		Configuration config = null;
		// 组装缓存键
		String key = StringUtils.joinWith(Symbol.UNDERSCORE.getSymbol(), propRoot, propName);
		if (propMap.containsKey(key)) {
			// 已经加载过该属性文件
			config = propMap.get(key);
		} else {
			// 初始化属性文件管理器
			config = processConfig(propRoot, propName);
			// 放入集合
			propMap.put(key, config);
		}
		return config;
	}

	/**
	 * 获取默认属性文件中的键值对
	 * @param key 属性键名
	 * @param cls 属性的数据类型
	 * @return
	 * @throws Exception
	 */
	public static <T> T get(String key, Class<T> cls) {
		return getConfig(DEFAULT_PROP_ROOT, DEFAULT_PROP_NAME).get(cls, key);
	}

	/**
	 * 获取默认属性文件中的键值对，指定属性默认值
	 * @param key 属性键名
	 * @param defaultValue 属性默认值
	 * @param cls 属性的数据类型
	 * @return
	 * @throws Exception
	 */
	public static <T> T get(String key, T defaultValue, Class<T> cls) {
		return getConfig(DEFAULT_PROP_ROOT, DEFAULT_PROP_NAME).get(cls, key, defaultValue);
	}

	/**
	 * 获取指定属性文件中的键值对
	 * @param propRoot 属性文件目录
	 * @param propName 属性文件名称
	 * @param key 属性键名
	 * @param cls 属性的数据类型
	 * @return
	 * @throws Exception
	 */
	public static <T> T get(String propRoot, String propName, String key, Class<T> cls) {
		return getConfig(propRoot, propName).get(cls, key);
	}

	/**
	 * 获取指定属性文件中的键值对，指定属性默认值
	 * @param propRoot 属性文件目录
	 * @param propName 属性文件名称
	 * @param key 属性键名
	 * @param defaultValue 属性默认值
	 * @param cls 属性的数据类型
	 * @return
	 * @throws Exception
	 */
	public static <T> T get(String propRoot, String propName, String key, T defaultValue, Class<T> cls) {
		return getConfig(propRoot, propName).get(cls, key, defaultValue);
	}

	/**
	 * 获取默认属性文件中的键值对
	 * @param key 属性键名
	 * @return
	 * @throws Exception
	 */
	public static String getString(String key) {
		return getConfig(DEFAULT_PROP_ROOT, DEFAULT_PROP_NAME).getString(key);
	}

	/**
	 * 获取默认属性文件中的键值对，指定属性默认值
	 * @param key 属性键名
	 * @param defaultValue 属性默认值
	 * @return
	 * @throws Exception
	 */
	public static String getString(String key, String defaultValue) {
		return getConfig(DEFAULT_PROP_ROOT, DEFAULT_PROP_NAME).getString(key, defaultValue);
	}

	/**
	 * 获取指定属性文件中的键值对
	 * @param propRoot 属性文件目录
	 * @param propName 属性文件名称
	 * @param key 属性键名
	 * @return
	 * @throws Exception
	 */
	public static String getString(String propRoot, String propName, String key) {
		return getConfig(propRoot, propName).getString(key);
	}

	/**
	 * 获取指定属性文件中的键值对，指定属性默认值
	 * @param propRoot 属性文件目录
	 * @param propName 属性文件名称
	 * @param key 属性键名
	 * @param defaultValue 属性默认值
	 * @return
	 * @throws Exception
	 */
	public static String getString(String propRoot, String propName, String key, String defaultValue) {
		return getConfig(propRoot, propName).getString(key, defaultValue);
	}

	/**
	 * 获取默认属性文件中的键值对
	 * @param key 属性键名
	 * @return
	 * @throws Exception
	 */
	public static Integer getInteger(String key) {
		return getConfig(DEFAULT_PROP_ROOT, DEFAULT_PROP_NAME).getInteger(key, null);
	}

	/**
	 * 获取默认属性文件中的键值对，指定属性默认值
	 * @param key 属性键名
	 * @param defaultValue 属性默认值
	 * @return
	 * @throws Exception
	 */
	public static Integer getInteger(String key, Integer defaultValue) {
		return getConfig(DEFAULT_PROP_ROOT, DEFAULT_PROP_NAME).getInteger(key, defaultValue);
	}

	/**
	 * 获取指定属性文件中的键值对
	 * @param propRoot 属性文件目录
	 * @param propName 属性文件名称
	 * @param key 属性键名
	 * @return
	 * @throws Exception
	 */
	public static Integer getInteger(String propRoot, String propName, String key) {
		return getConfig(propRoot, propName).getInteger(key, null);
	}

	/**
	 * 获取指定属性文件中的键值对，指定属性默认值
	 * @param propRoot 属性文件目录
	 * @param propName 属性文件名称
	 * @param key 属性键名
	 * @param defaultValue 属性默认值
	 * @return
	 * @throws Exception
	 */
	public static Integer getInteger(String propRoot, String propName, String key, Integer defaultValue) {
		return getConfig(propRoot, propName).getInteger(key, defaultValue);
	}
}