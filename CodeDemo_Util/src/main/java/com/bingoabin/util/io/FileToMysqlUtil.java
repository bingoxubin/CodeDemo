package com.bingoabin.util.io;

import com.bingoabin.util.jdbc.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author xubin03
 * @date 2021/5/13 9:23 下午
 */
public class FileToMysqlUtil {
	private static final String sql = "insert into hivefile(dbname,tablename,pre_file_count,after_file_count) values (?,?,?,?)";

	//实现从file里面读取数据，保存到mysql中
	public static void main(String[] args) throws SQLException {
		Connection conn = MysqlUtil.getConn();
		PreparedStatement stmt = null;
		File file = new File("/Users/xubin03/tmpfile/hivefile/dw");
		BufferedReader reader = null;
		StringBuffer sbf = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempStr;
			while ((tempStr = reader.readLine()) != null) {
				String[] str = tempStr.split(",");
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, str[0]);
				stmt.setString(2, str[1]);
				stmt.setInt(3, Integer.parseInt(str[2]));
				stmt.setInt(4, Integer.parseInt(str[3]));
				stmt.execute();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			MysqlUtil.close(conn, stmt, null);
		}
	}
}
