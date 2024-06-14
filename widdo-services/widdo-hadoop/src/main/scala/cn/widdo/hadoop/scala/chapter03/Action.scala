package cn.widdo.hadoop.scala.chapter03

/**
 * 特征，类似java中的接口
 *
 * @author XYL
 * @date 2023/06/30 16:25
 * @since 305.2.2.0
 */
trait Action {

  /**
   * 睡觉
   */
  def sleet(): Unit = println("人都要睡觉！！！")

  /**
   * 吃
   */
  def eat(): Unit

  /**
   * 喝
   */
  def drink(): Unit
}
