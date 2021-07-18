package com.bingoabin.jdbc;

import java.sql.*;

/**
 * @author xubin34
 * @date 2021/7/19 1:50 上午
 */
//第二步：impala的java代码查询开发
public class ImpalaJdbc {
	public static void main(String[] args) throws Exception {
		//定义连接驱动类，以及连接url和执行的sql**语句
		String driver = "org.apache.hive.jdbc.HiveDriver";
		String driverUrl = "jdbc:hive2://192.168.52.120:21050/mydb1;auth=noSasl";
		String sql = "select * from student";

		//通过反射加载数据库连接驱动*
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(driverUrl);
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		//通过查询，得到数据一共有多少列
		int col = resultSet.getMetaData().getColumnCount();
		//遍历结果集
		while (resultSet.next()) {
			for (int i = 1; i <= col; i++) {
				System.out.print(resultSet.getString(i) + "\t");
			}
			System.out.print("\n");
		}
		preparedStatement.close();
		connection.close();
	}
}
