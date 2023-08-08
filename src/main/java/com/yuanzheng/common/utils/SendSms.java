package com.yuanzheng.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

public class SendSms {

    private static String Url = "https://dx.ipyy.net/smsJson.aspx";


    private static String account = "20dx0015";

    private static String password = "20dx0015";

    private static String content = "尊敬的用户：您的验证码@，请妥善保管，请勿泄露。【可比达】";


    public static Boolean SendSms(String mobile, String code) {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);
        client.getParams().setContentCharset("UTF-8");
        method.setRequestHeader("ContentType",
                "application/x-www-form-urlencoded;charset=UTF-8");
        NameValuePair[] data = {new NameValuePair("action", "send"),
                new NameValuePair("account", account),
                new NameValuePair("password", password),
                new NameValuePair("mobile", mobile),
                new NameValuePair("code", "8"),
                new NameValuePair("content", content.replace("@", code)),};
        method.setRequestBody(data);
        try {
            client.executeMethod(method);
            String Result = method.getResponseBodyAsString();
            JSONObject object = JSONObject.parseObject(Result);
            System.out.println(Result);
            if ("Success".equals(object.getString("returnstatus"))) {
                return true;
            }
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    // 字符编码成HEX
    public static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return "0x" + str;// 0x表示十六进制
    }

    // 转换十六进制编码为字符串
    public static String toStringHex(String s) {
        if ("0x".equals(s.substring(0, 2))) {
            s = s.substring(2);
        }
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(
                        s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            s = new String(baKeyword, "GBK");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    /** */
    /**
     * 把字节数组转换成16进制字符串
     *
     * @param bArray
     * @return
     */
    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append("0");
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    // 字符编码成HEX
    public static String encodeHexStr(int dataCoding, String realStr) {
        String strhex = "";
        try {
            byte[] bytSource = null;
            if (dataCoding == 15) {
                bytSource = realStr.getBytes("GBK");
            } else if (dataCoding == 3) {
                bytSource = realStr.getBytes("ISO-8859-1");
            } else if (dataCoding == 8) {
                bytSource = realStr.getBytes("UTF-16BE");
            } else {
                bytSource = realStr.getBytes("ASCII");
            }
            strhex = bytesToHexString(bytSource);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return strhex;
    }

    // hex编码还原成字符
    public static String decodeHexStr(int dataCoding, String hexStr) {
        String strReturn = "";
        try {
            int len = hexStr.length() / 2;
            byte[] bytSrc = new byte[len];
            for (int i = 0; i < len; i++) {
                String s = hexStr.substring(i * 2, 2);
                bytSrc[i] = Byte.parseByte(s, 512);
                Byte.parseByte(s, i);
                // bytSrc[i] = Byte.valueOf(s);
                // bytSrc[i] = Byte.Parse(s,
                // System.Globalization.NumberStyles.AllowHexSpecifier);
            }

            if (dataCoding == 15) {
                strReturn = new String(bytSrc, "GBK");
            } else if (dataCoding == 3) {
                strReturn = new String(bytSrc, "ISO-8859-1");
            } else if (dataCoding == 8) {
                strReturn = new String(bytSrc, "UTF-16BE");
                // strReturn = Encoding.BigEndianUnicode.GetString(bytSrc);
            } else {
                strReturn = new String(bytSrc, "ASCII");
                // strReturn =
                // System.Text.ASCIIEncoding.ASCII.GetString(bytSrc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strReturn;
    }
}
