package com.bingo.sqlparser;

import com.google.common.collect.Sets;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.statement.update.UpdateSet;
import net.sf.jsqlparser.util.SelectUtils;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author bingoabin
 * @date 2023/6/1 17:24
 * @Description:
 */
public class JSqlParserTest {
	public static void main(String[] args){

	}

	//解析单个sql
	@Test
	public void parseSingleSql() throws JSQLParserException {
		//1、获取原始sql输入
		String sql="SELECT u.username, u.age, u.sex FROM user as u where u.id=1 and u.age in (1,2,3) ;";
		//构建解析器
		Select select = (Select) CCJSqlParserUtil.parse(sql);
		//获取select对象
		SelectBody selectBody = select.getSelectBody();
		System.err.println(selectBody.toString());

		//解析sql
		PlainSelect plainSelect=(PlainSelect) selectBody;
		//表名
		Table table= (Table) plainSelect.getFromItem();
		//表名称
		System.out.println(table.getName());
		//别名
		System.out.println(table.getAlias().getName());

		//查询列
		List<SelectItem> selectItems = plainSelect.getSelectItems();
		selectItems.stream().forEach(selectItem -> {
			System.out.println(selectItem.toString());
		});

		//where  一个and 条件
		//EqualsTo equalsTo = (EqualsTo) plainSelect.getWhere();
		//Expression leftExpression = equalsTo.getLeftExpression();
		//Expression rightExpression = equalsTo.getRightExpression();
		//System.err.println(leftExpression); // id
		//System.err.println(rightExpression); // 1

		//多个and
		AndExpression andExpression = (AndExpression) plainSelect.getWhere();
		Expression leftExpression = andExpression.getLeftExpression();
		System.err.println(((EqualsTo) leftExpression).getLeftExpression()); // ID
		System.err.println(((EqualsTo) leftExpression).getRightExpression()); // 1

		//in
		Expression rightExpression = andExpression.getRightExpression();
		InExpression inExpression = (InExpression) rightExpression;
		ItemsList rightItemsList = inExpression.getRightItemsList();
		System.err.println(rightItemsList); // (1,2,3)
	}

	//解析组合sql
	@Test
	public void parseMultiSql() throws JSQLParserException {
		// 多表SQL
		String sql = "SELECT t1.a , t1.b  FROM table1 AS t1 JOIN table2 t2 ON t1.user_id  = t2.user_id WHERE t1.id = 8 and t1.name='a' and t1.age IN (8,9,10)";
		// 1.解析表名
		CCJSqlParserManager parserManager = new CCJSqlParserManager();
		// 解析SQL为Statement对象
		Statement statement = parserManager.parse(new StringReader(sql));
		// 创建表名发现者对象
		TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
		// 获取到表名列表
		List<String> tableNameList = tablesNamesFinder.getTableList(statement);
		// 循环打印解析到的表名 table1 table2
		if (!CollectionUtils.isEmpty(tableNameList)) {
			tableNameList.forEach(System.err::println);
		}

		// 2.解析查询元素 列，函数等
		Select select = (Select) CCJSqlParserUtil.parse(sql);
		PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
		List<SelectItem> selectItems = plainSelect.getSelectItems();
		selectItems.forEach(System.err::println);

		// 3.解析Join
		List<Join> joins = plainSelect.getJoins();
		joins.forEach(e -> {
			Collection<Expression> onExpressions = e.getOnExpressions();
			for (Expression onExpression : onExpressions) {
				System.err.println(onExpression); // 获取ON 表达式 t1.user_id = t2.user_id
			}
		});

		// 3.解析WHERE条件
		AndExpression andExpression = (AndExpression) plainSelect.getWhere();
		AndExpression leftAndExpression=(AndExpression)andExpression.getLeftExpression();
		Expression leftExpression1 = leftAndExpression.getLeftExpression();
		Expression rightExpression1 = leftAndExpression.getRightExpression();
		System.out.println(leftExpression1);
		System.out.println(rightExpression1);

		// 4.解析IN
		InExpression rightAndExpression=(InExpression)andExpression.getRightExpression();
		System.out.println(rightAndExpression.getRightItemsList());
	}

	//解析Insert
	@Test
	public void parseInsert() throws JSQLParserException {
		String insertSql = "INSERT INTO test ( c1,c2) VALUES ( 1,2)";
		Statement statement = CCJSqlParserUtil.parse(insertSql);
		if (statement instanceof Insert) {
			Insert insert = (Insert) statement;
			// 添加新列
			insert.addColumns(new Column("c3 "));
			// 添加新插入值
			ExpressionList expressionList = (ExpressionList) insert.getItemsList();
			expressionList.getExpressions().add(new StringValue("3"));
			System.err.println(insert);
		}
	}

	//解析update
	@Test
	public void parseUpdate() throws JSQLParserException {
		String updateSql = "UPDATE User SET FirstName = 'a' WHERE LastName = 'b' ";
		Statement statement = CCJSqlParserUtil.parse(updateSql);
		if (statement instanceof Update) {
			Update update = (Update) statement;
			Expression where = update.getWhere();
			System.err.println(where);
			for (UpdateSet updateSet : update.getUpdateSets()) {
				updateSet.getColumns().forEach(System.out::println);
			}
		}
	}

	//解析delete
	@Test
	public void parseDelete() throws JSQLParserException{
		String updateSql = "delete from test  WHERE LastName = 'b' ";
		Statement statement = CCJSqlParserUtil.parse(updateSql);
		if (statement instanceof Delete) {
			Delete delete = (Delete) statement;
			Expression where = delete.getWhere();
			System.err.println(where);
		}
	}

	//組裝insert
	@Test
	public void mergeInsert(){
		// 创建表对象设置表名
		Table table = new Table();
		table.setName("table");
		// 创建插入对象
		Insert insert = new Insert();
		insert.setTable(table); // 设置插入对象的表对象
		// 设置插入列
		List<Column> columnList = Arrays.asList(new Column("a"), new Column("b"));
		insert.setColumns(columnList);
		// 设置插入值
		MultiExpressionList multiExpressionList = new MultiExpressionList();
		multiExpressionList.addExpressionList(Arrays.asList(new StringValue("1"), new StringValue("2")));
		insert.setItemsList(multiExpressionList);
		System.err.println(insert); // INSERT INTO table (a, b) VALUES ('1', '2')
	}

	//組裝update
	@Test
	public void mergeUpdate(){
		// 创建表对象设置表名
		Table table = new Table();
		table.setName("table");
		table.setAlias(new Alias("t"));
		// 创建更新对象
		Update update = new Update();
		update.setTable(table);// 设置更新对象的表对象
		update.addUpdateSet(new Column("a"), new StringValue("1"));
		update.addUpdateSet(new Column("b"), new StringValue("2"));
		// 添加Where条件
		EqualsTo equalsTo = new EqualsTo(); // 等于表达式
		// 设置表达式左边值
		equalsTo.setLeftExpression(new Column(table, "id"));
		// 设置表达式右边值
		equalsTo.setRightExpression(new StringValue("123456"));
		update.setWhere(equalsTo); // 设置Where
		System.out.println(update.toString());
	}

	//組裝delete
	@Test
	public void mergeDelete(){
		// 创建表对象设置表名
		Table table = new Table();
		table.setName("table");
		table.setAlias(new Alias("t"));

		// 创建更新对象
		Delete delete = new Delete();
		delete.setTable(table);// 设置更新对象的表对象

		// 添加Where条件
		EqualsTo equalsTo = new EqualsTo(); // 等于表达式
		// 设置表达式左边值
		equalsTo.setLeftExpression(new Column(table, "id"));
		// 设置表达式右边值
		equalsTo.setRightExpression(new StringValue("123456"));
		delete.setWhere(equalsTo); // 设置Where

		// 输入语句
		System.err.println(delete);
	}

	//组装单表查询
	@Test
	public void mergeSingleQuery(){
		// 单表全量
		Table table = new Table("test");
		table.setAlias(new Alias("t"));
		Select select = SelectUtils.buildSelectFromTable(table);
		System.err.println(select); // SELECT * FROM test

		// 指定列查询
		Select buildSelectFromTableAndExpressions = SelectUtils.buildSelectFromTableAndExpressions(table, new Column("a"), new Column("b"));
		System.err.println(buildSelectFromTableAndExpressions); // SELECT a, b FROM test

		// WHERE =
		// 等于表达式
		EqualsTo equalsTo = new EqualsTo();

		equalsTo.setLeftExpression(new Column(table, "id"));

		equalsTo.setRightExpression(new StringValue("12"));
		// 转换为更细化的Select对象
		PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
		plainSelect.setWhere(equalsTo);
		System.err.println(plainSelect);//  SELECT * FROM test WHERE test.id = '12'

		// WHERE  != <>
		NotEqualsTo notEqualsTo = new NotEqualsTo();
		notEqualsTo.setLeftExpression(new Column(table, "id"));
		notEqualsTo.setRightExpression(new StringValue("12"));
		PlainSelect plainSelectNot = (PlainSelect) select.getSelectBody();
		plainSelectNot.setWhere(notEqualsTo);
		System.err.println(plainSelectNot);//  SELECT * FROM test WHERE test.id <> '12'

		// 其他运算符, 参考上面代码添加表达式即可
		GreaterThan gt = new GreaterThan(); // ">"
		gt.setLeftExpression(new Column(table,"age"));
		gt.setRightExpression(new StringValue("12"));
		PlainSelect plainSelectGt = (PlainSelect) select.getSelectBody();
		plainSelectGt.setWhere(gt);
		System.err.println(plainSelectGt);//  SELECT * FROM test WHERE test.age > 12

		GreaterThanEquals geq = new GreaterThanEquals(); // ">="
		MinorThan mt = new MinorThan(); // "<"
		MinorThanEquals leq = new MinorThanEquals();// "<="
		IsNullExpression isNull = new IsNullExpression(); // "is null"
		isNull.setNot(true);// "is not null"
		LikeExpression nlike = new LikeExpression();
		nlike.setNot(true); // "not like"
		Between bt = new Between();
		bt.setNot(true);// "not between"

		// WHERE LIKE
		// 创建Like表达式对象
		LikeExpression likeExpression = new LikeExpression();
		likeExpression.setLeftExpression(new Column("username"));
		likeExpression.setRightExpression(new StringValue("张%"));
		PlainSelect plainSelectLike = (PlainSelect) select.getSelectBody();
		plainSelectLike.setWhere(likeExpression);
		System.err.println(plainSelectLike); // SELECT * FROM test WHERE username LIKE '张%'

		// WHERE IN
		Set<String> deptIds = Sets.newLinkedHashSet(); // 创建IN范围的元素集合
		deptIds.add("1");
		deptIds.add("2");
		// 把集合转变为JSQLParser需要的元素列表
		ItemsList itemsList = new ExpressionList(deptIds.stream().map(StringValue::new).collect(Collectors.toList()));
		// 创建IN表达式对象，传入列名及IN范围列表
		InExpression inExpression = new InExpression(new Column("user_id "), itemsList);
		PlainSelect plainSelectIn = (PlainSelect) select.getSelectBody();
		plainSelectIn.setWhere(inExpression);
		System.err.println(plainSelectIn);

		// WHERE BETWEEN AND
		Between between = new Between();
		// 设置起点值
		between.setBetweenExpressionStart(new LongValue(18));
		// 设置终点值
		between.setBetweenExpressionEnd(new LongValue(30));
		// 设置左边的表达式，一般为列
		between.setLeftExpression(new Column("age"));
		PlainSelect plainSelectBetween = (PlainSelect) select.getSelectBody();
		plainSelectBetween.setWhere(between);
		System.err.println(plainSelectBetween);

		//  WHERE AND 多个条件结合,都需要成立
		AndExpression andExpression = new AndExpression(); // AND 表达式
		andExpression.setLeftExpression(equalsTo); // AND 左边表达式
		andExpression.setRightExpression(between);  // AND 右边表达式
		PlainSelect plainSelectAnd = (PlainSelect) select.getSelectBody();
		plainSelectAnd.setWhere(andExpression);
		System.err.println(plainSelectAnd); //  SELECT * FROM test WHERE test.id = '1' AND age BETWEEN 18 AND 30

		//  WHERE OR 多个条件满足一个条件成立返回
		// OR 表达式
		OrExpression orExpression = new OrExpression();
		orExpression.setLeftExpression(equalsTo);
		orExpression.setRightExpression(between);
		PlainSelect plainSelectOr = (PlainSelect) select.getSelectBody();
		plainSelectOr.setWhere(orExpression);
		System.err.println(plainSelectOr); // SELECT * FROM test WHERE test.id = '1' OR age BETWEEN 18 AND 30

		// ORDER BY 排序
		OrderByElement orderByElement = new OrderByElement(); // 创建排序对象
		orderByElement.isAsc(); //  设置升序排列 从小到大
		orderByElement.setExpression(new Column("createTime")); // 设置排序字段
		PlainSelect plainSelectOrderBy = (PlainSelect) select.getSelectBody();
		plainSelectOrderBy.addOrderByElements(orderByElement);
		System.err.println(plainSelectOrderBy); // SELECT * FROM test AS t WHERE t.id = '12' OR age BETWEEN 18 AND 30 ORDER BY createTime
	}

	//组装多表查询
	@Test
	public void mergeMultiQuery(){
		Table t1 = new Table("t_user").withAlias(new Alias("t1").withUseAs(true)); // 表1
		Table t2 = new Table("t_user_book").withAlias(new Alias("t2", false)); // 表2
		PlainSelect plainSelect = new PlainSelect().addSelectItems(new AllColumns()).withFromItem(t1);

		// JOIN ON 如果表中有至少一个匹配，则返回行
		Join join = new Join(); // 创建Join对象
		// 设置join参数可实现其他类型join
		// join.setLeft(true); LEFT JOIN
		// join.setRight(true);  RIGHT JOIN
		// join.setFull(true); FULL JOIN
		// join.setInner(true);
		join.withRightItem(t2); // 添加Join的表 JOIN t2 =>JOIN t_user_book t2
		EqualsTo equalsTo = new EqualsTo(); // 添加 = 条件表达式  t1.id  = t2.user_id
		equalsTo.setLeftExpression(new Column(t1, "id "));
		equalsTo.setRightExpression(new Column(t2, "user_id "));
		join.addOnExpression(equalsTo);// 添加ON
		plainSelect.addJoins(join);
		System.err.println(plainSelect); // SELECT * FROM t_user AS t1 JOIN t_user_book t2 ON t1.id  = t2.user_id

		//where t2.book_name="小鸡快跑"

		EqualsTo equalsTo2 = new EqualsTo();
		equalsTo2.setLeftExpression(new Column(t2, "book_name"));
		equalsTo2.setRightExpression(new StringValue("小鸡快跑"));
		plainSelect.setWhere(equalsTo2);
		System.err.println(plainSelect);//  SELECT * FROM t_user AS t1 JOIN t_user_book t2 ON t1.id  = t2.user_id  WHERE t2.book_name = '小鸡快跑'
	}
}
