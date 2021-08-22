package com.bingoabin.algorithm.string;

/**
 * @Author: xubin34
 * @Date: 2021/8/22 12:48 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class CompressString {
	//Leetcode 443. 压缩字符串
	//案例：输入：chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
	//     输出：返回 4 ，输入数组的前 4 个字符应该是：["a","b","1","2"]。
	//分析：将字符串进行压缩，相同的字符统计次数，然后进行压缩
	//思路：定义初始下标i，以及修改原数组到得位置index；
	//     找到跟i 不相同的 idx下标所在的位置
	//     然后统计个数，并且将index赋值为i的字符串
	//     如果数量>1 那么进行拆分数字，从个位放到数组中，进行翻转
	//     最后i 为下面的idx
	public static void main(String[] args) {
		char[] chars = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
		CompressString compressString = new CompressString();
		System.out.println(compressString.compress(chars));
	}

	public int compress(char[] cs) {
		int len = cs.length;
		int index = 0;
		int i = 0;
		while (i < len) {
			//找到下一个跟i下标不相同的字符 idx
			int idx = i;
			while (idx < len && cs[idx] == cs[i]) idx++;
			//计算前面相同的数量
			int cnt = idx - i;
			//将字符进行填充
			cs[index++] = cs[i];
			//如果cnt > 1 那么将数字进行拆分，仿佛数组中，并进行翻转，因为是从个位开始插入的
			if (cnt > 1) {
				int start = index, end = index;
				while (cnt != 0) {
					cs[end++] = (char) ((cnt % 10) + '0');
					cnt /= 10;
				}
				reverse(cs, start, end - 1);
				index = end;
			}
			//处理完后 i更新到idx   主要：不能用for循环，for循环i还会加1
			i = idx;
		}
		System.out.println(String.valueOf(cs));
		return index;
	}

	public void reverse(char[] arr, int left, int right) {
		while (left < right) {
			char temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			left++;
			right--;
		}
	}
}
