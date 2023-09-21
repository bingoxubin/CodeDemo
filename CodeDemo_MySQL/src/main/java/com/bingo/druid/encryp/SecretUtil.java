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
	private static final String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANI/A62k04JsV70qGk3v1chYJTXDI8ntaWYmJL/2x1M1rd4hLz+imA3cF7fTtsnMqDObv3ls9Bfpg0CO0Qk26cECAwEAAQ==";

	private static final String privateKey = "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEA0j8DraTTgmxXvSoaTe/VyFglNcMjye1pZiYkv/bHUzWt3iEvP6KYDdwXt9O2ycyoM5u/eWz0F+mDQI7RCTbpwQIDAQABAkEApw2Mtac2q/W8DUDr36tGOUbYYgxoXmS2j4SVRLYnkJOv4AlFLr/EC6VIZXCM1ULYrhQ7GWjUIx76xrGvz+m4MQIhAPdecs6sN//o4Sw/KrWpOTfkyESDX7jy5B+a2uP6PkC1AiEA2ZT6MYSjfDz79jqybCX9fuJ+YqcFBLF8RdAHYWlcyF0CIQDdDpQ+SPzKPoxrBA/gw1cUseieAWDbYIOoErSloo1bOQIhAJiPXN5bokX6fLCpd3FdMeFF7lg9w8cGq5xHwOUMe4IBAiAeKGs5xWFFHoo3yIQNlnEDaCzZ1Huz3EPhALiKnjuVDQ==";

	public static String encryptDatasource(String plainText) throws Exception {
		logger.info("加密");
		return ConfigTools.encrypt(privateKey, plainText);
	}

	public static String decryptDatasource(String encrypt) throws Exception{
		logger.info("解密");
		return ConfigTools.decrypt(publicKey, encrypt);
	}
}
