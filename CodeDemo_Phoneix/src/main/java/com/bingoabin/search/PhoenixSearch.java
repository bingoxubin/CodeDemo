package com.bingoabin.search;

/**
 * @author xubin34
 * @date 2021/7/19 1:37 上午
 */
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class PhoenixSearch {
	private Connection connection;
	private Statement statement;
	private ResultSet rs;

	@Before
	public void init() throws SQLException {
		//定义phoenix的连接url地址
		String url = "jdbc:phoenix:node01:2181";
		connection = DriverManager.getConnection(url);
		//构建Statement对象
		statement = connection.createStatement();
	}

	@Test
	public void queryTable() throws SQLException {
		//定义查询的sql语句，注意大小写
		String sql = "select * from US_POPULATION";
		//执行sql语句
		try {
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				System.out.println("state:" + rs.getString("state"));
				System.out.println("city:" + rs.getString("city"));
				System.out.println("population:" + rs.getInt("population"));
				System.out.println("-------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
}
