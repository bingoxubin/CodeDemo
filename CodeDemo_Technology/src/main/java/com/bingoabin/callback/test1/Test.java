package com.bingoabin.callback.test1;

/**
 * @author xubin34
 * @date 2021/7/20 10:26 上午
 */
//假设你公司的总经理出差前需要你帮他办件事情，这件事情你需要花些时间去做，这时候总经理肯定不能守着你做完再出差吧，
// 于是就他告诉你他的手机号码叫你如果事情办完了你就打电话告诉他一声；这是一个现实生活中常能碰到的例子，
// 我们用呢就用代码的方式来实现一个这个过程，看一下这个过程究竟是怎样的。
public class Test {
	public static void main(String[] args) {
		// 首先我们需要一个员工
		Employee employee = new Employee();
		// 其次把这个员工指派给总经理
		Manager manager = new Manager(employee);
	}
}
