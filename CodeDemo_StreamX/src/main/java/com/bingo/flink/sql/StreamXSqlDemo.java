package com.bingo.flink.sql;

import com.streamxhub.streamx.flink.core.TableContext;
import com.streamxhub.streamx.flink.core.TableEnvConfig;

/**
 * @author bingoabin
 * @date 2023/7/16 12:16
 * @Description:
 */
public class StreamXSqlDemo {
	public static void main(String[] args) {
		TableEnvConfig tableEnvConfig = new TableEnvConfig(args, null);
		TableContext ctx = new TableContext(tableEnvConfig);

		ctx.sql("first");
	}
}
