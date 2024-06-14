package cn.widdo.hadoop.scala.chapter05

import scala.collection.mutable.ArrayBuffer

/**
 * scala学习第五天-集合之ArrayBuffer
 *
 * 可变序列
 *
 * @author XYL
 * @date 2023/07/05 17:30
 * @since 305.2.2.0
 */
object WiddoArrayBuffer {

  def main(args: Array[String]): Unit = {

    //声明一个可变序列
    val ints: ArrayBuffer[Int] = ArrayBuffer[Int]()

    //添加元素
    ints += 1

    println("添加单个元素：")
    ints.foreach(println)
    println()

    //添加多个元素
    ints += 2 += 3

    println("添加多个元素：")
    ints.foreach(println)

    //添加一个集合
    ints ++= List(4, 5)

    println("添加一个集合：")
    ints.foreach(println)

    //移除一个集合
    ints --= List(4, 5)

    println("移除一个集合：")
    ints.foreach(println)

    //移除多个元素
    ints -= 2 -= 3
    println("移除多个元素：")
    ints.foreach(println)

    //移除一个元素
    ints -= 1
    println("移除一个元素：")
    ints.foreach(println)

    //追加元素
    ints.append(1)
    println("追加一个元素：")
    ints.foreach(println)

    //追加多个元素
    ints.appendAll(List(2,3))

    ints.appendAll(Seq(4,5))

    println("追加多个元素：")
    ints.foreach(println)

    //指定位置插入元素
    ints.insert(0,10)
    println("指定位置插入元素：")
    ints.foreach(println)

    //指定位置插入多个元素
    ints.insertAll(0,List(1,2))
    println("指定位置插入多个元素：")
    ints.foreach(println)

    //前面插入元素
    ints.prepend(3)
    println("前面插入元素：")
    ints.foreach(println)

    //指定位置移除元素
    ints.remove(0)
    println(s"从 ${0}开始，移除${1}个元素")
    ints.foreach(println)

    //从指定位置开始，移除指定个数的元素
    ints.remove(0,3)
    println(s"从 ${0}开始，移除${3}个元素")
    ints.foreach(println)

    //范围生成ArrayBuffer
    val chars: ArrayBuffer[Char] = ArrayBuffer.range('a', 'h')
    println(s"生成从a到h的序列，不包含最后一个")
    chars.foreach(println)

    chars.drop(2)
    println(s"从 ${0}开始，移除${2}个元素")
    chars.foreach(println)

    chars.clear()
    println("清空")
    chars.foreach(println)
  }

}
