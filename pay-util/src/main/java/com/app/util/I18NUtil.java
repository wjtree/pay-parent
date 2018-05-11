package com.app.util;

import com.app.code.Symbol;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Administrator
 * @version 1.0
 */
public class I18NUtil {
    /**
     * 国际化信息，缓存不同语言类型的国际化属性配置文件
     */
    private static final ConcurrentMap<String, ResourceBundle> MESSAGES = new ConcurrentHashMap<>();

    /**
     * 国际化信息
     *
     * @param key    系统语言类型
     * @param params 属性中待替换的参数
     * @return 属性值
     */
    public static String getMessage(String baseName, String key, Object... params) {
        // 获取系统语言环境
        Locale locale = Locale.getDefault();

        // 根据不同的语言类型，获取对应的属性配置文件
        ResourceBundle message = MESSAGES.computeIfAbsent(getLangKey(locale), k -> getLangValue(baseName, locale));

        // 获取属性值
        String value = message.containsKey(key) ? message.getString(key) : null;

        // 替换属性值中的占位符
        if (StringUtils.isNotBlank(value) && ArrayUtils.isNotEmpty(params))
            value = String.format(value, params);

        return value;
    }

    /**
     * 清除国际化信息
     */
    public static void flushMessage() {
        MESSAGES.clear();
    }

    /**
     * 根据语言环境，将国家和语言类型组装成 language_country 形式
     *
     * @param locale 语言环境
     * @return language_country
     */
    private static String getLangKey(Locale locale) {
        // 获取系统语言环境对应的国家和语言类型
        String language = locale.getLanguage();
        String country = locale.getCountry();

        // 将国家和语言类型组装成 language_country 形式
        return StringUtils.joinWith(Symbol.UNDERSCORE.getSymbol(), language, country);
    }

    /**
     * 根据属性文件相对路径和系统语言环境，获取属性文件加载对象ResourceBundle
     *
     * @param baseName 属性文件相对路径
     * @param locale   系统语言环境
     * @return ResourceBundle
     */
    private static ResourceBundle getLangValue(String baseName, Locale locale) {
        return ResourceBundle.getBundle(baseName, locale);
    }
}
