package com.bingoabin.scandata;

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
 * @date 2021/7/19 1:20 上午
 */
public class ScanData {
	/**
	 * 不知道rowkey的具体值，我想查询rowkey范围值是0003  到0006
	 * select * from myuser  where age > 30  and id < 8  and name like 'zhangsan'
	 */
	@Test
	public void scanData() throws IOException {
		//获取table
		Table table = connection.getTable(TableName.valueOf(TABLE_NAME));
		Scan scan = new Scan();//没有指定startRow以及stopRow  全表扫描
		//只扫描f1列族
		scan.addFamily("f1".getBytes());
		//扫描 f2列族 phone  这个字段
		scan.addColumn("f2".getBytes(), "phone".getBytes());
		scan.setStartRow("0003".getBytes());
		scan.setStopRow("0007".getBytes());
		//通过getScanner查询获取到了表里面所有的数据，是多条数据
		ResultScanner scanner = table.getScanner(scan);
		//遍历ResultScanner 得到每一条数据，每一条数据都是封装在result对象里面了
		for (Result result : scanner) {
			List<Cell> cells = result.listCells();
			for (Cell cell : cells) {
				byte[] family_name = CellUtil.cloneFamily(cell);
				byte[] qualifier_name = CellUtil.cloneQualifier(cell);
				byte[] rowkey = CellUtil.cloneRow(cell);
				byte[] value = CellUtil.cloneValue(cell);
				//判断id和age字段，这两个字段是整形值
				if ("age".equals(Bytes.toString(qualifier_name)) || "id".equals(Bytes.toString(qualifier_name))) {
					System.out.println("数据的rowkey为" + Bytes.toString(rowkey) + "======数据的列族为" + Bytes.toString(family_name) + "======数据的列名为" + Bytes.toString(qualifier_name) + "==========数据的值为" + Bytes.toInt(value));
				} else {
					System.out.println("数据的rowkey为" + Bytes.toString(rowkey) + "======数据的列族为" + Bytes.toString(family_name) + "======数据的列名为" + Bytes.toString(qualifier_name) + "==========数据的值为" + Bytes.toString(value));
				}
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
