package cn.widdo.hadoop.scala.chapter05

import scala.collection.mutable

/**
 * scala学习第五天-集合之set
 *
 * @author XYL
 * @date 2023/07/06 11:27
 * @since 305.2.2.0
 */
object WiddoSet {

  def main(args: Array[String]): Unit = {

    //可变set集合
    val set: mutable.Set[Int] = collection.mutable.Set(1, 2, 1, 3)

    println("遍历Set")
    printInfo(set = set)

    println(s"添加元素：${5}")
    set += 5
    printInfo(set = set)

    println(s"删除元素：${5}")
    set -= 5
    printInfo(set = set)

    println("添加多个元素")
    set += 5 += 6
    printInfo(set = set)

    println("删除多个元素")
    set --= Set(5, 6)
    printInfo(set = set)

  }

  def printInfo(set: mutable.Set[Int]): Unit = {
    for (num <- set) println(num)
  }

}
