package com.bingo.kryo;

import java.util.*;

public class TestTree {

	public static void main(String[] args) {
		
		Map<Emp, Emp> empMap = new HashMap<>();
		
		for ( int i = 1; i <= 12; i++ ) {
			Emp emp = new Emp();
			empMap.put(emp, emp);
			System.out.println("增加数据成功 = " + i);
		}
	}

}
