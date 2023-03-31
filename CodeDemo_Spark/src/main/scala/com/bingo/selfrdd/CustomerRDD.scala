package com.bingo.selfrdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partition, TaskContext}

class CustomerRDD(prev: RDD[SalesRecord], discountPercentage: Double) extends RDD[SalesRecord](prev) {


	//def discount(discountPercentage:Double) = new IteblogDiscountRDD(discountPercentage)

	//继承compute方法
	override def compute(split: Partition, context: TaskContext): Iterator[SalesRecord] = {

		firstParent[SalesRecord].iterator(split, context).map(salesRecord => {
			val discount = salesRecord.itemValue * discountPercentage
			new SalesRecord(salesRecord.transactionId,
				salesRecord.customerId, salesRecord.itemId, discount)
		})

	}

	//继承getPartitions方法
	override protected def getPartitions: Array[Partition] =
		firstParent[SalesRecord].partitions
}
