package com.app.util;

import com.app.code.CryptType;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * <p>功 能：AES加解密工具类</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年6月27日 上午9:32:05</p>
 *
 * @author 王建
 * @version 1.0
 */
public class AesUtil {
    private static Logger LOG = LogManager.getLogger(AesUtil.class);

    /**
     * 生成AES加解密密钥使用的随机源位数
     */
    private static int KEY_SIZE = 128;

    /**
     * AES加解密模式：AES/CBC/NoPadding
     */
    public static String AES_CBC_NOPADDING = "AES/CBC/NoPadding";

    /**
     * 生成AES密钥
     *
     * @param rule AES加解密规则
     * @return
     * @throws Exception
     */
    private static SecretKey generateKey(String rule) throws Exception {
        // 1.构造密钥生成器，指定为AES算法，不区分大小写
        KeyGenerator keyGen = KeyGenerator.getInstance(CryptType.AES.name());
        // 2.使用128位的随机源和AES加解密规则，初始化密钥生成器
        keyGen.init(KEY_SIZE, new SecureRandom(rule.getBytes(StandardCharsets.UTF_8.name())));

        // 3.产生原始对称密钥
        SecretKey origi_key = keyGen.generateKey();
        // 4.获得原始对称密钥的字节数组
        byte[] raw = origi_key.getEncoded();

        // 5.根据字节数组生成AES密钥
        SecretKey key = new SecretKeySpec(raw, CryptType.AES.name());
        return key;
    }

    /**
     * 生成AES密码器
     *
     * @param rule   AES加解密规则
     * @param opmode 密码器操作类型，加密或解密
     * @return
     * @throws Exception
     */
    private static Cipher generateCipher(String rule, int opmode) throws Exception {
        // 1.生成AES密钥
        SecretKey key = generateKey(rule);

        // 2.生成密码器，设置解密模式为AES模式
        Cipher cipher = Cipher.getInstance(CryptType.AES.name());
        // 3.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
        cipher.init(opmode, key);

        return cipher;
    }

    /**
     * 生成AES-CBC模式密码器
     *
     * @param key    密钥规则
     * @param iv     密钥参数
     * @param opmode 密码器操作类型，加密或解密
     * @return
     * @throws Exception
     */
    private static Cipher generateCipherCBC(byte[] key, byte[] iv, int opmode) throws Exception {
        // 1.生成AES密钥
        SecretKeySpec key_spec = new SecretKeySpec(key, CryptType.AES.name());
        // 2.生成AES填充参数
        IvParameterSpec iv_spec = new IvParameterSpec(iv);

        // 3.生成密码器，设置解密模式为AES的CBC模式
        Cipher cipher = Cipher.getInstance(AES_CBC_NOPADDING);
        // 4.初始化密码器
        cipher.init(opmode, key_spec, iv_spec);

        return cipher;
    }

    /**
     * AES加密
     * <p>加密步骤：</p>
     * <ol>
     * <li>构造密钥生成器</li>
     * <li>根据rule规则初始化密钥生成器</li>
     * <li>产生密钥</li>
     * <li>创建和初始化密码器</li>
     * <li>内容加密</li>
     * <li>返回字符串</li>
     * </ol>
     *
     * @param content 待加密内容
     * @param rule    AES加密规则，凭此生成密钥
     * @return
     */
    public static String encrypt(String content, String rule) {
        String result = null;
        try {
            // 1.生成AES密码器
            Cipher cipher = generateCipher(rule, Cipher.ENCRYPT_MODE);

            // 2.获取明文内容的字节数组
            byte[] bytes = content.getBytes(StandardCharsets.UTF_8.name());

            // 3.使用AES密码器对明文的字节数组加密
            byte[] encrpt_bytes = cipher.doFinal(bytes);

            // 4.将加密后的字节数组转换为字符串
            result = Base64.encodeBase64String(encrpt_bytes);
        } catch (Exception e) {
            LOG.error("AES加密出错", e);
        }
        return result;
    }

    /**
     * AES解密
     * <p>加密步骤：</p>
     * <ol>
     * <li>构造密钥生成器</li>
     * <li>根据rule规则初始化密钥生成器</li>
     * <li>产生密钥</li>
     * <li>创建和初始化密码器</li>
     * <li>将加密后的字符串反纺成byte[]数组</li>
     * <li>将加密内容解密</li>
     * </ol>
     *
     * @param content 待解密内容
     * @param rule    AES加密规则，凭此生成密钥
     * @return
     */
    public static String decrypt(String content, String rule) {
        String result = null;
        try {
            // 1.生成AES密码器
            Cipher cipher = generateCipher(rule, Cipher.DECRYPT_MODE);

            // 2.将密文解码成字节数组
            byte[] bytes = Base64.decodeBase64(content);

            // 3.使用AES密码器对密文的字节数组解密
            byte[] decrypt_bytes = cipher.doFinal(bytes);

            // 4.将解密后的字节数组转换为字符串
            result = new String(decrypt_bytes, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            LOG.error("AES解密出错", e);
        }
        return result;
    }

    /**
     * AES-CBC模式加密
     *
     * @param content 待加密内容
     * @param key     密钥规则
     * @param iv      密钥参数
     * @return
     */
    public static String encryptCBC(String content, byte[] key, byte[] iv) {
        String result = null;
        try {
            // 1.生成AES-CBC模式密码器
            Cipher cipher = generateCipherCBC(key, iv, Cipher.ENCRYPT_MODE);

            // 2.获取明文内容的字节数组
            byte[] bytes = content.getBytes(StandardCharsets.UTF_8.name());

            // 3.使用AES密码器对明文的字节数组加密
            byte[] encrpt_bytes = cipher.doFinal(bytes);

            // 4.将加密后的字节数组转换为字符串
            result = Base64.encodeBase64String(encrpt_bytes);
        } catch (Exception e) {
            LOG.error("AES-CBC模式加密出错", e);
        }
        return result;
    }

    /**
     * AES-CBC模式加密
     *
     * @param bytes 待加密内容的字节数组，使用UTF-8编码
     * @param key   密钥规则
     * @param iv    密钥参数
     * @return
     */
    public static String encryptCBC(byte[] bytes, byte[] key, byte[] iv) {
        String result = null;
        try {
            // 1.生成AES-CBC模式密码器
            Cipher cipher = generateCipherCBC(key, iv, Cipher.ENCRYPT_MODE);

            // 2.使用AES密码器对明文的字节数组加密
            byte[] encrpt_bytes = cipher.doFinal(bytes);

            // 3.将加密后的字节数组转换为字符串
            result = Base64.encodeBase64String(encrpt_bytes);
        } catch (Exception e) {
            LOG.error("AES-CBC模式加密出错", e);
        }
        return result;
    }

    /**
     * AES-CBC模式解密，解密结果以字节数组形式返回
     *
     * @param content 待解密内容
     * @param key     密钥规则
     * @param iv      密钥参数
     * @return
     */
    public static byte[] decryptCBC(String content, byte[] key, byte[] iv) {
        byte[] result = null;
        try {
            // 1.生成AES-CBC模式密码器
            Cipher cipher = generateCipherCBC(key, iv, Cipher.DECRYPT_MODE);

            // 2.将密文解码成字节数组
            byte[] encrypted = Base64.decodeBase64(content);

            // 3.使用AES-CBC模式密码器对密文的字节数组解密
            result = cipher.doFinal(encrypted);
        } catch (Exception e) {
            LOG.error("AES-CBC模式解密出错", e);
        }
        return result;
    }

    /**
     * AES-CBC模式解密，解密结果以字符串形式返回
     *
     * @param content 待解密内容
     * @param key     密钥规则
     * @param iv      密钥参数
     * @return
     * @throws Exception
     */
    public static String decryptCBCString(String content, byte[] key, byte[] iv) throws Exception {
        // 1.获取解密的字节数组
        byte[] bytes = decryptCBC(content, key, iv);
        // 2.将字节数组转换为字符串
        return new String(bytes, StandardCharsets.UTF_8.name());
    }
}