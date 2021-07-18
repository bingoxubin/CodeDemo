package com.bingoabin.algorithm.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author xubin03
 * @date 2021/5/25 2:56 下午
 */
public class UnionSection {
	//NC37 合并区间 https://www.nowcoder.com/practice/69f4e5b7ad284a478777cb2a17fb5e6a?tpId=117&tqId=37737&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
	//样例：[[10,30],[20,60],[80,100],[150,180]]  返回：[[10,60],[80,100],[150,180]]
	//分析：给一个二维数组，将二维数组中的单个数组进行合并，首尾相连
	//思路：先将输入进行排序，然后进行合并，先加入的头比arraylist最后一个的尾大的话，就加入arraylist，否则将前面一个的end，更新成新加入节点的end和上一个end的最大值
	public static void main(String[] args) {
		Interval int1 = new Interval(10, 30);
		Interval int2 = new Interval(20, 60);
		Interval int3 = new Interval(80, 100);
		Interval int4 = new Interval(150, 180);
		ArrayList<Interval> intervals = new ArrayList<>();
		intervals.add(int1);
		intervals.add(int2);
		intervals.add(int3);
		intervals.add(int4);
		ArrayList<Interval> res = merge(intervals);
		for (Interval interval : res) {
			System.out.println("{" + interval.start + "," + interval.end + "}");
		}
	}

	public static ArrayList<Interval> merge(ArrayList<Interval> intervals) {
		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval i1, Interval i2) {
				if (i1.start == i2.start) {
					return i1.end - i2.end;
				}
				return i1.start - i2.start;
			}
		});
		ArrayList<Interval> res = new ArrayList<>();
		for (Interval temp : intervals) {
			if (res.size() == 0 || temp.start > res.get(res.size() - 1).end) {
				res.add(temp);
			} else {
				res.get(res.size() - 1).end = Math.max(temp.end, res.get(res.size() - 1).end);
			}
		}
		return res;
	}
}

class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}
}
