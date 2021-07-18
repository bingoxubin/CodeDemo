package com.bingoabin.filter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author xubin34
 * @date 2021/7/19 1:31 上午
 */
public class SingleColumnValueFilterTest {
	//单列值过滤器 SingleColumnValueFilter
	//SingleColumnValueFilter会返回满足条件的cell。所在行的所有cell的值
	//查询名字为刘备的数据

	/**
	 * select  *  from  myuser where name  = '刘备'
	 * 会返回我们符合条件数据的所有的字段
	 * <p>
	 * SingleColumnValueExcludeFilter  列值排除过滤器
	 * select  *  from  myuser where name  ！= '刘备'
	 */
	@Test
	public void singleColumnValueFilter() throws IOException {
		//查询 f1  列族 name  列  值为刘备的数据
		Scan scan = new Scan();
		//单列值过滤器，过滤  f1 列族  name  列  值为刘备的数据
		SingleColumnValueFilter singleColumnValueFilter = new SingleColumnValueFilter("f1".getBytes(), "name".getBytes(), CompareFilter.CompareOp.EQUAL, "刘备".getBytes());
		scan.setFilter(singleColumnValueFilter);
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
