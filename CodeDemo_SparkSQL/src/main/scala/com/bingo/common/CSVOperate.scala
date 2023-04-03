package com.bingo.common

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

import java.util.Properties

object CSVOperate {

	def main(args: Array[String]): Unit = {
		val sparkConf: SparkConf = new SparkConf().setMaster("local[8]").setAppName("sparkCSV")

		val session: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
		session.sparkContext.setLogLevel("WARN")
		val frame: DataFrame = session
		  .read
		  .format("csv")
		  .option("timestampFormat", "yyyy/MM/dd HH:mm:ss ZZ")
		  .option("header", "true")
		  .option("multiLine", true)
		  .load("file:///D:\\MarkDown\\kkbbook\\40-sparksql第一次课\\数据\\招聘数据")

		frame.createOrReplaceTempView("job_detail")
		//session.sql("select job_name,job_url,job_location,job_salary,job_company,job_experience,job_class,job_given,job_detail,company_type,company_person,search_key,city from job_detail where job_company = '北京无极慧通科技有限公司'  ").show(80)
		val prop = new Properties()
		prop.put("user", "root")
		prop.put("password", "111111")

		frame.write.mode(SaveMode.Append).jdbc("jdbc:mysql://localhost:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=UTF-8", "mydb.jobdetail_copy", prop)

	}
}
