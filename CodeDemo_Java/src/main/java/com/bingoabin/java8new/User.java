package com.bingoabin.java8new;

/**
 * @Author: xubin34
 * @Date: 2021/8/20 5:56 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class User {
	private int id;
	private String name;
	private int age;
	private String address;
	private String email;

	public User() {
	}

	public User(int id, int age) {
		this.id = id;
		this.age = age;
	}

	public User(int id, String name, int age, String address, String email) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.email = email;
	}

	public User(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (id != user.id) return false;
		if (age != user.age) return false;
		if (name != null ? !name.equals(user.name) : user.name != null) return false;
		if (address != null ? !address.equals(user.address) : user.address != null) return false;
		return email != null ? email.equals(user.email) : user.email == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + age;
		result = 31 * result + (address != null ? address.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", address='" + address + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
