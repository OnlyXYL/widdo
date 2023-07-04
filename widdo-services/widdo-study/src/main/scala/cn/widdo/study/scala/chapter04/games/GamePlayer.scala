package cn.widdo.study.scala.chapter04.games

/**
 * scala学习第四天-类，枚举，对象，特征、抽象类
 *
 * @author XYL
 * @date 2023/07/03 17:07
 * @since 305.2.2.0
 */

class GamePlayer(var name: String, var gameName: GameEnum) extends GameTrait {

  //路线，默认打野位置
  var feature: String = "打野"

  //构造方法
  def this(name: String, gameName: GameEnum, feature: String) {
    this(name, gameName)
    this.feature = feature
  }

  /**
   * play
   *
   */
  override def play(): String =
    s"我是：${name}，玩的游戏是：${is()}，是一名${gameName}的职业选手，偏爱的位置是：${feature}。\n ${gameName}的主要赛事有：${event().mkString("；")}"

  /**
   * 事件
   *
   * @param gameEnum
   */
  override def event(): List[Event] = {
    GameEventFactory.get(gameName)
  }

  /**
   * 游戏实例
   *
   * @param gameType
   * @author XYL
   * @date 2023/07/03 17:11:18
   * @return cn.widdo.study.scala.chapter04.games.GameEnum
   */
  override def is(): GameEnum = gameName
}


object GamePlayer {

  def main(args: Array[String]): Unit = {

    //初始化
    GameEventFactory.main(args)

    //1. 创建player
    val gamePlayer: GamePlayer = player("widdo", LOL, "ADC")

    //2. 根据游戏获取事件
    print(player = gamePlayer)

  }

  /**
   * 选手
   *
   * @param name
   * @param gameEnum
   * @param feature
   * @author XYL
   * @date 2023/07/04 11:35:53
   * @return void
   */
  def player(name: String, gameEnum: GameEnum, feature: String): GamePlayer = {
    new GamePlayer(name, gameEnum, feature)
  }

  /**
   * 打印信息.
   *
   * @param player
   * @author XYL
   * @date 2023/07/04 11:37:46
   * @return void
   */
  def print(player: GamePlayer): Unit = {
    println(s"【游戏】${player.is()}")

    println(s"【赛事】${player.event().mkString("，")}")

    println(s"【选手信息】${player.play}")

  }

}