package com.bingoabin.test;

import java.util.regex.Pattern;

/**
 * @author bingoabin
 * @date 2022/8/25 13:45
 * @Description: 正则
 */
public class TestPattern {
	public static final Pattern CHECK_IP_PATTERN = Pattern.compile("(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))");
	public static final Pattern IP_PATTERN = Pattern.compile("((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)");
	public static void main(String[] args){
		String ip = "127.0.0.1";
		String ip1 = "127.0.0.[1-100]";
		boolean matches = IP_PATTERN.matcher(ip).matches();
		System.out.println(matches);

		System.out.println("====="+ipIsInNet("192.168.1.3","192.168.1.3"));

		System.out.println("==========================================");
		String ipaddrs = ";192.168.1.1;";
		String[] ipLists = ip.trim().split(";");
		System.out.println(ipLists.length);
	}

	/**
	 * 判断ip是否在指定网段中
	 * @author dh
	 * @param iparea
	 * @param ip
	 * @return boolean
	 */
	public static boolean ipIsInNet(String iparea, String ip) {
		if (iparea == null)
			throw new NullPointerException("IP段不能为空！");
		if (ip == null)
			throw new NullPointerException("IP不能为空！");
		iparea = iparea.trim();
		ip = ip.trim();
		final String REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";
		// final String REGX_IP = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
		// 		+ "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
		// 		+ "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
		// 		+ "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
		final String REGX_IPB = REGX_IP + "\\-" + REGX_IP;
		if (!iparea.matches(REGX_IPB) || !ip.matches(REGX_IP))
			return false;
		int idx = iparea.indexOf('-');
		String[] sips = iparea.substring(0, idx).split("\\.");
		String[] sipe = iparea.substring(idx + 1).split("\\.");
		String[] sipt = ip.split("\\.");
		long ips = 0L, ipe = 0L, ipt = 0L;
		for (int i = 0; i < 4; ++i) {
			ips = ips << 8 | Integer.parseInt(sips[i]);
			ipe = ipe << 8 | Integer.parseInt(sipe[i]);
			ipt = ipt << 8 | Integer.parseInt(sipt[i]);
		}
		if (ips > ipe) {
			long t = ips;
			ips = ipe;
			ipe = t;
		}
		return ips <= ipt && ipt <= ipe;
	}
}
