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
object DeleteDemo {
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
      read.
      format("hudi").
      load(basePath).
      createOrReplaceTempView("hudi_trips_snapshot")

    sparkSession.sql("select uuid, partitionpath from hudi_trips_snapshot").count()

    val ds = sparkSession.sql("select uuid, partitionpath from hudi_trips_snapshot").limit(2)

    val deletes = dataGen.generateDeletes(ds.collectAsList())
    val df = sparkSession.read.json(sparkSession.sparkContext.parallelize(deletes, 2))

    df.write.format("hudi").
      options(getQuickstartWriteConfigs).
      option(OPERATION.key(),"delete").
      option(PRECOMBINE_FIELD.key(), "ts").
      option(RECORDKEY_FIELD.key(), "uuid").
      option(PARTITIONPATH_FIELD.key(), "partitionpath").
      option(TBL_NAME.key(), tableName).
      mode(Append).
      save(basePath)

    val roAfterDeleteViewDF = sparkSession.
      read.
      format("hudi").
      load(basePath)

    roAfterDeleteViewDF.createOrReplaceTempView("hudi_trips_snapshot")

    // 返回的总行数应该比原来少2行
    sparkSession.sql("select uuid, partitionpath from hudi_trips_snapshot").count()


  }

}
