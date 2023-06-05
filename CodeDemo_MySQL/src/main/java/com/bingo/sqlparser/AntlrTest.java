package com.bingo.sqlparser;

import antlr4.mysql.MySqlLexer;
import antlr4.mysql.MySqlParser;
import antlr4.mysql.MySqlParserBaseListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.List;

/**
 * @author bingoabin
 * @date 2023/6/5 10:42
 * @Description:
 */
public class AntlrTest {
	public static void main(String[] args){
	    //操作步骤 参考文档：https://www.bilibili.com/read/cv16868467/
		//1.https://github.com/antlr/grammars-v4/tree/master/sql/mysql/Positive-Technologies下载两个g4文件
		//2.右击文件Generate ANTLR Recognizer  第一个目录选到java，第二个目录选到src，后面package指明antlr4.mysql，最后用Java语言
			//填写的内容分别为：
			// 1、生成代码位置
			// 2、使用规则源文件存放位置，就是自己下载的模板文件路径
			// 3、包名，写代码引用的时候会用到
			// 4、自动生成代码语言，这里写Java

		/** antlr4 格式化SQL **/
		MySqlLexer lexer = new MySqlLexer(CharStreams.fromString("create table newtable select * from table1"));
		MySqlParser parser = new MySqlParser(new CommonTokenStream(lexer));
		//定义CreateTableListener
		CreateTableListener listener = new CreateTableListener();
		ParseTreeWalker.DEFAULT.walk(listener, parser.sqlStatements());
		/**提取关键参数-表名*/
		String tableName= listener.getTableName();
		/**测试打印方法*/
		System.out.println(tableName);
	}
}

class CreateTableListener extends MySqlParserBaseListener {
	private String tableName = null;

	@Override
	public void enterQueryCreateTable(MySqlParser.QueryCreateTableContext ctx) {
		List<MySqlParser.TableNameContext> tableSourceContexts = ctx.getRuleContexts(MySqlParser.TableNameContext.class);
		for (MySqlParser.TableNameContext tableSource : tableSourceContexts) {
			//通过tableSourceItems获取表名
			tableName = tableSource.getText();
		}
	}

	public String getTableName() {
		return tableName;
	}
}