package org.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.col;
public class Main {
	public static void main(String[] args) {
		System.out.println("Hello world!");
		// localPattern();
		localDSLPattern();
		// clientPattern();
		// clusterPattern();
	}

	public static void localPattern(){
		SparkSession spark = SparkSession
				.builder()
				.appName("SparkHiveExample")
				.config("spark.master", "local")
				.enableHiveSupport()
				.getOrCreate();

		spark.sql("USE default");
		spark.sql("SELECT * FROM test").show();
	}

	public static void localDSLPattern(){
		SparkSession spark = SparkSession
				.builder()
				.appName("SparkHiveExample")
				.config("spark.master", "local")
				.config("hive.metastore.uris","thrift://hadoop11:9083")
				.enableHiveSupport()
				.getOrCreate();

		Dataset<Row> tableDF = spark.table("default.test").filter(col("id").gt(2).and(col("age").equalTo(9)));
		tableDF.show();
	}

	public static void clientPattern(){
		SparkSession spark = SparkSession
				.builder()
				.appName("SparkHiveExample")
				.config("spark.master", "yarn")
				.config("spark.submit.deployMode", "client")
				.enableHiveSupport()
				.getOrCreate();

		spark.sql("USE default");
		spark.sql("SELECT * FROM test").show();
	}

	public static void clusterPattern(){
		SparkSession spark = SparkSession
				.builder()
				.appName("SparkHiveExample")
				.config("spark.master", "yarn")
				.config("spark.submit.deployMode", "cluster")
				.enableHiveSupport()
				.getOrCreate();

		spark.sql("USE default");
		spark.sql("SELECT * FROM test").show();
	}

	//localPattern()：这个方法使用了本地模式（local mode）来执行 Spark 应用程序。通过设置 spark.master 配置为 "local"，
	// Spark 将在单个本地节点上运行，不会连接到 Spark 集群。在本地模式下，Spark 不会分布任务到多个节点，而是在单个 JVM 进程中执行。
	//
	// clientPattern()：这个方法使用了客户端模式（client mode）来执行 Spark 应用程序。通过设置 spark.master 配置为 "yarn"，
	// 并且在设置 spark.submit.deployMode 配置为 "client"，Spark 提交作业并连接到提供的 YARN 集群。在客户端模式下，
	// Spark 客户端与 YARN 资源管理器通信，并向其提交应用程序。应用程序的驱动程序运行在 Spark 客户端上，而任务分布在 YARN 集群的多个节点上。
	//
	// 总结来说，localPattern() 在本地模式下执行，适用于测试和开发过程中小规模的数据处理；而 clientPattern() 在 YARN 集群上执行，
	// 适用于在大规模集群上运行 Spark 应用程序。具体使用哪种模式取决于你的应用程序的需求和可用资源。
}