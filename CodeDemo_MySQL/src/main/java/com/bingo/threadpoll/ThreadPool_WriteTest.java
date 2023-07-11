package com.bingo.threadpoll;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author bingoabin
 * @date 2023/7/11 22:16
 * @Description:
 */
public class ThreadPool_WriteTest {
	private DruidDataSource dataSource;

	public ThreadPool_WriteTest() {
		// 初始化连接池
		dataSource = new DruidDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/test"); // 根据你的数据库修改URL
		dataSource.setUsername("root"); // 根据你的数据库修改用户名
		dataSource.setPassword("111111"); // 根据你的数据库修改密码
		dataSource.setInitialSize(5);
		dataSource.setMaxActive(10);
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public void writeData() {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			final int threadId = i; // 为了简便起见，我们用线程id作为用户id的基数
			executor.submit(() -> {
				try (Connection connection = getConnection()){
					PreparedStatement ps = connection.prepareStatement("INSERT INTO user_t (id, user_name) VALUES (?, ?)");
					// 每个线程写入2条数据
					for (int j = 1; j <= 2; j++) {
						ps.setInt(1, threadId * 10 + j); // ID
						ps.setString(2, "user_name" + (threadId * 10 + j)); // Name
						ps.executeUpdate();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
		}
		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ThreadPool_WriteTest writer = new ThreadPool_WriteTest();
		writer.writeData();
	}
}
