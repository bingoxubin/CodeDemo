package com.bingoabin.javase.proxy;

/**
 * @author xubin03
 * @date 2021/5/19 9:04 下午
 */
class StaticReflection {
	public static void main(String[] args) {
		//创建被代理类的对象
		ClothFactory nike = new NikeClothFactory();
		//创建代理类的对象
		ClothFactory proxyClothFactory = new ProxyClothFactory(nike);
		proxyClothFactory.produceCloth();
	}
}

/**
 * 静态代理举例
 * 特点：代理类和被代理类在编译期间，就确定下来了。
 */
interface ClothFactory {
	void produceCloth();
}

//代理类
class ProxyClothFactory implements ClothFactory {
	private ClothFactory factory;//用被代理类对象进行实例化

	public ProxyClothFactory(ClothFactory factory) {
		this.factory = factory;
	}

	@Override
	public void produceCloth() {
		System.out.println("代理工厂做一些准备工作");
		factory.produceCloth();
		System.out.println("代理工厂做一些后续的收尾工作");
	}
}

//被代理类
class NikeClothFactory implements ClothFactory {
	@Override
	public void produceCloth() {
		System.out.println("Nike工厂生产一批运动服");
	}
}
