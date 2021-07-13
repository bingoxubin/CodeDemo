package com.bingoabin.designpattern.behavior;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bingoabin
 * @date 2021/6/15 1:49
 */
public class Mediator {
	//中介者模式：是一种行为设计模式，能让你减少对象之间混乱无序的依赖关系。该模式会限制对象之间的直接交互，迫使他们通过一个中介者对象进行合作
	//比如：多个飞机驾驶员协商降落次序，互相协商需要形成一个复杂的网络，需要一个中介者进行协调  比如租房中介  婚介所   将网状结构变成星型结构
	public static void main(String[] args) {
		//定义中介者
		MarriageAgency marriageAgency = new MarriageAgencyImpl();

		//第一位男嘉宾
		Person zhangsan = new Person("zhangsan", 18, Sex.MALE, 18, marriageAgency);

		//四位女嘉宾
		Person person1 = new Person("person1", 25, Sex.FEMALE, 18, marriageAgency);
		Person person2 = new Person("person2", 25, Sex.FEMALE, 18, marriageAgency);
		Person person3 = new Person("person3", 25, Sex.FEMALE, 18, marriageAgency);
		Person person4 = new Person("person4", 18, Sex.FEMALE, 18, marriageAgency);

		// marriageAgency.register(zhangsan);
		// marriageAgency.register(person1);
		// marriageAgency.register(person2);
		// marriageAgency.register(person3);
		// marriageAgency.register(person4);

		marriageAgency.pair(zhangsan);
	}
}

//中介者类  婚介所
interface MarriageAgency {
	void register(Person person); //注册会员

	void pair(Person person); //为person配对
}

//参与者
class Person {
	String name; //姓名
	int age; //年龄
	Sex sex; //性别
	int requestAge;//要求对象的年龄，对对象只有这一个要求
	MarriageAgency agency; //婚姻中介

	public Person(String name, int age, Sex sex, int requestAge, MarriageAgency agency) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.requestAge = requestAge;
		this.agency = agency;
		agency.register(this);
	}

	//寻找对象
	public void findPartner() {
		agency.pair(this);
	}
}

enum Sex {
	MALE,
	FEMALE;
}

//中介者实现类
class MarriageAgencyImpl implements MarriageAgency {
	List<Person> people = new ArrayList<>();

	@Override
	public void register(Person person) {
		people.add(person);
	}

	@Override
	public void pair(Person person) {
		for (Person p : people) {
			if (p.age == person.requestAge && p.sex != person.sex) {
				System.out.println("将 " + person.name + " 与 " + p.name + " 联姻！");
			}
		}
	}
}