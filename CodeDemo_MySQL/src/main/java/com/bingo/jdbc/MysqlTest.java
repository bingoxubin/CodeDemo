package com.bingo.jdbc;

import java.sql.*;

/**
 * @author bingoabin
 * @date 2023/5/25 14:13
 * @Description:
 */
public class MysqlTest {
	public static void main(String[] args) throws SQLException {
		String mysqlHost = "hahaha";
		int mysqlPort = 20001;
		String mysqlDatabase = "test";
		String mysqlUser = "root";
		String mysqlPassword = "111111";

		String jdbcUrl = "jdbc:mysql://" + mysqlHost + ":" + mysqlPort + "/" + mysqlDatabase + "?useSSL=false";
		String driverName = "com.mysql.cj.jdbc.Driver";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(jdbcUrl, mysqlUser, mysqlPassword);

			// 执行查询语句
			String query = "SELECT * FROM score";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			// 处理查询结果
			while (rs.next()) {
				// 从结果集中获取数据
				String name = rs.getString("name");
				String kecheng = rs.getString("kecheng");
				int fenshu = rs.getInt("fenshu");

				// 打印或处理数据
				System.out.println("Name: " + name + ", Kecheng: " + kecheng + ", Fenshu：" + fenshu);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			rs.close();
			stmt.close();
			conn.close();
		}
	}
}
