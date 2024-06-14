package cn.widdo.hadoop.scala.chapter03

/**
 * scala学习第三天之class，object
 *
 * @author XYL
 * @date 2023/06/30 14:39
 * @since 305.2.2.0
 */
class Person(var name: String, var age: Int, var country: String = "中国") extends Action with Likes {

  /**
   * 吃
   */
  override def eat(): Unit = println(s"${name}，不怎么吃饭，所以很瘦！！！")

  /**
   * 喝
   */
  override def drink(): Unit = println(s"${name}，喜欢和啤酒！！！")

  /**
   * 唱歌.
   *
   * @author XYL
   * @date 2023/06/30 16:40:54
   */
  override def sing(): Unit = println(s"${name}，喜欢唱歌！！！")

  /**
   * 跳舞.
   *
   * @author XYL
   * @date 2023/06/30 16:40:43
   */
  override def dance(): Unit = println(s"${name}，喜欢跳舞！！！")

  override def toString: String = s"姓名：${name}, 年龄：${age}，国家：${country}"

}

object Person {

  def main(args: Array[String]): Unit = {

    val person = new Person("张三", 20)

    println(person.toString)

    println(person.eat())

  }

}