package com.bingoabin.designpattern.behavior;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2021/6/15 1:46
 */
public class Memento {
	//备忘录模式：是一种行为型设计模式，允许在不暴露对象实现细节的情况下保存和恢复对象之前的状态
	//例如：可以撤销的功能，相当于保存了一份快照
	public static void main(String[] args) {
		History history = new History();
		Document document = new Document();
		document.change("abc");
		history.add(document.save());

		document.change("def");
		history.add(document.save());

		document.change("ghi");
		history.add(document.save());

		document.change("lmn");

		document.resume(history.getLastVersion());
		document.print();

		document.resume(history.getLastVersion());
		document.print();

		document.resume(history.getLastVersion());
		document.print();
	}
}

//文档类
class Document {
	private String content; //需要备份的数据

	public BackUp save() {
		return new BackUp(content);
	}

	public void resume(BackUp backUp) {
		content = backUp.content;
	}

	public void change(String content) {
		this.content = content;
	}

	public void print() {
		System.out.println(content);
	}
}

//备忘录接口  定义元数据方法
interface MementoPattern {

}

//备忘录模式
class BackUp implements MementoPattern {
	String content;

	public BackUp(String content) {
		this.content = content;
	}
}

//备忘录栈
class History {
	Deque<BackUp> backUpStack = new LinkedList<BackUp>();

	public void add(BackUp backUp) {
		backUpStack.push(backUp);
	}

	public BackUp getLastVersion() {
		return backUpStack.pop();
	}
}
