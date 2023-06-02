package com.bingo.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author bingoabin
 * @date 2023/6/2 15:49
 * @Description:
 */
public class ResultSetUtil {
	public static String resultString(ResultSet resultSet) throws SQLException {
		StringBuilder stringBuilder = new StringBuilder();

		ResultSetMetaData metaData = resultSet.getMetaData();
		int columnCount = metaData.getColumnCount();

		// 输出列名
		for (int i = 1; i <= columnCount; i++) {
			stringBuilder.append(metaData.getColumnName(i));
			if (i < columnCount) {
				stringBuilder.append("\t");
			}
		}
		stringBuilder.append("\n");

		// 输出数据行
		while (resultSet.next()) {
			for (int i = 1; i <= columnCount; i++) {
				Object value = resultSet.getObject(i);
				stringBuilder.append(value);
				if (i < columnCount) {
					stringBuilder.append("\t");
				}
			}
			stringBuilder.append("\n");
		}

		return stringBuilder.toString();
	}
}
