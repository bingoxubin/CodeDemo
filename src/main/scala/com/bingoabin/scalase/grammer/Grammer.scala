package com.bingoabin.scalase.grammer

object Grammer {
	def main(args: Array[String]): Unit = {
		//表达式
		var a = 10;
		val b = 20;
		println(a)
		println(b)

		//数据类型
		var num = 1.to(10)
		val result = {
			val a = "nihao"
			val b = "hello"
			val c = 12
			13
		}
		println(result)

		//条件表达式
		val m = 2
		val flag = if (m > 1) -1 else if (m == 1) 0 else 1
		println(flag)

		//循环
		for (i <- 1 to 3; j <- 2 to 3) {
			println(i + " " + j)
		}

		//函数
		val fun: Int => Int = x => x * x
		val fun1: Int => Int = { x => x * x }
		val fun2: (Int, Int) => Int = (x, y) => {
			x * y
		}
		println(fun(2))
		println(fun2(2,3))

		//方法
		println(method(5, 6))
		println(hello7(7))
	}

	def method(x: Int, y: Int): Int = {
		x + y
	}

	def hello7(x: Int): Int = {
		println(x);
		20
	}
}
