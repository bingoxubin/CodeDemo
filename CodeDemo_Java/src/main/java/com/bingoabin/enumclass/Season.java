package com.bingoabin.enumclass;

/**
 * @author xubin03
 * @date 2021/5/19 8:58 下午
 */
//方式一：jdk5.0之前，自定义枚举类
//自定义枚举类
class Season {
	//1.声明Season对象的属性:private final修饰
	private final String seasonName;
	private final String seasonDesc;

	//2.私有化类的构造器,并给对象属性赋值
	private Season(String seasonName, String seasonDesc) {
		this.seasonName = seasonName;
		this.seasonDesc = seasonDesc;
	}

	//3.提供当前枚举类的多个对象：public static final的
	public static final Season SPRING = new Season("春天", "春暖花开");
	public static final Season SUMMER = new Season("夏天", "夏日炎炎");
	public static final Season AUTUMN = new Season("秋天", "秋高气爽");
	public static final Season WINTER = new Season("冬天", "冰天雪地");

	//4.其他诉求1：获取枚举类对象的属性
	public String getSeasonName() {
		return seasonName;
	}

	public String getSeasonDesc() {
		return seasonDesc;
	}

	//4.其他诉求1：提供toString()
	@Override
	public String toString() {
		return "Season{" +
				"seasonName='" + seasonName + '\'' +
				", seasonDesc='" + seasonDesc + '\'' +
				'}';
	}
}