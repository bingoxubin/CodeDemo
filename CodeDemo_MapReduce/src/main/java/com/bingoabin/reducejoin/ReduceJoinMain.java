package com.bingoabin.reducejoin;

/**
 * @author xubin34
 * @date 2021/7/18 10:37 下午
 */
//3、定义main方法

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

public class ReduceJoinMain extends Configured implements Tool {
	@Override
	public int run(String[] args) throws Exception {
		//获取job对象
		Job job = Job.getInstance(super.getConf(), ReduceJoinMain.class.getSimpleName());
		job.setJarByClass(ReduceJoinMain.class);

		//第一步：读取文件
		job.setInputFormatClass(TextInputFormat.class);
		TextInputFormat.addInputPath(job, new Path(args[0]));

		//第二步：设置自定义mapper逻辑
		job.setMapperClass(ReduceJoinMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);

		//分区，排序，规约，分组 省略

		//第七步：设置reduce逻辑
		job.setReducerClass(ReduceJoinReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);

		//第八步：设置输出数据路径
		job.setOutputFormatClass(TextOutputFormat.class);
		TextOutputFormat.setOutputPath(job, new Path(args[1]));

		boolean b = job.waitForCompletion(true);
		return b ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		int run = ToolRunner.run(new Configuration(), new ReduceJoinMain(), args);
		System.exit(run);
	}
}
