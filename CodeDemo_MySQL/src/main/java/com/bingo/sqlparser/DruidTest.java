package com.bingo.sqlparser;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLInListExpr;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlUpdateStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author bingoabin
 * @date 2023/6/1 9:50
 * @Description:
 */
public class DruidTest {
	public static void main(String[] args) throws Exception {
	}

	//格式化
	@Test
	public void testFormatMysql() {
		String sql = "insert into table_test_1 select * from table_test_2;";
		String result = SQLUtils.format(sql, JdbcConstants.MYSQL);
		System.out.println("格式化后输出：\n" + result);
	}

	//获取表名和操作
	@Test
	public void testGetTableNameAndOperation() {
		String sql = "SELECT * FROM table_test_1;";
		//String sql = "insert into table_test_1 select * from table_test_2;";
		MySqlStatementParser parser = new MySqlStatementParser(sql);
		SQLStatement sqlStatement = parser.parseStatement();
		MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
		sqlStatement.accept(visitor);
		Map<TableStat.Name, TableStat> tableStatMap = visitor.getTables();
		for (Map.Entry<TableStat.Name, TableStat> tableStatEntry : tableStatMap.entrySet()) {
			System.out.println("表名：" + tableStatEntry.getKey().getName());
			System.out.println("操作名：" + tableStatEntry.getValue());
		}
	}

	//插入语句分析
	@Test
	public void testInsert(){
		String sql = "insert into table_test_2 (farendma, hesuandm, hesuanmc, weihguiy, weihjigo, weihriqi, shijchuo) values\n" +
				"('99996','HS205301','代码1','S####','101001','20140101',1414673101376), \n" +
				"('99996','HS205401','代码2','S####','101001','20140101',1414673101376);";
		MySqlStatementParser parser = new MySqlStatementParser(sql);
		SQLStatement sqlStatement = parser.parseStatement();
		MySqlInsertStatement insertStatement = (MySqlInsertStatement)sqlStatement;

		//获取列的名称
		//List<SQLExpr> columnExprs = insertStatement.getColumns();
		// System.out.println("列的名称为：");
		// for(SQLExpr expr : columnExprs){
		// 	System.out.print(expr + "\t");
		// }
		// System.out.println();
		//获取列的名称
		List<SQLExpr> columnExprs = insertStatement.getColumns();
		System.out.println("列的名称为：");
		for(SQLExpr expr : columnExprs){
			StringBuffer buffer = new StringBuffer();
			expr.output(buffer);
			System.out.print(buffer + "\t");
		}
		System.out.println();

		//获取插入的值
		List<SQLInsertStatement.ValuesClause> valuesClauseList = insertStatement.getValuesList();
		System.out.println("值分别是：");
		for(SQLInsertStatement.ValuesClause valuesClause : valuesClauseList){
			List<SQLExpr> valueExprList = valuesClause.getValues();
			for(SQLExpr expr : valueExprList){
				System.out.print(expr + "\t");
			}
			System.out.println();
		}
	}

	//更新 删除操作
	@Test
	public void testUpdate(){
		String sql = "UPDATE table_test_3 SET run_title='maple' WHERE run_id = '1';";
		MySqlStatementParser parser = new MySqlStatementParser(sql);
		SQLStatement sqlStatement = parser.parseStatement();
		MySqlUpdateStatement updateStatement = (MySqlUpdateStatement)sqlStatement;
		SQLExpr whereExpr = updateStatement.getWhere();
		if(whereExpr instanceof SQLInListExpr){
			// SQLInListExpr 指 run_id in ('1', '2') 这一情况
			SQLInListExpr inListExpr = (SQLInListExpr)whereExpr;
			List<SQLExpr> valueExprs = inListExpr.getTargetList();
			for(SQLExpr expr : valueExprs){
				System.out.print(expr + "\t");
			}
		} else {
			// SQLBinaryOpExpr 指 run_id = '1' 这一情况
			SQLBinaryOpExpr binaryOpExpr = (SQLBinaryOpExpr) whereExpr;
			System.out.println(binaryOpExpr.getLeft() + " --> " + binaryOpExpr.getRight());
		}
	}

	//更新语句
	@Test
	public void testAlter() {
		String sql = "alter table test_check1 ADD index ind1(farendma), add co2 VARCHAR(20), drop INDEX id3, drop column co1;";
		MySqlStatementParser parser = new MySqlStatementParser(sql);
		SQLStatement statement = parser.parseStatement();
		SQLAlterTableStatement alter = (SQLAlterTableStatement)statement;
		for (SQLAlterTableItem item : alter.getItems()) {
			if (item instanceof SQLAlterTableDropIndex) {
				SQLAlterTableDropIndex dropIndex = (SQLAlterTableDropIndex) item;
				System.out.println("删除的索引名为： " + dropIndex.getIndexName());
			} else if (item instanceof SQLAlterTableDropColumnItem){
				SQLAlterTableDropColumnItem dropColumn = (SQLAlterTableDropColumnItem)item;
				System.out.println("删除的列名为： " + dropColumn.getColumns());
			} else if (item instanceof SQLAlterTableAddIndex) {
				SQLAlterTableAddIndex addIndex = (SQLAlterTableAddIndex) item;
				if (addIndex.getName() != null) {
					String indexName = addIndex.getName().getSimpleName();
					System.out.println("新增的索引名为 ： " + indexName);
				}
			} else if (item instanceof SQLAlterTableAddColumn) {
				SQLAlterTableAddColumn addColumn = (SQLAlterTableAddColumn) item;
				System.out.println("新增的列为： " + addColumn.getColumns());
			}
		}
	}
}
