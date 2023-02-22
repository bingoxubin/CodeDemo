package com.bingoabin.test;

import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2022/12/11 10:34
 * @Description:
 */
public class TestArrays {
	public static void main(String[] args){
	    int[][] arr = {{5,4,3,2,1},{4,3,2,1}};
		for(int i = 0; i<arr.length;i++){
			Arrays.sort(arr[i]);
		}
		System.out.println(Arrays.deepToString(arr));
	}
}
