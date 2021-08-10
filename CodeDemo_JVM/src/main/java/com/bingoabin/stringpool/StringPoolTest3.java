package com.bingoabin.stringpool;

import org.junit.Test;

public class StringPoolTest3 {

	public static void main(String[] args) throws Exception {

		String a = new String("1") + new String("1") ;
		String c = a.intern();
		String b = "11" ;
		System.out.println(a == b);
		
	}

	@Test
	public void test(){
		String a = new String("1") + new String("1") ;
		String c = a.intern();
		String b = "11" ;
		System.out.println(a == b);
	}

}
