package com.bingoabin.filter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author xubin34
 * @date 2021/7/19 1:28 上午
 */
public class ValueFilterTest {
	//列值过滤器ValueFilter
//查询所有列当中包含8的数据

	/**
	 * 查询哪些字段值  包含数字8
	 */
	@Test
	public void contains8() throws IOException {
		Scan scan = new Scan();
		SubstringComparator substringComparator = new SubstringComparator("8");
		//列值过滤器，过滤列值当中包含数字8的所有的列
		ValueFilter valueFilter = new ValueFilter(CompareFilter.CompareOp.EQUAL, substringComparator);
		scan.setFilter(valueFilter);
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
