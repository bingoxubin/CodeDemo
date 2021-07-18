package scalase.grammer

object FunctionProgram {
	//函数式编程
	def main(args: Array[String]): Unit = {
		//遍历：foreach
		val list1 = List(1, 3, 5, 6, 2, 4)
		list1.foreach(i => print(i + " "))
		println()

		//映射：map
		val list2 = List(1, 3, 5, 6, 2, 4)
		list2.map(x => x * 10)
		println(list2)

		//扁平化映射：flatmap
		val list3 = List("spark hive hadoop flink", "hbase spark")
		val list4 = list3.flatMap(x => x.split(" "))
		println(list4)
		//本质
		println(list3.map(x => x.split(" ")).flatten)

		//过滤：filter
		val list5 = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
		println(list5.filter(x => x > 5))

		//排序：sort
		val list6 = List(5, 1, 2, 4, 3)
		println(list6.sorted)
		val list7 = List("4 hadoop", "3 spark", "1 flink")
		println(list7.sortBy(x => x.split(" ")(0)))
		//降序
		println(list6.sortWith((x, y) => x > y))
		println(list6.sortWith((x, y) => x < y))

		//分组：groupBy
		val list8 = List("zhangsan" -> "男", "lisi" -> "女", "wangwu" -> "男")
		println(list8.groupBy(x => x._2))
		//将分组后的映射转为性别/人数的元组列表
		println(list8.groupBy(x => x._2).map(x => x._1 -> x._2.size))

		//聚合：reduce
		val list9 = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
		println(list9.reduce((x, y) => (x + y)))

		//折叠：fold
		val list10 = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
		println(list10.fold(10)((x, y) => x + y))
	}
}
