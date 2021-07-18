package com.bingoabin.collection;

import com.bingoabin.commonclass.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author xubin03
 * @date 2021/5/19 8:40 下午
 */
public class IteratorClass {
	@Test
	public void test1() {
		Collection coll = new ArrayList();
		coll.add(123);
		coll.add(456);
		coll.add(new Person("Jerry", 20));
		coll.add(new String("Tom"));
		coll.add(false);

		Iterator iterator = coll.iterator();
		//方式一：
		// System.out.println(iterator.next());
		// System.out.println(iterator.next());
		// System.out.println(iterator.next());
		// System.out.println(iterator.next());
		// System.out.println(iterator.next());
		// //报异常：NoSuchElementException
		// System.out.println(iterator.next());

		//方式二：不推荐
		// for(int i = 0;i < coll.size();i++){
		//     System.out.println(iterator.next());
		// }

		//方式三：推荐
		////hasNext():判断是否还有下一个元素
		while (iterator.hasNext()) {
			//next():①指针下移 ②将下移以后集合位置上的元素返回
			System.out.println(iterator.next());
		}
	}

	@Test
	public void test2() {
		Collection coll = new ArrayList();
		coll.add(123);
		coll.add(456);
		coll.add(new Person("Jerry", 20));
		coll.add(new String("Tom"));
		coll.add(false);
		//错误方式一：
		// Iterator iterator = coll.iterator();
		// while((iterator.next()) != null){
		//    System.out.println(iterator.next());
		// }

		//错误方式二：
		//集合对象每次调用iterator()方法都得到一个全新的迭代器对象，默认游标都在集合的第一个元素之前。
		while (coll.iterator().hasNext()) {
			System.out.println(coll.iterator().next());
		}

	}

	//测试Iterator中的remove()
	//如果还未调用next()或在上一次调用 next 方法之后已经调用了 remove 方法，
	// 再调用remove都会报IllegalStateException。
	@Test
	public void test3() {
		Collection coll = new ArrayList();
		coll.add(123);
		coll.add(456);
		coll.add(new Person("Jerry", 20));
		coll.add(new String("Tom"));
		coll.add(false);

		//删除集合中"Tom"
		Iterator iterator = coll.iterator();
		while (iterator.hasNext()) {
			// iterator.remove();
			Object obj = iterator.next();
			if ("Tom".equals(obj)) {
				iterator.remove();
				// iterator.remove();
			}

		}
		//遍历集合
		iterator = coll.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	public static void main(String[] args) {
		List<Integer> result = new ArrayList<Integer>();
		result.add(2);
		result.add(3);
		result.add(5);
		//有问题的
		Iterator<Integer> iterator1 = result.iterator();
		int i = 0;
		while (iterator1.hasNext()) {
			if (i == 3) {
				result.remove(3);
			}
			System.out.println(iterator1.next());
			i++;
		}

		//没问题的
		Iterator<Integer> iterator = result.iterator();
		while (iterator.hasNext()) {
			if (iterator.next() == 5) {
				iterator.remove();
			}
			//System.out.println(iterator.next());
		}
		System.out.println(result.toString());
	}
}
