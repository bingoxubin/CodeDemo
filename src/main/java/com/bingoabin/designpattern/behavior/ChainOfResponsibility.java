package com.bingoabin.designpattern.behavior;

/**
 * @author bingoabin
 * @date 2021/6/15 1:42
 */
public class ChainOfResponsibility {
	//责任链模式：是一种处理请求的模式，它让多个处理器都有机会处理该请求，直到其中某个处理成功为止。责任链模式把多个处理器串成链，然后让请求在链上传递
	//例如：请假流程中  需要层层审批

	//缺点：性能问题，链头走到链尾  不易于排查
	public static void main(String[] args) {
		Handler leader = new Leader();
		Handler boss = new Boss();
		leader.setNextHandler(boss);

		leader.process(9);
		leader.process(11);
	}
}

//抽象的处理类
abstract class Handler {
	//下一个处理类
	protected Handler nextHandler;

	public void setNextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}

	public abstract void process(Integer info);
}

//leader处理
class Leader extends Handler {

	@Override
	public void process(Integer info) {
		if (info > 0 && info < 10) {
			System.out.println("leader process");
		} else {
			nextHandler.process(info);
		}
	}
}

//boss处理
class Boss extends Handler {

	@Override
	public void process(Integer info) {
		System.out.println("boss process");
	}
}
