package com.bingoabin.threadcreate;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author xubin03
 * @date 2021/5/25 3:55 下午
 */

////实现Callable接口
// 方式三：实现Callable接口：
// 1. 与使用Runnable相比， Callable功能更强大些
// 2. 相比run()方法，可以有返回值
// 3. 方法可以抛出异常
// 4. 支持泛型的返回值
// 5. 需要借助FutureTask类，比如获取返回结果
//
// Future接口
// 1. 可以对具体Runnable、Callable任务的执行结果进行取消、查询是否完成、获取结果等。
// 2. FutrueTask是Futrue接口的唯一的实现类
// 3. FutureTask 同时实现了Runnable, Future接口。它既可以作为Runnable被线程执行，又可以作为Future得到Callable的返回值
public class CallableTest {
	/**
	 * 创建线程的方式三：实现Callable接口。 --- JDK 5.0新增
	 * <p>
	 * 如何理解实现Callable接口的方式创建多线程比实现Runnable接口创建多线程方式强大？
	 * 1. call()可以有返回值的。
	 * 2. call()可以抛出异常，被外面的操作捕获，获取异常的信息
	 * 3. Callable是支持泛型的
	 */
	public static void main(String[] args) {
		//3.创建Callable接口实现类的对象
		Callable1 callableTest = new Callable1();
		//4.将此Callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask的对象
		FutureTask futureTask = new FutureTask(callableTest);
		//5.将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()
		new Thread(futureTask).start();
		try {
			//6.获取Callable中call方法的返回值
			//get()返回值即为FutureTask构造器参数Callable实现类重写的call()的返回值。
			Object sum = futureTask.get();
			System.out.println("总和为：" + sum);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	//1.创建一个实现Callable的实现类
	static class Callable1 implements Callable {
		//2.实现call方法，将此线程需要执行的操作声明在call()中
		@Override
		public Object call() throws Exception {
			int sum = 0;
			for (int i = 1; i <= 100; i++) {
				if (i % 2 == 0) {
					System.out.println(i);
					sum += i;
				}
			}
			return sum;
		}
	}
}
