package cn.widdo.study.scala.chapter03

/**
 * scala学习第三天之控制结构.
 *
 * 1. if/then/else
 * 2. for循环
 * 3. try/cache/finally
 * 4. for表达式
 * 5. match表达式
 *
 * @author XYL
 * @date 2023/06/30 9:33
 * @since 305.2.2.0
 */
object ControlStructure {

  /**
   * 入口
   *
   * @param args
   * @author XYL
   * @date 2023/06/30 09:34:59
   */
  def main(args: Array[String]): Unit = {

    //获取两个整数中的最小数
    minValue(a = 10, b = 8)

    //循环
    forLoops()

    //for表达式
    forExpression()

    //match 表达式
    matchExpression(1)

    //match 表达式作为方法体
    println("【scala】控制结构：match表达式作为方法体")
    println()
    convertBooleanToStringMessage(bool = true);

    //try/cache/finally
    tryCacheFinally()

  }

  /**
   * 获取两个整数中的最小数.
   *
   * @param a
   * @param b
   * @author XYL
   * @date 2023/06/30 09:39:30
   * @return int
   */
  def minValue(a: Int, b: Int) = {
    val value: Int = if (a < b) a else b

    println("【scala】控制结构之 if/then/else：求两个整数中的最小数")
    println()
    println(s"a = ${a}, b = ${b}，其中最小的是: ${value}")
    println()

    println()
    println("---------------------------------")
  }


  /**
   * for loops.
   *
   * @author XYL
   * @date 2023/06/30 09:47:39
   */
  def forLoops(): Unit = {

    println("【scala】控制结构之 for循环：循环输出集合元素")
    println()

    //list
    val nums = Seq(1, 2, 3)

    println(s"集合【List】：${nums}，循环输出：")
    println()

    println("方法一：for循环")
    println()
    for (n <- nums) println(n)

    println()
    println("方法二：foreach方法：")
    println()
    nums.foreach(println)

    //map
    val map: Map[String, Int] = Map(
      "张三" -> 18,
      "里斯" -> 20,
      "小红" -> 17
    )

    println()
    println(s"集合【Map】：${map}，循环输出：")
    println()

    println("方法一：for循环")
    println()
    for ((name, age) <- map) println(s"我是：${name}，今年：${age} 岁！")

    println()
    println("方法二：foreach方法：")
    println()

    map.foreach {
      case (name, age) => println(s"我是：${name}，今年：${age} 岁！")
    }

    println()
    println("---------------------------------")
  }

  /**
   * for表达式.
   *
   * @param
   * @author XYL
   * @date 2023/06/30 10:14:48
   */
  def forExpression(): Unit = {


    val nums = Seq(1, 2, 3, 4)

    println("【scala】控制结构：for表达式")
    println()

    //加倍
    println(s"集合：${nums}，元素加倍：")
    println()

    val ints: Seq[Int] = for (num <- nums) yield num * 2
    println(ints)
    println()

    println("---------------------------------")


    val names = List("adam", "david", "frank")

    //大写
    println(s"集合：${names}：")
    println()
    println("【元素转大写】")
    println()

    val upper: List[String] = for (name <- names) yield name.capitalize
    println(upper)
    println()

    println("---------------------------------")


    val names1 = List("_adam", "_david", "_frank")
    println(s"集合：${names1}：")
    println()
    println("【元素删除首字母，转大写】")
    println()

    val name = for (name <- names1) yield {
      val str: String = name.drop(1)

      str.capitalize
    }

    println(name)
    println()

    println("简写 1：")
    println()

    //简写
    val simple: List[String] = for (name <- names1) yield name.drop(1).capitalize
    println(simple)

    println()
    println("简写 2：")
    println()

    //简写
    val simple1: List[String] = for (name <- names1) yield {
      name.drop(1).capitalize
    }
    println(simple1)


    println("---------------------------------")

  }

  /**
   * match 表达式.
   *
   * @author XYL
   * @date 2023/06/30 10:35:48
   */
  def matchExpression(i: Int): Unit = {
    val month: String = i match {
      case 1 => "January"
      case 2 => "February"
      case 3 => "March"
      case 4 => "April"
      case 5 => "May"
      case 6 => "June"
      case 7 => "July"
      case 8 => "August"
      case 9 => "September"
      case 10 => "October"
      case 11 => "November"
      case 12 => "December"
      case _ => "Invalid month"
    }

    println()
    println("【scala】控制结构：match表达式")
    println()

    println(s" 1 => ${month}")
    println()

    println("---------------------------------")

  }

  /**
   * if/else.
   *
   * @param bool
   * @author XYL
   * @date 2023/06/30 11:27:04
   * @return void
   */
  def convertBoolean(bool: Boolean): Unit = {
    val str: String = if (bool) "you said true" else "you said false"
    println(str)
  }

  /**
   * match表达式作为方法体
   *
   * 效果等同于上面.
   *
   * 注意：由于方法体是match表达式，因此无法加入非case的代码块。例如：无法加入打印信息
   *
   * @param bool
   * @author XYL
   * @date 2023/06/30 11:23:08
   */
  def convertBooleanToStringMessage(bool: Boolean): Unit = bool match {
    case true =>
      println("you said true")
      println()
      println("---------------------------------")
    case false =>
      println("you said false")
      println()
      println("---------------------------------")
    case _ =>
      println("Invalid input")
      println()
      println("---------------------------------")
  }

  /**
   * try cache finally
   *
   * @param
   * @author XYL
   * @date 2023/06/30 13:59:54
   * @return void
   */
  def tryCacheFinally(): Unit = {
    println("【scala】控制结构：try/cache/finally")

    try {
      throw new Exception("error message.")
    } catch {
      case e: Exception => println(s"异常信息：${e.getMessage}")
    } finally {
      println("这里是 scala 的 异常 finally代码块.")
    }

  }


}
