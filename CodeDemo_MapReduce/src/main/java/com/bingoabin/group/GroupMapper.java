package com.bingoabin.group;

/**
 * @author xubin34
 * @date 2021/7/18 9:48 下午
 */
//自定义mapper类
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;
public class GroupMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable> {
	/**
	 * Order_0000001	Pdt_01	222.8
	 * Order_0000001	Pdt_05	25.8
	 * Order_0000002	Pdt_03	322.8
	 * Order_0000002	Pdt_04	522.4
	 * Order_0000002	Pdt_05	822.4
	 * Order_0000003	Pdt_01	222.8
	 * Order_0000003	Pdt_03	322.8
	 * Order_0000003	Pdt_04	522.4
	 */
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String[] fields = value.toString().split("\t");
		OrderBean orderBean = new OrderBean();
		orderBean.setOrderId(fields[0]);
		orderBean.setPrice(Double.valueOf(fields[2]));
		//输出orderBean
		context.write(orderBean, NullWritable.get());
	}
}
