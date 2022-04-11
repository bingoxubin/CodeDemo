package scalase.test

import scala.collection.mutable.ListBuffer

object Test04 {
	def main(args: Array[String]): Unit = {
		var list = ListBuffer(25, 4, 2, 1, 7)
		list.sortWith((x, y) => x < y)
		println(list)

		var list6 = ListBuffer(5, 1, 2, 4, 3)
		val list7 = list6.sorted
		list6 -= list6(0)
		println(list6)
	}
}
