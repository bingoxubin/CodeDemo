package com.bingo.sqlparser;

import com.bingo.util.ResultSetUtil;
import org.apache.calcite.adapter.csv.CsvSchema;
import org.apache.calcite.adapter.csv.CsvTable;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.SchemaPlus;
import org.junit.Test;

import java.io.File;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

/**
 * @author bingoabin
 * @date 2023/6/2 14:18
 * @Description:
 */
public class CalciteTest {
	public static void main(String[] args) {

	}

	//将csv中的数据，通过sql查询出来
	@Test
	public void SqlQueryCsv() throws SQLException {
		// 0.获取csv文件的路径，注意获取到文件所在上层路径就可以了
		String path = Objects.requireNonNull(CalciteTest.class.getClassLoader().getResource("csv").getPath());

		// 1.构建CsvSchema对象，在Calcite中，不同数据源对应不同Schema，比如CsvSchema、DruidSchema、ElasticsearchSchema等
		CsvSchema csvSchema = new CsvSchema(new File(path), CsvTable.Flavor.SCANNABLE);

		// 2.构建Connection
		// 2.1 设置连接参数
		Properties info = new Properties();
		// 不区分sql大小写
		info.setProperty("caseSensitive", "false");
		// 2.2 获取标准的JDBC Connection
		Connection connection = DriverManager.getConnection("jdbc:calcite:", info);
		// 2.3 获取Calcite封装的Connection
		CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);

		// 3.构建RootSchema，在Calcite中，RootSchema是所有数据源schema的parent，多个不同数据源schema可以挂在同一个RootSchema下
		// 以实现查询不同数据源的目的
		SchemaPlus rootSchema = calciteConnection.getRootSchema();

		// 4.将不同数据源schema挂载到RootSchema，这里添加CsvSchema
		rootSchema.add("csv", csvSchema);

		// 5.执行SQL查询，通过SQL方式访问csv文件
		String sql = "select * from csv.depts where empno = 100";
		Statement statement = calciteConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		// 6.遍历打印查询结果集
		System.out.println(ResultSetUtil.resultString(resultSet));
	}
}
