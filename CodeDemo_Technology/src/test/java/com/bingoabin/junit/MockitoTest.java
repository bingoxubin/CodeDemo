package com.bingoabin.junit;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author xubin34
 * @date 2021/7/23 11:05 上午
 */
public class MockitoTest {
	// 使用Mockito框架
	// 在做单元测试的时候，有的时候用到的一些类，我们构造起来不是那么容易，比如HttpRequest，或者说
	// 某个Service依赖到了某个Dao，想构造service还得先构造dao，这些外部对象构造起来比较麻烦。
	// 所以出现了Mock! 我们可以用 Mock 工具来模拟这些外部对象，来完成我们的单元测试。
	// 实现Mock技术的优秀开源框架有很多，下面以Mockito为例
	@Test
	public void mockTest() {

		//创建mock对象，参数可以是类，也可以是接口
		List<String> list = Mockito.mock(List.class);

		//设置方法的预期返回值 （如果list.get(0) 被调用 ，调用之后返回 helloworld）
		//当然前提是要创建了Mock对象，如这里就是创建了跟 List相关的 Mock对象
		//这里还看不出什么作用，因为Mock 还看不出来，List很容易就能创建
		//假如是一个很复杂的对象，构造这样一个对象很有难度，使用Mock就很方便了，我们不用去一步一步填充它的属性去构造，
		//只需要Mock 一下，就可以拿到这个对象，去测试它的方法，（当然，如果方法有参数我们是需要传递的，像get(0)）
		//这里定义了当我们调用list.get(0)的时候，返回“helloWorld"
		when(list.get(0)).thenReturn("helloworld");
		//list.get(0)的调用就会返回 helloworld
		String result = list.get(0);
		System.out.println(result);

		//验证方法调用(是否调用了get(0))
		verify(list).get(0);

		assertEquals("helloworld", result);
	}

	//模拟期望结果
	@Test
	public void testThen() {
		//mock 一个Iterator类
		Iterator iter = mock(Iterator.class);
		//预设 当 iter调用next()时，第一次返回hello 第n次返回world
		when(iter.next()).thenReturn("hello").thenReturn("world");
		//使用mock的对象
		String res = iter.next() + " " + iter.next();
		//验证结果
		assertEquals("hello world", res);
	}

	//验证交互行为
	@Test
	public void testMock(){
		List mockList = Mockito.mock(List.class);
		//使用mock对象
		mockList.add(1);
		//验证add(1) 行为是否发生
		verify(mockList).add(1);
	}
}
