package com.bingoabin

import org.apache.hudi.DataSourceReadOptions._
import org.apache.spark.SparkConf
import org.apache.spark.sql._


/**
  * TODO
  *
  * @version 1.0
  * @author cjp
  */
object PointInTimeQueryDemo {
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

    val basePath = "hdfs://hadoop1:8020/tmp/hudi_trips_cow"

    import sparkSession.implicits._
    val commits = sparkSession.sql("select distinct(_hoodie_commit_time) as commitTime from  hudi_trips_snapshot order by commitTime").map(k => k.getString(0)).take(50)
    val beginTime = "000"
    val endTime = commits(commits.length - 2)

    val tripsIncrementalDF = sparkSession.read.format("hudi").
      option(QUERY_TYPE.key(), QUERY_TYPE_INCREMENTAL_OPT_VAL).
      option(BEGIN_INSTANTTIME.key(), beginTime).
      option(END_INSTANTTIME.key(), endTime).
      load(basePath)

    tripsIncrementalDF.createOrReplaceTempView("hudi_trips_point_in_time")

    sparkSession.
      sql("select `_hoodie_commit_time`, fare, begin_lon, begin_lat, ts from hudi_trips_point_in_time where fare > 20.0").
      show()

  }

}
