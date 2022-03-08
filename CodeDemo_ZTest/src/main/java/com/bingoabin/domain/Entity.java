package com.bingoabin.domain;

/**
 * @Author: xubin34
 * @Date: 2022/3/8 1:44 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class Entity {

    private Long id;

    private String name;

    private String sex;

    private String address;

    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
