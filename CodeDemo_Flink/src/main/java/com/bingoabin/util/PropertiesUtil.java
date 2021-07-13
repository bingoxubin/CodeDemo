package com.bingoabin.util;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

//获取配置信息
public class PropertiesUtil {
	private static Properties prop = null;
	private static ResourceLoader loader = ResourceLoader.getInstance();
	private static ConcurrentMap<String, String> configMap = new ConcurrentHashMap<String, String>();

	/**
	 * 通过配置文件名获取配置文件中key对应的value值
	 *
	 * @param key      key键
	 * @param propName Properties对象名称
	 * @return key所对应的value
	 */
	public static String getStringByKey(String key, String propName) {
		prop = getProperties(propName);
		key = key.trim();
		if (!configMap.containsKey(key)) {
			if (prop.getProperty(key) != null) {
				configMap.put(key, prop.getProperty(key));
			}
		}
		return configMap.get(key);
	}

	/**
	 * 加载配置文件
	 *
	 * @param propName Properties对象名称
	 * @return Properties对象
	 */
	public static Properties getProperties(String propName) {
		try {
			prop = loader.getPropFromProperties(propName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return prop;
	}
}
