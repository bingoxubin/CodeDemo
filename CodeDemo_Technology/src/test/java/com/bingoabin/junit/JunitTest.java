package com.bingoabin.junit;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author xubin34
 * @date 2021/7/23 10:03 上午
 */
public class JunitTest {
	public int add(int one, int another) {
		return one + another;
	}

	//不使用测试框架
	public static void main(String[] args) {
		JunitTest junitTest = new JunitTest();
		int sum = junitTest.add(1, 2);
		assert sum == 3;
	}

	//使用测试框架
	@Test
	public void testAdd() {
		JunitTest junitTest = new JunitTest();
		int sum = junitTest.add(1, 2);
		assertThat(sum).isEqualTo(3);
	}

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
		Mockito.when(list.get(0)).thenReturn("helloworld");
		//list.get(0)的调用就会返回 helloworld
		String result = list.get(0);
		System.out.println(result);

		//验证方法调用(是否调用了get(0))
		Mockito.verify(list).get(0);

		Assert.assertEquals("helloworld", result);
	}
}
