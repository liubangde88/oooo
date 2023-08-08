/**
 * @Title: MyX509TrustManager.java
 * @Package com.jimi.weixin.util
 * @Description: 证书信任管理器（用于https请求）
 * @author yangbq
 * @date 2016年5月14日 下午5:08:03
 * @version V1.0
 */
package com.yuanzheng.common.utils;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @ClassName: MyX509TrustManager
 * @Description: 证书信任管理器（用于https请求）
 * @author yangbq
 * @date 2016年5月14日 下午5:08:03
 *
 */
public class MyX509TrustManager implements X509TrustManager {
    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
