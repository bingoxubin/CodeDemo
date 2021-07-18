package com.bingoabin.behavior;

/**
 * @author bingoabin
 * @date 2021/6/15 1:42
 */
public class TemplateMethod {
	//模板方法：定义一个操作中的算法框架，而将一些步骤延迟到子类中。使得子类可以不改变一个算法的结构即可重新定义该算法的某些特定步骤
	//比如：炒菜的模板  放油 爆香 放菜 放盐 翻炒 放调味料 关火 起锅
	public static void main(String[] args) {
		Cooking cooking = new CookingFood();
		cooking.cook();
	}
}

//定义模板
abstract class Cooking {
	protected abstract void step1();

	protected abstract void step2();

	//模板方法
	public void cook() {
		System.out.println("start cooking");
		step1();
		step2();
		System.out.println("stop cooking");
	}
}

//实现类 子类实现step1 step2
class CookingFood extends Cooking {

	@Override
	protected void step1() {
		System.out.println("step1");
	}

	@Override
	protected void step2() {
		System.out.println("step2");
	}
}