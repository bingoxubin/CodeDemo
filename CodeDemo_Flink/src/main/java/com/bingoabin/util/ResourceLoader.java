package com.bingoabin.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//加载资源文件
public class ResourceLoader {
	private static ResourceLoader loader = new ResourceLoader();
	private static Map<String, Properties> loaderMap = new HashMap<String, Properties>();

	private ResourceLoader() {
	}

	public static ResourceLoader getInstance() {
		return loader;
	}

	/**
	 * 根据配置文件名称加载配置文件
	 *
	 * @param fileName 文件名
	 * @return Properties对象
	 * @throws Exception
	 */
	public Properties getPropFromProperties(String fileName) throws Exception {

		Properties prop = loaderMap.get(fileName);
		if (prop != null) {
			return prop;
		}
		prop = new Properties();
		InputStream inputstream = getClass().getResourceAsStream("/" + fileName);
		prop.load(inputstream);
		loaderMap.put(fileName, prop);
		return prop;
	}
}
