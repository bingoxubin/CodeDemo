package com.bingoabin.test;

import com.bingoabin.domain.People;
import com.bingoabin.domain.PersonBO;
import com.bingoabin.utils.JsonUtil;
import com.bingoabin.utils.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author bingoabin
 * @Description: 测试分组
 */
public class TestGrouping {
	public static void main(String[] args){
		People people1 = new People(1, "zhangsan1", 13);
		People people2 = new People(1, "zhangsan2", 14);
		People people3 = new People(1, "zhangsan3", 13);
		People people4 = new People(2, "zhangsan4", 14);
		People people5 = new People(2, "zhangsan5", 14);
		People people6 = new People(2, "zhangsan6", 13);
		List<People> list = new ArrayList<>();
		list.add(people1);
		list.add(people2);
		list.add(people3);
		list.add(people4);
		list.add(people5);
		list.add(people6);
		Map<Integer, Map<Integer, List<String>>> collect = ListUtil
				.stream(list)
				.collect(Collectors.groupingBy(People::getId, Collectors.groupingBy(People::getAge, Collectors.mapping(People::getName, Collectors.toList()))));
		System.out.println(collect);

		System.out.println("string===" + JsonUtil.toJSONString(collect));

		PersonBO checkDDLDMLBO = JsonUtil.JSON.parseObject(JsonUtil.toJSONString(collect), PersonBO.class);
		System.out.println(checkDDLDMLBO);
		// System.out.println(collect);
		// ListUtil.stream(list).forEach(e->{
		// 	e.setAge(15);
		// 	e.setName("zhangsan");
		// });
		// System.out.println(list);
		//
		// Map<String, Map<String, List<People>>> amazonBalanceMap = list.stream()
		//                                                               .filter(item -> StringUtils.isNotBlank(item.getName()) && item.getName() != null)
		//                                                               .collect(Collectors.groupingBy(People::getName
		// 		                                                              , Collectors.groupingBy(item -> StringUtils.isNotBlank(item.getName()) ? item.getName() : "")));
	}
}
