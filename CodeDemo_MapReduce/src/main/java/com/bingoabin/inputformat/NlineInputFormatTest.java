package com.bingoabin.inputformat;

/**
 * @author xubin34
 * @date 2021/7/18 6:31 下午
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

//NLineInputFormat允许我们自己定义输入的行数作为一个切片数据
//案例：读取课件当中的数据，实现自己定义多少行数据作为一个切片，并统计单词出现的次数
public class NlineInputFormatTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration configuration = new Configuration();
		Job job = Job.getInstance(configuration);
		job.setJarByClass(NlineInputFormatTest.class);
		NLineInputFormat.setNumLinesPerSplit(job, 3);
		job.setInputFormatClass(NLineInputFormat.class);
		NLineInputFormat.addInputPath(job, new Path("/Users/xubin34/test/input"));
		//第二步：自定义mapper类
		job.setMapperClass(NLineMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		//第三步到第六步  分区，排序，规约，分组
		job.setReducerClass(NLineReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		TextOutputFormat.setOutputPath(job, new Path("/Users/xubin34/test/output"));
		job.waitForCompletion(true);
	}

	public static class NLineMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String[] s = value.toString().split(" ");
			for (String s1 : s) {
				context.write(new Text(s1), new LongWritable(1));
			}
		}
	}

	public static class NLineReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
		@Override
		protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
			long result = 0L;
			for (LongWritable value : values) {
				result += value.get();
			}
			context.write(key, new LongWritable(result));
		}
	}
}