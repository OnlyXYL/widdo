package cn.widdo.study.scala.chapter04.games

/**
 * scala学习第四天-特征（接口）
 *
 * @author XYL
 * @date 2023/07/03 17:52
 * @since 305.2.2.0
 */
trait Event {

  /**
   * 事件
   */
  def event(): String
}
