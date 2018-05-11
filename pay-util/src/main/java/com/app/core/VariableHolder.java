package com.app.core;

/**
 * <p>功 能：多线程变量管理器</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年1月22日 上午10:31:16</p>
 * @author 王建
 * @version 1.0
 */
public class VariableHolder {
    /** StringBuilder 变量管理器 */
    private static ThreadLocal<StringBuilder> builder = new ThreadLocal<StringBuilder>() {
        protected StringBuilder initialValue() {
            return new StringBuilder(256);
        }
    };

    /**
     * 获取空的 StringBuilder
     * @return
     */
    public static StringBuilder getStringBuilder() {
        // 清空 StringBuilder 的内容
        builder.get().setLength(0);
        return builder.get();
    }
}