package com.bingoabin.group;

/**
 * @author xubin34
 * @date 2021/7/18 9:49 下午
 */
//自定义reduce类
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
public class GroupReducer extends Reducer<OrderBean, NullWritable, OrderBean, NullWritable> {
	/**
	 * Order_0000002	Pdt_03	322.8
	 * Order_0000002	Pdt_04	522.4
	 * Order_0000002	Pdt_05	822.4
	 * => 这一组中有3个kv并且是排序的
	 * Order_0000002	Pdt_05	822.4
	 * Order_0000002	Pdt_04	522.4
	 * Order_0000002	Pdt_03	322.8
	 */
	@Override
	protected void reduce(OrderBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
		//Order_0000002	Pdt_05	822.4 获得了当前订单中进而最高的商品
		context.write(key, NullWritable.get());
	}
}
