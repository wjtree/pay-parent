package com.app.code;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>功 能：HTTP请求方法枚举</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年6月29日 下午12:30:37</p>
 *
 * @author 王建
 * @version 1.0
 */
public enum HttpMethod {

    GET, HEAD, POST, POST_FORM, PUT, PATCH, DELETE, OPTIONS, TRACE;

    private static final Map<String, HttpMethod> mappings = new HashMap<String, HttpMethod>(8);

    static {
        for (HttpMethod httpMethod : values()) {
            mappings.put(httpMethod.name(), httpMethod);
        }
    }

    public static HttpMethod resolve(String method) {
        return (method != null ? mappings.get(method) : null);
    }

    public boolean matches(String method) {
        return (this == resolve(method));
    }
}