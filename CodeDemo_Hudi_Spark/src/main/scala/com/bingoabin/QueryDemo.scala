package com.bingoabin

import org.apache.spark.SparkConf
import org.apache.spark.sql._


/**
  * TODO
  *
  * @version 1.0
  * @author cjp
  */
object QueryDemo {
  def main( args: Array[String] ): Unit = {
    // 创建 SparkSession
    val sparkConf = new SparkConf()
      .setAppName(this.getClass.getSimpleName)
      .setMaster("local[*]")
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sparkSession = SparkSession.builder()
      .config(sparkConf)
      .enableHiveSupport()
      .getOrCreate()

    val basePath = "hdfs://hadoop11:8020/tmp/hudi_trips_cow"

    val tripsSnapshotDF = sparkSession.
      read.
      format("hudi").
      load(basePath)

    //    时间旅行查询写法一
    //    sparkSession.read.
    //      format("hudi").
    //      option("as.of.instant", "20210728141108100").
    //      load(basePath)
    //
    //    时间旅行查询写法二
    //    sparkSession.read.
    //      format("hudi").
    //      option("as.of.instant", "2021-07-28 14:11:08.200").
    //      load(basePath)
    //
    //    时间旅行查询写法三：等价于"as.of.instant = 2021-07-28 00:00:00"
    //    sparkSession.read.
    //      format("hudi").
    //      option("as.of.instant", "2021-07-28").
    //      load(basePath)

    tripsSnapshotDF.createOrReplaceTempView("hudi_trips_snapshot")

    sparkSession
      .sql("select fare, begin_lon, begin_lat, ts from  hudi_trips_snapshot where fare > 20.0")
      .show()


  }

}
