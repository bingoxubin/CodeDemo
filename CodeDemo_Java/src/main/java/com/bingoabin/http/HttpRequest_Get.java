package com.bingoabin.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @author xubin34
 * @date 2021/7/20 11:04 上午
 */
public class HttpRequest_Get {
	/**
	 * 向指定URL发送GET方法的请求
	 *
	 * @param url   发送请求的URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
			                              "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			// for (String key : map.keySet()) {
			// 	System.out.println(key + "--->" + map.get(key));
			// }
			// 定义 BufferedReader输入流来读取URL的响应  输入到内存  然后再输出
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) {
		////接口开发地址  参考/Users/xubin34/project/MyWeb/SpringBoot 工程中的UserController
		//发送 GET 请求
		String s = HttpRequest_Get.sendGet("http://localhost:8080/index/home", "");
		System.out.println(s);

		//发送 GET 请求
		String s1 = HttpRequest_Get.sendGet("http://localhost:8080/index/bingo", "");
		System.out.println(s1);

		//发送 GET 请求
		String s2 = HttpRequest_Get.sendGet("http://localhost:8080/index/login/admin&admin", "");
		System.out.println(s2);

		//发送 GET 请求
		String s3 = HttpRequest_Get.sendGet("http://localhost:8080/index/loginbyget", "name=admin&pwd=11111");
		System.out.println(s3);
	}
}
