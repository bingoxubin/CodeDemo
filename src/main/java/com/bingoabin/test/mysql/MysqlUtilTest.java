package com.bingoabin.test.mysql;

import com.bingoabin.util.jdbc.MysqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author xubin03
 * @date 2021/4/28 5:04 下午
 */
public class MysqlUtilTest {
	public static void main(String[] args) {
		Connection conn = MysqlUtil.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("select * from test where id = ?");
			stmt.setInt(1, 1);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String address = rs.getString("address");
				String email = rs.getString("email");
				System.out.println(id + ":" + name + ":" + age + ":" + address + ":" + email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MysqlUtil.close(conn, stmt, rs);
		}

	}
}
