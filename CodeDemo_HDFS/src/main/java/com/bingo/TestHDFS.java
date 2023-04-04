package com.bingo;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author bingoabin
 * @date 2020/5/26 1:24
 */
public class TestHDFS {
	@Test
	public static void mkdirToHdfs() throws IOException {
		Configuration configuration = new Configuration();
		configuration.set("fs.defaultFS", "hdfs://node01:8020");
		FileSystem fileSystem = FileSystem.get(configuration);
		fileSystem.mkdirs(new Path("/kaikeba/dir1"));
		fileSystem.close();
	}

	@Test
	public void downloadFile() throws IOException {
		Configuration configuration = new Configuration();
		configuration.set("fs.defaultFS", "hdfs://node01:8020");
		FileSystem fileSystem = FileSystem.get(configuration);
		fileSystem.copyToLocalFile(new Path("hdfs://node01:8020/kaikeba/dir1/hello.txt"), new Path("file:///d:\\hello2.txt"));
		fileSystem.close();
	}

	@Test
	public void testdfs() throws Exception {
		Configuration configuration = new Configuration();
		configuration.set("fs.defaultFS", "hdfs://node01:8020");
		DistributedFileSystem dfs = new DistributedFileSystem();
		FSDataInputStream fs = dfs.open(new Path("hdfs://node01:8020/kaikeba/dir1/hello.txt"));
		fs.read();
	}

	@Test
	public void testListFiles() throws IOException, InterruptedException, URISyntaxException {
		// 1获取文件系统
		Configuration configuration = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://node01:8020"), configuration);
		// 2 获取文件详情
		RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
		while (listFiles.hasNext()) {
			LocatedFileStatus status = listFiles.next();
			// 输出详情
			// 文件名称
			System.out.println(status.getPath().getName());
			// 长度
			System.out.println(status.getLen());
			// 权限
			System.out.println(status.getPermission());
			// 分组
			System.out.println(status.getGroup());
			// 获取存储的块信息
			BlockLocation[] blockLocations = status.getBlockLocations();

			for (BlockLocation blockLocation : blockLocations) {
				// 获取块存储的主机节点
				String[] hosts = blockLocation.getHosts();
				for (String host : hosts) {
					System.out.println(host);
				}
			}
		}
		// 3 关闭资源
		fs.close();
	}

	@Test
	public void putFileToHDFS() throws IOException, InterruptedException, URISyntaxException {
		// 1 获取文件系统
		Configuration configuration = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://node01:8020"), configuration);
		// 2 创建输入流 不需要加file:///
		FileInputStream fis = new FileInputStream(new File("file:///e:\\helo.txt"));
		// 3 获取输出流
		FSDataOutputStream fos = fs.create(new Path("hdfs://node01:8020/outresult.txt"));
		// 4 流对拷
		IOUtils.copy(fis, fos);
		// 5 关闭资源
		IOUtils.closeQuietly(fos);
		IOUtils.closeQuietly(fis);
		fs.close();
	}

	/**
	 * 小文件合并
	 */
	@Test
	public static void mergeFile() throws URISyntaxException, IOException, InterruptedException {
		//获取分布式文件系统hdfs
		FileSystem fileSystem = FileSystem.get(new URI("hdfs://node01:8020"), new Configuration(), "hadoop");
		FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path("hdfs://node01:8020/test.txt"));
		//获取本地文件系统 localFileSystem
		LocalFileSystem localFileSystem = FileSystem.getLocal(new Configuration());
		//读取本地的文件
		FileStatus[] fileStatuses = localFileSystem.listStatus(new Path("file:///E://60.test//littlefile"));
		for (FileStatus fileStatus : fileStatuses) {
			//获取每一个本地的文件路径
			Path path = fileStatus.getPath();
			System.out.println(path + "======");
			//读取本地小文件
			FSDataInputStream fsDataInputStream = localFileSystem.open(path);
			IOUtils.copy(fsDataInputStream, fsDataOutputStream);
			IOUtils.closeQuietly(fsDataInputStream);
		}
		IOUtils.closeQuietly(fsDataOutputStream);
		localFileSystem.close();
		fileSystem.close();
		//读取所有本地小文件，写入到hdfs的大文件里面去
	}

	public static void main(String[] args) {
		try {
			mkdirToHdfs();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
