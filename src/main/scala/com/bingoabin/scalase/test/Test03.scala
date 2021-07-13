package com.bingoabin.scalase.test

object Test03 {
	def main(args: Array[String]): Unit = {
		val arr1 = Array(1, 2, 3, 4, 5)
		//定义一个函数
		val fun1 = (x: Int) => x * 10

		//返回值是一个函数
		def stringCon(s: String): String => String = {
			def stringProc(s1: String): String = {
				s + "\t" + s1
			}

			stringProc
		}
		//函数作为参数传递到方法中
		arr1.map(fun1)
		//调用返回值是一个函数
		println(stringCon("scala")("spark")) //("spark")


		//柯里化的定义形式
		def kery(x: Int)(y: Int): Int = {
			x + y
		}

		println(kery(3)(5))

		//柯里化的推导过程,注意方法的返回值不要定义任何的返回值类型
		val keryResult = (x: Int) => (y: Int) => {
			x + y
		}

		def keryMethod(x: Int) = {
			(y: Int) => x + y
		}

		println(keryMethod(20))
		println(keryMethod(20)(10))
		println(keryResult(10)(20))

		//可以通过柯里化，直接判断两个集合当中元素是否相等
		val firstArray = Array("Hello", "World")
		val secondArray = Array("hello", "world")
		println(firstArray.corresponds(secondArray)(_.equalsIgnoreCase(_)))

		//演变案例
		def getAddress(a: String): (String, String) => String = {
			(b: String, c: String) => a + "-" + b + "-" + c
		}

		val f1 = getAddress("china") //f1: (String, String) => String = <function2>
		f1("beijing", "tiananmen") //china-beijing-tiananmen

		//这里就可以这样去定义方法
		def getAddress1(a: String)(b: String, c: String): String = {
			a + "-" + b + "-" + c
		}

		getAddress1("china")("beijing", "tiananmen") //china-beijing-tiananmen

		//之前学习使用的下面这些操作就是使用到了柯里化
		List(1, 2, 3, 4).fold(0)(_ + _)
		List(1, 2, 3, 4).foldLeft(0)(_ + _)
		List(1, 2, 3, 4).foldRight(0)(_ + _)

		//柯里化的定义形式
		def method1(x: Int)(y: Int) = {
			x + y
		}

		println(method1(1)(2))

		//推导过程
		def method2(x: Int) = {
			(y: Int) => x + y
		}

		val func2 = (x: Int) => (y: Int) => x + y

		//定义的函数f2,它的返回值依赖于不在函数作用域的一个变量
		//在spark和flink程序中的函数，函数的返回值依赖的变量可能需要大量的网络传输获取到，因此需要将这些变量实现序列化进行网络传输
		var factor = 10
		val f2 = (x: Int) => x * factor
		println(f2(5))

		val point = new Point
		point.x = 99
		point.y = 101
		println(point.x)
		println(point.y)
	}
}

class Point {
	private var _x = 0
	private var _y = 0
	private val bound = 100

	def x = _x

	def x_=(newValue: Int): Unit = {
		if (newValue < bound) _x = newValue else printWarning
	}

	def y = _y

	def y_=(newValue: Int): Unit = {
		if (newValue < bound) _y = newValue else printWarning
	}

	private def printWarning = println("WARNING: Out of bounds")
}
