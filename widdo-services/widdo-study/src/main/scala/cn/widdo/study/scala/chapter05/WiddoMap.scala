package cn.widdo.study.scala.chapter05

/**
 * scala学习第五天-集合之Map
 *
 * @author XYL
 * @date 2023/07/06 10:02
 * @since 305.2.2.0
 */
object WiddoMap {

  def main(args: Array[String]): Unit = {

    //创建一个map
    var map: Map[String, String] = Map(
      "1" -> "张三",
      "2" -> "李四",
      "3" -> "王五",
      "4" -> "小红"
    )

    println("遍历方法一：for")
    print1(map = map)

    println()
    println("--------------------")

    println("遍历方法二：for")
    print2(map = map)

    println()
    println("--------------------")

    println("遍历方法三：foreach")
    print3(map = map)

    //添加元素
    map += ("5" -> "小花")

    println(s"添加元素:${5}")
    print1(map = map)

    //添加多个元素
    map += ("6" -> "小小", "7" -> "熊熊")

    println("添加多个元素")
    print1(map = map)

    println("添加map")
    map ++= Map("8" -> "8", "9" -> "9")
    print1(map = map)

    //删除元素
    println("删除元素")
    map --= List("8", "9")
    map -= ("6", "7")
    map -= "5"
    print1(map = map)

    //查看元素
    val str: String = map("4")
    println(s"查看key是：${4},的value")
    println(str)

    //更新元素
    println(s"更新key是:${4},的value")

    map += ("4" -> "更新4")
    print1(map = map)

    println("清空map")
    map = map.empty
    print1(map = map)

    collection.mutable.Map("" -> "")

    collection.immutable.Map("" -> "")

  }

  /**
   * 打印方法1
   *
   * @param map
   * @author XYL
   * @date 2023/07/06 10:43:36
   * @return void
   */
  def print1(map: Map[String, String]): Unit = {
    for ((k, v) <- map) println(s"k:${k},value:${v}")
  }

  /**
   * 打印方法1
   *
   * @param map
   * @author XYL
   * @date 2023/07/06 10:43:47
   * @return void
   */
  def print2(map: Map[String, String]): Unit = {
    for (elem <- map) println(s"${elem}，key:${elem._1}，value:${elem._2}")
  }

  /**
   * 打印方法3
   *
   * @param map
   * @author XYL
   * @date 2023/07/06 10:43:53
   * @return void
   */
  def print3(map: Map[String, String]): Unit = {
    map.foreach {
      case (key, value) => println(s"key: ${key},value:${value}")
    }
  }

}
