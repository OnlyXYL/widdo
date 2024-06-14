package cn.widdo.hadoop.scala.chapter02

/**
 * scala学弟第二天之包引用
 *
 * @author XYL
 * @date 2023/06/29 13:18
 * @since ${since}
 */
object PackageImport {

  //引入HashMap

  //引入包内所有成员

  import java.awt._

  def main(args: Array[String]): Unit = {

    println("scala的包导入：");
    println("1. 可以导入包内所有成员，例如：import java.util._");
    println("2. 可以导入单个类或对象，例如：import java.util.HashMap");
    println("3. 可以导入一个包下的多个对象，例如：import java.util.{HashMap,List}");
    println("4. 可以重命名成员，例如：import java.util.{HashMap => JavaHashMap}");
    println("5. 可以隐藏成员，例如：import java.util.{HashMap => _,_} 隐藏util包下的所有成员，但是隐藏了HashMap");
    println("6. 可以省略公共部分");
  }

  /**
   * java.awt.event.ActionEvent
   *
   * scala的包引用，可以省略公共部分
   *
   * @param evt
   * @author XYL
   * @date 2023/06/29 13:23:20
   */
  def importPackage(evt: event.ActionEvent): Unit = {
    //因为引入了java.awt，所以可以省略前面部分

    //1. 导入包内所有成员

    //2. 导入单个类
    import java.util.HashMap

    //3. 导入一个包下的多个对象
    val str = new StringBuffer()
    val value = new Object()

    //4. 重命名成员
    import java.util.{HashMap => JavaHashMap}
    val javaHashMap = new JavaHashMap()
    val hashMap = new HashMap()

    //5. 隐藏成员。导入java.io下的所有对象，隐藏File
    import java.io.{File => _, _}
    //    val file = new File()
    val writer = new FileWriter("")
  }

}
