package com.bingoabin.createtable;

/**
 * @author bingoabin
 * @date 2021/6/15 1:44
 */
public class Prototype {
	//原型模式：
	//比如：你有一个对象  你想生成一个和它一样的复制品，解决复制对象的问题；将复制过程称为克隆
	public static void main(String[] args) {
		Plane plane = new Plane();
		System.out.println(plane.getName() + "  " + plane.getType());

		Plane clone = (Plane) plane.clone();
		System.out.println(clone.getName() + "  " + clone.getType());

		// Name:0.7387932220702589  Type:0.29680329452231247
		// Name:0.7387932220702589  Type:0.29680329452231247
	}
}

//原型接口
interface Proto {
	Object clone();
}

//飞机类
class Plane implements Proto {
	private String name;
	private String type;

	public Plane() {
		name = "Name:" + Math.random();
		type = "Type:" + Math.random();
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	//实现克隆接口方法，并且添加带参的构造器
	@Override
	public Object clone() {
		return new Plane(this);
	}

	public Plane(Plane plane) {
		this.name = plane.name;
		this.type = plane.type;
	}
}
