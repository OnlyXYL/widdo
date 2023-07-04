package cn.widdo.study.scala.chapter04

/**
 * scala学习第四天-类
 *
 * 1. 每个类都有主构建函数，参数直接在类名后
 * 2. 构造方法带有默认值时，创建实例时，可以不用指定有默认值的参数
 * 3. 用this关键字，定义辅助构造函数
 *
 * @author XYL
 * @date 2023/07/03 10:30
 * @since ${since}
 */
class Person(var name: String, var age: Int = 18, var country: String = "北京") extends Action with Likes {

  /**
   * 默认访问权限为 public
   */
  var address = "北京"

  /**
   * 对象私有字段
   */
  private[this] val HOME = "对象私有字段"

  /**
   * 私有字段
   */
  private val myProperty = "私有字段，只能在类的内部使用"

  /**
   * 辅助构造器，必须以主构造器或者其他的辅助构造器开始
   *
   * @param name
   * @param address
   */
  def this(name: String, address: String) {
    this(name)
    this.address = address
  }

  /**
   * 吃
   *
   * scala中，无参方法在调用时，可以省略括号。
   *
   */
  def eat(): Unit = println("不怎么吃饭，所以很瘦！！！")

  /**
   * 喝
   */
  def drink(drink: String): Unit = println(s"喜欢喝:${drink}！！！")

  /**
   * 唱歌.
   *
   * @author XYL
   * @date 2023/06/30 16:40:54
   */
  def sing(): Unit = println(s"喜欢唱歌！！！")

  /**
   * 跳舞.
   *
   * @author XYL
   * @date 2023/06/30 16:40:43
   */
  def dance(): Unit = println("喜欢跳舞！！！")

  /**
   * 当前对象
   */
  def printInfo(): Unit = println(this)

  override def toString: String = s"姓名：${name}, 年龄：${age}，国家：${country}，地址：${address}，HOME：${HOME}"

}

object Person {

  def main(args: Array[String]): Unit = {

    val person = new Person("李四")

    val hua = new Person("小花", 18)

    val hong = new Person("小红", 20, "北京")

    //    println(s"我是：${person.name}。个人简介：$person，${person.eat()}，${person.drink("啤酒")}")

    println("我是：" + person.name + "。个人简介：" + person + person.eat() + person.drink("啤酒"))

    //无参方法，在调用时，可以省略括号
    println(s"我是：${hua.name}。个人简介：${hua}，${hua.dance}")

    println(s"我是：${hong.name}。个人简介：${hong}，${hong.sing}")

    //访问私有字段
    println(person.myProperty)


    //通过辅助构造器构建实例
    val fuzhu = new Person("辅助", "上海")

    println(fuzhu)


  }
}
