package com.bingoabin.annotation;

import java.util.LinkedList;
import java.util.List;

/**
 * @author bingoabin
 * @date 2023/5/5 15:34
 * @Description:
 */
//抑制所有警告
@SuppressWarnings("all")
public class AnnotationTest {
	public static void main(String[] args){
		ColorPoint point = new ColorPoint(3, 4, "red");
		System.out.println(point.getString());

		List list = new LinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println(list);
	}
}

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String getString() {
		return String.format("(%d,%d)", x, y);
	}
}

class ColorPoint extends Point {
	String color;

	public ColorPoint(int x, int y, String color) {
		super(x, y);
		this.color = color;
	}

	//表示废弃的，当调用的时候，会提示删除符号
	@Override
	// @Deprecated
	public String getString() {
		return String.format("(%d, %d, %s)", x, y, color);
	}
}
