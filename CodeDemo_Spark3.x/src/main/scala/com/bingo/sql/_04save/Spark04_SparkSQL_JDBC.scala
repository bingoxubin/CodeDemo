package com.bingo.sql._04save

import org.apache.spark.SparkConf
import org.apache.spark.sql._

object Spark04_SparkSQL_JDBC {

    def main(args: Array[String]): Unit = {

        // TODO 创建SparkSQL的运行环境
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
        val spark = SparkSession.builder().config(sparkConf).getOrCreate()
        import spark.implicits._

        // 读取MySQL数据
        val df = spark.read
                .format("jdbc")
                .option("url", "jdbc:mysql://localhost:3306/test")
                .option("driver", "com.mysql.jdbc.Driver")
                .option("user", "root")
                .option("password", "111111")
                .option("dbtable", "test")
                .load()
        //df.show

        // 保存数据  如果目标表不存在，默认新建
        df.write
                .format("jdbc")
                .option("url", "jdbc:mysql://localhost:3306/test")
                .option("driver", "com.mysql.jdbc.Driver")
                .option("user", "root")
                .option("password", "111111")
                .option("dbtable", "test1")
                .mode(SaveMode.Append)
                .save()


        // TODO 关闭环境
        spark.close()
    }
}
