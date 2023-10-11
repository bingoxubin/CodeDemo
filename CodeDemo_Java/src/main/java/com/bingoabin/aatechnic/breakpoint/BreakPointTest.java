package com.bingoabin.aatechnic.breakpoint;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author bingoabin
 * @date 2023/10/11 7:35
 * @Description:
 */
public class BreakPointTest {
	//条件断点
	@Test
	public void conditionPoint(){
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		for(int i = 0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		//1.操作方式，在sysout哪一行，打上断点
		//2.在断点上面，右击，加上条件 i == 3
		//3.debug 当i==3的时候，才会停止在sysout
	}

	//断点回退
	@Test
	public void backPoint(){
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		for(int i = 0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		//1.操作方式，list和for循环上，分别打上断点
		//2.然后debug调试，当调试到systemout的时候
		//3.可以点击回退+乌龟的图标进行回退到上一步断点位置
	}

	//执行表达式
	@Test
	public void expressPoint(){
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		for(int i = 0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		//1.操作方式，list和systemout循环上，分别打上断点
		//2.然后debug调试，当调试到systemout的时候
		//3.此时第一个list.get(i) 是1
		//4.可以在下面debug的结果值上面的输入框中输入  list.set(i,10) 进行修改结果
		//5.输出 10 2 3 4 5 6 7 8 9 只改变当前值
	}

	//不想继续执行，直接跳出
	@Test
	public void breakPoint(){
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		for(int i = 0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		//1.操作方式，systemout上，打上断点
		//2.然后debug调试，当调试到systemout的时候
		//3.在下方的左边栈针上，右击进行Force Return然后代码走完，是不会打印出东西的
	}
}
