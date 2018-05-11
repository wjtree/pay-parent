package com.app.core;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>功 能：字节型数组容器</p>
 * <p>描 述：该类用于将多个字节型数组合并为一个字节型数组</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年6月28日 下午10:34:03</p>
 * @author WangJian
 * @version 1.0
 */
public class ByteGroup {
    /** 字节型集合 */
    private List<Byte> byteContainer = new ArrayList<Byte>();

    /**
     * 将字节型集合转换为字节型数组
     * @return 字节数组
     */
    public byte[] toBytes() {
        byte[] bytes = new byte[byteContainer.size()];

        for (int i = 0; i < byteContainer.size(); i++)
            bytes[i] = byteContainer.get(i);

        return bytes;
    }

    /**
     * 将字节型数组加入到字节型集合中
     * @param bytes
     * @return 该字节型容器对象
     */
    public ByteGroup addBytes(byte[] bytes) {
        for (byte b : bytes)
            byteContainer.add(b);

        // 使用链式写法
        return this;
    }

    /**
     * 返回字节型集合的元素个数
     * @return 字节数量
     */
    public int size() {
        return byteContainer.size();
    }
}