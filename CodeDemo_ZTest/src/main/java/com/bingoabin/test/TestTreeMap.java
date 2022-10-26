package com.bingoabin.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author bingoabin
 * @date 2022/10/26 8:52
 * @Description:
 */
public class TestTreeMap {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		String[] strs = br.readLine().split(" ");
		int[] arrs = new int[n];
		for (int i = 0; i < n; i++) {
			arrs[i] = Integer.parseInt(strs[i]);
		}

		TreeMap<Integer, Integer> map = new TreeMap<>();
		map.put(arrs[0], 0);
		for (int i = 1; i < n; i++) {
			Map.Entry<Integer, Integer> up = map.ceilingEntry(arrs[i]);
			Map.Entry<Integer, Integer> down = map.floorEntry(arrs[i]);
			int res = Integer.MAX_VALUE;
			int pos = -1;
			if (down != null) {
				res = arrs[i] - down.getKey();
				pos = down.getValue();
			}
			if (up != null && up.getKey() - arrs[i] <= res && (pos == -1 || up.getValue() < pos)) {
				res = up.getKey() - arrs[i];
				pos = up.getValue();
			}
			bw.write(res + " " + (pos + 1) + "\n");
			map.put(arrs[i], i);
		}
		bw.flush();
	}


	public static void test() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		String[] strs = br.readLine().split(" ");
		int[] w = new int[n];
		for (int i = 0; i < n; i ++ )
			w[i] = Integer.parseInt(strs[i]);

		TreeMap<Integer, Integer> map = new TreeMap<>();
		map.put(w[0], 0);
		for (int i = 1; i < n; i ++ ) {
			Map.Entry<Integer, Integer> up = map.ceilingEntry(w[i]);
			Map.Entry<Integer, Integer> down = map.floorEntry(w[i]);
			int res = Integer.MAX_VALUE, pos = -1;
			if (down != null) {
				res = w[i] - down.getKey();
				pos = down.getValue();
			}
			if (up != null && up.getKey() - w[i] < res) {
				res = up.getKey() - w[i];
				pos = up.getValue();
			}
			bw.write(res + " " + (pos + 1) + "\n");
			map.put(w[i], i);
		}

		bw.flush();
	}
}
