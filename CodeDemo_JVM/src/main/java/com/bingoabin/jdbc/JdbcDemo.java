package com.bingoabin.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcDemo {
	public static void main(String[] args) throws Exception {
		Connection connection = DriverManager.getConnection("", "", "");
	}
}
