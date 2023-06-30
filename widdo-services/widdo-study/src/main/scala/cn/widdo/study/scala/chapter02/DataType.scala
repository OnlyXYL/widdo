package cn.widdo.study.scala.chapter02

/**
 * scala学习之数据类型
 *
 * @author XYL
 * @date 2023/06/29 14:14
 * @since 305.2.2.0
 */
object DataType {

  /**
   * 入口
   *
   * @param args
   * @author XYL
   * @date 2023/06/29 14:37:45
   */
  def main(args: Array[String]): Unit = {

    //整型字面量
    println()
    println("【scala】数据类型-整型字面量")
    println()

    val a: Int = 1
    val b = 10L
    println(s"a = ${a}, b = ${b}")
    println()

    //浮点型字面量
    println("【scala】数据类型-浮点型字面量")
    println()
    val c: Double = 0.0
    val d: Float = 10.0F
    println(s"c = ${c}, d = ${d}")
    println()

    //布尔型字面量
    println("【scala】数据类型-布尔型字面量")
    println()
    val e: Boolean = true
    val f: Boolean = false
    println(s"e = ${e}, f = ${f}")
    println()

    //符号字面量
    println("【scala】数据类型-符号字面量")
    println()
    val symbol: Symbol = Symbol("1a")
    println(symbol)
    println()

    //字符字面量
    println("【scala】数据类型-字符字面量")
    println()
    val g: Char = 'a'
    val h: Char = '1'
    val i: Char = '\n'
    println(s"g = ${g}, h = ${h}, i = ${i}")
    println()

    //字符串字面量
    println("【scala】数据类型-字符串字面量")
    println()
    val j: String = "scala"
    println(s"j = ${j}")
    println()

    //多行字符串的表示方法
    println("【scala】数据类型-多行字符串标识符方法")
    println()
    val k: String =
      """
        |www.widdo.cn
        |这是我的域名
        |在这里，记录学习和生活...
        |""".stripMargin
    println(s"k = ${k}")

    //Nothing类型
    println("【scala】数据类型-Nothing类型")
    println()
    val ints: List[Int] = List(1)

    val list: List[Nothing] = List()
    println(s"list = ${list}, ints = ${ints}")
    println()

    def error = throw new Exception("error message")

    println(s"error = ${error}")

  }

}
