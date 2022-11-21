package com.bingoabin.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author bingoabin
 * @date 2022/11/9 12:05
 * @Description:
 */
public class Test2 {
	public static void main(String[] args){
		// Scanner sc = new Scanner(System.in);
		// String str = sc.nextLine();
		// int b = sc.nextInt();
		String str = "7319951122";
		int b = 19;
		List<Integer> list = new ArrayList<>();
		for(int i = str.length() - 1;i>= 0;i--) list.add(str.charAt(i) - '0');

		List<Integer> res = getDiv(list,b);
		for(int i = res.size() - 2;i>= 0;i--){
			System.out.print(res.get(i));
		}
		System.out.println();
		System.out.println(res.get(res.size() - 1));
	}

	public static List<Integer> getDiv(List<Integer> list,int num){
		List<Integer> res = new ArrayList<>();
		int temp = 0;
		for(int i = list.size() - 1;i>= 0;i--){
			temp = 10 * temp + list.get(i);
			res.add(temp / num);
			temp %= num;
		}
		Collections.reverse(res);
		while(res.size() > 1 && res.get(res.size() - 1) == 0) res.remove(res.size() - 1);
		res.add(temp);
		return res;
	}
}
