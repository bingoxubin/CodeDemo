package com.bingo;

import ru.yandex.clickhouse.ClickHouseConnection;
import ru.yandex.clickhouse.ClickHouseConnectionImpl;
import ru.yandex.clickhouse.ClickHouseDataSource;
// import ru.yandex.clickhouse.settings.ClickHouseProperties;

/**
 * @author bingoabin
 * @date 2020/8/14 17:37
 */
public class ClickHouseTest {
	public static void main(String[] args) {
		ClickHouseConnection conn = null;
		// ClickHouseProperties properties = new ClickHouseProperties();
		// properties.setUser("default");
		// properties.setPassword("123456");
		// properties.setDatabase("default");
		try {
			Class.forName("ru.yandex.clickhouse.ClickHouseDriver");
			ClickHouseDataSource dataSource = new ClickHouseDataSource("jdbc:clickhouse://192.168.214.11:8123/?user=default&password=111111"); //default表示数据表 ,port原来为8123
			ClickHouseConnectionImpl connection = (ClickHouseConnectionImpl) dataSource.getConnection();
			if (connection != null) {
				System.out.println("连接成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
