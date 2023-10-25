package com.bingo.sql._02udf

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object Spark02_SparkSQL_UDF {

    def main(args: Array[String]): Unit = {

        // TODO 创建SparkSQL的运行环境
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
        val spark = SparkSession.builder().config(sparkConf).getOrCreate()
        import spark.implicits._

        val df = spark.read.json("datas/user.json")
        df.createOrReplaceTempView("user")

        spark.udf.register("prefixName", (name:String) => {
            "Name: " + name
        })

        spark.sql("select age, prefixName(username) from user").show
        //这种方式不行：
        //spark.sql("select age,'Name:' + username from user").show()


        // TODO 关闭环境
        spark.close()
    }
}
