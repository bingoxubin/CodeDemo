package com.bingoabin.antlr;

import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.ParseDriver;
import org.apache.hadoop.hive.ql.parse.ParseException;

/**
 * @author bingoabin
 * @date 2023/10/11 9:49
 * @Description:
 */
public class TestAntlr {
	public static void main(String[] args) throws ParseException {
		ParseDriver parseDriver = new ParseDriver();
		String sql = "create external table if not exists DL_SDB.EXT_MAIN${hivevar:EXT_DATA} (id STRING) stored as textfile location '/datalake/user/${hivevar:EXT_DATA}/date'";
		ASTNode node = parseDriver.parse(sql);
		System.out.println("success");
	}
}
