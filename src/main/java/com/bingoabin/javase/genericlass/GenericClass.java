package com.bingoabin.javase.genericlass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xubin03
 * @date 2021/5/19 9:02 下午
 */
//一、泛型接口
public class GenericClass<T> {
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static void main(String[] args) {
		GenericClass<String> genericClass = new GenericClass<>();
		genericClass.setData("Generic Class");
		System.out.println(genericClass.getData());
	}

	//二、泛型方法
	//泛型方法跟所在的类是不是泛型类，没有任何关系
	//泛型方法可以是static的，在方法调用的时候确定参数类型
	public <E> List<E> copyFromArrayToList(E[] arr) {
		ArrayList<E> list = new ArrayList<>();
		for (E e : arr) {
			list.add(e);
		}
		return list;
	}
}
//子类继承泛型类，
//一种是：public SubOrder extends Order<String>{} ，那么初始化子类无需指定类型，默认为String
//另一种是：public SubOrder<T> extends Order<T>{}，那么初始化子类最好指明类型

//上界<? extend Fruit>   表示所有继承Fruit的子类，以及自己本身，可以读数据，返回Fruit，但是不能添加数据
//下界<? super Apple>    表示所有Apple的父类，以及自己本身，可以读数据，返回object，能添加数据，添加Apple，以及Apple的子类