package com.bingoabin.designpattern.create;

/**
 * @author bingoabin
 * @date 2021/6/15 1:37
 */
public class AbstractFactory {
	//抽象工厂模式：抽象工厂是工厂方法的一个扩展，抽象工厂包含多个产品体系，当抽象工厂只有一个产品体系的时候，就会退化为工厂模式
	//抽象工厂模式可以使得具体工厂类可以创建多个”大类"(产品体系）对象，但是还是需要修改抽象工厂和具体工厂的代码，违反开闭原则
	public static void main(String[] args) {
		AbsFactory absFactory = new SuperFactory();
		AlienWare computer = (AlienWare) absFactory.createComputer();
		computer.print();
	}
}

//电脑产品大类
interface Computer {
}

class AlienWare implements Computer {
	public void print() {
		System.out.println("alienware");
	}
}

//键盘产品大类
interface KeyBoard {
}

class Filco implements KeyBoard {
	public void print() {
		System.out.println("filco");
	}
}

//抽象工厂
interface AbsFactory {
	Computer createComputer();

	KeyBoard createKeyBoard();
}

//具体工厂
class SuperFactory implements AbsFactory {

	@Override
	public Computer createComputer() {
		return new AlienWare();
	}

	@Override
	public KeyBoard createKeyBoard() {
		return new Filco();
	}
}

