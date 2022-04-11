package com.bingoabin.object;

/**
 * @author xubin03
 * @date 2021/5/19 8:01 下午
 */
public class ObjectClass {
	//一：关键字：
	//this、super、static、final、abstract、interface、package、import
	// private:类内部
	// 缺省：同一个包
	// protected：不同包的子类
	// public：同一个工程
	//
	// this 当前对象的    this.name = name   this修饰的是属性  后面的是形参
	// super 子类和父类中定义同名的属性，super.表示调用的是父类中声明的属性，子类构造器中默认添加了super();
	// static 修饰属性  多个对象共享同一个静态变量
	// final 修饰类，不能被继承   修饰属性，显示初始化，构造器初始化，代码块初始化；修饰局部变量（或者形参）不能被修改   修饰方法，不能被重写
	public static void main(String[] args) {
		//二、多态
		Animal a = new Cat();
		a.say();
		Animal b = new Dog();
		b.say();
	}

	//三、代码块
	//局部代码块：
	//作用在方法当中，作用是控制变量的生命周期：
	//在程序中当我们定义完成一个局部变量x之后，并且在接下来的代码中，不想再用到它时，那么就没必要让x在内存中继续占用空间。因此就有了局部代码块。
	public void show() {
		{
			System.out.println("局部代码块运行！");
		}
	}

	//构造代码块：
	//作用在类的定义Body中，作用是给类的部分字段统一初始化：
	//构造代码块与构造函数的区别是：构造代码块是给所有对象进行统一初始化，而构造函数是给对应的对象初始化，因为构造函数是可以多个的，运行哪个构造函数就会建立什么样的对象，但无论建立哪个对象，都会先执行相同的构造代码块。也就是说，构造代码块中定义的是不同对象共性的初始化内容。所以理所当然的，构造代码块在构造函数之前执行。
	public class Apple {
		private String size;

		//构造代码块
		{
			System.out.println("构造代码块运行！");
			size = "E";
		}
	}

}

//静态代码块：
// 	作用有两个：
// 			（1）给类的静态变量赋值；
// 			（2）声明静态变量；
// 	作用在类的Body中，对类中的静态变量初始化：
// 输出：23
// 注意，如果静态代码块中的代码改为：
// static {
// 	int x = 5; // 声明一个静态变量
// }
//则只是在声明静态变量，而且这个静态变量的作用域只在这个代码块中，而不是进行初始化，最后的输出结果是3。
class APP {
	static int x, y; // 静态变量

	static {
		x = 5; // 给静态变量x赋值
	}

	public static void myMethod() {
		y = x++ + ++x; // x++ 先使用x的值再加1；++x先加1再使用x的值
	}

	public static void main(String[] args) {
		x--;
		myMethod();
		System.out.println(x + y + ++x);
	}
}
