package com.bingoabin.createtable;

/**
 * @author bingoabin
 * @date 2021/6/15 0:39
 */
public class SimpleFactory {
	//简单工厂模式：又称为静态工厂方法模式，在简单工厂模式中，可以根据参数的不通返回不同类的实例。
	//简单工厂模式专门定义一个类来负责创建其他类的实例，被创建的实例通常都具有共同的父类
	//比如DateFormat

	//优点：用户不用关心对象的创建
	//缺点：不够灵活
	public static void main(String[] args) {
		Product product = Factory.createProduct("A");
		product.print();
	}
}

//产品的父类
abstract class Product {
	public void print() {
	}
}

//产品A
class ProductA extends Product {
	public void print() {
		System.out.println("A");
	}
}

//产品B
class ProductB extends Product {
	public void print() {
		System.out.println("B");
	}
}

//工厂类
class Factory {
	public static Product createProduct(String type) {
		if (type.equals("A")) {
			return new ProductA();
		} else {
			return new ProductB();
		}
	}
}