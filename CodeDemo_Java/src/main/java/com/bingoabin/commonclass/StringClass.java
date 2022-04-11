package com.bingoabin.commonclass;

/**
 * @author xubin03
 * @date 2021/5/18 10:02 上午
 */
public class StringClass {
	public static void main(String[] args) {
		String s1 = "javaEE";
		String s2 = "hadoop";
		String s3 = "javaEEhadoop";
		String s4 = "javaEE" + "hadoop";
		String s5 = s1 + "hadoop";
		String s6 = "javaEE" + s2;
		String s7 = s1 + s2;

		System.out.println(s3 == s4);//true
		System.out.println(s3 == s5);//false
		System.out.println(s3 == s6);//false
		System.out.println(s3 == s7);//false
		System.out.println(s5 == s6);//false
		System.out.println(s5 == s7);//false
		System.out.println(s6 == s7);//false

		String s8 = s6.intern();//返回值得到的s8使用的常量值中已经存在的“javaEEhadoop”
		String s9 = s7.intern();
		System.out.println(s3 == s8);//true
		System.out.println(s3 == s9);
	}

	//String常用方法：
	// int length()：返回字符串的长度： return value.length
	// char charAt(int index)： 返回某索引处的字符return value[index]
	// boolean isEmpty()：判断是否是空字符串：return value.length == 0
	// String toLowerCase()：使用默认语言环境，将 String 中的所有字符转换为小写
	// String toUpperCase()：使用默认语言环境，将 String 中的所有字符转换为大写
	// String trim()：返回字符串的副本，忽略前导空白和尾部空白
	// boolean equals(Object obj)：比较字符串的内容是否相同
	// boolean equalsIgnoreCase(String anotherString)：与equals方法类似，忽略大小写
	// String concat(String str)：将指定字符串连接到此字符串的结尾。 等价于用“+”
	// int compareTo(String anotherString)：比较两个字符串的大小
	// String substring(int beginIndex)：返回一个新的字符串，它是此字符串的从beginIndex开始截取到最后的一个子字符串。
	// String substring(int beginIndex, int endIndex) ：返回一个新字符串，它是此字符串从beginIndex开始截取到endIndex(不包含)的一个子字符串。
	// boolean endsWith(String suffix)：测试此字符串是否以指定的后缀结束
	// boolean startsWith(String prefix)：测试此字符串是否以指定的前缀开始
	// boolean startsWith(String prefix, int toffset)：测试此字符串从指定索引开始的子字符串是否以指定前缀开始
	// boolean contains(CharSequence s)：当且仅当此字符串包含指定的 char 值序列时，返回 true
	// int indexOf(String str)：返回指定子字符串在此字符串中第一次出现处的索引
	// int indexOf(String str, int fromIndex)：返回指定子字符串在此字符串中第一次出现处的索引，从指定的索引开始
	// int lastIndexOf(String str)：返回指定子字符串在此字符串中最右边出现处的索引
	// int lastIndexOf(String str, int fromIndex)：返回指定子字符串在此字符串中最后一次出现处的索引，从指定的索引开始反向搜索        注：indexOf和lastIndexOf方法如果未找到都是返回-1
	// 替换：
	// String replace(char oldChar, char newChar)：返回一个新的字符串，它是通过用 newChar 替换此字符串中出现的所有 oldChar 得到的。
	// String replace(CharSequence target, CharSequence replacement)：使用指定的字面值替换序列替换此字符串所有匹配字面值目标序列的子字符串。
	// String replaceAll(String regex, String replacement)：使用给定的 replacement 替换此字符串所有匹配给定的正则表达式的子字符串。
	// String replaceFirst(String regex, String replacement)：使用给定的 replacement 替换此字符串匹配给定的正则表达式的第一个子字符串。
	// 匹配:
	// boolean matches(String regex)：告知此字符串是否匹配给定的正则表达式。
	// 切片：
	// String[] split(String regex)：根据给定正则表达式的匹配拆分此字符串。
	// String[] split(String regex, int limit)：根据匹配给定的正则表达式来拆分此字符串，最多不超过limit个，如果超过了，剩下的全部都放到最后一个元素中。
	//
	//
	// indexOf()：返回指定字符的索引。
	// charAt()：返回指定索引处的字符。
	// replace()：字符串替换。
	// trim()：去除字符串两端空白。
	// split()：分割字符串，返回一个分割后的字符串数组。
	// getBytes()：返回字符串的 byte 类型数组。
	// length()：返回字符串长度。
	// toLowerCase()：将字符串转成小写字母。
	// toUpperCase()：将字符串转成大写字符。
	// substring()：截取字符串。
	// equals()：字符串比较。

	//类型转换：
	// String与基本数据类型、包装类型转换：
	// String 与基本数据类型、包装类之间的转换。
	// String --> 基本数据类型、包装类：调用包装类的静态方法：parseXxx(str)
	// 基本数据类型、包装类 --> String:调用String重载的valueOf(xxx)
	//
	// String 与 char[]之间的转换：
	// String --> char[]:调用String的toCharArray()
	// char[] --> String:调用String的构造器，new String(arr)
	//
	// String 与 byte[]之间的转换:
	// 编码：String --> byte[]:调用String的getBytes()
	// 解码：byte[] --> String:调用String的构造器
	// 编码：字符串 -->字节  (看得懂 --->看不懂的二进制数据)
	// 解码：编码的逆过程，字节 --> 字符串 （看不懂的二进制数据 ---> 看得懂）
	// 说明：解码时，要求解码使用的字符集必须与编码时使用的字符集一致，否则会出现乱码。

	//StringBuffer的常用方法：
	// StringBuffer append(xxx)：提供了很多的append()方法，用于进行字符串拼接
	// StringBuffer delete(int start,int end)：删除指定位置的内容
	// StringBuffer replace(int start, int end, String str)：把[start,end)位置替换为str
	// StringBuffer insert(int offset, xxx)：在指定位置插入xxx
	// StringBuffer reverse() ：把当前字符序列逆转
	// public int indexOf(String str)
	// public String substring(int start,int end):返回一个从start开始到end索引结束的左闭右开区间的子字符串
	// public int length()
	// public char charAt(int n )
	// public void setCharAt(int n ,char ch)
	// 总结：
	// 增：append(xxx)
	// 删：delete(int start,int end)
	// 改：setCharAt(int n ,char ch) / replace(int start, int end, String str)
	// 查：charAt(int n )
	// 插：insert(int offset, xxx)
	// 长度：length();
	// 遍历：for() + charAt() / toString()
}
