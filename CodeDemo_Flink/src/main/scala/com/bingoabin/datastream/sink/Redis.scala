package com.bingoabin.datastream.sink

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.redis.RedisSink
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig
import org.apache.flink.streaming.connectors.redis.common.mapper.{RedisCommand, RedisCommandDescription, RedisMapper}

//flink实时程序处理保存结果到redis中
object Redis {
	def main(args: Array[String]): Unit = {
		//获取程序入口类
		val executionEnvironment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		//组织数据
		val streamSource: DataStream[String] = executionEnvironment.fromElements("1 hadoop", "2 spark", "3 flink")
		//将数据包装成为key,value对形式的tuple
		val tupleValue: DataStream[(String, String)] = streamSource.map(x => (x.split(" ")(0), x.split(" ")(1)))
		val builder = new FlinkJedisPoolConfig.Builder
		//设置redis客户端参数
		builder.setHost("localhost")
		builder.setPort(6379)
		builder.setTimeout(5000)
		builder.setMaxTotal(50)
		builder.setMaxIdle(10)
		builder.setMinIdle(5)
		val config: FlinkJedisPoolConfig = builder.build()
		//获取redis  sink
		val redisSink = new RedisSink[Tuple2[String, String]](config, new MyRedisMapper)
		//使用我们自定义的sink
		tupleValue.addSink(redisSink)
		//执行程序
		executionEnvironment.execute("redisSink")
	}
}

//定义一个RedisMapper类
class MyRedisMapper extends RedisMapper[Tuple2[String, String]] {
	override def getCommandDescription: RedisCommandDescription = {
		//设置插入数据到redis的命令
		new RedisCommandDescription(RedisCommand.SET)
	}

	//指定key
	override def getKeyFromData(data: (String, String)): String = {
		data._1
	}

	//指定value
	override def getValueFromData(data: (String, String)): String = {
		data._2
	}
}
