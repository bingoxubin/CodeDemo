package com.bingo.druid.encryp;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * @author bingoabin
 * @date 2023/9/20 8:03
 * @Description:
 */
public class Test {
	public static void main(String[] args) throws Exception {
		//生成公私钥
		String[] keyPair = ConfigTools.genKeyPair(512);
		System.out.println("Private Key: " + keyPair[0]);
		System.out.println("Public Key: " + keyPair[1]);
		System.out.println(SecretUtil.decryptDatasource("UFMmeZA9V0IoA4yVh/9hcRtvJMlAjSdJF9NGLiBgotnwcVr/Vgtbpnw/xjDnqFYh7QwW4ooQlwzSi7a7K7Mz3w=="));
		System.out.println(SecretUtil.encryptDatasource("moiaP@12!"));

	}
}
