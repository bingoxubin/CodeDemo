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
public class ThreadPool_ReadTest {
	private DruidDataSource dataSource;

	public ThreadPool_ReadTest() {
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
		// 使用Java 8的并发处理
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			executor.submit(() -> {
				try (Connection connection = getConnection()){
					PreparedStatement ps = connection.prepareStatement("SELECT * FROM user_t");
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						System.out.println(rs.getInt("id"));
						System.out.println(rs.getString("user_name"));
					}
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
		ThreadPool_ReadTest handler = new ThreadPool_ReadTest();
		handler.readData();
	}
}
