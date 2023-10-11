package com.bingo.jdbc;

import java.sql.*;

/**
 * @author bingoabin
 * @date 2023/10/7 12:23
 * @Description:
 */
public class MysqlGet {
	public static void main(String[] args) throws Exception{
		String url = "jdbc:mysql://localhost:3306/test?useSSL=false";
		String driver = "com.mysql.cj.jdbc.Driver";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer res = null;

		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "root", "111111");
			String query = "select * from score";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				// 从结果集中获取数据
				String name = rs.getString("name");
				String kecheng = rs.getString("kecheng");
				int fenshu = rs.getInt("fenshu");

				// 打印或处理数据
				System.out.println("Name: " + name + ", Kecheng: " + kecheng + ", Fenshu：" + fenshu);
				// res.append(rs.getString("name"));
				// res.append(rs.getString("kecheng"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			rs.close();
			stmt.close();
			conn.close();
		}
	}
}
