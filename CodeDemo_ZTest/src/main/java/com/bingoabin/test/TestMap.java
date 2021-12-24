package com.bingoabin.test;

import com.bingoabin.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: xubin34
 * @Date: 2021/12/24 3:30 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class TestMap {
    public static void main(String[] args){
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "xb"));
        persons.add(new Person(2, "xb4"));
        persons.add(new Person(3, "xb4"));
        persons.add(new Person(4, "xb4"));
        persons.add(new Person(5, "xb5"));
        persons.add(new Person(6, "xb6"));
        persons.add(new Person(7, "xb7"));
        persons.add(new Person(8, ""));
        Map<String, List<Person>> names = persons.stream().collect(Collectors.groupingBy(Person::getName));
        Set<String> strings = names.keySet();
        System.out.println(names);
    }
}
