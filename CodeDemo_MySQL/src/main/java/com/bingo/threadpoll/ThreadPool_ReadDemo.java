package com.bingo.threadpoll;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author bingoabin
 * @date 2023/7/14 15:28
 * @Description:
 */
public class ThreadPool_ReadDemo {
	private static DruidDataSource dataSource;

	static {
		// 创建 DruidDataSource 对象
		dataSource = new DruidDataSource();

		// 设置连接参数
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setUsername("root");
		dataSource.setPassword("111111");
		// 设置连接池参数
		dataSource.setInitialSize(5);
		dataSource.setMaxActive(10);  // 最多10个连接
	}

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 从连接池中获取连接
			conn = dataSource.getConnection();

			// 执行查询，这里只是示例
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM user_t");

			// 处理结果集
			while (rs.next()) {
				String id = rs.getString("id");  // 你需要查询的字段
				String name = rs.getString("user_name");
				System.out.println(id + " " + name);
			}
		} catch (SQLException se) {
			// 处理 JDBC 错误
			se.printStackTrace();
		} finally {
			try {
				// 释放
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();  // 这里只是把连接归还给了连接池
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		dataSource.close(); // 关闭连接池，一般来说，你应该在应用程序完全结束时才关闭连接池。
	}
}
