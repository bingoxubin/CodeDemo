package com.bingoabin.batchinsert;

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
import java.util.ArrayList;
import java.util.List;

/**
 * @author xubin34
 * @date 2021/7/19 1:16 上午
 */
public class BatchInsert {
	/**
	 * hbase的批量插入数据
	 * 初始化一批数据到HBase表当中，用于查询
	 */
	@Test
	public void batchInsert() throws IOException {
		//创建put对象，并指定rowkey
		Put put = new Put("0002".getBytes());
		//f1
		put.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(1));
		put.addColumn("f1".getBytes(), "name".getBytes(), Bytes.toBytes("曹操"));
		put.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(30));
		//f2
		put.addColumn("f2".getBytes(), "sex".getBytes(), Bytes.toBytes("1"));
		put.addColumn("f2".getBytes(), "address".getBytes(), Bytes.toBytes("沛国谯县"));
		put.addColumn("f2".getBytes(), "phone".getBytes(), Bytes.toBytes("16888888888"));
		put.addColumn("f2".getBytes(), "say".getBytes(), Bytes.toBytes("helloworld"));

		Put put2 = new Put("0003".getBytes());
		put2.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(2));
		put2.addColumn("f1".getBytes(), "name".getBytes(), Bytes.toBytes("刘备"));
		put2.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(32));
		put2.addColumn("f2".getBytes(), "sex".getBytes(), Bytes.toBytes("1"));
		put2.addColumn("f2".getBytes(), "address".getBytes(), Bytes.toBytes("幽州涿郡涿县"));
		put2.addColumn("f2".getBytes(), "phone".getBytes(), Bytes.toBytes("17888888888"));
		put2.addColumn("f2".getBytes(), "say".getBytes(), Bytes.toBytes("talk is cheap , show me the code"));

		Put put3 = new Put("0004".getBytes());
		put3.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(3));
		put3.addColumn("f1".getBytes(), "name".getBytes(), Bytes.toBytes("孙权"));
		put3.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(35));
		put3.addColumn("f2".getBytes(), "sex".getBytes(), Bytes.toBytes("1"));
		put3.addColumn("f2".getBytes(), "address".getBytes(), Bytes.toBytes("下邳"));
		put3.addColumn("f2".getBytes(), "phone".getBytes(), Bytes.toBytes("12888888888"));
		put3.addColumn("f2".getBytes(), "say".getBytes(), Bytes.toBytes("what are you 弄啥嘞！"));

		Put put4 = new Put("0005".getBytes());
		put4.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(4));
		put4.addColumn("f1".getBytes(), "name".getBytes(), Bytes.toBytes("诸葛亮"));
		put4.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(28));
		put4.addColumn("f2".getBytes(), "sex".getBytes(), Bytes.toBytes("1"));
		put4.addColumn("f2".getBytes(), "address".getBytes(), Bytes.toBytes("四川隆中"));
		put4.addColumn("f2".getBytes(), "phone".getBytes(), Bytes.toBytes("14888888888"));
		put4.addColumn("f2".getBytes(), "say".getBytes(), Bytes.toBytes("出师表你背了嘛"));

		Put put5 = new Put("0006".getBytes());
		put5.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(5));
		put5.addColumn("f1".getBytes(), "name".getBytes(), Bytes.toBytes("司马懿"));
		put5.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(27));
		put5.addColumn("f2".getBytes(), "sex".getBytes(), Bytes.toBytes("1"));
		put5.addColumn("f2".getBytes(), "address".getBytes(), Bytes.toBytes("哪里人有待考究"));
		put5.addColumn("f2".getBytes(), "phone".getBytes(), Bytes.toBytes("15888888888"));
		put5.addColumn("f2".getBytes(), "say".getBytes(), Bytes.toBytes("跟诸葛亮死掐"));

		Put put6 = new Put("0007".getBytes());
		put6.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(5));
		put6.addColumn("f1".getBytes(), "name".getBytes(), Bytes.toBytes("xiaobubu—吕布"));
		put6.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(28));
		put6.addColumn("f2".getBytes(), "sex".getBytes(), Bytes.toBytes("1"));
		put6.addColumn("f2".getBytes(), "address".getBytes(), Bytes.toBytes("内蒙人"));
		put6.addColumn("f2".getBytes(), "phone".getBytes(), Bytes.toBytes("15788888888"));
		put6.addColumn("f2".getBytes(), "say".getBytes(), Bytes.toBytes("貂蝉去哪了"));

		List<Put> listPut = new ArrayList<Put>();
		listPut.add(put);
		listPut.add(put2);
		listPut.add(put3);
		listPut.add(put4);
		listPut.add(put5);
		listPut.add(put6);

		table.put(listPut);
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
