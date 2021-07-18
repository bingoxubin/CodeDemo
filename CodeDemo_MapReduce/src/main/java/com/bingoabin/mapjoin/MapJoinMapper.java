package com.bingoabin.mapjoin;

/**
 * @author xubin34
 * @date 2021/7/18 10:22 下午
 */
//适用于关联表中有小表的情形
//将小表分发到所有的map节点，这样map节点就可以在本地对自己所读到的大表数据进行join输出最终结果，提高join操作的并发度，加快处理速度

//案例：先在mapper类中预先定义好小表，进行join
//1、定义mapper类
//1、原理阐述
// - 适用于关联表中有小表的情形；
// - 可以将小表分发到所有的map节点，这样，map节点就可以在本地对自己所读到的大表数据进行join并输出最终结果，可以大大提高join操作的并发度，加快处理速度
//
// 2、实现示例
// - 先在mapper类中预先定义好小表，进行join
// - 引入实际场景中的解决方案：一次加载数据库或者用

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class MapJoinMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
	//用于保存商品表的数据；productMap中的key是商品id，value是与key对应的表记录
	private Map<String, String> productMap;

	//初始化方法，只在程序启动调用一次
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		productMap = new HashMap<String, String>();
		Configuration configuration = context.getConfiguration();
		//获取到所有的缓存文件
		//方式一
		URI[] cacheFiles = Job.getInstance(context.getConfiguration()).getCacheFiles();
		//方式二：deprecated
		//URI[] cacheFiles = DistributedCache.getCacheFiles(configuration);

		//现在只有一个缓存文件放进了分布式缓存中
		URI cacheFile = cacheFiles[0];

		//获取FileSystem
		FileSystem fileSystem = FileSystem.get(cacheFile, configuration);
		//读取文件，获取到输入流。这里面装的都是商品表的数据
		FSDataInputStream fsDataInputStream = fileSystem.open(new Path(cacheFile));

		/**
		 * 商品表数据如下：
		 * p0001,xiaomi,1000,2
		 * p0002,appale,1000,3
		 * p0003,samsung,1000,4
		 */
		//获取到BufferedReader之后，可以一行一行的读取数据
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fsDataInputStream));
		String line = null;
		//每次循环，获得表的一行数据
		while ((line = bufferedReader.readLine()) != null) {
			String[] split = line.split(",");
			productMap.put(split[0], line);
		}
	}

	//value   订单表的记录，如1001,20150710,p0001,2
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String[] split = value.toString().split(",");
		//获取订单表的商品id
		String pid = split[2];
		//获取商品表的数据
		String pdtsLine = productMap.get(pid);
		context.write(new Text(value.toString() + "\t" + pdtsLine), NullWritable.get());
	}
}
