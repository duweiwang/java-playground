package com.wangduwei.encode;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * <p>
 *
 * @author : wangduwei
 * @since : 2020/3/25  10:10
 **/
public class DES {

    public static String encrypt(String content, String key) {
        try {
            byte[] encryptionBytes = content.getBytes("UTF-8");
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secureKey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secureKey, random);
            byte[] encryptionBase64Bytes = Base64.getEncoder().encode(cipher.doFinal(encryptionBytes));
            return new String(encryptionBase64Bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String content, String key) {
        try {
            byte[] decryptionBytes = Base64.getDecoder().decode(content);
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secureKey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secureKey, random);
            return new String(cipher.doFinal(decryptionBytes), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {

        final String key = "this_is_key";
        String content = "九点下班";

        String encryptStr = DES.encrypt(content, key);
        System.out.println("加密=" + encryptStr);

        String decryptStr = DES.decrypt(encryptStr, key);
        System.out.println("解密=" + decryptStr);
    }
}
