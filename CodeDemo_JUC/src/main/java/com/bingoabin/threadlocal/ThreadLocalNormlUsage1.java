package com.bingoabin.threadlocal;

/**
 * @author bingoabin
 * @date 2022/9/13 10:01
 * @Description: 常用方式
 */
//典型场景2： 每个线程内需要保存全局变量（例如在拦截器中获取用户信息），可以让不同方法直接使用，避免参数传递的麻烦。
public class ThreadLocalNormlUsage1 {
	public static void main(String[] args) {
		new Service1().process("");
	}
}

class Service1 {

	public void process(String name) {
		User user = new User("超哥");
		UserContextHolder.holder.set(user);
		new Service2().process();
	}
}

class Service2 {
	public void process() {
		User user = UserContextHolder.holder.get();
		// ThreadSafeFormatter.dateFormatThreadLocal.get();
		System.out.println("Service2拿到用户名：" + user.name);
		new Service3().process();
	}
}

class Service3 {
	public void process() {
		User user = UserContextHolder.holder.get();
		System.out.println("Service3拿到用户名：" + user.name);
		UserContextHolder.holder.remove();
	}
}

class UserContextHolder {
	public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class User {

	String name;

	public User(String name) {
		this.name = name;
	}
}