package com.app.util;

import com.app.code.Symbol;
import com.app.core.VariableHolder;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;

import java.util.Arrays;

/**
 * <p>功 能：字符串操作工具类</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年3月28日 上午9:39:06</p>
 *
 * @author 王建
 * @version 1.0
 */
public class StringUtil {
    /**
     * 判断字符串是否为JSON格式
     *
     * @param str
     * @return
     */
    public static boolean isJson(final String str) {
        return str.startsWith(Symbol.LEFT_CURLY_BRACE.getSymbol()) && str.endsWith(Symbol.RIGHT_CURLY_BRACE.getSymbol());
    }

    /**
     * 判断字符串是否为XML格式
     *
     * @param str
     * @return
     */
    public static boolean isXml(final String str) {
        return str.startsWith("<xml>") && str.endsWith("</xml>");
    }

    /**
     * 获取项目部署的根路径
     *
     * @return
     */
    public static String getProjectPath() {
        String path = StringUtil.class.getResource("/").getPath();
        // 去掉前面的"/"
        path = StringUtils.substringAfter(path, Symbol.SLASH.getSymbol());
        // 去掉结尾的classes
        path = StringUtils.substringBefore(path, "classes");
        return path;
    }

    /**
     * 将下划线分割的字符串转换为首字母大写输出
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static String convertCamel(final String str) throws Exception {
        StringBuilder sb = VariableHolder.getStringBuilder();
        String[] arr = StringUtils.split(str, Symbol.UNDERSCORE.getSymbol());
        for (String string : arr) {
            if (StringUtils.isNotBlank(string))
                sb.append(StringUtils.capitalize(string));
        }
        return StringUtils.uncapitalize(sb.toString());
    }

    /**
     * 使用随机数算法获取指定位数的随机字符串
     *
     * @param count
     * @return
     */
    public static String getNonceStr(int count) {
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
        return generator.generate(count);
    }

    /**
     * 将多个字符串参数进行字典排序，并拼接为一个字符串返回
     *
     * @param params
     * @return
     * @throws Exception
     */
    public static String sortParams(final String... params) throws Exception {
        // 将参数组装成数组
        String[] arr = ArrayUtils.toArray(params);
        // 对数组进行字典排序，默认升序
        Arrays.sort(arr);
        // 将排序后的结果拼接为字符串返回
        return StringUtils.join(arr);
    }
}