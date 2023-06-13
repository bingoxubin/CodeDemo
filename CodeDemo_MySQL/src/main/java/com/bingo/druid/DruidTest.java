package com.bingo.druid;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlSelectParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author bingoabin
 * @date 2023/6/14 0:29
 * @Description:
 */
public class DruidTest {
	public static void main(String[] args){
		// String sql = "SELECT id, name, age FROM myuser";
		String sql = "select id,name,age,dob,created_at,updated_at,salary,longitude,department,active_status,address_json,fsp,long_description,serialized_data from complex_types_example;";
		// String sql = "select cast(id as varchar(10)),name,age,dob,created_at,updated_at,salary,longitude,department,active_status,address_json,fsp,long_description,serialized_data,1 as num from complex_types_example;";
		Map<String, String> columnTypes = DruidTest.getSqlColumnTypes(sql, DruidTest.createDataSource());
		System.out.println(columnTypes);
	}

	public static Map<String, String> getSqlColumnTypes(String sql, DataSource dataSource) {
		MySqlSelectParser parser = new MySqlSelectParser(sql);
		SQLSelect select = parser.select();
		MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
		select.accept(visitor);

		// 获取所有列信息
		List<SQLSelectItem> selectItemList = ((SQLSelectQueryBlock) select.getQuery()).getSelectList();
		Map<String, String> columnTypes = new HashMap<>();

		try (Connection connection = dataSource.getConnection();
		     Statement statement = connection.createStatement()) {

			for (SQLSelectItem item : selectItemList) {
				SQLExpr expr = item.getExpr();
				String columnName = expr.toString(); // 从SQLExpr中获取列名称
				String tableName = null;

				// 获取完整列名，包括表名
				SQLObject parent = expr;
				while (parent != null && !(parent instanceof SQLSelectQueryBlock)) {
					parent = parent.getParent();
				}

				if (parent != null && parent instanceof SQLSelectQueryBlock) {
					SQLSelectQueryBlock block = (SQLSelectQueryBlock) parent;
					SQLTableSource tableSource = block.getFrom();
					if (tableSource instanceof SQLExprTableSource) {
						tableName = ((SQLExprTableSource) tableSource).getName().getSimpleName();
					}
				}

				if (tableName != null) {
					ResultSet resultSet = statement.executeQuery("SELECT COLUMN_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='" + tableName + "' AND COLUMN_NAME='" + columnName + "'");
					if (resultSet.next()) {
						columnTypes.put(columnName, resultSet.getString("COLUMN_TYPE"));
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return columnTypes;
	}

	public static DataSource createDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/test");
		config.setUsername("root");
		config.setPassword("111111");
		config.setMaximumPoolSize(20);
		config.setConnectionTimeout(30000);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		return new HikariDataSource(config);
	}
}
