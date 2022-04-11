package com.bingoabin.structure;

/**
 * @author bingoabin
 * @date 2021/6/15 1:38
 */
public class Decorator {
	//装饰者模式：给一个类或者对象增加新的功能。动态的给一个对象增加一些额外的功能，就增加功能来说，装饰着比生成子类更为灵活
	//举例：一个机器人有讲话 唱歌的功能，需要加上扫地 跳舞的功能
	//方式一：继承机制，继承一个现有的类，在子类中进行扩展
	//方式二：关联机制，把一个类的对象嵌入到另一个类中，相当于把机器人嵌入到箱子中，给他套一个壳子，来扩展功能；这个壳子就是我们说的装饰器

	//区别：第一种方式是静态的，一定要写一个新的子类，对类层级进行扩展，第二种方法是动态的，拿到一个对象就可以对其进行扩展，不需要修改原有的逻辑
	public static void main(String[] args) {
		//jdk中输入输出流 inputstream 大量使用装饰者模式
		Robot1 robot1 = new Robot1();
		Robot2 robot2 = new Robot2(robot1);
		robot2.doMorething();
	}
}

//接口
interface Robot {
	void doSomething();
}

//第一代机器人
class Robot1 implements Robot {

	@Override
	public void doSomething() {
		System.out.println("讲话");
		System.out.println("唱歌");
	}
}

//第二代机器人：在第一代原有的基础上进行扩展
class Robot2 implements Robot {
	private Robot robot;

	public Robot2(Robot robot) {
		this.robot = robot;
	}

	@Override
	public void doSomething() {
		robot.doSomething();
	}

	public void doMorething() {
		robot.doSomething();
		System.out.println("扫地");
		System.out.println("跳舞");
	}
}