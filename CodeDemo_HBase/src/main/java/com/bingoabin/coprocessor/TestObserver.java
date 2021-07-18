package com.bingoabin.coprocessor;

/**
 * @author xubin34
 * @date 2021/7/19 12:58 上午
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

public class TestObserver {
	@Test
	public void testPut() throws Exception {
		//获取连接
		Configuration configuration = HBaseConfiguration.create();
		configuration.set("hbase.zookeeper.quorum", "node01,node02");

		Connection connection = ConnectionFactory.createConnection(configuration);

		Table proc1 = connection.getTable(TableName.valueOf("proc1"));

		Put put1 = new Put(Bytes.toBytes("hello_world"));

		put1.addColumn(Bytes.toBytes("info"), "name".getBytes(), "helloworld".getBytes());
		put1.addColumn(Bytes.toBytes("info"), "gender".getBytes(), "abc".getBytes());
		put1.addColumn(Bytes.toBytes("info"), "nationality".getBytes(), "test".getBytes());
		proc1.put(put1);
		byte[] row = put1.getRow();
		System.out.println(Bytes.toString(row));
		proc1.close();
		connection.close();
	}
}

//#注意：如果需要卸载我们的协处理器，那么进入hbase的shell命令行，执行以下命令即可
// disable 'proc1'
// alter 'proc1', METHOD=>'table_att_unset', NAME=>'coprocessor$1'
// enable 'proc1'
