package com.bingo.zoptimize.cache

import com.bingo.zoptimize.bean.CoursePay
import com.bingo.zoptimize.utils.InitUtil
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object DatasetCacheDemo {

  def main( args: Array[String] ): Unit = {
    val sparkConf = new SparkConf().setAppName("DataSetCacheDemo")
//      .setMaster("local[*]")
    val sparkSession: SparkSession = InitUtil.initSparkSession(sparkConf)


    import sparkSession.implicits._
    val result = sparkSession.sql("select * from sparktuning.course_pay").as[CoursePay]
    result.cache()
    result.foreachPartition(( p: Iterator[CoursePay] ) => p.foreach(item => println(item.orderid)))
    while (true) {
    }

  }

}
