package cn.widdo.study.scala.chapter04

/**
 * scala学习第四天-类
 *
 * 1.
 *
 * @author XYL
 * @date 2023/07/03 15:27
 * @since ${since}
 */
class Teacher(var name: String, var course: String) {

  def this(name: String){
    this(name,Teacher.course)

  }
}

object Teacher {

  private var course: String = "英语"

  def main(args: Array[String]): Unit = {

    new Teacher("张老师")

  }
}