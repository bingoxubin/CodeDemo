package com.bingoabin.algorithm.advanced;

import java.util.*;

/**
 * @author bingoabin
 * @date 2022/4/23 20:46
 */
public class Hongbao {
	public static void main(String[] args){
		LinkedHashSet<Integer> set = new LinkedHashSet<>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);
		set.add(5);
		set.add(6);
		set.add(7);
		System.out.println(set);
		Integer next = set.iterator().next();
		set.remove(next);
		System.out.println(set);

		System.out.println(deviceRedPacket3(100,4));
	}

	public static List<Integer> deviceRedPacket3(Integer money, Integer person) {
		List<Integer> result = new ArrayList<Integer>();
		Set<Integer> segments = new HashSet<Integer>();
		Random random = new Random();
		for(int i = 0;i<person -1;i++){
			int segment = random.nextInt(money);
			int flag = random.nextInt(1) == 0?1:-1;
			while(segments.contains(segment)){
				segment = (segment + flag) % money;
			}
			segments.add(segment);
		}

		List<Integer> segmentList = new ArrayList<Integer>(segments);
		Collections.sort(segmentList);
		for(int i = 0;i< segmentList.size();i++){
			if(i == 0){
				result.add(segmentList.get(i));
			}else{
				result.add(segmentList.get(i) - segmentList.get(i - 1));
			}
		}
		result.add(money - segmentList.get(segmentList.size() - 1));
		return result;
	}
}
