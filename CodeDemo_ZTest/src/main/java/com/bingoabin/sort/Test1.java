package com.bingoabin.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author bingoabin
 * @date 2022/11/9 11:28
 * @Description:
 */
public class Test1 {
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		int b = sc.nextInt();
		List<Integer> list = new ArrayList<>();
		for(int i = a.length() - 1;i>= 0;i--) list.add(a.charAt(i) - '0');

		List<Integer> res = getMulti(list,b);
		for(int i = res.size() - 1;i>= 0;i--){
			System.out.print(res.get(i));
		}
	}

	public static List<Integer> getMulti(List<Integer> list,int num){
		List<Integer> res = new ArrayList<>();
		int temp = 0;
		for(int i = 0;i<list.size() || temp != 0;i++){
			if(i < list.size()) temp += list.get(i) * num;
			res.add(temp % 10);
			temp /= 10;
		}
		while(res.size() > 1 && res.get(res.size() - 1) == 0) res.remove(res.size() - 1);
		return res;
	}
}
