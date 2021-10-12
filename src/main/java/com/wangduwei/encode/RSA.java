package com.wangduwei.encode;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/**
 * <p>
 *
 * @author : wangduwei
 * @since : 2020/3/25  10:22
 **/
public class RSA {
    private static Cipher cipher;

    static {
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public static String[] generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            String publicKeyStr = getKeyString(publicKey);
            String privateKeyStr = getKeyString(privateKey);
            return new String[]{publicKeyStr, privateKeyStr};
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes = Base64.decode(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }


    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = Base64.decode(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }


    public static String getKeyString(Key key) {
        byte[] keyBytes = key.getEncoded();
        return Base64.encode(keyBytes);
    }


    public static String encrypt(String publicKey, String content) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
            byte[] encryptBytes = cipher.doFinal(content.getBytes());
            return Base64.encode(encryptBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String decrypt(String privateKey, String content) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
            byte[] decryptBytes = cipher.doFinal(Base64.decode(content));
            return new String(decryptBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {

        String[] strings = generateKeyPair();
        System.out.println("生成的密钥对是空的");
        if (strings == null) return;

        final String publicKey = strings[0];
        final String privateKey = strings[1];

        String content = "九点下班";
        String encryptStr = encrypt(publicKey, content);
        System.out.println("加密：" + encryptStr);
        String decryptStr = decrypt(privateKey, encryptStr);
        System.out.println("解密：" + decryptStr);
    }
}
