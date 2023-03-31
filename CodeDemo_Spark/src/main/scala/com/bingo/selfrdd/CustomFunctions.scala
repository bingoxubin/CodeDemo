package com.bingo.selfrdd

import org.apache.spark.rdd.RDD

class CustomFunctions(rdd:RDD[SalesRecord]) {
	def changeDatas:RDD[Double] =  rdd.map(x => x.itemValue )
	def getTotalValue:Double =  rdd.map(x => x.itemValue).sum()
	def discount(discountPercentage:Double) = new CustomerRDD(rdd,discountPercentage)
}

object CustomFunctions{
	implicit def addIteblogCustomFunctions(rdd: RDD[SalesRecord]) = new CustomFunctions(rdd)

}
