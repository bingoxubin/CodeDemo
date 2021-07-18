package com.bingoabin.partition;

/**
 * @author xubin34
 * @date 2021/7/18 6:33 下午
 */

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

//#partition分区：mr执行当中，有一个默认的步骤是partition分区，partition分区的主要作用就是将相同的数据发送到同一个reduceTask里面去，即map生成的kv对，被分配到哪个分区中
// #mr中有一个抽象类Partitioner，默认使用的实现类是HashPartitioner
//
// #具体步骤
// 自定义分区器：
//     1.HashPartitioner实现Partitioner接口
//     2.实现了getPartition()方法，方法中对k取hash值，与上MAX_VALUE，结果模上reduceTask个数
//     3.默认分区器：(key.hashCode()&Integer.MAX_VALUE)%numReduceTasks
// main方法：
//     1.在main方法中加入分区器逻辑：job.setPartitionerClassCustomPartitioner.class)
//     2.设置reduceTask个数：job.setNumReduceTasks(3)
//自定义分区器
public class MyPartition extends Partitioner<Text, Text> {
	@Override
	public int getPartition(Text text, Text flowBean, int numPartitions) {
		String phoenNum = text.toString();
		if (null != phoenNum && !phoenNum.equals("")) {
			if (phoenNum.startsWith("135")) {
				return 0;
			} else if (phoenNum.startsWith("136")) {
				return 1;
			} else if (phoenNum.startsWith("137")) {
				return 2;
			} else if (phoenNum.startsWith("138")) {
				return 3;
			} else if (phoenNum.startsWith("139")) {
				return 4;
			} else {
				return 5;
			}
		} else {
			return 5;
		}
	}
}

//main方法中
// job.setPartitionerClass(PartitionOwn.class); //设置分区器
// job.setNumReduceTasks(6); //设置reduce task个数

//如果手动指定6个分区，reduceTask个数设置为3，报错
//如果手动指定6个分区，reduceTask个数设置为9，出现3个多余的空文件

//如何指定分区数？分两种情况
// 		1.使用默认分区器，设置setNumReduceTasks(6),就相当于设置了分区个数
// 		2.使用自定义分区器，比如上述代码中，表示设置了6个分区，setNumReduceTasks(6)表示设置reduceTask个数