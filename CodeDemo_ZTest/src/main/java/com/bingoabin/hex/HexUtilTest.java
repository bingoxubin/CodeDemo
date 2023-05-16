package com.bingoabin.hex;

/**
 * @author bingoabin
 * @date 2023/5/16 11:28
 * @Description:
 */
public class HexUtilTest {
	/**
	 * 将16进制字符串数转换为汉字,可包含数字及符号
	 */
	public static String HexToUnicode(String content) {
		String enUnicode = null;
		String deUnicode = null;
		for (int i = 0; i < content.length(); i++) {
			if (enUnicode == null) {
				enUnicode = String.valueOf(content.charAt(i));
			} else {
				enUnicode = enUnicode + content.charAt(i);
			}

			if (i % 4 == 3) {
				if (enUnicode != null) {
					if (deUnicode == null) {
						deUnicode = String.valueOf((char) Integer.valueOf(enUnicode, 16).intValue());
					} else {
						deUnicode = deUnicode + String.valueOf((char) Integer.valueOf(enUnicode, 16).intValue());
					}
				}
				enUnicode = null;
			}
		}
		return deUnicode;
	}

	/**
	 * 将汉字转换为16进制字符串数,可包含数字及符号
	 */
	public static String UnicodeToHex(String content) {

		String enUnicode = null;

		for (int i = 0; i < content.length(); i++) {
			if (i == 0) {
				enUnicode = getHexString(Integer.toHexString(content.charAt(i)).toUpperCase());
			} else {
				enUnicode = enUnicode + getHexString(Integer.toHexString(content.charAt(i)).toUpperCase());
			}
		}
		return enUnicode;
	}

	private static String getHexString(String hexString) {
		String hexStr = "";
		for (int i = hexString.length(); i < 4; i++) {
			if (i == hexString.length())
				hexStr = "0";
			else
				hexStr = hexStr + "0";
		}
		return hexStr + hexString;
	}

	public static void main(String[] args) {
		String str = "1";
		String unicodes = UnicodeToHex(str);
		System.out.println(unicodes);
		System.out.println(HexToUnicode(unicodes));
	}
}
