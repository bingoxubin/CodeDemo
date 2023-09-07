package com.bingo.core._03rdd._02operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark03_MapPartitionsWithIndex {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local").setAppName("map")
		val sc = new SparkContext(conf)
		val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)

		val mapRDD: RDD[Int] = rdd.mapPartitionsWithIndex(
			(index, iter) => {
				if(index == 1){
					iter
				}else{
					Nil.iterator
				}
			}
		)

		val rdds: RDD[(Int, Int)] = rdd.mapPartitionsWithIndex(
			(index, iter) => {
				iter.map(
					num => {
						(index, num)
					}
				)
			}
		)

		//mapRDD.collect().foreach(println)
		rdds.collect().foreach(println)
		sc.stop()
	}
}
