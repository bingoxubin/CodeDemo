package com.bingo.selfrdd

import com.bingo.selfrdd.CustomFunctions.addIteblogCustomFunctions
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


case class SalesRecord(val transactionId: String,
					   val customerId: String,
					   val itemId: String,
					   val itemValue: Double) extends Serializable


object SparkMain {


	def main(args: Array[String]): Unit = {
		val sparkConf = new SparkConf().setAppName(this.getClass.getName).setMaster("local[*]")
		val sc = new SparkContext(sparkConf)
		val dataRDD = sc.textFile("file:///D:\\开课吧课程资料\\15、scala与spark课程资料\\2、spark课程\\spark_day03\\2、数据准备\\sales.txt")
		val salesRecordRDD = dataRDD.map(row => {
			val colValues = row.split(",")
			new SalesRecord(colValues(0), colValues(1), colValues(2), colValues(3).toDouble)
		})

		println("Spark RDD API : " + salesRecordRDD.map(_.itemValue).sum)

		//通过隐式转换的方法，增加rdd的transformation算子
		val moneyRDD: RDD[Double] = salesRecordRDD.changeDatas
		println("customer RDD  API:" + salesRecordRDD.changeDatas.collect().toBuffer)


		//给rdd增加action算子
		val totalResult: Double = salesRecordRDD.getTotalValue
		println("total_result" + totalResult)

		//自定义RDD，将RDD转换成为新的RDD
		val resultCountRDD: CustomerRDD = salesRecordRDD.discount(0.8)

		println(resultCountRDD.collect().toBuffer)

		sc.stop()


	}
}