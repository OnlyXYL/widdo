package cn.widdo.study.scala.chapter02

/**
 * scala 学习第二天.
 *
 * 1. 注释
 * 2. 空格和空行
 * 3. 换行符
 * 4. 包
 *
 * @author XYL
 * @date 2023/06/29 10:55
 * @since 305.2.2.0
 */
object Chapter02 {

  /**
   * 这是一个多行注释.
   *
   * 程序入口
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {
    note()
  }

  /**
   * 空格和空行，换行符.
   *
   * @param
   * @author XYL
   * @date 2023/06/29 11:04:18
   */
  def note(): Unit = {

    //仅包含空格和注释，这是个空行，scala会进行忽略

    val a: Int = 1;val b : Int = 2

    print(a,b)
  }

}
