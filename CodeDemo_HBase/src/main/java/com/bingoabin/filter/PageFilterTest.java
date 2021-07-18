package com.bingoabin.filter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author xubin34
 * @date 2021/7/19 1:33 上午
 */
public class PageFilterTest {
	//分页过滤器PageFilter
	//通过pageFilter实现分页过滤器

	/**
	 * HBase当中的分页
	 */
	@Test
	public void hbasePageFilter() throws IOException {
		int pageNum = 3;
		int pageSize = 2;
		Scan scan = new Scan();
		if (pageNum == 1) {
			//获取第一页的数据
			scan.setMaxResultSize(pageSize);
			scan.setStartRow("".getBytes());
			//使用分页过滤器来实现数据的分页
			PageFilter filter = new PageFilter(pageSize);
			scan.setFilter(filter);
			ResultScanner scanner = table.getScanner(scan);
			System.out.println(scanner);
		} else {
			String startRow = "";
			//扫描数据的调试 扫描五条数据
			int scanDatas = (pageNum - 1) * pageSize + 1;
			scan.setMaxResultSize(scanDatas);//设置一步往前扫描多少条数据
			PageFilter filter = new PageFilter(scanDatas);
			scan.setFilter(filter);
			ResultScanner scanner = table.getScanner(scan);
			for (Result result : scanner) {
				byte[] row = result.getRow();//获取rowkey
				//最后一次startRow的值就是0005
				startRow = Bytes.toString(row);//循环遍历我们多有获取到的数据的rowkey
				//最后一条数据的rowkey就是我们需要的起始的rowkey
			}
			//获取第三页的数据
			scan.setStartRow(startRow.getBytes());
			scan.setMaxResultSize(pageSize);//设置我们扫描多少条数据
			PageFilter filter1 = new PageFilter(pageSize);
			scan.setFilter(filter1);
			ResultScanner scanner1 = table.getScanner(scan);
			System.out.println(scanner1);
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
