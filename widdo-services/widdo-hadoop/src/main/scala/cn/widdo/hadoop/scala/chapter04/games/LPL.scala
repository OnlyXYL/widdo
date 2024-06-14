package cn.widdo.hadoop.scala.chapter04.games

/**
 * scala学习第四天-类 （LPL）
 *
 * 英雄联盟职业联赛（英语：League of Legends Pro League），简称LPL，是《英雄联盟》中国大陆地区的顶级职业联赛。
 *
 * 2013年春季，英雄联盟职业联赛开赛。2014年，英雄联盟职业联赛开始提供英文转播。2017年，英雄联盟职业联赛进行了包括取消升降级制度，增加队伍数目以及采取主客场制等一系列改革。
 * 目前英雄联盟职业联赛有17支参赛队伍，每年进行春季赛和夏季赛两季比赛，每季比赛又各分为常规赛、季后赛两部分。[2]英雄联盟职业联赛拥有4个英雄联盟世界总决赛参赛名额，具体参赛队伍由春季赛，夏季赛的成绩和夏季赛后举行的资格赛决定。
 *
 * @author XYL
 * @date 2023/07/03 17:23
 * @since 305.2.2.0
 */
object LPL extends AbstractEvent {

  /**
   * 事件名
   */
  override var name: String = "LPL"

  /**
   * 事件
   */
  override def event(): String = {
    "英雄联盟职业联赛"
  }

  /**
   * 时间
   */
  override var date: String = "2021-01-01"
}
