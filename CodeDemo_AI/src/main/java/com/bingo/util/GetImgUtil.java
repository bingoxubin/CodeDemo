package com.bingo.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author bingoabin
 * @date 2023/6/5 10:17
 * @Description:
 */
public class GetImgUtil {
	public static String getImgicUrl(String targetUrl){
		try {
			// 定义目标页面URL
			// String targetUrl = "https://www.example.com/page.html";

			// 创建URL对象
			URL url = new URL(targetUrl);

			// 创建HTTP连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET"); // 设置请求方法为GET
			connection.setConnectTimeout(5000); // 设置连接超时时间，单位为毫秒
			connection.setReadTimeout(5000); // 设置读取超时时间，单位为毫秒

			// 发送请求并获取响应状态码
			int responseCode = connection.getResponseCode();

			// 根据响应状态码判断请求是否成功
			if (responseCode == HttpURLConnection.HTTP_OK) {
				// 读取响应数据
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuilder response = new StringBuilder();

				while ((line = reader.readLine()) != null) {
					response.append(line);
				}

				reader.close();

				// 处理响应数据
				String responseBody = response.toString();
				System.out.println("Response: " + responseBody);

				// 提取<img>标签中的地址
				String imgPattern = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
				Pattern pattern = Pattern.compile(imgPattern);
				Matcher matcher = pattern.matcher(responseBody);

				while (matcher.find()) {
					String imgSrc = matcher.group(1);
					System.out.println("Image URL: " + imgSrc);
					return imgSrc;
				}
			} else {
				System.out.println("Request failed. Response Code: " + responseCode);
			}

			// 关闭连接
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) {
		GetImgUtil.getImgicUrl("https://checkcoverage.apple.com/");
	}
}
