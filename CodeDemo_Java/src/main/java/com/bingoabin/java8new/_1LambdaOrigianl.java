package com.bingoabin.java8new;

/**
 * @Author: xubin34
 * @Date: 2021/11/26 5:29 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class _1LambdaOrigianl {
	public static final int method(int x, int y) {
		return x * x + y * y;
	}

	//接口 定义方法
	public static interface IntOps {
		public int right(int y);
	}

	//lambda两个输入值，转化成一个一个输入
	public static IntOps left(final int x) {
		return new IntOps() {
			@Override
			public int right(int y) {
				return x * x + y * y;
			}
		};
	}

	//演变一次
	public static IntOps left1(int x){
		return (int y) -> {
			return x * x + y * y;
		};
	}

	//演变二次
	public static IntOps left2(int x){
		return (y) -> {
			return x * x + y * y;
		};
	}

	//演变三次
	public static IntOps left3(int x){
		return y -> {
			return x * x + y * y;
		};
	}

	//演变四次
	public static IntOps left4(int x){
		return y -> x * x + y * y;
	}


	public static void main(String[] args) {
		System.out.println(method(5, 2));
		System.out.println(left(5).right(2));
		System.out.println(left4(5).right(2));
	}
}
