package com.bingoabin.utils;

import com.bingoabin.utils.constant.BizCodes;
import com.bingoabin.utils.dto.ApiRespAndErrorBase;
import com.bingoabin.utils.dto.ApiRespBase;
import com.bingoabin.utils.dto.ApiRespMessageBase;
import com.bingoabin.utils.exception.BizException;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author HanJunsheng
 * @date 2021/7/16
 * @copyright sankuai.com
 */
public class HttpUtil {
    static Logger logger = LogManager.getLogger(HttpUtil.class);

    private static final CloseableHttpClient httpClient;

    //设置连接时间 128s
    private static int CONNECTION_TIMEOUT = 128 * 1000;

    //数据传输时间默认 128s
    private static int SO_TIMEOUT = 128 * 1000;

    public static final PoolingHttpClientConnectionManager cm;
    //utf 编码
    public static final String CHARSET = "UTF-8";
    // cookie默认path
    public static final String COOKIE_DEFAULT_PATH = "/";

    public static final String GET = "GET";
    public static final String POST = "POST";


    // 设置httpclient 的超时时间
    static {
        cm = new PoolingHttpClientConnectionManager();
        cm.setDefaultMaxPerRoute(10);
        cm.setMaxTotal(100);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(CONNECTION_TIMEOUT)
                .setSocketTimeout(SO_TIMEOUT).build();
        httpClient = null;//HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(config).build();
    }


    /**
     * get请求
     *
     * @return
     */
    public static <T> ApiRespBase<T> doGet(String url, Map<String, String> header, Type... clazz) {
        return doBaseGet(url, header, ApiRespBase.class, clazz);
    }
    public static <T> ApiRespMessageBase<T> doMessageGet(String url, Map<String, String> header, Type... clazz) {
        return doBaseGet(url, header, ApiRespMessageBase.class, clazz);
    }

    public static <B> B doBaseGet(String url, Map<String, String> header, Type baseClazz, Type... clazz) {

        //发送get请求
        HttpGet request = new HttpGet(url);

        for (Map.Entry<String, String> entry : header.entrySet()) {
            request.setHeader(entry.getKey(), entry.getValue());
        }
        try (CloseableHttpResponse response = httpClient.execute(request)) {

            //* 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //* 读取服务器返回过来的json字符串数据
                String strResult = EntityUtils.toString(response.getEntity());
                TypeToken<?> typeToken = TypeToken.getParameterized(baseClazz, clazz);
                B obj = (B) JsonUtil.parse(strResult, typeToken);
                return obj;
            } else {
                logger.error("请求url:{}失败,返回结果{}", url, EntityUtils.toString(response.getEntity()));
                request.abort();
            }
        } catch (Exception e) {
            logger.error("http请求失败，url[{}]", url, e);
        }
        return null;
    }

    /**
     * post请求（用于请求json格式的参数）
     *
     * @param url
     * @param params
     * @return
     */

    public static <T> ApiRespBase<T> doPost(String url, Object params, Map<String, String> header, Type clazz) {
        return doBasePost(url, params, header, ApiRespBase.class, clazz);
    }

    public static <T> ApiRespMessageBase<T> doMessagePost(String url, Object params, Map<String, String> header, Type clazz) {
        return doBasePost(url, params, header, ApiRespMessageBase.class, clazz);
    }

    public static <B, T> ApiRespAndErrorBase<B, T> doAndErrorPost(String url, Object params, Map<String, String> header, Type... clazz) {
        return doBasePost(url, params, header, ApiRespAndErrorBase.class, clazz);
    }

    public static <B> B doBasePost(String url, Object params, Map<String, String> header, Type baseType, Type... Type) {

        // test bug
        String resp = null;
        // 创建httpPost
        HttpPost httpPost = new HttpPost(url);
        header.put("Content-Type", "application/json");
        for (Map.Entry<String, String> entry : header.entrySet()) {
            httpPost.setHeader(entry.getKey(), entry.getValue());
        }
        String paramsString = JsonUtil.JSONObject.toJSONString(params);
        StringEntity entity = new StringEntity(paramsString, CHARSET);
        httpPost.setEntity(entity);
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            HttpEntity responseEntity = response.getEntity();
            String jsonString = EntityUtils.toString(responseEntity);
            resp = jsonString;
            if (state == HttpStatus.SC_OK) {
                TypeToken<?> typeToken = TypeToken.getParameterized(baseType, Type);
                B obj = (B) JsonUtil.parse(jsonString, typeToken);
                return obj;
            } else {
                throw new Exception("请求返回:" + state + ",url:" + url + ",response:" + jsonString);
            }
        } catch (Exception e) {
            logger.error("sysrecall response:" + resp);
            logger.error("请求失败url:{}", url, e);
            throw new BizException(BizCodes.ERROR, "请求失败url:" + url + ", 异常信息:" + e.getMessage());
        }
    }

    public static void setCookie(HttpServletResponse response, String key, String value) {
        setCookie(response, key, value, COOKIE_DEFAULT_PATH, null);
    }

    public static void setCookie(
            HttpServletResponse response, String key, String value,
            @Nullable Integer maxAgeSec) {
        setCookie(response, key, value, COOKIE_DEFAULT_PATH, maxAgeSec);
    }

    public static void setCookie(
            HttpServletResponse response, String key, String value,
            String path, @Nullable Integer maxAgeSec) {
        Cookie cookie = new Cookie(key, value);
        if (maxAgeSec != null) {
            cookie.setMaxAge(maxAgeSec);
        }
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    public static Optional<String> getCookie(HttpServletRequest request, String key) {
        return getCookie(request, key, Function.identity());
    }

    public static <V> Optional<V> getCookie(
            HttpServletRequest request, String key, Function<String, V> transform) {
        return Arrays.stream(request.getCookies())
                .filter(e -> e.getName().equals(key))
                .map(e -> e.getValue())
                .map(transform)
                .filter(Objects::nonNull)
                .findAny();
    }
}
