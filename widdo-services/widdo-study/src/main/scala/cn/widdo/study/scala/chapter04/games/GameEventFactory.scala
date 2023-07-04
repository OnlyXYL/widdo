package cn.widdo.study.scala.chapter04.games

/**
 * 游戏事件工厂
 *
 * @author XYL
 * @date 2023/07/03 17:56
 * @since 305.2.2.0
 */
class GameEventFactory {

  /**
   * 保存游戏和事件关系
   *
   * 游戏和事件是一对多关系
   *
   * @param gameEnum
   * @param event
   */
  def put(gameEnum: GameEnum, event: Event): Unit = {

    //1. 根据游戏获取事件列表
    val events: List[Event] = GameEventFactory.map.get(gameEnum).getOrElse(List[Event]())

    //2. 新事件加入列表
    val list: List[Event] = events :+ event

    //3. 新列表加入map
    GameEventFactory.map += (gameEnum -> list)
  }

  /**
   * 根据游戏获取事件
   *
   * @param gameEnum
   * @author XYL
   * @date 2023/07/03 18:27:04
   * @return cn.widdo.study.scala.chapter04.games.Event
   */
  def get(gameEnum: GameEnum): List[Event] = {

    //1. 根据游戏获取事件列表
    GameEventFactory.map.get(gameEnum).getOrElse(List[Event]())
  }

}

//伴生对象。实现静态代码块功能
object GameEventFactory {

  /**
   * 游戏，事件集合
   */
  var map = Map[GameEnum, List[Event]]()

  def main(args: Array[String]): Unit = {

    val factory = new GameEventFactory()

    //初始化
    init(factory = factory)
  }

  /**
   * 根据游戏获取事件
   *
   * @param gameEnum
   * @author XYL
   * @date 2023/07/04 10:02:10
   * @return scala.collection.immutable.List<cn.widdo.study.scala.chapter04.games.Event>
   */
  def get(gameEnum: GameEnum): List[Event] = {
    map.get(gameEnum).getOrElse(List[Event]())
  }

  /** *
   * 初始化游戏事件
   *
   * @author XYL
   * @date 2023/07/04 9:58
   * */
  def init(factory: GameEventFactory): Unit = {

    //dota
    factory.put(gameEnum = DOTA, event = LPL)

    //地下城与勇士
    factory.put(gameEnum = DNF, event = LPL)

    //英雄联盟
    factory.put(gameEnum = LOL, event = LPL)
    factory.put(gameEnum = LOL,event = MSI)
    factory.put(gameEnum = LOL,event = WORLDS)

    //坦克世界
    factory.put(gameEnum = WOT, event = LPL)

    //梦三国
    factory.put(gameEnum = MSG, event = LPL)

  }


}