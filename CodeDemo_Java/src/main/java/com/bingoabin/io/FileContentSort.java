package com.bingoabin.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author bingoabin
 * @date 2021/6/28 17:04
 */
public class FileContentSort {
	//读取文件内容，内容都是数字按,号分割,对文件中的数字进行排序
	public static void main(String[] args) throws IOException {
		//读取文件
		BufferedReader bufferedReader = new BufferedReader(new FileReader("E:\\60.test\\num.txt"));
		//优先队列用于排序
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
		//读取内容，进行放入队列中
		String data;
		while ((data = bufferedReader.readLine()) != null) {
			System.out.println(data);
			String[] str = data.split(",");
			for (String s : str) {
				int i = Integer.parseInt(s);
				queue.offer(i);
			}
		}
		//输出结果
		ArrayList<Integer> res = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			res.add(queue.poll());
		}
		//打印
		System.out.println(res);
	}
}
