package com.bingoabin.create;

/**
 * @author bingoabin
 * @date 2021/6/15 1:05
 */
public class FactoryMethod {
	//工厂方法模式：定义一个用于创建对象的接口，让子类决定实例化哪个类。工厂方法使一个类的实例化延迟到其子类
	//比如jdk中的Collection   是一个接口下面的iterator迭代器   由不同工厂进行创建出来，比如ArrayList LinkedList等工厂
	public static void main(String[] args) {
		//创建工厂 华为工厂  可以做一个判断，如果满足什么条件，创建华为工厂，如果满足什么条件，创建小米工厂
		PhoneFactory phoneFactory = new HuaWeiFactory();
		phoneFactory.createPhone().print();
	}
}

//手机产品模板
interface Phone {
	public void print();
}

//华为手机
class HuaWei implements Phone {
	public void print() {
		System.out.println("huawei");
	}
}

//小米手机
class XiaoMi implements Phone {
	public void print() {
		System.out.println("xiaomi");
	}
}

//通用工厂
interface PhoneFactory {
	Phone createPhone();
}

//华为工厂
class HuaWeiFactory implements PhoneFactory {
	public Phone createPhone() {
		return new HuaWei();
	}
}

//小米工厂
class AppleFactory implements PhoneFactory {
	public Phone createPhone() {
		return new XiaoMi();
	}
}