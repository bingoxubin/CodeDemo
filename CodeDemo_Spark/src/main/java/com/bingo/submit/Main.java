package com.bingo.submit;

import org.apache.spark.sql.SparkSession;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello world!");
		localPattern();
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
}