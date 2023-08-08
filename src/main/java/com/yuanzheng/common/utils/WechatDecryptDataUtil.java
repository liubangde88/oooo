package com.yuanzheng.common.utils;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;

public class WechatDecryptDataUtil {

    public static Map<String, Object> decryptData(String encryptedData, String iv, String sessionKey) {
        Map<String, Object> wres = new HashMap<String, Object>();
        if (sessionKey.length() != 24) {
            wres.put("status", 1);
            wres.put("msg", "参数错误");
            return wres;
        }
        if (iv.length() != 24) {
            wres.put("status", 1);
            wres.put("msg", "参数错误");
            return wres;
        }
        byte[] aesKey = Base64.decode(sessionKey);
        byte[] aesIV = Base64.decode(iv);
        byte[] aesEncryptedData = Base64.decode(encryptedData);

        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(aesIV);
            cipher.init(cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] original = cipher.doFinal(aesEncryptedData);
            if (null != original && original.length > 0) {
                String result = new String(original, "UTF-8");
                JSONObject userInfo = JSONObject.parseObject(result);
                if (userInfo.containsKey("phoneNumber")) {
                    wres.put("phoneNumber", userInfo.getString("phoneNumber"));

                    return wres;
                }
            }
            wres.put("status", 1);
            wres.put("msg", "解密错误");
            return wres;
        } catch (UnsupportedEncodingException ex) {
            wres.put("status", 1);
            wres.put("msg", ex.getMessage());
            return wres;
        } catch (NoSuchPaddingException ex) {
            wres.put("status", 1);
            wres.put("msg", ex.getMessage());
            return wres;
        } catch (NoSuchAlgorithmException ex) {
            wres.put("status", 1);
            wres.put("msg", ex.getMessage());
            return wres;
        } catch (InvalidAlgorithmParameterException ex) {
            wres.put("status", 1);
            wres.put("msg", ex.getMessage());
            return wres;
        } catch (InvalidKeyException ex) {
            wres.put("status", 1);
            wres.put("msg", ex.getMessage());
            return wres;
        } catch (BadPaddingException ex) {
            wres.put("status", 1);
            wres.put("msg", ex.getMessage());
            return wres;
        } catch (IllegalBlockSizeException ex) {
            wres.put("status", 1);
            wres.put("msg", ex.getMessage());
            return wres;
        } catch (JSONException ex) {
            wres.put("status", 1);
            wres.put("msg", ex.getMessage());
            return wres;
        }
    }

}
