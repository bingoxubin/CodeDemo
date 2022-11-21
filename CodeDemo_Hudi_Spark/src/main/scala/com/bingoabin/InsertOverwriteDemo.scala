package com.bingoabin

import org.apache.hudi.DataSourceWriteOptions._
import org.apache.hudi.QuickstartUtils._
import org.apache.hudi.config.HoodieWriteConfig._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SaveMode._
import org.apache.spark.sql._

import scala.collection.JavaConversions._


/**
  * TODO
  *
  * @version 1.0
  * @author cjp
  */
object InsertOverwriteDemo {
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

    val tableName = "hudi_trips_cow"
    val basePath = "hdfs://hadoop1:8020/tmp/hudi_trips_cow"
    val dataGen = new DataGenerator

    sparkSession.
      read.format("hudi").
      load(basePath).
      select("uuid","partitionpath").
      sort("partitionpath","uuid").
      show(100, false)



    val inserts = convertToStringList(dataGen.generateInserts(10))
    val df = sparkSession.read.json(sparkSession.sparkContext.parallelize(inserts, 2)).
      filter("partitionpath = 'americas/united_states/san_francisco'")

    df.write.format("hudi").
      options(getQuickstartWriteConfigs).
      option(OPERATION.key(),"insert_overwrite").
      option(PRECOMBINE_FIELD.key(), "ts").
      option(RECORDKEY_FIELD.key(), "uuid").
      option(PARTITIONPATH_FIELD.key(), "partitionpath").
      option(TBL_NAME.key(), tableName).
      mode(Append).
      save(basePath)

    sparkSession.
      read.format("hudi").
      load(basePath).
      select("uuid","partitionpath").
      sort("partitionpath","uuid").
      show(100, false)

  }

}
