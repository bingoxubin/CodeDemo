package com.bingoabin.behavior;

/**
 * @author bingoabin
 * @date 2021/6/15 1:40
 */
public class State {
	//状态模式：允许一个对象在其内部状态改变时改变它的行为，对象看起来似乎修改了它的类，其别名为状态对象，状态模式是一种对象行为型模式
	//比如：在做事情的时候不同状态下的表现是不一样的，通常用if else根据不同状态进行判断，if else层数不能太多，状态模式相当于将状态抽象出来

	//状态模式的主要优点在于封装了转换规则，并枚举了可能的状态，它将所有与某个对状态有关的行为放到了状态类中，并且可以方便的增加新的状态，还可以多个环境对象共享一个状态对象
	//从而减少系统对象的个数，其缺点在于使用状态模式会增加系统（状态）类和（状态）对象的个数，如果使用不当会导致程序结构或代码的混乱
	public static void main(String[] args) {
		LiSi lisi = new LiSi();
		lisi.changeState(new Happy());
		lisi.doSomething();
	}
}

//抽象状态类
abstract class StatePattern {
	abstract void doWork();
}

//状态一：
class Happy extends StatePattern {
	void doWork() {
		System.out.println("happy");
	}
}

//状态二：
class Angry extends StatePattern {
	void doWork() {
		System.out.println("angry");
	}
}

//状态三：
class Sad extends StatePattern {
	void doWork() {
		System.out.println("sad");
	}
}

//具体类  根据状态不同 做不同的事情
class LiSi {
	private StatePattern statePattern;

	//改变状态
	public void changeState(StatePattern statePattern) {
		this.statePattern = statePattern;
	}

	//根据状态做事情
	public void doSomething() {
		statePattern.doWork();
	}
}