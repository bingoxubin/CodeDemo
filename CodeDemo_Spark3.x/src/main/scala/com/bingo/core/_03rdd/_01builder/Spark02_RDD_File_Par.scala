package com.bingo.core._03rdd._01builder

import org.apache.spark.{SparkConf, SparkContext}
//文件分区 如何进行分区
object Spark02_RDD_File_Par {

    def main(args: Array[String]): Unit = {

        // TODO 准备环境
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
        val sc = new SparkContext(sparkConf)

        // TODO 创建RDD
        // textFile可以将文件作为数据处理的数据源，默认也可以设定分区。
        //     minPartitions : 最小分区数量
        //     math.min(defaultParallelism, 2)
        //val rdd = sc.textFile("datas/1.txt")
        // 如果不想使用默认的分区数量，可以通过第二个参数指定分区数
        // Spark读取文件，底层其实使用的就是Hadoop的读取方式
        // 分区数量的计算方式：
        //    totalSize = 7   其中1.txt的文件大小是7个字节
        //    goalSize =  7 / 2 = 3（byte） （表示每个分区存放3个字节）

        //    7 / 3 = 2...1 (1.1) + 1 = 3(分区)
        //  1.1表示剩余的数，占每个分区字节数的多少，如果大于了10%，要产生新的分区

        //
        val rdd = sc.textFile("datas/1.txt", 2)
        //如果分区设置为2，分区还是3，如果数据是1 2 3
        //最终产生3个文件第一个文件1,2  第二个文件3  第三个文件空

        rdd.saveAsTextFile("output")


        // TODO 关闭环境
        sc.stop()
    }
}
