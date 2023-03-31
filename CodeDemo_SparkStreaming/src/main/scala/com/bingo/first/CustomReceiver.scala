/**
  * 自定义一个Receiver，这个Receiver从socket中接收数据
  * 使用方式：nc -lk 8888
  */
package com.bingo.first

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket
import java.nio.charset.StandardCharsets

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.internal.Logging
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 自定义数据源
  */
object CustomReceiver {
	def main(args: Array[String]): Unit = {
		Logger.getLogger("org").setLevel(Level.ERROR)

		// todo: 1、创建SparkConf对象
		val sparkConf: SparkConf = new SparkConf()
		  .setAppName("CustomReceiver")
		  .setMaster("local[2]")

		// todo: 2、创建StreamingContext对象
		val ssc = new StreamingContext(sparkConf, Seconds(2))

		//todo: 3、调用 receiverStream api，将自定义的Receiver传进去
		val receiverStream = ssc.receiverStream(new CustomReceiver("node01", 8888))

		//todo: 4、对数据进行处理
		val result: DStream[(String, Int)] = receiverStream
		  .flatMap(_.split(" "))
		  .map((_, 1))
		  .reduceByKey(_ + _)
		//todo: 5、打印结果
		result.print()

		//todo: 6、开启流式计算
		ssc.start()
		ssc.awaitTermination()

	}

}

/**
  * 自定义source数据源
  *
  * @param host
  * @param port
  */
class CustomReceiver(host: String, port: Int) extends Receiver[String](StorageLevel.MEMORY_AND_DISK_SER) with Logging {
	override def onStart(): Unit = {
		//启动一个线程，开始接受数据
		new Thread("socket receiver") {
			override def run(): Unit = {
				receive()
			}
		}.start()
	}

	/** Create a socket connection and receive data until receiver is stopped */
	private def receive() {
		var socket: Socket = null
		var userInput: String = null
		try {
			logInfo("Connecting to " + host + ":" + port)
			socket = new Socket(host, port)
			logInfo("Connected to " + host + ":" + port)
			val reader = new BufferedReader(
				new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))

			userInput = reader.readLine()
			while (!isStopped && userInput != null) {
				store(userInput)
				userInput = reader.readLine()
			}
			reader.close()
			socket.close()
			logInfo("Stopped receiving")
			restart("Trying to connect again")
		} catch {
			case e: java.net.ConnectException =>
				restart("Error connecting to " + host + ":" + port, e)
			case t: Throwable =>
				restart("Error receiving data", t)
		}
	}

	override def onStop(): Unit = {

	}
}
