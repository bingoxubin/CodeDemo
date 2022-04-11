package com.bingoabin.reflection;

/**
 * @author xubin03
 * @date 2021/5/19 9:12 下午
 */
public class Reflection {
	public static void main(String[] args) throws Exception {
		//说说获取Class实例的方式
		//调用运行时类的属性 .class
		Class clazz1 = Reflection.class;

		//通过运行时类的对象 调用getClass()方法
		Reflection p1 = new Reflection();
		Class clazz2 = p1.getClass();

		//调用Class的静态方法：forName(String classPath)
		Class clazz3 = Class.forName("com.atguigu.java.Person");

		//使用类的加载器：ClassLoader
		ClassLoader classLoader = Reflection.class.getClassLoader();
		Class clazz4 = classLoader.loadClass("com.atguigu.java.Person");

		/**
		 //第一：Class.forName(“className”);
		 其实这种方法调运的是：Class.forName(className,true,ClassLoader.getCallerClassLoader())方法
		 - 参数一：className，需要加载的类的名称。
		 - 参数二：true，是否对class进行初始化（需要initialize）
		 - 参数三：classLoader，对应的类加载器

		 //第二：ClassLoader.laodClass(“className”);
		 其实这种方法调运的是：ClassLoader.loadClass(name,false)方法
		 - 参数一：name,需要加载的类的名称
		 - 参数二：false，这个类加载以后是否需要去连接（不需要linking）

		 //第三：区别
		 1.可见Class.forName除了将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块。
		 2.而classloader只干一件事情，就是将.class文件加载到jvm中，不会执行static中的内容，只有在newInstance才会去执行static块。
		 */
	}
}
