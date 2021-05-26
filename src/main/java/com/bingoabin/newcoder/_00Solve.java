package com.bingoabin.newcoder;

import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2020/12/11 20:56
 */
public class _00Solve {
	public static void main(String[] args){
		System.out.println(solve("ababababa"));
		int[] arr = new int[]{1, 5, 4, 3, 2, 6};
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static int solve1 (String s) {
		// write code here
		int len =s.length() - 1;
		while(len > 0){
			String temp = s.substring(0,len);
			if(s.indexOf(temp,1) != -1){
				return len;
			}
			len --;
		}
		return -1;
	}

	public static int solve (String ss) {
		// write code here
		int temp = 0;
		char[] s = ss.toCharArray();
		for(int i = 1;i<ss.length();i++){
			if(s[i] == s[temp]) {
				temp++;
			} else{
				if(s[i] == s[0]){
					temp = 1;
				} else{
					temp = 0;
				}
			}
		}
		if(temp == 0) return -1;
		return temp;
	}
}
