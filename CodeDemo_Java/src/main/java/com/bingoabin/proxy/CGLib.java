package com.bingoabin.proxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xubin03
 * @date 2021/5/19 9:08 下午
 */
public class CGLib {
	public static void main(String[] args) {
		//在指定目录下生成动态代理类，我们可以反编译看一下里面到底是一些什么东西
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\57.问题处理\\cglib");
		//创建Enhancer对象，类似于JDK动态代理的Proxy类，下一步就是设置几个参数
		Enhancer enhancer = new Enhancer();
		//设置目标类的字节码文件
		enhancer.setSuperclass(Dog.class);
		//设置回调函数
		enhancer.setCallback(new MyMethodInterceptor());
		//这里的creat方法就是正式创建代理类
		Dog proxyDog = (Dog) enhancer.create();
		//调用代理类的run方法
		proxyDog.run("旺财");
	}
}

class Dog {
	final public void run(String name) {
		System.out.println("dog:" + name + "------run");
	}

	public void eat() {
		System.out.println("dog------eat");
	}
}

class MyMethodInterceptor implements MethodInterceptor {
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("这里是对目标类进行增强！！！");
		//注意这里的方法调用，不是用反射！
		Object object = methodProxy.invokeSuper(o, objects);
		return object;
	}
}
