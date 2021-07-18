package com.bingoabin.create;

import lombok.Data;

/**
 * @author bingoabin
 * @date 2021/6/15 1:45
 */
public class BuilderPattern {
	//建造者模式：也叫作生成器模式，是一种创建型设计模式，可以使得我们分步骤创建复杂对象
	//比如：创建一个房子，需要创建四面墙 地板等 房门 窗户 地板等
	public static void main(String[] args) {
		House build = new House.Builder().setWindow(new Window()).setDoor(new Door()).setWall(new Wall()).build();
	}
}

@Data
//或者使用 @Builder
class House {
	private Window window;
	private Door door;
	private Wall wall;

	public House(Builder builder) {
		this.window = builder.window;
		this.door = builder.door;
		this.wall = builder.wall;
	}

	static final class Builder {
		private Window window;
		private Door door;
		private Wall wall;

		public Builder setWindow(Window window) {
			this.window = window;
			return this;
		}

		public Builder setDoor(Door door) {
			this.door = door;
			return this;
		}

		public Builder setWall(Wall wall) {
			this.wall = wall;
			return this;
		}

		public House build() {
			return new House(this);
		}
	}
}

class Window {

}

class Door {

}

class Wall {

}
