package com.bingoabin.coprocessor;

/**
 * @author xubin34
 * @date 2021/7/19 12:56 上午
 */

//#打开hbase shell
// cd /kkb/install/hbase-1.2.0-cdh5.14.2/
// bin/hbase shell

// #在HBase当中创建一张表，表名proc1，并只有一个列族info
// hbase(main):053:0> create 'proc1','info'

//#创建第二张表proc2，作为目标表
// #将第一张表当中插入数据的部分列，使用协处理器，复制到proc2表当中来
// hbase(main):054:0> create 'proc2','info'

//开发HBase的协处理器

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;

import java.io.IOException;
import java.util.List;

public class Observer extends BaseRegionObserver {
	/**
	 * @param e
	 * @param put        插入到proc1表里面的数据，都是封装在put对象里面了，可以解析put对象，获取数据，获取到了数据之后，插入到proc2表里面去
	 * @param edit
	 * @param durability
	 * @throws IOException
	 */
	@Override
	public void prePut(ObserverContext<RegionCoprocessorEnvironment> e, Put put, WALEdit edit, Durability durability) throws IOException {
		//获取连接
		Configuration configuration = HBaseConfiguration.create();
		configuration.set("hbase.zookeeper.quorum", "node01:2181,node02:2181,node03:2181");
		Connection connection = ConnectionFactory.createConnection(configuration);

		//涉及到多个版本问题
		List<Cell> cells = put.get("info".getBytes(), "name".getBytes());
		Cell nameCell = cells.get(0);//获取最新的那个版本数据
		//Cell nameCell = put.get("info".getBytes(), "name".getBytes()).get(0);

		Table proc2 = connection.getTable(TableName.valueOf("proc2"));

		Put put1 = new Put(put.getRow());
		put1.add(nameCell);

		proc2.put(put1);
		proc2.close();
		connection.close();
	}
}

//#将项目打成jar包，并上传到HDFS上面
// #将我们的协处理器打成一个jar包，此处不需要用任何的打包插件即可
// #然后将打好的jar包上传到linux的/kkb/install路径下
// #再将jar包上传到HDFS
// cd /kkb/install
// mv original-hbaseStudy-1.0-SNAPSHOT.jar  processor.jar
// hdfs dfs -mkdir -p /processor
// hdfs dfs -put processor.jar /processor

//#将jar包挂载到proc1表
// hbase(main):056:0> describe 'proc1'
// hbase(main):055:0> alter 'proc1', METHOD => 'table_att', 'Coprocessor'=>'hdfs://node01:8020/processor/processor.jar|com.kkb.hbasemr.MyProcessor|1001|'
// #注意：根据自己的实际情况，修改全类名com.kkb.hbasemr.MyProcessor
// #再次查看'proc1'表；可以查看到我们的卸载器已经加载了
// hbase(main):043:0> describe 'proc1'