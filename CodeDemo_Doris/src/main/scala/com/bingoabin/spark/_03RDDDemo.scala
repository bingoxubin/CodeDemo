package com.bingoabin.spark

import org.apache.spark.{SparkConf, SparkContext}

object _03RDDDemo {
	def main(args: Array[String]): Unit = {
		val sparkConf = new SparkConf().setAppName("RDDDemo")
		  .setMaster("local[*]") //TODO 要打包提交集群执行，注释掉
		val sc = new SparkContext(sparkConf)

		//目前只支持读
		import org.apache.doris.spark._
		val dorisSparkRDD = sc.dorisRDD(
			tableIdentifier = Some("test_db.table1"),
			cfg = Some(Map(
				"doris.fenodes" -> "bingo41:8030",
				"doris.request.auth.user" -> "test",
				"doris.request.auth.password" -> "test"
			))
		)
		dorisSparkRDD.collect().foreach(println)
	}
}
