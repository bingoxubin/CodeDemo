package com.bingoabin.behavior;

/**
 * @author bingoabin
 * @date 2021/6/15 1:43
 */
public class CommandPattern {
	//命令模式：是一种行为设计模式，它可将请求转换为一个包含与请求相关的所有信息的独立对象。该转换让你能根据不同的请求将方法参数化，延迟请求执行或将其放入队列中，且能实现可撤销操作。
	public static void main(String[] args) {
		SaveButton saveButton = new SaveButton();
		TextBox textBox = new TextBox();

		PrintCommand printCommand = new PrintCommand(textBox);
		saveButton.bindCommand(printCommand);

		textBox.setContext("ABCDEFG");
		saveButton.doPrint();

		textBox.setContext("ABCDEFGHIJK");
		saveButton.doPrint();
	}
}

//GUI层 保存按钮
class SaveButton {
	private Command command;
	// 省略对渲染的逻辑

	public void bindCommand(Command command) {
		this.command = command;
	}

	public void doPrint() {
		if (command == null) {
			throw new RuntimeException("设备初始化失败！");
		}
		command.execute();
	}
}

//业务逻辑层  打印服务
class PrintService {
	public void print(String text) {
		System.out.println(text);
	}
}

//命令
interface Command {
	void execute();
}

//具体命令
class PrintCommand implements Command {
	private PrintService service = new PrintService();
	private TextBox box;

	public PrintCommand(TextBox box) {
		this.box = box;
	}

	public void execute() {
		service.print(box.getContext());
	}
}

//文本编辑器的文本框
class TextBox {
	private String context;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
}