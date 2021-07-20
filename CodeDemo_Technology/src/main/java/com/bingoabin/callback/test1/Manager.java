package com.bingoabin.callback.test1;

/**
 * @author xubin34
 * @date 2021/7/20 10:22 上午
 */
//模拟一个总经理
public class Manager {
	//传入一个员工对象
	public Manager(Employee e){
		//想让员工做什么
		e.dosomething(this,"帮我做件事情，做好了给我打电话");
	}

	//当员工做完总经理让他做的事后就通过该方法通知总经理 事情结果
	public void phoneCall(String result){
		System.out.println(result);
	}
}
