package com.bingoabin.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bingoabin
 * @date 2021/6/15 1:48
 */
public class CompositePattern {
	//结构型模式是介绍如何将对象和类组装成一个较大的结构，并同时保持结构的灵活和高效，所以说组合模式本质是一种类和对象的组织结构！
	//组合模式：是一种结构型设计模式，你可以使用它将对象组合成树状结构，并且能像使用独立对象一样使用它们
	//例如：人口普查，先统计区 再统计市 再统计省 直辖市等  一级一级向上   统计命令从上级向下级进行传达，每一层统计当前层人口，最终进行汇总
	public static void main(String[] args) {
		Composite china = new Composite();
		china.add(new City(1000)); //直辖市 北京
		china.add(new City(2000)); //直辖市 上海

		Composite shanXi = new Composite();
		shanXi.add(new City(3000));//大同
		shanXi.add(new City(3000));//太原
		china.add(shanXi);

		//china
		// - 北京
		// - 上海
		// - 山西
		//   - 大同
		//   - 太原
		System.out.println(china.count());
	}
}

//统计接口
interface Counter {
	//计数
	int count();
}

//叶子节点
class City implements Counter {
	private int sum = 0;

	public City(int sum) {
		this.sum = sum;
	}

	public int count() {
		return sum;
	}
}

//容器
class Composite implements Counter {
	private List<Counter> counterList = new ArrayList<>();

	public void add(Counter counter) {
		counterList.add(counter);
	}

	public void delete(Counter counter) {
		counterList.remove(counter);
	}

	public List<Counter> getChild() {
		return counterList;
	}

	public int count() {
		int sum = 0;
		for (Counter counter : counterList) {
			sum += counter.count();
		}
		return sum;
	}
}
