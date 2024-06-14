package cn.widdo.hadoop.scala.chapter04.games

/**
 * scala学习第四天-类 （MSI）
 *
 * 英雄联盟季中邀请赛（英语：Mid-Season Invitational，又译作“英雄联盟季中冠军赛”，简称MSI）是由Riot Games公司举行的《英雄联盟》电子竞技大赛，该赛事成立于2015年，每年举办一届，每届比赛都会有来自全球12大赛区
 * （即中国大陆 - LPL、韩国 - LCK、台/港/澳/东南亚 - PCS、北美 - LCS、欧洲 - LEC、巴西 - CBLoL、独联体 - LCL、日本 - LJL、拉丁美洲 - LLA、大洋洲 - OPL、土耳其 - TCL、越南 - VCS）
 * 的12支队伍参赛，参赛队伍为对应赛区的春季季后赛的冠军。而在2017年以前，外卡赛区的参赛队伍则为该年的国际外卡邀请赛（IWCI）的冠军
 *
 * @author XYL
 * @date 2023/07/03 17:30
 * @since 305.2.2.0
 */
object MSI extends AbstractEvent {
  /**
   * 事件名
   */
  override var name: String = "MSI"

  /**
   * 事件
   */
  override def event(): String = {
    "英雄联盟季中邀请赛"
  }

  /**
   * 时间
   */
  override var date: String = "2021-01-01"
}
