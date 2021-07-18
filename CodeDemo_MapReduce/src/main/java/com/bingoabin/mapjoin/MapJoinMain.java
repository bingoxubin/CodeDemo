package com.bingoabin.mapjoin;

/**
 * @author xubin34
 * @date 2021/7/18 10:22 下午
 */
//2、定义main方法

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.net.URI;

/**
 * 需求：同reduce join
 * 实现：select a.id, a.date, b.name, b.category_id, b.price
 * from t_order a
 * join t_product b
 * on a.pid = b.id
 */
public class MapJoinMain extends Configured implements Tool {
	@Override
	public int run(String[] args) throws Exception {
		//分布式缓存的hdfs路径
		URI uri = new URI("hdfs://node01:8020/cache/pdts.txt");

		//本地路径：需要用一下形式file:///C:/1、HK/.../pdts.txt   ；需要指定到具体的文件；如果是使用file:///c:\\1、HK\\3、ME...不符合语法会报错
		//URI uri = new URI("file:///C:/1、HK/3、ME/2、高级0x/1、Hadoop集群升级课件/9、MapReduce/MR第一次/12、join操作/map端join/cache/pdts.txt");
		Configuration configuration = super.getConf();
		//添加缓存文件 方式二：deprecated
		//DistributedCache.addCacheFile(uri, configuration);

		//获取job对象
		Job job = Job.getInstance(configuration, MapJoinMain.class.getSimpleName());
		//添加缓存文件：方式一
		//job.addCacheFile(uri);
		job.setJarByClass(MapJoinMain.class);

		//读取文件，解析成为key，value对
		job.setInputFormatClass(TextInputFormat.class);
		TextInputFormat.addInputPath(job, new Path(args[0]));

		job.setMapperClass(MapJoinMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);

		//没有reducer逻辑，不用设置了
		job.setOutputFormatClass(TextOutputFormat.class);
		TextOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setNumReduceTasks(2);

		boolean b = job.waitForCompletion(true);
		return b ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		int run = ToolRunner.run(new Configuration(), new MapJoinMain(), args);
		System.exit(run);
	}
}
