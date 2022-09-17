package com.bingoabin.behavior;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import sun.misc.Request;

import javax.annotation.PostConstruct;
import javax.xml.ws.Response;
import java.util.List;

/**
 * @author bingoabin
 * @date 2022/9/17 16:23
 * @Description: 责任链模式
 */
@Component("ChainOfResponsiblility2")
public class ChainOfResponsibility2 {
	//自动注入各个责任链的对象
	@Autowired
	private List<AbstracHandler> abstracHandlerList;
	private AbstracHandler abstracHandler;

	@PostConstruct
	public void initializeChainFilter() {
		for (int i = 0; i < abstracHandlerList.size(); i++) {
			if (i == 0) {
				abstracHandler = abstracHandlerList.get(0);
			} else {
				AbstracHandler currentHandler = abstracHandlerList.get(i - 1);
				AbstracHandler nextHandler = abstracHandlerList.get(i);
				currentHandler.setNextHandler(nextHandler);
			}
		}
	}

	//直接调用这个方法使用
	public Response exec(Request request, Response response) {
		abstracHandler.filter(request, response);
		return response;
	}

	public AbstracHandler getAbstracHandler() {
		return abstracHandler;
	}

	public void setAbstracHandler(AbstracHandler abstracHandler) {
		this.abstracHandler = abstracHandler;
	}
}

abstract class AbstracHandler {
	//责任链的下一个对象
	private AbstracHandler nextHandler;

	public AbstracHandler getNextHandler() {
		return nextHandler;
	}

	public void setNextHandler(AbstracHandler nextHandler) {
		this.nextHandler = nextHandler;
	}

	//具体参数拦截逻辑，给子类去实现
	public void filter(Request request, Response response) {
		doFilter(request, response);
		if (getNextHandler() != null) {
			getNextHandler().filter(request, response);
		}
	}

	abstract void doFilter(Request request, Response response);
}

//参数校验对象
@Component
@Order(1) //最先校验
class CheckParamFilterObject extends AbstracHandler {

	@Override
	void doFilter(Request request, Response response) {
		System.out.println("非空参数校验");
	}
}

//安全校验
@Component
@Order(2)
class CheckSecurityFilterObject extends AbstracHandler {
	@Override
	void doFilter(Request request, Response response) {
		System.out.println("安全校验");
	}
}
