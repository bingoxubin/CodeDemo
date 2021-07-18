package com.bingoabin.createtable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;

/**
 * @author xubin34
 * @date 2021/7/19 1:04 上午
 */
public class CreateTable {
	//创建myuser表，此表有两个列族f1和f2
	//操作数据库  第一步：获取连接  第二步：获取客户端对象   第三步：操作数据库  第四步：关闭

	/**
	 * 创建一张表  myuser  两个列族  f1   f2
	 */
	@Test
	public void createTable() throws IOException {
		Configuration configuration = HBaseConfiguration.create();
		//连接HBase集群不需要指定HBase主节点的ip地址和端口号
		configuration.set("hbase.zookeeper.quorum", "node01:2181,node02:2181,node03:2181");
		//创建连接对象
		Connection connection = ConnectionFactory.createConnection(configuration);
		//获取连接对象，创建一张表
		//获取管理员对象，来对手数据库进行DDL的操作
		Admin admin = connection.getAdmin();
		//指定我们的表名
		TableName myuser = TableName.valueOf("myuser");
		HTableDescriptor hTableDescriptor = new HTableDescriptor(myuser);
		//指定两个列族
		HColumnDescriptor f1 = new HColumnDescriptor("f1");
		HColumnDescriptor f2 = new HColumnDescriptor("f2");
		hTableDescriptor.addFamily(f1);
		hTableDescriptor.addFamily(f2);

		admin.createTable(hTableDescriptor);
		admin.close();
		connection.close();
	}
}
