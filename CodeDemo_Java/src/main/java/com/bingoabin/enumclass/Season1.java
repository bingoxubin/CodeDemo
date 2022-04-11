package com.bingoabin.enumclass;

//方式二：jdk5.0，可以使用enum关键字定义枚举类
//使用enum关键字枚举类
enum Season1 {
	//1.提供当前枚举类的对象，多个对象之间用","隔开，末尾对象";"结束
	SPRING("春天", "春暖花开"),
	SUMMER("夏天", "夏日炎炎"),
	AUTUMN("秋天", "秋高气爽"),
	WINTER("冬天", "冰天雪地");

	//2.声明Season对象的属性:private final修饰
	private final String seasonName;
	private final String seasonDesc;

	//2.私有化类的构造器,并给对象属性赋值
	private Season1(String seasonName, String seasonDesc) {
		this.seasonName = seasonName;
		this.seasonDesc = seasonDesc;
	}

	//4.其他诉求1：获取枚举类对象的属性
	public String getSeasonName() {
		return seasonName;
	}

	public String getSeasonDesc() {
		return seasonDesc;
	}

	public static void main(String[] args) {
		Season1 summer = Season1.SUMMER;
		//toString():返回枚举类对象的名称
		System.out.println(summer.toString());

		//System.out.println(Season1.class.getSuperclass());
		System.out.println("****************");

		//values():返回所有的枚举类对象构成的数组
		Season1[] values = Season1.values();
		for (int i = 0; i < values.length; i++) {
			System.out.println(values[i]);
		}
		System.out.println("****************");

		Thread.State[] values1 = Thread.State.values();
		for (int i = 0; i < values1.length; i++) {
			System.out.println(values1[i]);
		}

		//valueOf(String objName):返回枚举类中对象名是objName的对象。
		Season1 winter = Season1.valueOf("WINTER");
		//如果没有objName的枚举类对象，则抛异常：IllegalArgumentException
		//Season1 winter = Season1.valueOf("WINTER1");
		System.out.println(winter);
	}
}
