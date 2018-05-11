package com.app.code;

/**
 * <p>功 能：文件扩展名枚举</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年6月13日 下午5:23:53</p>
 *
 * @author 王建
 * @version 1.0
 */
public enum Extension {
    JS(".js"), JSP(".jsp"), PROPERTIES(".properties"), JSON(".json"), XML(".xml"), TXT(".txt"), XLSX(".xlsx"), CSV(".csv");

    private String extension;

    private Extension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}