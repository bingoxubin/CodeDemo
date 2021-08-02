import org.apache.spark.sql.{Column, DataFrame, SparkSession}

object SqlTest {

	var debug: Boolean = false

	def prepare(spark: SparkSession) = {
		import spark.implicits._
		var df = (1 to 100)
		  .map(i => (i, "name_" + i, "key_" + (i % 10)))
		  .toDF("id", "name", "key")
		  .repartition(new Column("key"))
		df.createOrReplaceTempView("table1")
		df.createGlobalTempView("table2")
	}

	def doSparkFn(spark: SparkSession) = {
		var sql: DataFrame = spark.sql("select * from table1 where id > 1")
		debug = true
		sql.show(3)
	}

	def doSparkFn2(spark: SparkSession) = {
		var sql = spark.sql("select * from global_temp.table2 where id > 1")

		sql.show()

		spark.catalog.listDatabases().foreach(s => println("db:" + s.toString))
	}

	def main(args: Array[String]): Unit = {
		var spark = SparkSession.builder
		  .master("local[*]")
		  .appName("testing")
		  .config("spark.network.timeout", 70L * 60 * 1000)
		  .config("spark.executor.heartbeatInterval", 60L * 60 * 1000)
		  .getOrCreate

		spark.sparkContext.setLogLevel("INFO")

		prepare(spark)

		for (i <- 1 to 100) {
			doSparkFn(spark)
		}

		spark.stop()
	}
}

