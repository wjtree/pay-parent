package com.app.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>功 能：数组操作工具类</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年3月28日 上午11:18:30</p>
 * @author 王建
 * @version 1.0
 */
public class ArrayUtil {
    /**
     * 移除每个数组元素中指定字符
     * @param strings
     * @param separator
     * @return
     * @throws Exception
     */
    public static String[] removeChar(final String[] strings, final String separator) throws Exception {
        String[] rsArr = null;
        if (ArrayUtils.isNotEmpty(strings)) {
            int len = strings.length;
            // 实例化返回数组
            rsArr = new String[len];
            // 循环移除每个旧数组元素中的指定字符
            for (int i = 0; i < len; i++)
                rsArr[i] = StringUtils.remove(strings[i], separator);
        }
        return rsArr;
    }
}