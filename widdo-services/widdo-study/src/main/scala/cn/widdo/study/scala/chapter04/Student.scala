package cn.widdo.study.scala.chapter04

/**
 * scala学习第四天-类
 *
 * @author XYL
 * @date 2023/06/30 14:54
 * @since 305.2.2.0
 */
class Student(val sName: String) extends Person(sName) {

  var school: String = ""

  def this(sName: String, school: String) {
    //辅助构造函数以主构造函数调用开始
    this(sName)
    this.school = school
  }

}

object Student {

  def main(args: Array[String]): Unit = {

    val student = new Student("张微", "四川大学")

    println(student.school)
  }

}