package com.bingo.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

/**
 * @author bingoabin
 * @date 2023/7/15 1:07
 * @Description:
 */
public class HikariUtil {
	private static final Logger log = LoggerFactory.getLogger(HikariUtil.class);
	public final static HikariUtil HIKARI_CP_UTI = new HikariUtil();

	private static Properties properties = null;
	private static HikariDataSource dataSource = null;
	private static transient Connection conn = null;

	//1.单例模式中,应该创建私有构造方法
	private HikariUtil() {
	}

	/**
	 * 1.配置和获取数据库连接配置信息
	 * 2.扩展HikariCP功能,进行配置
	 * 3.获取数据库连接,提供对外获取数据库资源的方法
	 */
	private void initConfig() throws IOException {
		String filePath = Objects.requireNonNull(HikariUtil.class.getClassLoader().getResource("hikaricp.properties")).getFile();
		FileReader fileReader = new FileReader(filePath);
		properties = new Properties();
		properties.load(fileReader);
		properties.forEach((k, v) -> {
			log.debug(String.format("key:%s value:%S", k, v));
		});
		log.info("初始化配置文件成功.....");
	}

	/**
	 * 注册HikariCP
	 */
	private void registerHikariCP() {
		if (null != dataSource) {
			return;
		}
		HikariConfig config = new HikariConfig(properties);
		dataSource = new HikariDataSource(config);
	}

	/**
	 * 提供对外 获取 HikariCPDatasource 的方法
	 *
	 * @return
	 */
	public static DataSource getHikariCPDataSource() {
		if (null != dataSource) {
			return dataSource;
		}
		try {
			HIKARI_CP_UTI.initConfig();
			HIKARI_CP_UTI.registerHikariCP();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataSource;
	}

	/**
	 * 提供对外链接
	 */
	public static Connection getconn() {
		try {
			if (conn == null) {
				if (dataSource != null) {
					conn = dataSource.getConnection();
				} else {
					conn = getHikariCPDataSource().getConnection();
				}
			}
			//设置手动提交
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 提交事务
	 */
	public static void commit() {
		if (conn != null) {
			try {
				conn.commit();
			} catch (SQLException e) {
				log.error("提交事务失败,Connection:" + conn);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 事务回滚
	 */
	public static void rollback() {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				log.error("事务回滚失败,Connection:" + conn);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭连接
	 */
	public static void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				log.error("关闭连接失败,Connection:" + conn);
				e.printStackTrace();
			}
		}
	}
}
