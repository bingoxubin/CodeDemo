package com.bingoabin.aatechnic.transferparam;

/**
 * @author bingoabin
 * @date 2023/10/11 8:51
 * @Description:
 */
public class TransParam {
	public static void main(String[] args){
		System.out.println("hello");

		//程序参数
		for(String arg:args){
			System.out.println("program para = " + arg);
		}

		System.out.println("=================================");

		//jvm参数
		String vmPara = System.getProperty("vmPara");
		System.out.println("vmPara = " + vmPara);

		System.out.println("=================================");

		//环境参数
		String envParam = System.getenv("envParam");
		System.out.println("envParam = " + envParam);
	}
}
