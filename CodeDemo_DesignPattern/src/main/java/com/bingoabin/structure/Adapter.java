package com.bingoabin.structure;

/**
 * @author bingoabin
 * @date 2021/6/15 1:39
 */
public class Adapter {
	//适配器模式：将一个类的接口变换成客户端所期待的另一种接口，从而使根本因接口不匹配而无法在一起工作的两个类能够在一起工作
	//方式一：关联使用，把被适配的对象放到适配器内，通过访问适配器的方法，间接调用被适配的对象
	//方式二：继承方法，成为子类，通过访问子类间接访问原有类

	//适配器模式原有接口挺好用的，但是客户端不兼容，适配器只提供一个转换的作用，相当于客户端直接访问原有接口之间的桥梁，不会改变原先的接口
	//但是装饰器模式不一样，就是为了对原有接口进行功能扩展，所以说需要修改原来的接口
	public static void main(String[] args) {
		Speaker speaker = new Speaker();
		AdapterSpeaker adapter = new AdapterSpeaker(speaker);
		String res = adapter.translate();
		System.out.println(res);
	}
}

//演讲者
class Speaker {
	public String speak() {
		return "im chinese";
	}
}

//适配器接口
interface Translate {
	public String translate();
}

//适配器,将演讲者的话进行手语翻译
class AdapterSpeaker implements Translate {
	private Speaker speaker;

	public AdapterSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	@Override
	public String translate() {
		String speak = speaker.speak();
		//进行翻译
		speak = "我是中国人";
		return speak;
	}
}
