package com.bingo.threadpoll;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author bingoabin
 * @date 2023/7/11 22:10
 * @Description:
 */
public class ThreadPool_ReadTest1 {
	private DruidDataSource dataSource;

	public ThreadPool_ReadTest1() {
		// 初始化连接池
		dataSource = new DruidDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setUsername("root");
		dataSource.setPassword("111111");
		dataSource.setInitialSize(5);
		dataSource.setMaxActive(10);
	}

	public Connection getConnection(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void readData() {
		try {
			Connection connection1 = getConnection();
			PreparedStatement ps1 = connection1.prepareStatement("CREATE TEMPORARY TABLE t_temp( name VARCHAR(20),age INT);");
			ps1.execute();
			ps1.close();
			connection1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用Java 8的并发处理
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			executor.submit(() -> {
				try (Connection connection = getConnection()){
					PreparedStatement ps = connection.prepareStatement("SELECT * FROM t_temp");
					ResultSet rs = ps.executeQuery();
					System.out.println("存在表！！");
					// while(rs.next()) {
					// 	System.out.println(rs.getInt("id"));
					// 	System.out.println(rs.getString("user_name"));
					// }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
		}
		executor.shutdown();
		// 等待所有任务都执行完
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ThreadPool_ReadTest1 handler = new ThreadPool_ReadTest1();
		handler.readData();
	}
}
