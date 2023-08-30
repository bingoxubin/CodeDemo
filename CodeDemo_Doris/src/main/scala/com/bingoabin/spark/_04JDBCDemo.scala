package com.bingoabin.spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

//这种方式是早期写法，Spark 无法感知Doris 的数据分布，会导致打到 Doris 的查询压力非常大。
object _04JDBCDemo {
	def main(args: Array[String]): Unit = {
		val sparkConf = new SparkConf().setAppName("JDBCDemo").setMaster("local[*]")
		val sparkSession = SparkSession.builder().config(sparkConf).getOrCreate()

		// 读取数据
		val df = sparkSession.read.format("jdbc")
		  .option("url", "jdbc:mysql://bingo41:9030/test_db")
		  .option("user", "test")
		  .option("password", "test")
		  .option("dbtable", "table1")
		  .load()

		df.show()

		// 写入数据
		/*    import sparkSession.implicits._
			val mockDataDF = List(
			  (21,23, "haha", 8),
			  (21, 3, "hehe", 9),
			  (21, 3, "heihei", 10)
			).toDF("siteid", "citycode", "username","pv")

			val prop = new Properties()
			prop.setProperty("user", "test")
			prop.setProperty("password", "test")

			mockDataDF.write.mode(SaveMode.Append)
			  .jdbc("jdbc:mysql://hadoop1:9030/test_db", "table1", prop)*/

	}
}
