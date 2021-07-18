package com.bingoabin.outputformat;

/**
 * @author xubin34
 * @date 2021/7/18 9:50 下午
 */
//自定义OutputFormat
//根据订单的评论数据将订单的好评，中评，差评区分开来，将最终的数据分开到不同的文件夹下面去，0：好评，1：中评，2:差评

//自定义OutputFormat：继承FileOutputFormat，改写其中的recordwriter，改写具体输出数据的方法write()

//1、自定义一个outputformat

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

//泛型指的是输出的k，v类型
public class MyOutputFormat extends FileOutputFormat<Text, NullWritable> {
	@Override
	public RecordWriter<Text, NullWritable> getRecordWriter(TaskAttemptContext context) throws IOException, InterruptedException {
		FileSystem fs = FileSystem.get(context.getConfiguration());
		Path goodComment = new Path("/Users/xubin34/test/data/good/1.txt");
		Path badComment = new Path("/Users/xubin34/test/data/bad/1.txt");
		FSDataOutputStream goodOutputStream = fs.create(goodComment);
		FSDataOutputStream badOutputStream = fs.create(badComment);
		return new MyRecordWriter(goodOutputStream, badOutputStream);
	}

	static class MyRecordWriter extends RecordWriter<Text, NullWritable> {
		FSDataOutputStream goodStream = null;
		FSDataOutputStream badStream = null;

		public MyRecordWriter(FSDataOutputStream goodStream, FSDataOutputStream badStream) {
			this.goodStream = goodStream;
			this.badStream = badStream;
		}

		@Override
		public void write(Text key, NullWritable value) throws IOException, InterruptedException {
			if (key.toString().split("\t")[9].equals("0")) {//好评
				goodStream.write(key.toString().getBytes());
				goodStream.write("\r\n".getBytes());
			} else {//中评或差评
				badStream.write(key.toString().getBytes());
				badStream.write("\r\n".getBytes());
			}
		}

		//释放资源
		@Override
		public void close(TaskAttemptContext context) throws IOException, InterruptedException {
			if (badStream != null) {
				badStream.close();
			}
			if (goodStream != null) {
				goodStream.close();
			}
		}
	}
}
