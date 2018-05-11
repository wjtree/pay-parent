package com.app.code;

/**
 * <p>功 能：逻辑状态（成功，失败，错误）枚举</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年6月13日 下午6:06:11</p>
 *
 * @author 王建
 * @version 1.0
 */
public enum Status {
    SUCCESS("0"), FAIL("-1"), ERROR("1");

    /**
     * 状态码
     */
    private String status;

    private Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}