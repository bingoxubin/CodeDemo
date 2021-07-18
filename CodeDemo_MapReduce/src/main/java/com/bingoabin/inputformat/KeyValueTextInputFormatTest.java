package com.bingoabin.inputformat;

/**
 * @author xubin34
 * @date 2021/7/18 6:24 下午
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

////KeyValueTextInputFormat允许我们自己来定义分隔符，通过分隔符来自定义我们的key和value
//
// //案例
// //输入，分隔符为@zolen@
// hello@zolen@ input datas today
// count@zolen@ hadoop spark
// hello@zolen@ input some datas to test
// //输出
// hello  2
// count  1
public class KeyValueTextInputFormatTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration configuration = new Configuration();
		//翻阅 KeyValueLineRecordReader 的源码，发现切割参数的配置
		configuration.set("key.value.separator.in.input.line", "@zolen@");
		Job job = Job.getInstance(configuration);
		//第一步：读取文件，解析成为key，value对
		KeyValueTextInputFormat.addInputPath(job, new Path("/Users/xubin34/test/input"));

		job.setInputFormatClass(KeyValueTextInputFormat.class);
		//第二步：设置mapper类
		job.setMapperClass(KeyValueMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		//第三步到第六步 分区，排序，规约，分组 省略
		//第七步：设置reducer类
		job.setReducerClass(KeyValueReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		//第八步：输出数据
		job.setOutputFormatClass(TextOutputFormat.class);
		TextOutputFormat.setOutputPath(job, new Path("/Users/xubin34/test/output"));
		boolean b = job.waitForCompletion(true);
		System.exit(0);
	}

	public static class KeyValueMapper extends Mapper<Text, Text, Text, LongWritable> {
		LongWritable outValue = new LongWritable(1);

		@Override
		protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
			context.write(key, outValue);
		}
	}

	public class KeyValueReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
		@Override
		protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
			long result = 0;
			for (LongWritable value : values) {
				long l = value.get();
				result += l;
			}
			context.write(key, new LongWritable(result));
		}
	}
}
