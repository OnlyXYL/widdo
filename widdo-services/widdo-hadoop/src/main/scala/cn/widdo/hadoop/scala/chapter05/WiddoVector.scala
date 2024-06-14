package cn.widdo.hadoop.scala.chapter05

/**
 * scala学习第五天-集合之Vector
 *
 * @author XYL
 * @date 2023/07/06 9:50
 * @since 305.2.2.0
 */
object WiddoVector {

  def main(args: Array[String]): Unit = {

    val vector: Vector[Int] = Vector(1, 2, 3, 4)

    val i: Int = vector.indexOf(2)

    println(s"元素${2}的索引是")
    println(i)

    val i1: Int = vector(1)

    println(s"索引为${1}的元素是")
    println(i1)
  }

}
