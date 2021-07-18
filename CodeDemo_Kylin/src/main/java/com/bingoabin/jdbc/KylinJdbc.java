package com.bingoabin.jdbc;

/**
 * @author xubin34
 * @date 2021/7/19 1:52 上午
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class KylinJdbc {
	public static void main(String[] args) throws Exception {

		//Kylin_JDBC 驱动
		String KYLIN_DRIVER = "org.apache.kylin.jdbc.Driver";

		//Kylin_URL
		String KYLIN_URL = "jdbc:kylin://node02:8066/kylin_hive";

		//Kylin的用户名
		String KYLIN_USER = "ADMIN";

		//Kylin的密码
		String KYLIN_PASSWD = "KYLIN";

		//添加驱动信息
		Class.forName(KYLIN_DRIVER);

		//获取连接
		Connection connection = DriverManager.getConnection(KYLIN_URL, KYLIN_USER, KYLIN_PASSWD);

		//预编译SQL
		PreparedStatement ps = connection.prepareStatement("SELECT sum(sal) FROM emp group by deptno");

		//执行查询
		ResultSet resultSet = ps.executeQuery();

		//遍历打印
		while (resultSet.next()) {
			System.out.println(resultSet.getInt(1));
		}
	}
}
