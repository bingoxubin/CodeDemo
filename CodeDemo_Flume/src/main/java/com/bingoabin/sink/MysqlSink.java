package com.bingoabin.sink;

/**
 * @author xubin34
 * @date 2021/7/19 1:45 上午
 */
//#官方提供的sink类型已经很多，但是有时候并不能满足实际开发当中的需求，此时我们就需要根据实际需求自定义某些sink。如：需要把接受到的数据按照规则进行过滤之后写入到某张mysql表中，所以此时需要我们自己实现MySQLSink。
// #官方也提供了自定义sink的接口：
// #官网说明：https://flume.apache.org/FlumeDeveloperGuide.html#sink
//
// #代码开发步骤：
// 1. 根据官方说明自定义MysqlSink需要继承AbstractSink类并实现Configurable
// 2. 实现对应的方法
//    1. configure(Context context)
//       初始化context
//    2. start()
//       启动准备操作
//    3. process()
//       从channel获取数据，然后解析之后，保存在mysql表中
//    4. stop()
//       关闭相关资源

//--第一步：创建mysql数据库表
// -- 创建一个数据库
// CREATE DATABASE IF NOT EXISTS mysqlsource DEFAULT CHARACTER SET utf8 ;
// USE mysqlsource;
// -- 创建一个表，用户保存拉取目标表位置的信息
// CREATE TABLE mysqlsource.flume2mysql (
//   id INT(11) NOT NULL AUTO_INCREMENT,
//   createTime VARCHAR(64) NOT NULL,
//   content VARCHAR(255) NOT NULL,
//   PRIMARY KEY (id)
// ) ENGINE=INNODB DEFAULT CHARSET=utf8;

//第二步：定义mysqlSink类

import org.apache.flume.conf.Configurable;
import org.apache.flume.*;
import org.apache.flume.sink.AbstractSink;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义MysqlSink
 */
public class MysqlSink extends AbstractSink implements Configurable {
	private String mysqlurl = "";
	private String username = "";
	private String password = "";
	private String tableName = "";

	Connection con = null;

	@Override
	public Status process() {
		Status status = null;
		// Start transaction 获得Channel对象
		Channel ch = getChannel();
		Transaction txn = ch.getTransaction();
		txn.begin();
		try {
			Event event = ch.take();

			if (event != null) {
				//获取body中的数据
				String body = new String(event.getBody(), "UTF-8");

				//如果日志中有以下关键字的不需要保存，过滤掉
				if (body.contains("delete") || body.contains("drop") || body.contains("alert")) {
					status = Status.BACKOFF;
				} else {

					//存入Mysql
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String createtime = df.format(new Date());

					PreparedStatement stmt = con.prepareStatement("insert into " + tableName + " (createtime, content) values (?, ?)");
					stmt.setString(1, createtime);
					stmt.setString(2, body);
					stmt.execute();
					stmt.close();
					status = Status.READY;
				}
			} else {
				status = Status.BACKOFF;
			}

			txn.commit();
		} catch (Throwable t) {
			txn.rollback();
			t.getCause().printStackTrace();
			status = Status.BACKOFF;
		} finally {
			txn.close();
		}

		return status;
	}

	/**
	 * 获取配置文件中指定的参数
	 *
	 * @param context
	 */
	@Override
	public void configure(Context context) {
		mysqlurl = context.getString("mysqlurl");
		username = context.getString("username");
		password = context.getString("password");
		tableName = context.getString("tablename");
	}

	@Override
	public synchronized void start() {
		try {
			//初始化数据库连接
			con = DriverManager.getConnection(mysqlurl, username, password);
			super.start();
			System.out.println("finish start");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public synchronized void stop() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		super.stop();
	}

}

//#第三步：代码打包上传
// #将我们的代码打成jar包上传到flume的lib目录下
//
// #第四步：开发flume的配置文件
// cd /kkb/install/apache-flume-1.6.0-cdh5.14.2-bin/conf
// vim mysqlsink.conf
// #内容如下
// #根据自己的实际情况修改sink
// a1.sources = r1
// a1.sinks = k1
// a1.channels = c1
// #配置source
// a1.sources.r1.type = exec
// a1.sources.r1.command = tail -F /kkb/install/flumeData/data.log
// a1.sources.r1.channels = c1
//
// #配置channel
// a1.channels.c1.type = memory
// a1.channels.c1.capacity = 1000
// a1.channels.c1.transactionCapacity = 100
//
// #配置sink
// a1.sinks.k1.channel = c1
// a1.sinks.k1.type = com.kaikeba.sink.MysqlSink
// a1.sinks.k1.mysqlurl=jdbc:mysql://node03:3306/mysqlsource?useSSL=false
// a1.sinks.k1.username=root
// a1.sinks.k1.password=123456
// a1.sinks.k1.tablename=flume2mysql
//
// #第五步：启动flume
// cd /kkb/install/apache-flume-1.6.0-cdh5.14.2-bin
// bin/flume-ng agent -n a1 -c conf -f conf/mysqlsink.conf -Dflume.root.logger=info,console
//
// #第六步：创建文件验证数据进入mysql
// #创建文件，验证数据进入mysql数据库
// mkdir -p /kkb/install/flumeData
// echo "helloworld" >> /kkb/install/flumeData/data.log