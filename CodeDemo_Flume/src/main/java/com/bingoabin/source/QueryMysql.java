package com.bingoabin.source;

/**
 * @author xubin34
 * @date 2021/7/19 1:43 上午
 */
//#官方提供的source类型已经很多，但是有时候并不能满足实际开发当中的需求，此时我们就需要根据实际需求自定义某些source。如：实时监控MySQL，从MySQL中获取数据传输到HDFS或者其他存储框架，所以此时需要我们自己实现MySQLSource。
// 官方也提供了自定义source的接口：
// 官网说明：https://flume.apache.org/FlumeDeveloperGuide.html#source
//
// #需求说明：
// #自定义flume的source，实现从mysql数据库当中获取数据，将数据打印到控制台上面来
// 代码开发步骤：
// #1. 根据官方说明自定义mysqlsource需要继承AbstractSource类并实现Configurable和PollableSource接口。
// #2. 实现对应的方法
//     1. configure(Context context)
//     初始化context
//     2. process()
//     从mysql表中获取数据，然后把数据封装成event对象写入到channel，该方法被一直调用
//     3. stop()
//     关闭相关资源

//-- 第一步：创建mysql数据库表
// --创建一个数据库
// CREATE DATABASE IF NOT EXISTS mysqlsource DEFAULT CHARACTER SET utf8;
// use mysqlsource;
//
// --创建一个表，用户保存拉取目标表位置的信息
// CREATE TABLE mysqlsource.flume_meta (
//   source_tab varchar(255) NOT NULL,
//   currentIndex varchar(255) NOT NULL,
//   PRIMARY KEY (source_tab)
// ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
//
// --插入数据
// insert  into mysqlsource.flume_meta(source_tab,currentIndex) values ('student','1');
// select * from mysqlsource.flume_meta;
//
// --创建要拉取数据的表
// CREATE TABLE mysqlsource.student(
//   id int(11) NOT NULL AUTO_INCREMENT,
//   name varchar(255) NOT NULL,
//   PRIMARY KEY (id)
// ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
//
// --向student表中添加测试数据
// insert  into mysqlsource.student(id,name) values (1,'zhangsan'),(2,'lisi'),(3,'wangwu'),(4,'zhaoliu');
//
// select * from mysqlsource.student;

//#第三步：添加配置文件
// #在我们工程的resources目录下，添加jdbc.properties
// #以下属性的值，根据自己的实际情况修改
// dbDriver=com.mysql.jdbc.Driver
// dbUrl=jdbc:mysql://node03:3306/mysqlsource?useUnicode=true&characterEncoding=utf-8
// dbUser=root
// dbPassword=123456

//第四步：代码开发
//定义查询mysql的工具类

import org.apache.flume.Context;
import org.apache.flume.conf.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class QueryMysql {
	private static final Logger LOG = LoggerFactory.getLogger(QueryMysql.class);

	private int runQueryDelay,   //两次查询的时间间隔
			startFrom,            //开始id
			currentIndex,       //当前id
			recordSixe = 0,        //每次查询返回结果的条数
			maxRow;                //每次查询的最大条数

	private String table,          //要操作的表
			columnsToSelect,       //用户传入的查询的列
			customQuery,          //用户传入的查询语句
			query,                 //构建的查询语句
			defaultCharsetResultSet;//编码集

	//上下文，用来获取配置文件
	private Context context;

	//为定义的变量赋值（默认值），可在flume任务的配置文件中修改
	private static final int DEFAULT_QUERY_DELAY = 10000;
	private static final int DEFAULT_START_VALUE = 0;
	private static final int DEFAULT_MAX_ROWS = 2000;
	private static final String DEFAULT_COLUMNS_SELECT = "*";
	private static final String DEFAULT_CHARSET_RESULTSET = "UTF-8";

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	//连接mysql的url、用户名、密码
	private static String connectionURL, connectionUserName, connectionPassword;

	//加载静态资源
	static {
		Properties p = new Properties();
		try {
			p.load(QueryMysql.class.getClassLoader().getResourceAsStream("jdbc.properties"));
			connectionURL = p.getProperty("dbUrl");
			connectionUserName = p.getProperty("dbUser");
			connectionPassword = p.getProperty("dbPassword");
			Class.forName(p.getProperty("dbDriver"));
		} catch (Exception e) {
			LOG.error(e.toString());
		}
	}

	//获取JDBC连接Connection
	private static Connection initConnection(String url, String user, String pw) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pw);
			if (conn == null)
				throw new SQLException();
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//构造方法
	QueryMysql(Context context) throws ParseException {
		//初始化上下文
		this.context = context;

		//有默认值参数：获取flume任务配置文件中的参数，读不到的采用默认值
		this.columnsToSelect = context.getString("columns.to.select", DEFAULT_COLUMNS_SELECT);
		this.runQueryDelay = context.getInteger("run.query.delay", DEFAULT_QUERY_DELAY);
		this.startFrom = context.getInteger("start.from", DEFAULT_START_VALUE);
		this.defaultCharsetResultSet = context.getString("default.charset.resultset", DEFAULT_CHARSET_RESULTSET);

		//无默认值参数：获取flume任务配置文件中的参数
		this.table = context.getString("table");
		this.customQuery = context.getString("custom.query");

		connectionURL = context.getString("connection.url");
		connectionUserName = context.getString("connection.user");
		connectionPassword = context.getString("connection.password");
		conn = initConnection(connectionURL, connectionUserName, connectionPassword);

		//校验相应的配置信息，如果没有默认值的参数也没赋值，抛出异常
		checkMandatoryProperties();
		//获取当前的id
		currentIndex = getStatusDBIndex(startFrom);
		//构建查询语句
		query = buildQuery();
	}

	//校验相应的配置信息（表，查询语句以及数据库连接的参数）
	private void checkMandatoryProperties() {
		if (table == null) {
			throw new ConfigurationException("property table not set");
		}
		if (connectionURL == null) {
			throw new ConfigurationException("connection.url property not set");
		}
		if (connectionUserName == null) {
			throw new ConfigurationException("connection.user property not set");
		}
		if (connectionPassword == null) {
			throw new ConfigurationException("connection.password property not set");
		}
	}

	//构建sql语句
	private String buildQuery() {
		String sql = "";
		//获取当前id
		currentIndex = getStatusDBIndex(startFrom);
		LOG.info(currentIndex + "");
		if (customQuery == null) {
			sql = "SELECT " + columnsToSelect + " FROM " + table;
		} else {
			sql = customQuery;
		}
		StringBuilder execSql = new StringBuilder(sql);
		//以id作为offset
		if (!sql.contains("where")) {
			execSql.append(" where ");
			execSql.append("id").append(">").append(currentIndex);
			return execSql.toString();
		} else {
			int length = execSql.toString().length();
			return execSql.toString().substring(0, length - String.valueOf(currentIndex).length()) + currentIndex;
		}
	}

	//执行查询
	List<List<Object>> executeQuery() {
		try {
			//每次执行查询时都要重新生成sql，因为id不同
			customQuery = buildQuery();
			//存放结果的集合
			List<List<Object>> results = new ArrayList<>();
			if (ps == null) {
				//初始化PrepareStatement对象
				ps = conn.prepareStatement(customQuery);
			}
			ResultSet result = ps.executeQuery(customQuery);
			while (result.next()) {
				//存放一条数据的集合（多个列）
				List<Object> row = new ArrayList<>();
				//将返回结果放入集合
				for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
					row.add(result.getObject(i));
				}
				results.add(row);
			}
			LOG.info("execSql:" + customQuery + "\nresultSize:" + results.size());
			return results;
		} catch (SQLException e) {
			LOG.error(e.toString());
			// 重新连接
			conn = initConnection(connectionURL, connectionUserName, connectionPassword);
		}
		return null;
	}

	//将结果集转化为字符串，每一条数据是一个list集合，将每一个小的list集合转化为字符串
	List<String> getAllRows(List<List<Object>> queryResult) {
		List<String> allRows = new ArrayList<>();
		if (queryResult == null || queryResult.isEmpty())
			return allRows;
		StringBuilder row = new StringBuilder();
		for (List<Object> rawRow : queryResult) {
			Object value = null;
			for (Object aRawRow : rawRow) {
				value = aRawRow;
				if (value == null) {
					row.append(",");
				} else {
					row.append(aRawRow.toString()).append(",");
				}
			}
			allRows.add(row.toString());
			row = new StringBuilder();
		}
		return allRows;
	}

	//更新offset元数据状态，每次返回结果集后调用。必须记录每次查询的offset值，为程序中断续跑数据时使用，以id为offset
	void updateOffset2DB(int size) {
		//以source_tab做为KEY，如果不存在则插入，存在则更新（每个源表对应一条记录）
		String sql = "insert into flume_meta(source_tab,currentIndex) VALUES('"
				+ this.table
				+ "','" + (recordSixe += size)
				+ "') on DUPLICATE key update source_tab=values(source_tab),currentIndex=values(currentIndex)";
		LOG.info("updateStatus Sql:" + sql);
		execSql(sql);
	}

	//执行sql语句
	private void execSql(String sql) {
		try {
			ps = conn.prepareStatement(sql);
			LOG.info("exec::" + sql);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//获取当前id的offset
	private Integer getStatusDBIndex(int startFrom) {
		//从flume_meta表中查询出当前的id是多少
		String dbIndex = queryOne("select currentIndex from flume_meta where source_tab='" + table + "'");
		if (dbIndex != null) {
			return Integer.parseInt(dbIndex);
		}
		//如果没有数据，则说明是第一次查询或者数据表中还没有存入数据，返回最初传入的值
		return startFrom;
	}

	//查询一条数据的执行语句(当前id)
	private String queryOne(String sql) {
		ResultSet result = null;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeQuery();
			while (result.next()) {
				return result.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//关闭相关资源
	void close() {
		try {
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	int getCurrentIndex() {
		return currentIndex;
	}

	void setCurrentIndex(int newValue) {
		currentIndex = newValue;
	}

	int getRunQueryDelay() {
		return runQueryDelay;
	}

	String getQuery() {
		return query;
	}

	String getConnectionURL() {
		return connectionURL;
	}

	private boolean isCustomQuerySet() {
		return (customQuery != null);
	}

	Context getContext() {
		return context;
	}

	public String getConnectionUserName() {
		return connectionUserName;
	}

	public String getConnectionPassword() {
		return connectionPassword;
	}

	String getDefaultCharsetResultSet() {
		return defaultCharsetResultSet;
	}
}
