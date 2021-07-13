package com.bingoabin.scalase.test

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object Test02 {
	def main(args: Array[String]): Unit = {
		val arr1 = new Array[Int](8)
		val arr2 = Array(1, 2, 3, 4, 5, 6, "String")
		println(arr2(6)) //返回String


		val arrbuf1 = new ArrayBuffer[Int]()
		val arrbuf2 = ArrayBuffer("hadoop", "spark", 3)

		val a = ArrayBuffer("hadoop", "spark")
		a += "flink"
		a -= "hadoop"
		a ++= ArrayBuffer("hadoop", "helloworld", "flink")
		print(a) //ArrayBuffer(spark, flink, hadoop, helloworld, flink)

		println()

		for (i <- a) print(i + ",")
		for (i <- 0 to a.length - 1) print(a(i) + ",")
		for (i <- 0 until a.length) print(a(i) + ",")

		val arr3 = Array(6, 5, 3, 8, 7, 1, 9)
		//arr3.sum
		//arr3.max
		//arr3.min
		//arr3.sorted
		//arr3.sorted.reverse
		println()

		val tuple1 = (1, "zhangsan", 2, "lisi")
		val tuple2 = 1 -> 2
		println(tuple1._1 + "," + tuple1._2) //1,zhangsan
		//不能给tuple1._1赋值，因为元素不可变

		val map1 = scala.collection.mutable.Map("zhangsan" -> 20, "lisi" -> 30)
		val map2 = scala.collection.immutable.Map(("zhangsan", 10), ("lisi", 20))
		println(map1("zhangsan")) //20
		map1("zhangsan") = 40 //ok
		//map2("zhangsan") = 50 //报错
		println(map1("zhangsan"))

		val map3 = scala.collection.mutable.Map("zhangsan" -> 30, "lisi" -> 40)
		//获取值
		map3("zhangsan")
		map3.getOrElse("wangwu", -1) //如果不存在返回-1
		//添加
		map3 += ("wangwu" -> 25)
		//删除
		map3 -= "wangwu"
		//合并一个map
		map3 ++= Map("wangwu" -> 55)
		//获取所有key，value
		map3.keys
		map3.keySet
		map3.values
		//遍历map
		for (k <- map3.keys) println(k + "->" + map3(k))
		for ((k, v) <- map3) println(k + "->" + v)

		val set1 = Set(1, 1, 2, 3, 4, 5)
		set1.size
		set1 + 6


		//集合操作
		val set2 = scala.collection.mutable.Set(1, 2, 3, 4, 5)
		println(set2)
		//添加元素
		set2 += 6
		println(set2)
		//添加多个元素
		set2 += (6, 7, 8, 9)
		println(set2)
		//合并一个集合
		set2 ++= Set(10, 11)
		println(set2)
		//删除一个元素
		set2 -= 6
		println(set2)
		//删除多个元素
		set2 -= (9, 10)
		println(set2)
		//删除一个子集集合
		set2 --= Set(7, 8)
		println(set2)
		set2.remove(3)
		println(set2)


		val list1 = ListBuffer(1, 2, 3, 4)
		//获取第一个元素
		list1(0)
		list1.head
		//获取除了第一个元素后的其他所有元素
		list1.tail
		//添加单个元素
		list1 += 5
		//添加一个不可变列表
		list1 ++= List(6, 7)
		//添加一个可变列表
		list1 ++= ListBuffer(8, 9)
		//删除单个元素
		list1 -= 9
		//删除一个不可变的列表
		list1 --= List(7, 8)
		//删除一个可变的列表
		list1 --= ListBuffer(5, 6)
		//可变列表抓内尔不可变列表
		list1.toList
		//可变列表转为不可变数组
		list1.toArray

		val list2 = List(1, 2, 3, 4, 5)
		//定义一个匿名函数传入foreach中
		list2.foreach((i: Int) => println(i))
		//匿名函数的参数类型可以省略，由编译器自动推断
		list2.foreach(i => println(i))
		//当函数参数，只在函数体中出现一次，而且函数体没有嵌套调用，可以使用下划线来简化函数定义
		list2.foreach(println(_))
		// 简写，直接给定println
		list2.foreach(println)

		val list3 = List(1, 2, 3, 4, 5)
		//将集合中的每个元素乘以10，生成一个新的list集合
		list3.map((x: Int) => x * 10)
		//省略，参数类型
		list3.map(x => x * 10)
		//最简写，用下划线
		list3.map(_ * 10)

		val list4 = List("spark hive hadoop flink", "hbase spark")
		//使用flatmap进行扁平化处理，获取得到所有的单词
		list4.flatMap(x => x.split(" ")) //List(spark, hive, hadoop, flink, hbase, spark)
		list4.flatMap(_.split(" "))
		//flatMap本质是先进行了map，然后又调用了flatten
		list4.map(_.split(" ")).flatten

		val list5 = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
		//过滤出集合中大于5的元素
		list5.filter(x => x > 5)
		//把集合中大于5的元素取出来乘以10，生成新的元素
		list5.filter(_ > 5).map(_ * 10)
		println(list5.filter(x => x > 5).map(x => x * 10))

		//默认排序
		val list6 = List(5, 1, 2, 4, 3)
		list6.sorted

		//按单词首字母排序
		val list7 = List("1 hadoop", "2 spark", "3 flink")
		println(list7.sortBy(x => x.split(" ")(1)))

		//自定义排序
		val list8 = List(2, 3, 1, 6, 4, 5)
		//降序
		list8.sortWith((x, y) => x > y)
		//升序
		list8.sortWith((x, y) => x < y)

		val list9 = List("zhangsan" -> "男", "lisi" -> "女", "wangwu" -> "男")
		//按照性别分组
		list9.groupBy(_._2) //Map(男 -> List((zhangsan,男), (wangwu,男)), 女 -> List((lisi,女)))
		list9.groupBy(x => x._2)
		//将分组后的映射转为性别/人数元组列表
		list9.groupBy(x => x._2).map(x => x._1 -> x._2.size) //Map(男 -> 2, 女 -> 1)

		val list10 = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
		list10.reduce((x, y) => x + y)
		//简写,第一个下划线表示第一个参数，历史聚合的数据结果；第二个下划线表示第二个参数，当前要聚合的元素
		list10.reduce(_ + _)
		//与reduce一样，从左往右计算
		list10.reduceLeft(_ + _)
		//从右往左计算
		list10.reduceRight(_ + _)

		val list11 = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
		list11.fold(0)(_ + _)
		list11.fold(10)(_ + _)
		list11.foldLeft(10)(_ + _)
		list11.foldRight(10)(_ + _)

	}
}
