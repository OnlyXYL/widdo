package cn.widdo.hadoop.scala.chapter04.games

/**
 * scala学习第四天-类 （WORLDS）
 *
 * 英雄联盟全球总决赛（英语：League of Legends World Championship Series，也被称为英雄联盟世界大赛、简称为Worlds）[1]是由Riot Games公司举行的英雄联盟电子竞技比赛
 *
 * @author XYL
 * @date 2023/07/03 17:38
 * @since 305.2.2.0
 */
object WORLDS extends AbstractEvent {
  /**
   * 事件名
   */
  override var name: String = "WORLDS"

  /**
   * 事件
   */
  override def event(): String = {
    "英雄联盟全球总决赛"
  }

  /**
   * 时间
   */
  override var date: String = "2021-01-01"
}