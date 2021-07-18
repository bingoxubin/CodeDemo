package com.bingoabin.filter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author xubin34
 * @date 2021/7/19 1:21 上午
 */
public class RowFilterTest {
	/**
	 * rowKey过滤器RowFilter
	 * 通过RowFilter过滤比rowKey  0003小的所有值出来
	 * 查询所有的rowkey比0003小的所有的数据
	 */
	@Test
	public void rowFilter() throws IOException {
		Table table = connection.getTable(TableName.valueOf(TABLE_NAME));
		Scan scan = new Scan();
		//获取我们比较对象
		BinaryComparator binaryComparator = new BinaryComparator("0003".getBytes());
		/***
		 * rowFilter需要加上两个参数
		 * 第一个参数就是我们的比较规则
		 * 第二个参数就是我们的比较对象
		 */
		RowFilter rowFilter = new RowFilter(CompareFilter.CompareOp.LESS, binaryComparator);
		//为我们的scan对象设置过滤器
		scan.setFilter(rowFilter);
		ResultScanner scanner = table.getScanner(scan);
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
