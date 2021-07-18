package com.bingoabin.reducejoin;

/**
 * @author xubin34
 * @date 2021/7/18 10:36 下午
 */
//2、定义Reducer

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;

public class ReduceJoinReducer extends Reducer<Text, Text, Text, NullWritable> {
	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		//p0003 商品，订单的数据多条；保存订单信息
		ArrayList<String> orders = new ArrayList<>();
		//保存商品信息
		String product = "";

		for (Text value : values) {
			if (value.toString().startsWith("p")) {//商品
				product = value.toString();
			} else {
				orders.add(value.toString());
			}
		}

		for (String order : orders) {
			context.write(new Text(order + "\t" + product), NullWritable.get());
		}
	}
}


