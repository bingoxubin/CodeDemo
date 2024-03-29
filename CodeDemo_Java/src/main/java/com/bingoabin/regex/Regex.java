package com.bingoabin.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xubin03
 * @date 2021/5/19 11:42 下午
 */
public class Regex {
	//#不包含某个字符串
	// ^((?!word).)*$
	//
	// #限定符
	// ?  比如used？ 表示d可以出现0次或者1次
	// *  表示出现0次或多次字符  比如ab*c  表示ac中间只能出现0个或者多个b
	// +  表示出现1次以上的字符  比如ab+c  表示ac中间至少出现一个b  abc abbbbbbc
	// {2,6}  表示精确出现的次数   比如ab{2,6}c,表示ac中间有只有2-6个b,如果想表示2次以上，可以把6省略,{2,}
	//
	// #上面的只是判断前面一个字符，如果想表示多个字符，可以用（）括起来
	// (ab)+ 表示至少出现ab 一次以上
	// |  或表达式，比如匹配a cat和a dog  用 a (cat|dog)
	//
	// #字符类 另一个与或运算相关的是字符类
	// [abc]+  字符串字符只能取至于abc三个字母
	// [a-zA-Z]+   表示小写字母和大写字母的范围
	// [^0-9a-zA-Z]+  表示非数字,非a-z,非A-Z
	//
	// #元字符
	// \d+  等同于[0-9]+  数字字符
	// \w+  等同于[0-9a-zA-Z_]+  单词字符
	// \s+  代表空白符  [\r\n\t\f\v ]+   空白字符
	// \b   匹配单词的开始或结束，单词字符的边界
	// \D+  等同于[^0-9]+  非数字字符
	// \W+  等同于[^0-9a-zA-Z_]+  非单词字符
	// \S+  等同于[^\r\n\t\f\v ]+  非空白字符
	// \B+  匹配不是单词开头或结束的位置
	// .    表示任意字符，但不表示换行符
	// ^    匹配行首
	// $    匹配行尾  ^m.*t$  匹配  my name is cat
	// #注意：
	// #1.^在[]外面代表是为首的数字，所以它代表头
	// #2.^在[]里面代表是非数字，所以它代表是非
	// #3.^[0-9] 表示数字开头   [^0-9] 表示非数字
	//
	// #高级概念
	// #贪婪与懒惰匹配,默认* + {} 默认会尽可能多的匹配字符
	// 例如：匹配<span><b>This is a sample text</b></span>中的html标签，用<.+>会匹配所有的  用<.+?>会将默认的贪婪匹配转成懒惰匹配
	//
	// #懒惰限定
	// *？  重复任意次，但尽可能少重复
	// +？  重复1次或更多次，但尽可能少重复
	// ??   重复0次或1次，但尽可能少重复
	// {n,m}? 重复n到m次，但尽可能少重复
	// {n,}?  重复n次以上，但尽可能少重复

	public static void main(String[] args) {
		boolean matches = Pattern.matches("data:hadoop-.*\\.pyspark\\.jupyter\\..*", "data:hadoop-.asfasfs.pyspark.jupyter.asfaf");
		System.out.println(matches);

		String str = "data:hadoop-1.pyspark.jupyter.2";
		Pattern pattern = Pattern.compile("data:hadoop-(.*)\\.pyspark\\.jupyter\\.(.*)");
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
		}

		String path = "公司-美团-基础研发平台-数据科学与平台部-数据平台中心-数据开发平台组-数据开发组";
		path = path.replaceAll("-", "/").replaceAll("公司/美团", "");
		System.out.println(path);

		//取库名.表名   前面的库名
		Pattern TBL_NAME_PATTERN = Pattern.compile("^([^.]+)(\\.)(.*)$");
		Matcher matcher1 = TBL_NAME_PATTERN.matcher("*saf.xxb.xxl.sfsfs");
		System.out.println(matcher1.find() ? matcher1.group(1) : "");

		System.out.println("===================");
		boolean flag = Pattern.matches("^[a-z][a-z0-9_]*$", "Aasdfsfsa342424");
		System.out.println(flag);
	}

	@Test
	public void test() {
		String sql = "create external table if not exists DL_SDB.EXT_MAIN${hivevar:EXT_DATA} (id STRING) stored as textfile location '/datalake/user/${hivevar:EXT_DATA}/date'";
		Pattern pattern = Pattern.compile("\\$\\{hivevar:[^}]+}");
		Matcher matcher = pattern.matcher(sql);

		while (matcher.find()) {
			System.out.println(matcher.group());
		}
	}

	@Test
	public void test1(){
		String sql = "create external table if not exists DL_SDB.EXT_MAIN${hivevar:EXT_DATA} (id STRING) stored as textfile location '/datalake/user/${hivevar:EXT_DATA}/date'";
		String res = sql.replace("${hivevar:EXT_DATA}", "_HIVEVAR_EXT_DATA_385");
		System.out.println(res);
	}
}
