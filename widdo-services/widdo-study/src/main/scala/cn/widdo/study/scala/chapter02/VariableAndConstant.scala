package cn.widdo.study.scala.chapter02

/**
 * scala学习第二天之变量和常量
 *
 * @author XYL
 * @date 2023/06/29 16:36
 * @since 305.2.2.0
 */
object VariableAndConstant {

  /**
   * 入口.
   *
   * @param args
   * @author XYL
   * @date 2023/06/29 16:36:54
   */
  def main(args: Array[String]): Unit = {

    //变量和常量
    variableAndConstant()

  }

  /**
   * 变量声明和变量类型声明.
   *
   * @param
   * @author XYL
   * @date 2023/06/29 16:41:42
   */
  def variableAndConstant(): Unit = {
    //var 声明变量，可以进行修改
    var myVar: String = "widdo"
    myVar = "www.widdo.cn"

    println("【scala】变量和常量之---变量声明")
    println()

    println(s"myVar = ${myVar}")
    println()

    //val 声明常量，不能进行修改
    val myVal: String = "widdo.cn是我的域名"

    println(s"myVal = ${myVal}")

    //多种分配
    println()
    println("【scala】变量和常量之---多种分配")
    println()

    //多分配
    val aVar, bVar = 100

    println(s"aVar = ${aVar}, bVar = ${bVar}")

    val (a1: Int, b1: Int) = (1, 2)

    val a = (2, 3)

    val (b, c) = (3, 4)

    println(s"a1 = ${a1}, b1 = ${b1},(a1,b1) = ${(a1, b1)}, a = ${a}, (b,c) = ${(b, c)}")

  }

}
