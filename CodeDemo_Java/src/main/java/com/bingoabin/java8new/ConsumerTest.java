package com.bingoabin.java8new;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author bingoabin
 * @date 2023/10/11 12:44
 * @Description:
 */
public class ConsumerTest {

	@Test
	public void test1(){
		Consumer<String> consumer = s -> System.out.println(s);

		consumer.accept("zhangsan");
		consumer.accept("lisi");
	}

	@Test
	public void test2(){
		List<Integer> oddList = new ArrayList<>();
		List<Integer> evenList = new ArrayList<>();

		Consumer<Integer> storeNum = n -> {
			if( n % 2 == 0){
				evenList.add(n);
			}else{
				oddList.add(n);
			}
		};

		Consumer<List<Integer>> printList = List -> List.forEach(n -> System.out.println(n));

		storeNum.accept(10);
		storeNum.accept(15);
		storeNum.accept(25);
		storeNum.accept(30);

		printList.accept(oddList);

		printList.accept(evenList);
	}
}
