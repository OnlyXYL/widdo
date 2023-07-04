package cn.widdo.study.scala.chapter04.games

/**
 * scala学习第四天-抽象类
 *
 * 类中具有以下情况之一的，就是抽象类
 *
 * 1. 存在变量没有初始化
 * 2. 存在方法没有方法体
 *
 * @author XYL
 * @date 2023/07/03 17:20
 * @since 305.2.20
 */
abstract class AbstractEvent extends Event {

  /**
   * 事件名
   */
  var name: String

  /**
   * 时间
   */
  var date: String

  /**
   * 事件
   */
  override def event(): String = name

  /**
   * 重写toString方法
   * @return
   */
  override def toString: String = s"${event()},简称：${name}"
}
