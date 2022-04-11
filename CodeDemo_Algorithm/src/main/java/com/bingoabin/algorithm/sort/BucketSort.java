package com.bingoabin.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2021/6/12 13:30
 */
public class BucketSort {
	//桶排序
	//桶排序同样是一种线性时间的排序算法，类似于计数排序所创建的统计数组，桶排序需要创建若干个桶来协助排序。
	public static void main(String[] args) {
		double[] res = {4.5, 0.84, 3.25, 2.18, 0.5};
		bucketSort(res);
		System.out.println(Arrays.toString(res));
	}

	public static void bucketSort(double[] array) {
		//1.得到数列的最大值和最小值，并算出差值d
		double max = array[0];
		double min = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
			if (array[i] < min) {
				min = array[i];
			}
		}
		double d = max - min;

		//2.初始化桶
		int bucketNum = array.length;
		ArrayList<LinkedList<Double>> bucketList = new ArrayList<LinkedList<Double>>(bucketNum);
		for (int i = 0; i < bucketNum; i++) {
			bucketList.add(new LinkedList<Double>());
		}

		//3.遍历原始数组，将每个元素放入桶中
		for (int i = 0; i < array.length; i++) {
			int num = (int) ((array[i] - min) * (bucketNum - 1) / d);
			bucketList.get(num).add(array[i]);
		}

		//4.对每个桶内部进行排序
		for (int i = 0; i < bucketList.size(); i++) {
			//JDK底层采用了归并排序或归并的优化版本
			Collections.sort(bucketList.get(i));
		}

		//5.输出全部元素
		int index = 0;
		for (LinkedList<Double> list : bucketList) {
			for (double element : list) {
				array[index] = element;
				index++;
			}
		}
	}
}
