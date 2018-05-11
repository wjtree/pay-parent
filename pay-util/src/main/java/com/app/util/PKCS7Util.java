package com.app.util;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * <p>功 能：PKCS7算法的加解密工具类</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年6月28日 下午2:57:44</p>
 * @author 王建
 * @version 1.0
 */
public class PKCS7Util {
    /** 加解密位数 */
    private static int BLOCK_SIZE = 32;

    /**
     * 获得对明文进行补位填充的字节
     * @param count 需要进行填充补位操作的明文字节个数
     * @return 补齐用的字节数组
     * @throws Exception
     */
    public static byte[] encode(int count) throws Exception {
        // 计算需要填充的位数
        int amountToPad = BLOCK_SIZE - (count % BLOCK_SIZE);
        amountToPad = amountToPad == 0 ? BLOCK_SIZE : amountToPad;

        // 获得补位所用的字符
        char padChr = chr(amountToPad);

        String tmp = new String();
        for (int index = 0; index < amountToPad; index++)
            tmp += padChr;
        return tmp.getBytes(StandardCharsets.UTF_8.name());
    }

    /**
     * 删除解密后明文的补位字符
     * @param decrypted 解密后的明文
     * @return 删除补位字符后的明文
     * @throws Exception
     */
    public static byte[] decode(byte[] decrypted) throws Exception {
        int pad = (int) decrypted[decrypted.length - 1];
        pad = (pad < 1 || pad > 32) ? 0 : pad;

        return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
    }

    /**
     * 将数字转化成ASCII码对应的字符，用于对明文进行补码
     * @param a 需要转化的数字
     * @return 转化得到的字符
     */
    private static char chr(int a) {
        byte target = (byte) (a & 0xFF);
        return (char) target;
    }
}