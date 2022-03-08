package com.bingoabin.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BAUtils {
    public static final String AUTH_METHOD = "MWS";
    //使用HmacSHA1算法
    private static final String HMAC_SHA1 = "HmacSHA1";

    private static final String FORMAT_GMT = "EEE, d MMM yyyy HH:mm:ss 'GMT'";


    public static Map<String, String> getBasicAuthHeader(String appKey, String appSecret, String method, String uri) {
        try {
            String date = LocalDateTime.now(ZoneId.of("GMT")).format(DateTimeFormatter.ofPattern(FORMAT_GMT, Locale.US));

            Map<String, String> header = new HashMap<>();
            String encryptStr = String.format("%s %s%n%s", method, uri, date);
            String sign = hmacSHA1(appSecret, encryptStr);
            header.put("Date", date);
            header.put("Authorization", String.format("%s %s:%s", AUTH_METHOD, appKey, sign));
            return header;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static String auth(HttpServletRequest request, Map<String, String> clientSecretMap, String urlPrefix) throws Exception {
        // 鉴权失败则抛出异常，否则正常 return
        String authorizationHeader = request.getHeader("Authorization");
        String dateHeader = request.getHeader("Date");

        if (authorizationHeader == null || authorizationHeader.isEmpty() || dateHeader == null || dateHeader.isEmpty()) {
            throw new Exception("header empty error");
        }

        String[] authorizationHeaderList = authorizationHeader.split(":");
        if (authorizationHeaderList.length < 2) {
            throw new Exception("Authorization format error ");
        }
        String[] appSecretArr = authorizationHeaderList[0].split(" ");

        if (appSecretArr.length < 2) {
            throw new Exception("Authorization format error ");
        }

        String appKey = appSecretArr[1];
        if (!clientSecretMap.containsKey(appKey)) {
            throw new Exception("client  key  secret error ");
        }
        String clientSecret = clientSecretMap.get(appKey);

        String method = request.getMethod();
        String servletPath = request.getServletPath();
        if (urlPrefix != null) {
            servletPath = urlPrefix + servletPath;
        }

        String encryptStr = String.format("%s %s%n%s", method, servletPath, dateHeader);
        String sign = BAUtils.hmacSHA1(clientSecret, encryptStr);
        String computeAuthorization = String.format("%s %s:%s", BAUtils.AUTH_METHOD, appKey, sign);


        if (!computeAuthorization.equals(authorizationHeader)) {
            // 计算出来的和header传过来的签名sign不一致
            RuntimeException inner = new RuntimeException(String.format("encryptStr [%s]", encryptStr));
            Exception exception = new Exception(" header sign error ");
            exception.addSuppressed(inner);
            throw exception;
        }

        return appKey;

    }

    public static String hmacSHA1(String key, String data) {
        try {
            java.security.Security.addProvider(new com.sun.crypto.provider.SunJCE());
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1);
            Mac mac = Mac.getInstance(HMAC_SHA1);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes("utf-8"));
            //必须使用 commons-codec 1.5及以上版本，否则base64加密后会出现换行问题
            return Base64.encodeBase64String(rawHmac);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        Map<String, String> headers = BAUtils.getBasicAuthHeader("meituan.waimai.d.tracking", "35c7067075252430fa38021f8e794bc9", "GET", "/vinci/openapi/v1/app/meta/model/detail");
        System.out.println(headers);
    }
}
