package cn.widdo.hadoop.scala.chapter04

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
   * @param drink 喝的东西
   */
  def drink(drink: String): Unit
}
