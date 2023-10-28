package com.bingo.sql._04save

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkReadTest {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("ReadTest")
		val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

		import spark.implicits._

		//报错，默认load只能是parquet文件
		//val df: DataFrame = spark.read.load("datas/test.json")
		val df: DataFrame = spark.read.load("datas/users.parquet")
		df.show()

		//读json的方式
		val df1: DataFrame = spark.read.format("json").load("datas/test.json")
		//或者更简单的方式
		spark.read.json("datas/test.json")
		df1.show()

		//读取csv格式
		val df2: DataFrame = spark.read.format("csv")
		  .option("sep", ";")
		  .option("inferSchema", "true")
		  .option("header", "true")
		  .load("datas/people.csv")
		df2.show()

		//保存，默认是parquet格式
		df.write.mode("append").save("output")
		//可以用一下方式保存其他方式
		//df.write.format("json").save("output")
		//添加方式 有默认error append overwrite ignore（已经存在就忽略）

		spark.stop()
	}
}
