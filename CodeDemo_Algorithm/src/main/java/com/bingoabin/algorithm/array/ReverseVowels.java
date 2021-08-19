package com.bingoabin.algorithm.array;

/**
 * @Author: xubin34
 * @Date: 2021/8/19 10:32 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ReverseVowels {
	//leetcode 345. 反转字符串中的元音字母
	//示例：输入："hello"   输出："holle"
	//分析：编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
	//思路：采用双指针的方式，一个从左边找元音   一个从右边找元音， 找到后 进行交换即可
	public static void main(String[] args) {
		String str = "leetcode";
		ReverseVowels res = new ReverseVowels();
		System.out.println(res.reverseVowels(str));
	}

	//思路：采用双指针的方式，一个从左边找元音   一个从右边找元音， 找到后 进行交换即可
	public String reverseVowels(String s) {
		//将字符串转化为char数组
		char[] ans = s.toCharArray();
		//标记左右两个指针
		int left = 0;
		int right = ans.length - 1;
		while (left < right) {
			//从左边开始找，找到元音
			while (left < right && !check(ans[left])) left++;
			//从右边开始找，找到元音
			while (left < right && !check(ans[right])) right--;
			//将两个元音位置进行交换
			if (left < right) {
				change(ans, left, right);
				left++;
				right--;
			}
		}
		return String.valueOf(ans);
	}

	//验证是否是元音字母
	public boolean check(char ch) {
		if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
			return true;
		}
		return false;
	}

	//将两个字母进行交换
	public void change(char[] arr, int left, int right) {
		char temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}
}
