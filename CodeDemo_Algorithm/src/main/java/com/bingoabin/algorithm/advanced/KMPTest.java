package com.bingoabin.algorithm.advanced;

import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2022/4/23 14:56
 */
public class KMPTest {
	public static void main(String[] args) {
		String str = "abababaababacb";
		String pattern = "ababacb";
		System.out.println(kmp(str, pattern));
		System.out.println(kmp1(str, pattern));
	}

	// KMP算法，返回匹配的首字符下标，否则返回-1
	public static int kmp(String s, String t) {
		int m = s.length(), n = t.length();
		int[] next = new int[n];
		getNext(t, next);
		System.out.println(Arrays.toString(next));
		int i = 0, j = 0;
		while (i < m && j < n) {
			if (j == -1 || s.charAt(i) == t.charAt(j)) {
				i++;
				j++;
			} else {
				j = next[j];
			}
		}
		if (j >= n) {
			return i - n;
		} else {
			return -1;
		}
	}

	// 计算next数组 "ababacb"  [-1, 0, 0, 1, 2, 3, 0]
	public static void getNext(String t, int[] next) {
		int j = 0, k = -1, n = t.length();
		next[0] = -1;
		while (j < n - 1) {
			if (k == -1 || t.charAt(j) == t.charAt(k)) {
				j++;
				k++;
				next[j] = k;
			} else {
				k = next[k];
			}
		}
	}

	//自己实现一遍
	public static int kmp1(String str,String pattern){
		int slen = str.length();
		int klen = pattern.length();
		int[] next = new int[klen];
		next(pattern, next);
		System.out.println(Arrays.toString(next));
		int i = 0;int j = 0;
		while(i < slen && j < klen){
			if(j == -1 || str.charAt(i) == pattern.charAt(j)){
				i++;
				j++;
			}else{
				j = next[j];
			}
		}
		if(j >= klen){
			return i - klen;
		}else{
			return -1;
		}
	}

	public static void next(String pattern,int[] res){
		int k = -1;
		int i = 0;
		int len = pattern.length();
		res[0] = -1;
		while(i < len - 1){
			if(k == -1 || pattern.charAt(k) == pattern.charAt(i)){
				k++;
				i++;
				res[i] = k;
			}else{
				k = res[k];
			}
		}
	}
}
