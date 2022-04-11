import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
	//如遇到jar包冲突的问题，将mr计算那块的pom去掉
	def main(args: Array[String]): Unit = {
		//1.创建SparkConf并设置App名称
		val conf = new SparkConf().setAppName("WC").setMaster("local")
		//2.创建SparkContext，该对象是提交Spark App的入口
		val sc = new SparkContext(conf)
		//3.使用sc创建RDD并执行相应的transformation和action
		//sc.textFile(args(0)).flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_, 1).sortBy(_._2, false).saveAsTextFile(args(1))
		//val tuples: Array[(String, Int)] = sc.textFile("E:\\60.test\\littlefile").flatMap(_.split(" ")).map((_, 1)).reduceByKey((_ + _)).collect()
		val tuples: Array[(String, Int)] = sc.textFile("E:\\60.test\\littlefile").flatMap(x => x.split(" ")).map(x => (x,1)).reduceByKey((x,y)=>x+y).collect()
		print(tuples.toBuffer)
		//4.关闭连接
		sc.stop()
	}
}
