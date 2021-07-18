package com.bingoabin.filter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author xubin34
 * @date 2021/7/19 1:32 上午
 */
public class PrefixFilterTest {
	//rowkey前缀过滤器PrefixFilter
	//查询以00开头的所有前缀的rowkey

	/**
	 * 查询rowkey前缀以  00开头的所有的数据
	 */
	@Test
	public void prefixFilter() throws IOException {
		Scan scan = new Scan();
		//过滤rowkey以  00开头的数据
		PrefixFilter prefixFilter = new PrefixFilter("00".getBytes());
		scan.setFilter(prefixFilter);
		ResultScanner scanner = table.getScanner(scan);
		System.out.println(scanner);
	}

	private Connection connection;
	private final String TABLE_NAME = "myuser";
	private Table table;

	@Before
	public void initTable() throws IOException {
		Configuration configuration = HBaseConfiguration.create();
		configuration.set("hbase.zookeeper.quorum", "node01:2181,node02:2181");
		connection = ConnectionFactory.createConnection(configuration);
		table = connection.getTable(TableName.valueOf(TABLE_NAME));
	}

	@After
	public void close() throws IOException {
		table.close();
		connection.close();
	}
}
