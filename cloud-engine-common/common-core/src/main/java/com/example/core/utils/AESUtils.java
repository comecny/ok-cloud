package com.example.core.utils;

import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESUtils {

    /**
     * key 加密算法
     */
    private static final String KEY_ALGORITHM = "AES";

    /**
     * 固定值
     */
    private static final String SECRET_RANDOM = "SHA1PRNG";

    /**
     * 编码方式
     */
    public static final String ENCODING_TYPE = "UTF-8";

    /**
     * 默认的加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";


    /**
     * 加密
     *
     * @param content 加密内容
     * @param password 私钥
     * @return
     */
    public static String encrypt(String content, String password) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            byte[] byteContent = content.getBytes(ENCODING_TYPE);
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            // 通过Base64转码返回
            return Base64Utils.encodeToString(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成加密私钥
     * @param password 私钥
     * @return
     */
    private static SecretKeySpec getSecretKey(String password) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
            SecureRandom secureRandom = SecureRandom.getInstance(SECRET_RANDOM);
            secureRandom.setSeed(password.getBytes());
            //AES 要求密钥长度为 128
            keyGenerator.init(128, secureRandom);
            //生成一个密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
           throw new RuntimeException(e);
        }
    }

    /**
     * AES 解密操作
     *
     * @param content 加密内容
     * @param password 私钥
     * @return
     */
    public static String decrypt(String content, String password) {
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));
            //执行操作
            byte[] result = cipher.doFinal(Base64Utils.decodeFromString(content));
            return new String(result, "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
