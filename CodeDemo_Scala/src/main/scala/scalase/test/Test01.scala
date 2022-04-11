package scalase.test

object Test01 {
	def main(args: Array[String]): Unit = {
		val a = 10
		lazy val c = 10
		println(c)
		println(1.to(10))
		println(1.toString)

		val x = 0
		val result = {
			val y = x + 10
			val z = y + "-hello"
			val m = z + "-kaikeba"
			"over"
		}
		println(result)

		val m: Int = 1
		val p: Int = if (m > 1) 1 else -1
		val n: Any = if (m > 1) 0 else "error"
		val q: AnyVal = if (m > 1) 1 //相当于下面这个语句
		val r: AnyVal = if (m > 1) 1 else ()
		val s: Int = if (m > 1) -1 else if (m == 1) 0 else 1

		/**
		  * for(i <- 表达式/数组/集合){}
		  * for(i <- 表达式/数组/集合 if 表达式) {}
		  */
		for (i <- 1 to 10) println(i)
		for (i <- 1 to 3; j <- 1 to 3) println(i * 10 + j)
		for (i <- 1 to 10 if i > 5) println(i)
		//用yield表达式构建出一个新集合
		val v: IndexedSeq[Int] = for (i <- 1 to 5) yield i * 10

		/*
		while(Boolean表达式){}
		do{}while(Boolean表达式)
		*/
		var y = 10
		while (y > 1) {
			print(y)
			y -= 1
		}

		/**
		  * def methodName (参数名:参数类型, 参数名:参数类型) : [return type] = {
		  * // 方法体：一系列的代码
		  * }
		  */
		def method(x: Int, y: Int): Int = {
			x + y
		}

		print(method(1, 2))

		def hello3(first: Int, second: String): Any = {
			if (first > 10) {
				first
			} else {
				second
			}
		} //自动推断返回类型

		def hello4(first: Int = 10, second: String) = {
			println(first + "\t" + second)
		}

		hello4(second = "helloworld") //如果不传参数，使用默认值代替

		def method1(first: Int*) = {
			var result = 0
			for (arg <- first) {
				result += arg
			}
			result
		}

		print(method1(1, 2, 3, 4, 5))
		print(method1(2, 3)) //变长参数

		def fibi(x: Int): Int = {
			if (x == 1) {
				1
			} else {
				x * fibi(x - 1)
			}
		}

		print(fibi(5)) //递归方法

		def hello7(first: Int) {
			print(first)
			20
		}

		hello7(7) //没有显示指定返回值，方法定义省略“=”,表示一个代码执行过程

		/**
		  * val 函数名 = (参数名1:参数类型,参数名2:参数类型) => {函数体}
		  * val 函数名:(参数类型,参数类型2) => (返回类型) = {函数体}
		  */
		val fun1: (Int, Int) => Int = (x: Int, y: Int) => {
			x + y
		}

		val fun2: Int => Int = {
			x => x * x
		}

		val fun3: (Int, String) => (String, Int) = { (x, y) => (y, x) }

		(x: String, y: String) => {
			x + y
		} //匿名函数



	}
}
