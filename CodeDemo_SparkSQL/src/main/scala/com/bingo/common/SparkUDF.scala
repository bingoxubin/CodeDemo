package com.bingo.common

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.api.java.UDF1
import org.apache.spark.sql.types.DataTypes
import org.apache.spark.sql.{DataFrame, SparkSession}

import java.util.regex.{Matcher, Pattern}

object SparkUDF {

	def main(args: Array[String]): Unit = {
		Logger.getLogger("org").setLevel(Level.WARN)
		val sparkConf: SparkConf = new SparkConf().setMaster("local[8]").setAppName("sparkCSV")

		val session: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
		session.sparkContext.setLogLevel("WARN")
		val frame: DataFrame = session
		  .read
		  .format("csv")
		  .option("timestampFormat", "yyyy/MM/dd HH:mm:ss ZZ")
		  .option("header", "true")
		  .option("multiLine", true)
		  .load("file:///D:\\MarkDown\\kkbbook\\40-sparksql第一次课\\数据\\深圳链家二手房成交明细")

		frame.createOrReplaceTempView("house_sale")


		session.udf.register("house_udf", new UDF1[String, String] {

			val pattern: Pattern = Pattern.compile("^[0-9]*$")

			override def call(input: String): String = {
				val matcher: Matcher = pattern.matcher(input)
				if (matcher.matches()) {
					input
				} else {
					"1990"
				}
			}
		}, DataTypes.StringType)

		session.sql("select house_udf(house_age) from house_sale limit 200").show()
		session.stop()
	}
}
