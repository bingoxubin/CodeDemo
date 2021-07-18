package com.bingoabin.getdata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author xubin34
 * @date 2021/7/19 1:17 上午
 */
public class GetData {
	/**
	 * 按照rowkey进行查询，获取所有列的所有值
	 * 查询rowkey为0003的人
	 */
	@Test
	public void getData() throws IOException {
		Table table = connection.getTable(TableName.valueOf(TABLE_NAME));
		//通过get对象，指定rowkey
		Get get = new Get(Bytes.toBytes("0003"));

		get.addFamily("f1".getBytes());//限制只查询f1列族下面所有列的值
		//查询f2  列族 phone  这个字段
		get.addColumn("f2".getBytes(), "phone".getBytes());
		//通过get查询，返回一个result对象，所有的字段的数据都是封装在result里面了
		Result result = table.get(get);
		List<Cell> cells = result.listCells();  //获取一条数据所有的cell，所有数据值都是在cell里面 的
		for (Cell cell : cells) {
			byte[] family_name = CellUtil.cloneFamily(cell);//获取列族名
			byte[] column_name = CellUtil.cloneQualifier(cell);//获取列名
			byte[] rowkey = CellUtil.cloneRow(cell);//获取rowkey
			byte[] cell_value = CellUtil.cloneValue(cell);//获取cell值
			//需要判断字段的数据类型，使用对应的转换的方法，才能够获取到值
			if ("age".equals(Bytes.toString(column_name)) || "id".equals(Bytes.toString(column_name))) {
				System.out.println(Bytes.toString(family_name));
				System.out.println(Bytes.toString(column_name));
				System.out.println(Bytes.toString(rowkey));
				System.out.println(Bytes.toInt(cell_value));
			} else {
				System.out.println(Bytes.toString(family_name));
				System.out.println(Bytes.toString(column_name));
				System.out.println(Bytes.toString(rowkey));
				System.out.println(Bytes.toString(cell_value));
			}
		}
		table.close();
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
