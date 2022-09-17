package com.bingoabin.behavior;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bingoabin
 * @date 2022/9/17 17:21
 * @Description: 观察者
 */
public class Observer2 {
}

class Observerable {
	private List<Observers> observers = new ArrayList<>();
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	//添加观察者
	public void addObServer(Observers observer) {
		observers.add(observer);
	}

	//移除观察者
	public void removeObserver(Observers observer) {
		observers.remove(observer);
	}

	//通知
	public void notifyAllObservers(int state) {
		if (state != -1) {
			System.out.println("no notify");
			return;
		}
		for (Observers observer : observers) {
			observer.doEvent();
		}
	}
}

//观察者
interface Observers {
	void doEvent();
}

//IM消息
class IMMessageObserver implements Observers {
	@Override
	public void doEvent() {
		System.out.println("do IM");
	}
}

//手机短信
class MobileNoObserver implements Observers {
	@Override
	public void doEvent() {
		System.out.println("do message");
	}
}

//email
class EmailObserver implements Observers {
	@Override
	public void doEvent() {
		System.out.println("do email");
	}
}


