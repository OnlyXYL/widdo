package cn.widdo.study.scala

import scala.io.StdIn

/**
 * ${title}
 *
 * @author XYL
 * @date 2023/06/21 16:55
 * @since ${since}
 */
object Chapter01 {

  def main(args: Array[String]): Unit = {

    print("请输入：")

    val input = StdIn.readLine()

    helloScala(args = input)

  }

  /**
   *
   * 打印输入信息并返回
   *
   * @param args 参数
   * @author XYL
   * @date 2023/06/27 17:00:56
   * @return java.lang.String
   */
  def helloScala(args: String): Unit = {
    println(args)
  }

}
