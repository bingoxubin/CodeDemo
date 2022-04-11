package scalase.grammer

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object Structure {
	def main(args: Array[String]): Unit = {
		//一、数组
		//定长
		val arr1 = Array[Int](8)
		val arr2 = Array(1, 3, 4, 5, 6, 7)
		println(arr2(4))
		//不定长
		val arr3 = ArrayBuffer[Int](8)
		val arr4 = ArrayBuffer("nihao", 3, "world")
		arr4 += "flink"
		arr4 ++= ArrayBuffer("nishuo", 7, 8, 9)
		println(arr4)
		//数组遍历
		for (i <- arr4) print(i + ",")
		for (i <- 0 to arr4.length - 1) print(arr4(i) + ",")
		for (i <- 0 until arr4.length) print(arr4(i) + ",")
		println()
		//常用操作
		val arr5 = Array(6, 5, 3, 8, 1, 7, 9)
		println(arr5.sum + " " + arr5.max + " " + arr5.min + " " + arr5.sorted + " " + arr5.sorted.reverse)

		//二、元组
		val tuple1 = (1, "zhangsan", 2, "lisi")
		val tuple2 = 1 -> 2
		println(tuple1._1 + " " + tuple1._2)

		//三、Map
		//不可变
		val map1 = Map("zhangsan" -> 20, "lisi" -> 15)
		println(map1("zhangsan"))
		//可变
		val map2 = scala.collection.mutable.Map("zhangsan" -> 10, "lisi" -> 15)
		map2("zhangsan") = 30 //map1("zhangsan") = 30报错
		println(map2.getOrElse("wangwu", -1))
		map2 += "wangwu" -> 25
		map2 ++= Map("zhaoliu" -> 25, "liqi" -> 30)
		//遍历
		for (k <- map2.keys) print(k + "->" + map2(k) + "||")
		for (k <- map2.keySet) print(k + "->" + map2(k) + "||")
		for ((k, v) <- map2) print(k + "->" + v + "||")
		for (k <- map2.values) print(k + " ")

		//四、Set
		val set1 = Set[Int](9)
		val set2 = Set(1, 1, 2, 3, 4, 5)
		//不可变 添加删除无效
		println()
		println(set2.size)
		set2 + 6
		println(set2)
		set2 -- Set(2, 3)
		println(set2)
		println(set2 & Set(2, 3, 4, 5))
		for (i <- set2) print(i + " ")
		println()
		//可变
		val set3 = scala.collection.mutable.Set(1, 5, 2, 3, 4)
		set3 += 6
		println(set3)
		set3 --= Set(2, 3, 4)
		println(set3)

		//List
		//不可变
		val list = List[Int](8)
		val list1 = List(1, 1, 2, 2, 3, 5, 5, 6)
		val lise2 = 1 :: 2 :: 3 :: Nil
		//可变
		val list3 = ListBuffer[Int]()
		val list4 = ListBuffer(1, 1, 2, 2, 3, 5, 5, 6)
		println(list4(0) + " " + list4.head + " " + list4.tail)
		list4 += 5
		list4 ++= ListBuffer(7, 8, 9)
		println(list4)
		//转化
		list4.toList //可变转不可变
		list4.toArray //列表转数组
	}
}
