package com.bingoabin.adddata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author xubin34
 * @date 2021/7/19 1:06 上午
 */
public class AddData {
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

	/**
	 * 向myuser表当中添加数据
	 */
	@Test
	public void addData() throws IOException {
		//获取表
		Table table = connection.getTable(TableName.valueOf(TABLE_NAME));
		Put put = new Put("0001".getBytes());//创建put对象，并指定rowkey值
		put.addColumn("f1".getBytes(), "name".getBytes(), "zhangsan".getBytes());
		put.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(18));
		put.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(25));
		put.addColumn("f1".getBytes(), "address".getBytes(), Bytes.toBytes("地球人"));
		table.put(put);
		table.close();
	}
}
