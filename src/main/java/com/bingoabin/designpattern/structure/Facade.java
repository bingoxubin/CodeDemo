package com.bingoabin.designpattern.structure;

/**
 * @author bingoabin
 * @date 2021/6/15 1:40
 */
public class Facade {
	//外观模式：要求一个子系统的外部与其内部的通信必须通过一个统一的对象进行。外观模式提供一个高层次的接口，使得子系统更易使用
	//相当于：一个人要去开证明，需要签名，办证，流水等，现在只需要对接政务服务中心，一次搞定

	//违反了开闭原则，如果修改了逻辑，添加了步骤，必须要修改外观类的代码
	public static void main(String[] args) {
		FacadePattern facadePattern = new FacadePattern();
		System.out.println(facadePattern.prove());
	}
}

//子流程一
class SubFlow1 {
	boolean isTrue() {
		return true;
	}
}

//子流程二
class SubFlow2 {
	boolean isOK() {
		return true;
	}
}

//子流程三
class SubFlow3 {
	boolean isGoodMan() {
		return true;
	}
}

//统一流程
class FacadePattern {
	SubFlow1 subFlow1 = new SubFlow1();
	SubFlow2 subFlow2 = new SubFlow2();
	SubFlow3 subFlow3 = new SubFlow3();

	public boolean prove() {
		return subFlow1.isTrue() && subFlow2.isOK() && subFlow3.isGoodMan();
	}
}