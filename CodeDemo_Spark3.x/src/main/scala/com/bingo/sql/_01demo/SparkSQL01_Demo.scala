package com.bingo.sql._01demo

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object SparkSQL01_Demo {
	def main(args: Array[String]): Unit = {
		//创建上下文环境对象
		val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparksql01_demo")
		//创建sparksession对象
		val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

		//RDD => DataFrame => DataSet转化需要引入隐式转换规则，否则无法转换
		//spark不是包名，是上下文环境对象
		import spark.implicits._

		//读取json文件  创建DataFrame {"username":"zhangsan","age":18}
		val df: DataFrame = spark.read.json("datas/test.json")
		df.show()

		//sql 风格语法
		df.createOrReplaceTempView("user")
		spark.sql("select avg(age) from user").show

		//dsl 风格语法
		df.select("username","age").show()

		//RDD => DataFrame => DataSet
		//创建rdd
		val rdd1: RDD[(Int, String, Int)] = spark.sparkContext.makeRDD(List((1, "zhangsan", 30), (2, "lisi", 28), (3, "wwangwu", 20)))
		//RDD -> DataFrame
		val df1: DataFrame = rdd1.toDF("id", "name", "age")
		df1.show()
		//DataFrame -> DataSet
		val ds1: Dataset[User] = df1.as[User]
		ds1.show()

		//DataSet => DataFrame => RDD
		//DataSet -> DataFrame
		val df2: DataFrame = ds1.toDF()
		//DataFrame -> RDD rdd返回的RDD类型为Row，里面提供了getXXX方法可以获取字段值，类似jdbc处理结果集，但索引从0开始
		val rdd2: RDD[Row] = df2.rdd
		rdd2.foreach(a => println(a.getString(1)))
		//RDD -> DataSet
		val ds2: Dataset[User] = rdd1.map {
			case (id, name, age) => User(id, name, age)
		}.toDS()
		ds2.show()
		//DataSet -> RDD
		val rdd3: RDD[User] = ds2.rdd

		spark.stop()
	}

	case class User(id:Int,name:String,age:Int)
}
