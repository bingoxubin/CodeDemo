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
		//${hivevar:EXT_DATA}
		ParseDriver parseDriver = new ParseDriver();
		String sql = " ";
		ASTNode node = parseDriver.parse(sql);
		System.out.println("success");
	}
}
