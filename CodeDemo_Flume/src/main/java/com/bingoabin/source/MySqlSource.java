package com.bingoabin.source;

/**
 * @author xubin34
 * @date 2021/7/19 1:44 上午
 */
//自定义mysqlSource类
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.source.AbstractSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MySqlSource extends AbstractSource implements Configurable, PollableSource {

	//打印日志
	private static final Logger LOG = LoggerFactory.getLogger(MySqlSource.class);
	//定义sqlHelper
	private QueryMysql sqlSourceHelper;

	@Override
	public long getBackOffSleepIncrement() {
		return 0;
	}

	@Override
	public long getMaxBackOffSleepInterval() {
		return 0;
	}

	@Override
	public void configure(Context context) {
		//初始化
		try {
			sqlSourceHelper = new QueryMysql(context);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 接受mysql表中的数据
	 *
	 * @return
	 * @throws EventDeliveryException
	 */
	@Override
	public PollableSource.Status process() throws EventDeliveryException {
		try {
			//查询数据表
			List<List<Object>> result = sqlSourceHelper.executeQuery();
			//存放event的集合
			List<Event> events = new ArrayList<>();
			//存放event头集合
			HashMap<String, String> header = new HashMap<>();
			//如果有返回数据，则将数据封装为event
			if (!result.isEmpty()) {
				List<String> allRows = sqlSourceHelper.getAllRows(result);
				Event event = null;
				for (String row : allRows) {
					event = new SimpleEvent();
					event.setBody(row.getBytes());
					event.setHeaders(header);
					events.add(event);
				}
				//将event写入channel
				this.getChannelProcessor().processEventBatch(events);
				//更新数据表中的offset信息
				sqlSourceHelper.updateOffset2DB(result.size());
			}
			//等待时长
			Thread.sleep(sqlSourceHelper.getRunQueryDelay());
			return Status.READY;
		} catch (InterruptedException e) {
			LOG.error("Error procesing row", e);
			return Status.BACKOFF;
		}
	}

	@Override
	public synchronized void stop() {
		LOG.info("Stopping sql source {} ...", getName());
		try {
			//关闭资源
			sqlSourceHelper.close();
		} finally {
			super.stop();
		}
	}
}

//#第五步：打包上传到flume的lib目录下
// #将我们开发的代码，打成jar包上传到flume的lib目录下
//
// #第六步：开发flume的配置文件
// #开发flume的配置文件
// cd /kkb/install/apache-flume-1.6.0-cdh5.14.2-bin/conf
// vim mysqlsource.conf
// #内容如下
// #source中的内容根据自己的实际情况修改；如MySqlSource的FQCN
// # Name the components on this agent
// a1.sources = r1
// a1.sinks = k1
// a1.channels = c1
//
// # set source
// a1.sources.r1.type = com.kkb.flume.source.MySqlSource
// a1.sources.r1.connection.url = jdbc:mysql://node03:3306/mysqlsource
// a1.sources.r1.connection.user = root
// a1.sources.r1.connection.password = 123456
// a1.sources.r1.table = student
// a1.sources.r1.columns.to.select = *
// a1.sources.r1.start.from=0
// a1.sources.r1.run.query.delay=3000
//
// # Describe the channel
// a1.channels.c1.type = memory
// a1.channels.c1.capacity = 1000
// a1.channels.c1.transactionCapacity = 100
//
// # Describe the sink
// a1.sinks.k1.type = logger
//
// # Bind the source and sink to the channel
// a1.sources.r1.channels = c1
// a1.sinks.k1.channel = c1
//
// #第七步：启动flume
// cd /kkb/install/apache-flume-1.6.0-cdh5.14.2-bin
// bin/flume-ng agent -n a1 -c conf -f conf/mysqlsource.conf -Dflume.root.logger=info,console
