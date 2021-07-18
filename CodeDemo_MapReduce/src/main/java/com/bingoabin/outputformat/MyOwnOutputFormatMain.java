package com.bingoabin.outputformat;

/**
 * @author xubin34
 * @date 2021/7/18 9:52 下午
 */
//开发mapreduce处理流程

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

public class MyOwnOutputFormatMain extends Configured implements Tool {
	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = super.getConf();
		Job job = Job.getInstance(conf, MyOwnOutputFormatMain.class.getSimpleName());
		job.setJarByClass(MyOwnOutputFormatMain.class);

		job.setInputFormatClass(TextInputFormat.class);
		TextInputFormat.addInputPath(job, new Path(args[0]));

		job.setMapperClass(MyOwnMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);

		//使用默认的Reduce类的逻辑
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);

		job.setOutputFormatClass(MyOutputFormat.class);
		//设置一个输出目录，这个目录会输出一个success的成功标志的文件
		MyOutputFormat.setOutputPath(job, new Path(args[1]));

		//可以观察现象
		job.setNumReduceTasks(2);

		boolean b = job.waitForCompletion(true);
		return b ? 0 : 1;
	}

	//kout：评分等级 0， 1， 2
	public static class MyOwnMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
		@Override
		protected void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {
			//评分
			//String commentStatus = split[9];
			context.write(value, NullWritable.get());
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration();
		ToolRunner.run(configuration, new MyOwnOutputFormatMain(), args);
	}
}