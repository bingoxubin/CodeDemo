package com.bingoabin.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author bingoabin
 * @date 2023/5/5 16:21
 * @Description:
 */
public class AcwingReflectionTest {
	public static void main(String[] args) throws Exception{
		Class<?> cls = Class.forName("com.bingoabin.reflection.Calculator");
		Object o = cls.newInstance();

		Method method = cls.getMethod("add", int.class, int.class);
		int res = (int)method.invoke(o, 3, 4);
		System.out.println(res);

		Field field = cls.getField("name");
		field.set(o, "My Calculator!");
		System.out.println(field.get(o));

		Constructor<?> constructor = cls.getConstructor(String.class);
		Object new_o = constructor.newInstance("New Calculator!");
		System.out.println(new_o);
	}
}

class Calculator {
	public String name;

	public Calculator() {

	}

	public Calculator(String name) {
		this.name = name;
	}

	public int add(int x, int y) {
		return x + y;
	}
}
