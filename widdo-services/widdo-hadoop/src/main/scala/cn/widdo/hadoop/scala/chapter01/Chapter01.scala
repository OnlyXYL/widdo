package cn.widdo.hadoop.scala.chapter01

/**
 * scala学习第一天
 * 1. 标识符
 *
 * @author XYL
 * @date 2023/06/28 16:54
 * @since 305.2.2.0
 */
object Chapter01 {

  /**
   * scala程序入口.
   *
   * @param args 参数
   * @author XYL
   * @date 2023/06/28 17:03:05
   * @return void
   */
  def main(args: Array[String]): Unit = {

    //标识符
    identifier()
  }

  /**
   * 字符数字
   *
   * @param
   * @author XYL
   * @date 2023/06/28 17:03:54
   * @return void
   */
  def identifier(): Unit = {

    println("------【字母数字标识符】------")
    println()

    //val ab : Int = 1
    println("ab -- 字母开头，后面跟字母")

    //val a1 : Int = 1
    println("a1 -- 字母开头，后面跟数字")

    //val _a : Int = 1
    println("_a -- 下划线开头，后面跟字母")

    // val _1 : Int = 1
    println("_1 -- 下划线开头，后面跟数字")

    //$虽然也能用，但是"$"开头的标识符为保留scala编译器产生的标识符，应用程序中尽量避免使用"$"开始的标识符

    //val $1 : Int = 1
    println("$1 -- $开头，后面跟数字")

    //val $a : Int = 1
    println("$a -- 开头，后面跟字母")

    println()
    println("------【操作符标识符】------")
    println()

    //val ++ : Int = 1
    println("++ -- ")

    //val ? : Int = 1
    println("?")

    println()
    println("------【混合标识符】------")
    println()

    //val 1a_+ : Int = 1
    println("1a_+ ")

    println()
    println("------【文本标识符】------")
    println()

    //val `&` : Int = 1
    println("`&`")

  }

}

