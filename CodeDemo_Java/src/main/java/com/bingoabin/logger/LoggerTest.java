package com.bingoabin.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Author: xubin34
 * @Date: 2021/8/10 8:12 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class LoggerTest {
	static Logger logger = LogManager.getLogger(LoggerTest.class);

	public static void main(String[] args) {
		int a = 10;
		if (a == 10) {
			logger.error("a的值不对啊:{}", a);
		}
		System.out.println("helloworld");
		//ERROR StatusLogger No log4j2 configuration file found. Using default configuration: logging only errors to the console. Set system property 'log4j2.debug' to show Log4j2 internal initialization logging.
		// 20:17:08.069 [main] ERROR com.bingoabin.logger.LoggerTest - a的值不对啊:10
		// helloworld

		try {
			a = 10 / 0;
		} catch (Exception e) {
			logger.error("分母出现0：{}", a, e);
		}
		//20:19:28.911 [main] ERROR com.bingoabin.logger.LoggerTest - 分母出现0：10
		// java.lang.ArithmeticException: / by zero
		// 	at com.bingoabin.logger.LoggerTest.main(LoggerTest.java:26) [classes/:?]
	}

}
