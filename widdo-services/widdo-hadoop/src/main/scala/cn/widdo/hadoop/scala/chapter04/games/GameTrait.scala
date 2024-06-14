package cn.widdo.hadoop.scala.chapter04.games

/**
 * scala学习第四天-特征(接口)
 *
 * @author XYL
 * @date 2023/07/03 17:08
 * @since 305.2.2.0
 */
trait GameTrait {

  /**
   * 游戏实例
   *
   * @param gameType
   * @author XYL
   * @date 2023/07/03 17:11:18
   * @return cn.widdo.study.scala.chapter04.games.GameEnum
   */
  def is(): GameEnum

  /**
   * play
   *
   */
  def play(): String

  /**
   * 事件
   *
   */
  def event(): List[Event]

}
