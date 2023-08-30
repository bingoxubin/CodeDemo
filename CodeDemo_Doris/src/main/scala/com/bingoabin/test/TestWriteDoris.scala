package com.bingoabin.test

import org.apache.spark.SparkConf
import org.apache.spark.sql.{SaveMode, SparkSession}

import java.util.Properties

//从doris中写入数据
object TestWriteDoris {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("testWriteDoris").setMaster("local[*]")
    val sparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    val df = sparkSession
      .createDataFrame(Array((4, "2020-09-13", "testName1", 18), (5, "2020-09-13", "testName2", 18), (6, "2020-09-13", "testName3", 18)))
      .toDF("id", "time", "name", "age")
    val prop = new Properties()
    prop.setProperty("user", "root")
    prop.setProperty("password", "111111")
    //doris地址 "http://doris1:9030/doris"，跟操作mysql一致
    df.write.mode(SaveMode.Append).jdbc("jdbc:mysql://localhost:3306/doris", "doris", prop)
    print("写入成功！")
  }
}
