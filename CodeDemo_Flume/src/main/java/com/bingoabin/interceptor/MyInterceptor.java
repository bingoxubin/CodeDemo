package com.bingoabin.interceptor;

/**
 * @author xubin34
 * @date 2021/7/19 1:40 上午
 */

import com.google.common.base.Charsets;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MyInterceptor implements Interceptor {
	/**
	 * encrypted_field_index. 指定需要加密的字段下标
	 */
	private final String encrypted_field_index;
	/**
	 * The out_index. 指定不需要对应列的下标
	 */
	private final String out_index;

	/**
	 * 提供构建方法，后期可以接受配置文件中的参数
	 *
	 * @param encrypted_field_index
	 * @param out_index
	 */
	public MyInterceptor(String encrypted_field_index, String out_index) {
		this.encrypted_field_index = encrypted_field_index.trim();
		this.out_index = out_index.trim();
	}

	/*
	 *
	 * 单个event拦截逻辑
	 */
	public Event intercept(Event event) {
		if (event == null) {
			return null;
		}

		try {
			String line = new String(event.getBody(), Charsets.UTF_8);
			String[] fields = line.split(",");

			String newLine = "";
			for (int i = 0; i < fields.length; i++) {
				//字符串数字转换成int
				int encryptedField = Integer.parseInt(encrypted_field_index);
				int outIndex = Integer.parseInt(out_index);

				if (i == encryptedField) {
					newLine += md5(fields[i]) + ",";
				} else if (i != outIndex) {
					newLine += fields[i] + ",";
				}
			}
			//去掉最后多余的逗号
			newLine = newLine.substring(0, newLine.length() - 1);

			event.setBody(newLine.getBytes(Charsets.UTF_8));
			return event;
		} catch (Exception e) {
			return event;
		}

	}

	/*
	 *
	 * 批量event拦截逻辑
	 */
	public List<Event> intercept(List<Event> events) {
		List<Event> out = new ArrayList<Event>();
		for (Event event : events) {
			Event outEvent = intercept(event);
			if (outEvent != null) {
				out.add(outEvent);
			}
		}
		return out;
	}

	public void close() {

	}

	public void initialize() {

	}

	//写一个md5加密的方法
	public static String md5(String plainText) {
		//定义一个字节数组
		byte[] secretBytes = null;
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			//对字符串进行加密
			md.update(plainText.getBytes());
			//获得加密后的数据
			secretBytes = md.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		//将加密后的数据转换为16进制数字
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

	/**
	 * 相当于自定义Interceptor的工厂类
	 * 在flume采集配置文件中通过指定该Builder来创建Interceptor对象
	 * 可以在Builder中获取、解析flume采集配置文件中的拦截器Interceptor的自定义参数：
	 * 指定需要加密的字段下标 指定需要剔除的对应列的下标等
	 *
	 * @author
	 */
	public static class MyBuilder implements Interceptor.Builder {

		/**
		 * 与agent “spool-interceptor-hdfs.conf”中的i1拦截器的两个属性名保持一致
		 * encrypted_field_index. 指定需要加密的字段下标
		 */
		private String encrypted_field_index;

		/**
		 * The out_index. 指定不需要对应列的下标
		 */
		private String out_index;

		public void configure(Context context) {
			this.encrypted_field_index = context.getString("encrypted_field_index", "");
			this.out_index = context.getString("out_index", "");
		}

		/*
		 * @see org.apache.flume.interceptor.Interceptor.Builder#build()
		 */
		public MyInterceptor build() {
			return new MyInterceptor(encrypted_field_index, out_index);
		}
	}
}

//#第三步：打包上传服务器
// #将我们的拦截器打成jar包放到flume的lib目录下
//
// #第四步：开发flume的配置文件
// #第三台机器开发flume的配置文件
// cd /kkb/install/apache-flume-1.6.0-cdh5.14.2-bin/conf
// vim spool-interceptor-hdfs.conf
// #内容如下
// #记得将下边的i1.type根据自己的实际情况进行替换
// # Name the components on this agent
// a1.sources = r1
// a1.sinks = k1
// a1.channels = c1
//
// #配置source
// a1.sources.r1.type = exec
// a1.sources.r1.command = tail -F /kkb/install/flumeData/user.txt
// a1.sources.r1.channels = c1
// a1.sources.r1.interceptors =i1
// a1.sources.r1.interceptors.i1.type =com.kkb.flume.interceptor.MyInterceptor$MyBuilder
// # 数据中index=0的列，被加密
// a1.sources.r1.interceptors.i1.encrypted_field_index=0
// # # 数据中index=3的列，被剔除
// a1.sources.r1.interceptors.i1.out_index=3
//
// #配置channel
// a1.channels.c1.type = memory
// a1.channels.c1.capacity = 1000
// a1.channels.c1.transactionCapacity = 100
//
// #配置sink
// a1.sinks.k1.type = hdfs
// a1.sinks.k1.channel = c1
// a1.sinks.k1.hdfs.path = hdfs://node01:8020/interceptor/files/%Y-%m-%d/%H%M
// a1.sinks.k1.hdfs.filePrefix = events-
// a1.sinks.k1.hdfs.round = true
// a1.sinks.k1.hdfs.roundValue = 10
// a1.sinks.k1.hdfs.roundUnit = minute
// a1.sinks.k1.hdfs.rollInterval = 5
// a1.sinks.k1.hdfs.rollSize = 50
// a1.sinks.k1.hdfs.rollCount = 10
// a1.sinks.k1.hdfs.batchSize = 100
// a1.sinks.k1.hdfs.useLocalTimeStamp = true
// #生成的文件类型，默认是Sequencefile，可用DataStream，则为普通文本
// a1.sinks.k1.hdfs.fileType = DataStream
//
// #第五步：上传测试数据
// #node03上传我们的测试数据到/kkb/install/flumeData 这个目录下面去，如果目录不存在则创建
// mkdir -p /kkb/install/flumeData
// cd /kkb/install/flumeData
// vim user.txt
// #测试数据如下
// 13901007610,male,30,sing,beijing
// 18600000035,male,40,dance,shanghai
// 13366666659,male,20,Swimming,wuhan
// 13801179888,female,18,dance,tianjin
// 18511111114,male,35,sing,beijing
// 13718428888,female,40,Foodie,shanghai
// 13901057088,male,50,Basketball,taiwan
// 13671057777,male,60,Bodybuilding,xianggang
//
// #第六步：node03启动flume
// cd /kkb/install/apache-flume-1.6.0-cdh5.14.2-bin
// bin/flume-ng agent -c conf -f conf/spool-interceptor-hdfs.conf -name a1 -Dflume.root.logger=DEBUG,console
