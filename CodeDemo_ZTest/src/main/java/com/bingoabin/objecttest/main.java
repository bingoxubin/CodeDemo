package com.bingoabin.objecttest;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.*;

/**
 * @author bingoabin
 * @date 2022/10/27 0:57
 * @Description:
 */
@SuppressFBWarnings("NM_CLASS_NAMING_CONVENTION")
public class main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Stack<Integer> stk = new Stack<>();
		Arrays.sort(new int[5]);
		PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
		PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
		String name = sc.next();
		Hero hero;
		if (name.equals("zeus")) {
			hero = new Zeus();
		} else {
			hero = new Athena();
		}
		hero.speek();
	}
}
