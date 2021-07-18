package com.bingoabin.filter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author xubin34
 * @date 2021/7/19 1:25 上午
 */
public class QualifierFilterTest {
	//列过滤器QualifierFilter
	//只查询列名包含name的列的值

	/**
	 * 列名过滤器 只查询包含name列的值
	 */
	@Test
	public void qualifierFilter() throws IOException {
		Scan scan = new Scan();
		SubstringComparator substringComparator = new SubstringComparator("name");
		//定义列名过滤器，只查询列名包含name的列
		QualifierFilter qualifierFilter = new QualifierFilter(CompareFilter.CompareOp.EQUAL, substringComparator);
		scan.setFilter(qualifierFilter);
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
