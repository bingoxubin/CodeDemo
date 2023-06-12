package com.bingoabin.acwing._02structure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author bingoabin
 * @date 2023/6/12 14:56
 * @Description:
 */
public class _06SlidingWindow {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] lines = br.readLine().split(" ");
		int n = Integer.parseInt(lines[0]);
		int k = Integer.parseInt(lines[1]);

		String[] strs = br.readLine().split(" ");
		int[] arrs = new int[n];
		for (int i = 0; i < strs.length; i++) arrs[i] = Integer.parseInt(strs[i]);

		List<Integer> min = getMin(arrs, k);
		for (int i = 0; i < min.size(); i++) {
			bw.write(min.get(i) + " ");
		}

		bw.write("\n");
		List<Integer> max = getMax(arrs, k);
		for (int i = 0; i < max.size(); i++) {
			bw.write(max.get(i) + " ");
		}
		bw.flush();
	}

	public static List<Integer> getMin(int[] arrs, int size) {
		List<Integer> min = new ArrayList<>();
		Deque<Integer> minq = new LinkedList<>();
		for(int i = 0;i<arrs.length;i++){
			while(!minq.isEmpty() && arrs[i] <= arrs[minq.peekLast()]) minq.pollLast();
			minq.offerLast(i);
			if(i - minq.peekFirst() + 1 > size) minq.pollFirst();
			if(i + 1 > size) min.add(arrs[minq.peekFirst()]);
		}
		return min;
	}

	public static List<Integer> getMax(int[] arrs, int size) {
		List<Integer> max = new ArrayList<>();
		Deque<Integer> maxq = new LinkedList<>();
		for(int i = 0;i<arrs.length;i++){
			while(!maxq.isEmpty() && arrs[i] >= arrs[maxq.peekLast()]) maxq.pollLast();
			maxq.offerLast(i);
			if(i - maxq.peekFirst() + 1 > size) maxq.pollFirst();
			if(i + 1 >= size) max.add(arrs[maxq.peekFirst()]);
		}
		return max;
	}
}
