package com.bingo.druid.encryp;

import com.alibaba.druid.filter.config.ConfigTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bingoabin
 * @date 2023/9/20 7:58
 * @Description:
 */
public class SecretUtil {

	private static final Logger logger = LoggerFactory.getLogger(SecretUtil.class);
	private static final String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJ6+g3lLjeTDXngtutPnsDMOvz3c/M+ZlsD5JLXbVKTEmZbnz2zxjFZh0fPZ1gXf22qCDesvqeYtJjU0DYaw3hkCAwEAAQ==";

	private static final String privateKey = "";

	public static String encryptDatasource(String plainText) throws Exception {
		logger.info("加密");
		return ConfigTools.encrypt(privateKey, plainText);
	}

	public static String decryptDatasource(String encrypt) throws Exception{
		logger.info("解密");
		return ConfigTools.decrypt(publicKey, encrypt);
	}
}
