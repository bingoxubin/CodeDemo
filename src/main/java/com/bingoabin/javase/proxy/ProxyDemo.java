package com.bingoabin.javase.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author bingoabin
 * @date 2021/6/14 1:33
 */
public class ProxyDemo {
	public static void main(String[] args) {
		//静态代理
		Speaker speaker = new ZhangSanLawyer();
		speaker.speak();
		//动态代理  JDK方式
		Lawyer lawyer = new Lawyer(new ZhangSan());
		Speaker speaker1 = (Speaker) Proxy.newProxyInstance(ProxyDemo.class.getClassLoader(), new Class[]{Speaker.class}, lawyer);
		speaker1.speak();
		//动态代理  CGLib
		LawyerInteceptor lawyerInteceptor = new LawyerInteceptor(new LiSi());
		LiSi lisi = (LiSi) Enhancer.create(LiSi.class, lawyerInteceptor);
		lisi.speak();
	}
}

//静态代理 一个代理类代表一个本体，内部有个本体的实例
interface Speaker {
	public void speak();
}

class ZhangSan implements Speaker {
	public void speak() {
		System.out.println("我是张三");
	}
}

class ZhangSanLawyer implements Speaker {
	private ZhangSan zhangsan = new ZhangSan();

	public void speak() {
		System.out.println("zhangsan 代理开始");
		zhangsan.speak();
		System.out.println("zhangsan 代理结束");
	}
}

//动态代理 JDK方式  本体必须实现接口，才能进行动态代理
class Lawyer implements InvocationHandler {
	private Object obj;

	public Lawyer(Object obj) {
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (method.getName().equals("speak")) {
			System.out.println("zhangsan 代理开始");
			method.invoke(obj, args);//反射的方式
			System.out.println("zhangsan 代理结束");
		}
		return null;
	}
}

//动态代理 CGLib方式  本体可以给没有实现接口的类，进行动态代理
//        是JDK代理的补充
class LiSi{
	public void speak(){
		System.out.println("我是李四");
	}
}

class LawyerInteceptor implements MethodInterceptor {
	private Object obj;
	public LawyerInteceptor(Object obj){
		this.obj = obj;
	}
	@Override
	public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		if (method.getName().equals("speak")) {
			System.out.println("zhangsan 代理开始");
			method.invoke(obj, args);//反射的方式
			System.out.println("zhangsan 代理结束");
		}
		return null;
	}
}


