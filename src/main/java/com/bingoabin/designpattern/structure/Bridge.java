package com.bingoabin.designpattern.structure;

/**
 * @author bingoabin
 * @date 2021/6/15 1:49
 */
public class Bridge {
	//桥接模式：将抽象与实现解耦，使得两者可以独立地变化。将继承关系转换为关联关系，从而降低了类与类之间的耦合，减少了代码的编写量
	//比如：一个品牌，有n个商品，并且呢有m个销售渠道，可以通过n个商品每个都实现m个渠道，但是如果再新增一个类 新增一个渠道 就非常麻烦；可以通过抽象出来商品类  渠道类  进行桥接
	//
	public static void main(String[] args) {
		Abstraction abstraction = new Abstraction(new ConcreteImplementor());
		abstraction.doWork();
	}
}

//抽象类（需不需要抽象看实际业务需要）  比如品牌类
class Abstraction {
	private Implementor implementor;

	public Abstraction(Implementor implementor) {
		this.implementor = implementor;
	}

	public void doWork() {
		this.implementor.doSomething();
	}
}

//实现接口 比如销售渠道
interface Implementor {
	public void doSomething();
}

//实现类
class ConcreteImplementor implements Implementor {
	public void doSomething() {
		System.out.println("hello");
	}
}