package com.bingoabin.structure;

import java.util.HashSet;
import java.util.Set;

/**
 * @author bingoabin
 * @date 2021/6/15 1:43
 */
public class Flyweight {
	//享元模式：运用共享技术有效的支持大量细粒度的对象，池技术，比如字符串常量池
	//例如：可以共享的资源，比如共享单车，百度网盘公共资源只要一个即可（拥有者的创建时间，使用时间不一致）
	//所以有两部分组成：一部分是共享的，一部分是每个对象私有的内容

	//优点：通过池技术，减少内存中对象的数量，内外状态相互分离
	//缺点：内外部状态分离，使得变得复杂
	public static void main(String[] args) {
		BikeFlyWeight bike1 = BikeFlyWeightFactory.getInstance().getBike();
		bike1.ride("zhangsan");
		bike1.back();

		BikeFlyWeight bike2 = BikeFlyWeightFactory.getInstance().getBike();
		bike2.ride("zhaosi");
		// bike2.back();

		BikeFlyWeight bike3 = BikeFlyWeightFactory.getInstance().getBike();
		bike3.ride("wangwu");
		bike3.back();

		System.out.println(bike1 == bike2);
		System.out.println(bike2 == bike3);
	}
}

//定义模板单车的享元类
abstract class BikeFlyWeight {
	//内部状态
	protected Integer state = 0; //0是未使用  1是使用中

	//username 外部状态
	abstract void ride(String userName);

	abstract void back();

	public Integer getState() {
		return state;
	}
}

//具体的摩拜单车
class MoBikeFlyWeight extends BikeFlyWeight {
	//定义新的内部状态，车架号
	private String bikeId;

	public MoBikeFlyWeight(String bikeId) {
		this.bikeId = bikeId;
	}

	@Override
	void ride(String userName) {
		state = 1;
		System.out.println(userName + "骑" + bikeId + "号 自行车出行！");
	}

	@Override
	void back() {
		state = 0;
	}
}

//享元工厂
class BikeFlyWeightFactory {
	//单例模式
	private static BikeFlyWeightFactory instance = new BikeFlyWeightFactory();
	private Set<BikeFlyWeight> pool = new HashSet<BikeFlyWeight>();

	public static BikeFlyWeightFactory getInstance() {
		return instance;
	}

	private BikeFlyWeightFactory() {
		for (int i = 0; i < 2; i++) {
			pool.add(new MoBikeFlyWeight(i + "号"));
		}
	}

	public BikeFlyWeight getBike() {
		for (BikeFlyWeight bike : pool) {
			if (bike.state == 0) {
				return bike;
			}
		}
		return null;
	}
}