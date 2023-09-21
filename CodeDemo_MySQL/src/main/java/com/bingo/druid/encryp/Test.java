package com.bingo.druid.encryp;

/**
 * @author bingoabin
 * @date 2023/9/20 8:03
 * @Description:
 */
public class Test {
	public static void main(String[] args) throws Exception{
		System.out.println(SecretUtil.decryptDatasource("iE90MMLY0kMd3aKpYFXVG0fRbpbmgz7hrMyVJ9qmrEcpik4PSdQITcd8hKzixEQSiwpJKQ89IIH+vreh0e/tnw=="));
		System.out.println(SecretUtil.encryptDatasource("moiaP@12!"));

	}
}
