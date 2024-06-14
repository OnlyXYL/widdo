package cn.widdo.hadoop.scala.chapter06

/**
 * scala学习第六天-样例类
 *
 * @author XYL
 * @date 2023/07/06 15:21
 * @since 305.2.2.0
 */
case class Person(name: String, age: Int)

object Person {

  def main(args: Array[String]): Unit = {

    val person: Person = Person("张三", 18)
    println(person)

    try {
      //模式匹配
      printPersonString(person = null)
    } catch {
      case e: Exception => println(e.getMessage)
    }
  }

  /**
   * 样例类用于模式匹配
   *
   * @param person
   * @author XYL
   * @date 2023/07/06 16:05:37
   * @return void
   */
  def printPersonString(person: Person): Unit = person match {
    case Person(name, age) => println(s"${person}")
    case _ => println("不认识")
  }
}
