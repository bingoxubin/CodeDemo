package com.bingo.test;

/**
 * @author bingoabin
 * @date 2023/6/5 10:24
 * @Description:
 */
public class Test {
	//需求描述：https://checkcoverage.apple.com/ 根据请求地址输入动态识别码以及序列号，求出具体的型号，以及购买日期
	//思路：1.先通过http请求，静态页面，拿到动态验证码的图像地址
	//     2.调用opencv 解析图像中的字符串
	//     3.将解析后的字符串 以及序列号  作为参数  输入  发送post请求，获取结果
	//     4.整合结果输出
	public static void main(String[] args){

	}
}
