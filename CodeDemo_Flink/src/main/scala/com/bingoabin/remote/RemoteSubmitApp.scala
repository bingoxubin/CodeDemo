package com.bingoabin.remote

import org.apache.flink.streaming.api.scala._


object RemoteSubmitApp extends App {

	val host: String = "hadoop11"
	val port: Int = 8081
	val jarFiles = "E:\\CDHProjectDemo\\flink-demo\\target\\flink-demo-0.0.1-SNAPSHOT.jar"

	val env = StreamExecutionEnvironment.createRemoteEnvironment(host, port, jarFiles)

	val socketHost: String = "node01"
	val socketPort: Int = 7777
	val socketDs: DataStream[String] = env.socketTextStream(socketHost, socketPort)

	socketDs.flatMap(_.split(" "))
	  .map((_, 1))
	  .keyBy(0)
	  .sum(1)
	  .print()

	env.execute("Remote Submit Job")
}
