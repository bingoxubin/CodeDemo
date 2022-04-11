package com.bingoabin.structure;

/**
 * @author bingoabin
 * @date 2021/6/15 1:41
 */
public class Proxy {
	//代理模式：为其他对象提供一种代理以控制对这个对象的访问
	public static void main(String[] args){
		new RealSubjectProxy().doWork();
		new RealSubjectProxy().doSpeak();
	}
}

//目标类接口
interface Subject{
	void doWork();
}

//真正目标类接口
class RealSubject implements Subject {
	public void doWork(){
		System.out.println("hello world");
	}
}

//代理类
class RealSubjectProxy implements Subject {
	private RealSubject realSubject;

	public RealSubjectProxy(){
		try{
			this.realSubject = (RealSubject) this.getClass().getClassLoader().loadClass("com.bingoabin.designpattern.structure.RealSubject").newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void doSpeak(){
		realSubject.doWork();
		System.out.println("do speak");
	}

	@Override
	public void doWork() {
		connect();
		realSubject.doWork();
		log();
	}

	public void connect(){
		System.out.println("connect");
	}

	public void log(){
		System.out.println("log");
	}
}