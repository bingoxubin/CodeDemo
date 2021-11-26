package com.bingoabin.java8new;

import java.util.HashMap;
import java.util.Optional;

/**
 * @Author: xubin34
 * @Date: 2021/11/26 5:42 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class _2Optional {
	//Optional
	// JDK 提供三个静态方法来构造一个Optional：
	// 2.1 Optional.of(T value)，该方法通过一个非 null 的 value 来构造一个 Optional，返回的 Optional 包含了 value 这个值。对于该方法，传入的参数一定不能为 null，否则便会抛出 NullPointerException。
	// 2.2 Optional.ofNullable(T value)，该方法和 of 方法的区别在于，传入的参数可以为 null , 但是前面 javadoc 不是说 Optional 只能包含非 null 值吗？原来该方法会判断传入的参数是否为 null，如果为 null 的话，返回的就是 Optional.empty()。
	// 2.3 Optional.empty()，该方法用来构造一个空的 Optional，即该 Optional 中不包含值,其实底层实现还是 如果 Optional 中的 value 为 null 则该 Optional 为不包含值的状态，然后在 API 层面将 Optional 表现的不能包含 null 值，使得 Optional 只存在 包含值 和 不包含值 两种状态。
	static HashMap<Integer, User> map;

	static {
		map = new HashMap<>();
		map.put(1, new User(1, 1));
		map.put(2, new User(2, 2));
		map.put(3, new User(3, 3));
		map.put(4, new User(4, 4));
	}

	public static void main(String[] args) throws Exception {
		//测试一：ofNullable,ifPresent ofNullable可以传入空值，传入的参数如果是null，返回Optional.empty(),ifPresent如果有值调用，否则什么也不做
		System.out.println("==测试ofNullable ifPresent==");
		Optional<User> user1 = Optional.ofNullable(getUserById(4));
		user1.ifPresent(e -> System.out.println("username is:" + e.getId() + "," + e.getAge()));

		//测试二：orElse 如果Optional中有值就返回，否则返回orElse方法传入的参数
		System.out.println("==测试ofNullable orElse==");
		User user = Optional.ofNullable(getUserById(5)).orElse(new User(0, 0));
		System.out.println("Username is:" + user.getId() + "," + user.getAge());

		//测试三：orElseGet orElseGet 与 orElse 方法的区别在于，orElseGet 方法传入的参数为一个 Supplier 接口的实现 —— 当 Optional 中有值的时候，返回值；当 Optional 中没有值的时候，返回从该 Supplier 获得的值。
		System.out.println("==测试ofNullable orElseGet==");
		Optional.ofNullable(getUserById(5)).orElseGet(() -> new User(0, 0));
		System.out.println("Username is:" + user.getId() + "," + user.getAge());

		//测试四：orElseThrow  orElseThrow 与 orElse 方法的区别在于，orElseThrow 方法当 Optional 中有值的时候，返回值；没有值的时候会抛出异常，抛出的异常由传入的 exceptionSupplier 提供
		System.out.println("==测试ofNullable orElseThrow==");
		User user2 = Optional.ofNullable(getUserById(4)).orElseThrow(() -> new Exception("没找到id:" + 5));
		System.out.println("Username is:" + user.getId() + "," + user.getAge());

		//测试五：map  如果当前 Optional 为 Optional.empty，则依旧返回 Optional.empty；否则返回一个新的 Optional，该 Optional 包含的是：函数 mapper 在以 value 作为输入时的输出值。
		System.out.println("==测试ofNullable map==");
		Optional<Integer> user3 = Optional.ofNullable(getUserById(4)).map(e -> e.getAge());
		System.out.println(user3.get());

		//测试六：flatMap flatMap 方法与 map 方法的区别在于，map 方法参数中的函数 mapper 输出的是值，然后 map 方法会使用 Optional.ofNullable 将其包装为 Optional；而 flatMap 要求参数中的函数 mapper 输出的就是 Optional。
		System.out.println("==测试ofNullable flatMap==");
		Optional<String> username = Optional
				.ofNullable(getUserById(5))
				.flatMap(e -> Optional.of(e.getId()))
				.flatMap(id -> Optional.of(id.toString()));
		System.out.println("Username is: " + username.orElse("Unknown"));

		//测试七：filter filter 方法接受一个 Predicate 来对 Optional 中包含的值进行过滤，如果包含的值满足条件，那么还是返回这个 Optional；否则返回 Optional.empty。
		System.out.println("==测试ofNullable filter==");
		Optional<Integer> user5 = Optional
				.ofNullable(getUserById(4))
				.filter(e -> e.getId() < 10)
				.map(e -> e.getId());

		System.out.println("user5 is: " + user5.orElse(2));
	}

	public static User getUserById(int id) {
		return map.get(id);
	}
}
