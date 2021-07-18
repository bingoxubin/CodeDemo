package com.bingoabin.reducejoin;

/**
 * @author xubin34
 * @date 2021/7/18 10:36 下午
 */
//假如数据量巨大，两表的数据是以文件的形式存储在hdfs中，需要用mr程序来实现以下sql查询运算：
//select  a.id,a.date,b.name,b.category_id,b.price from t_order a join t_product  b on a.pid = b.id
//t_order 订单数据表    id  date  pid       amount
//t_product 商品信息表  id(pid)  pname category_id  price

//实现机制：通过将关联的条件作为map输出的key，将两表满足join条件的数据并携带数据所来源的文件信息，发往同一个reducetask，在reduce中进行数据的串联
//1、定义Mapper

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ReduceJoinMapper extends Mapper<LongWritable, Text, Text, Text> {
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		//现在我们读取了两个文件，如何确定当前处理的这一行数据是来自哪一个文件里面的
		//方式一：通过获取文件的切片，获得文件明
        /*
        FileSplit inputSplit = (FileSplit) context.getInputSplit();//获取我们输入的文件的切片
        //获取文件名称
        String name = inputSplit.getPath().getName();
        if (name.equals("orders.txt")) {
            //订单表数据
        } else {
            //商品表数据
        }
        */
		String[] split = value.toString().split(",");

		//方式二：因为t_product表，都是以p开头，所以可以作为判断的依据
		if (value.toString().startsWith("p")) {
			//p0002,锤子T1,1000,3000
			//以商品id作为key2,相同商品的数据都会到一起去
			context.write(new Text(split[0]), value);
		} else {
			//order
			// 1001,20150710,p0001,2
			context.write(new Text(split[2]), value);
		}
	}
}
