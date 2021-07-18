package com.bingoabin.group;

/**
 * @author xubin34
 * @date 2021/7/18 9:48 下午
 */
//自定义分区类
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class GroupPartitioner extends Partitioner<OrderBean, NullWritable> {
	@Override
	public int getPartition(OrderBean orderBean, NullWritable nullWritable, int numPartitions) {
		//将每个订单的所有的记录，传入到一个reduce当中
		return orderBean.getOrderId().hashCode() % numPartitions;
	}
}

