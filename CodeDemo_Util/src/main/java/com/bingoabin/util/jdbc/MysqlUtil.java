package com.bingoabin.util.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author xubin03
 * @date 2021/4/28 5:03 下午
 */
public class MysqlUtil {
	private static String url;
	private static String user;
	private static String password;

	static {
		//加载配置文件
		Properties ppt = new Properties();
		InputStream is = MysqlUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			ppt.load(is);
			url = ppt.getProperty("mysql_url");
			user = ppt.getProperty("mysql_user");
			password = ppt.getProperty("mysql_password");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Class.forName(ppt.getProperty("mysql_driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//获取连接
	public static Connection getConn() {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//执行sql
	public static void executeSQL(String sql) {
		Connection conn = getConn();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, null);
		}
	}

	//关闭连接
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
