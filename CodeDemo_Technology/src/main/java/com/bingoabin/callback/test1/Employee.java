package com.bingoabin.callback.test1;

/**
 * @author xubin34
 * @date 2021/7/20 10:22 上午
 */
//模拟一个员工
public class Employee {
	// 总经理通过doSomething方法告诉员工要做什么
	public void dosomething(Manager manager, String task) {
		System.out.println("manager通知做：" + task);
		// 当事情做完了我们就通过总经理公布的phoneCall方法通知总经理结果
		manager.phoneCall("做好了");
	}
}
