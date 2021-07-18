package com.bingoabin.enumclass;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * @author bingoabin
 * @date 2021/6/14 2:08
 */
public class EnumTest {
	//1.为什么使用枚举？  使用枚举的地方会有更强的类型约束，编译期就帮我们检查入参类型，规避问题
	//2.枚举的常用方法？
	//3.枚举的扩展  在枚举类里面可以声明属性、构造函数、方法等
	//4.专用于枚举的集合类   EnumSet EnumMap
	//5.可以实现单例模式、策略模式
	public static void main(String[] args) {
		//1.为什么使用枚举？
		getState(State.NEW);

		//2.枚举的常用方法？
		//获取枚举的序号
		State state1 = State.BLOCKED;
		System.out.println(state1.ordinal());
		//获取名称
		System.out.println(state1.name());
		//根据字符串  返回枚举对象
		System.out.println(State.valueOf("NEW"));

		//4.专用于枚举的集合类   EnumSet EnumMap
		EnumSet<State> enumSet = EnumSet.of(State.BLOCKED, State.RUNNING);
		System.out.println(enumSet.contains(State.WAITING));
		EnumMap enumMap = new EnumMap(Role.class);
		enumMap.put(Role.DISABLE, 1);
		enumMap.put(Role.NORMAL, 2);
	}

	public static void getState(State state) {
		System.out.println(state);
	}
}

enum State {
	NEW,
	BLOCKED,
	WAITING,
	RUNNING;
}

//3.枚举的扩展  在枚举类里面可以声明属性、构造函数、方法等
enum Role {
	NORMAL("ADMIN", 1001),
	LOCKED("USER", 1002),
	DISABLE("OTHER", 1003);

	//自定义属性
	private final String roleName;
	private final Integer roleNum;

	//自定义构造函数
	Role(String roleName, Integer roleNum) {
		this.roleName = roleName;
		this.roleNum = roleNum;
	}

	//自定义方法
	public String getRoleName() {
		return this.roleName;
	}
}
