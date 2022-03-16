package com.bingoabin.domain;

/**
 * @Author: xubin34
 * @Date: 2022/3/16 12:47 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class Staff {
    //员工信息
    private String name;
    private Integer age;
    private String number;

    public Staff() {
    }

    public Staff(String name, Integer age, String number) {
        this.name = name;
        this.age = age;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        if (name != null ? !name.equals(staff.name) : staff.name != null) return false;
        if (age != null ? !age.equals(staff.age) : staff.age != null) return false;
        return number != null ? number.equals(staff.number) : staff.number == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }
}
