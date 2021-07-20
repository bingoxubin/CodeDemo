package com.bingoabin.callback.test2;

/**
 * @author xubin34
 * @date 2021/7/20 10:36 上午
 */
public class Manager implements CallBack {
	public Manager(Employee employee) {
		// 想让该让员工做什么
		employee.doSomething(this, "整理公司文件");
	}

	//当员工做完总经理让他做的事后就通过该方法通知总经理
	public void backResult(String result) {
		System.out.println("事情" + result);
	}
}
