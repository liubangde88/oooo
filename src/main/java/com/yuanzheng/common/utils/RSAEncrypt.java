package com.yuanzheng.common.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAEncrypt {

    private static final String public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCcgBNvjhC3h+cXrXJ/q+G1b3F9Z4M7TdISt1n3LkdFiCcg1bxd7LebP9Bs5WtS+R/F8wMe5BYlOwrvfm1u/gY8pBrhpTSkMiQJObnECXg3D7Y9y0JSbaVKdJruOqC7/sH9Ei9kxXHJTwEhgDvXHbriqiQrHldjEoOiybzfb/D3GQIDAQAB";
    private static final String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJyAE2+OELeH5xetcn+r4bVvcX1ngztN0hK3WfcuR0WIJyDVvF3st5s/0Gzla1L5H8XzAx7kFiU7Cu9+bW7+BjykGuGlNKQyJAk5ucQJeDcPtj3LQlJtpUp0mu46oLv+wf0SL2TFcclPASGAO9cduuKqJCseV2MSg6LJvN9v8PcZAgMBAAECgYAs0xY+Bm/NUXSkOH6qCL4OLIxSAdr5mnGIANIY1T1+sd+bLPzV3h48rktxVUinLEmoddE4ANZC9aVNMWYAu2tnclBhLM8UC5ochOuuaRwiDBlrNEVYeOnsbYXWGM9c+Yk83ltGBTcfYYQ6PXcFKdbiAbLrZxjrZEnbqrwYkghlbQJBAM+pq8imTyR1eNYK8HaNiUAivO//Guc1K9yFkfCkFXCnazGY0yazf3/2aL4gbkICfFnec6EmtFpPzVN7h2uJocMCQQDA7bSf7m2QvdLHbc8vbuzQxR1NisCEErws/mmvuHaXZux+fNehl3rbK+SN4iYBpam+7rtZLmly8ev05shvGDnzAkEAkKa8zXjjeh8F+/sEw2AV8gIAoCCQSeuk0OiN3xU9BgroxVSSCMxSDP3EumN2yi5XFna5DtEaIiqiePt8OjFV5wJAeXVtEH6/da87wpfMJtWTu39NCmM+/ib5Vu44RQtBheGjI6Ke8jl2fALvGa/1M3ZDHlQXmHaSpm3Nmdms99vtpwJBAIbjvBpAWtD+m6EOias7R35e+v3STKTq+F9XQtwyg0Vlr3/nBAe9FzE6q4bpqGJhUWDxT0O7stbONRmZc4qEhC0=";
    private static Map<Integer, String> keyMap = new HashMap<Integer, String>();  //用于封装随机产生的公钥与私钥

    public static void main(String[] args) throws Exception {
        //生成公钥和私钥
        genKeyPair();
        //加密字符串
        String message = "df723820";
        String messageEn = encrypt(message);
        System.out.println(messageEn.length());
    }

    /**
     * 随机生成密钥对
     *
     * @throws NoSuchAlgorithmException
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        keyMap.put(0, publicKeyString);  //0表示公钥
        keyMap.put(1, privateKeyString);  //1表示私钥
    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(public_key);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

}
