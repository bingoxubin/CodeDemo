package com.bingo.core._04acc

import org.apache.spark.{SparkConf, SparkContext}

//有问题的情况
object Spark01_Acc {

    def main(args: Array[String]): Unit = {

        val sparConf = new SparkConf().setMaster("local").setAppName("Acc")
        val sc = new SparkContext(sparConf)

        val rdd = sc.makeRDD(List(1,2,3,4))

        // reduce : 分区内计算，分区间计算
        //val i: Int = rdd.reduce(_+_)
        //println(i)

        //注意：这种方式，因为是分区的，多次修改，可能不是10，多种可能
        var sum = 0
        rdd.foreach(
            num => {
                sum += num
                println(sum)
            }
        )
        println("sum = " + sum)

        sc.stop()
    }
}
