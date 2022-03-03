package com.bingoabin.optional;

import com.bingoabin.domain.Person;
import com.bingoabin.domain.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * @Author: xubin34
 * @Date: 2022/3/3 4:38 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class TestOptional {
    private static final Logger logger = LoggerFactory.getLogger(TestOptional.class);

    public static void main(String[] args) {

    }

    @Test
    public void testCreate() {
        Person person = null;
        //Optional<Person> person1 = Optional.of(person);
        Optional<Person> person1 = Optional.ofNullable(person);
    }

    @Test
    public void testGet() {
        Person person1 = null;
        Person person2 = new Person(1, "xb");
        //Optional.ofNullable(person1).get();    空值
        Optional.ofNullable(person1).ifPresent(p -> System.out.println(p.getId()));
        if (Optional.of(person2).isPresent()) {
            System.out.println(Optional.of(person2).get());
        }
    }

    @Test
    public void testDefaultValue() {
        Person person1 = null;
        Person person2 = new Person(1, "xb");
        Person p1 = Optional.ofNullable(person2).orElse(createPerson1());
        Person p2 = Optional.ofNullable(person2).orElseGet(() -> createPerson());
//        System.out.println(p1.getId() + "," + p1.getName());
//        System.out.println(p2.getId() + "," + p2.getName());
    }

    public Person createPerson() {
        System.out.println("createing new user");
        return new Person(3, "xxbb");
    }

    public Person createPerson1() {
        System.out.println("createing new user1");
        return new Person(4, "xxbb1");
    }

    @Test
    public void givenEmptyValue_whenCompare_thenOk() {
        User user = null;
        logger.debug("Using orElse");
        User result = Optional.ofNullable(user).orElse(createNewUser());
        logger.debug("Using orElseGet");
        User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
    }

    private User createNewUser() {
        logger.debug("Creating New User");
        return new User("extra@gmail.com", "1234");
    }

    public User createUser() {
        logger.debug("createing new user");
        return new User("email", "xxbb");
    }

    @Test
    public void testException() {
        Person person = null;
        Optional.ofNullable(person).orElseThrow(() -> new IllegalArgumentException());
    }

    @Test
    public void testMap() {
        Person xx = new Person(1, "xx");
        String name1 = Optional.ofNullable(xx)
                .map(e -> e.getName()).orElse("name");

    }

    @Test
    public void testFilter(){
        Person person = new Person(3, "fw");
        Person person1 = Optional.ofNullable(person).filter(p -> p.getId() == 3).get();
        System.out.println(person1.getId() + "," + person1.getName());
    }
}
