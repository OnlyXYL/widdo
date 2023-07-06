package cn.widdo.study.scala.chapter05

/**
 * scala学习第五天-集合之List
 *
 * 不可变集合，操作之后会生成新的List
 *
 * @author XYL
 * @date 2023/07/05 17:55
 * @since 305.2.2.0
 */
object WiddoList {

  def main(args: Array[String]): Unit = {

    //方法调用，无参方法可以省略括号
    baseOperation

  }

  /**
   * scala list 基础操作
   *
   * @param
   * @author XYL
   * @date 2023/07/06 09:25:42
   * @return void
   */
  def baseOperation(): Unit = {
    //创建list
    val ints: List[Int] = List(1, 2, 3)
    println(s"创建包含${3}个元素的List")
    ints.foreach(println)

    //添加元素
    val ints1: List[Int] = ints :+ 4
    println(s"添加元素${4}")
    ints1.foreach(println)

    //添加多个元素
    val ints2: List[Int] = ints :+ 5 :+ 6
    println(s"添加多个元素")
    ints2.foreach(println)

    //左侧添加元素.需要指定集合，否则不知道类型
    val ints3: List[Int] = 0 +: ints2

    println(s"左侧添加元素${0}")
    ints3.foreach(println)

    //左侧添加多个
    val ints4: List[Int] = List(-1, -2) ++: ints3
    println(s"左侧添加多个元素")
    ints4.foreach(println)

    //遍历元素
    println("遍历元素")
    for (num <- ints4) println(num)

    println("遍历元素方法二")
    ints4.foreach(println)

    println("遍历元素方法三")
    ints4.foreach(s => println(s))

    //创建list.list是一个以Nil结尾的单链表
    val list: List[Int] = 1 :: 2 :: 3 :: Nil

    println("::分号创建List")
    list.foreach(println)
  }


}
