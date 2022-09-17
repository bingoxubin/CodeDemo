package com.bingoabin.behavior;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bingoabin
 * @date 2021/6/15 1:40
 */
public class Observer {
	//观察者模式：一种依赖关系，一对多依赖，也称为发布订阅模式。定义对象间的一种一对多依赖关系，使得每当一个对象状态发生改变时，其相关依赖对象皆得到通知并被自动更新
	//使用场景：完成某个事件后，异步通知场景。如，登陆成功，发个IM消息等
	public static void main(String[] args) {
		ZhangSan zhangSan = new ZhangSan();
		zhangSan.borrow(new ZhaoSi());
		zhangSan.borrow(new WangWu());

		zhangSan.notiryCredit();
	}
}

//借款方
interface Debit {
	//借钱
	void borrow(Credit creadit);

	//通知还钱
	void notiryCredit();
}

//借款方 - zhangsan
class ZhangSan implements Debit {
	private List<Credit> list = new ArrayList<Credit>();

	@Override
	public void borrow(Credit credit) {
		list.add(credit);
	}

	@Override
	public void notiryCredit() {
		list.forEach(credit -> credit.takeMoney());
	}
}

//贷款方
interface Credit {
	void takeMoney();
}

//贷款方 - zhaosi
class ZhaoSi implements Credit {

	@Override
	public void takeMoney() {
		System.out.println("赵四 来拿钱");
	}
}

//贷款方 - wangwu
class WangWu implements Credit {

	@Override
	public void takeMoney() {
		System.out.println("王五 来拿钱");
	}
}