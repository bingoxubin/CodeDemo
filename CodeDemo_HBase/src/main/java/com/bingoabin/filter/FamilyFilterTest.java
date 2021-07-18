package com.bingoabin.filter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author xubin34
 * @date 2021/7/19 1:24 上午
 */
public class FamilyFilterTest {
	//列族过滤器FamilyFilter
//查询列族名包含f2的所有列族下面的数据

	/**
	 * 通过familyFilter来实现列族的过滤
	 * 需要过滤，列族名包含f2
	 * f1  f2   hello   world
	 */
	@Test
	public void familyFilter() throws IOException {
		Table table = connection.getTable(TableName.valueOf(TABLE_NAME));
		Scan scan = new Scan();
		SubstringComparator substringComparator = new SubstringComparator("f2");
		//通过familyfilter来设置列族的过滤器
		FamilyFilter familyFilter = new FamilyFilter(CompareFilter.CompareOp.EQUAL, substringComparator);
		scan.setFilter(familyFilter);
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
