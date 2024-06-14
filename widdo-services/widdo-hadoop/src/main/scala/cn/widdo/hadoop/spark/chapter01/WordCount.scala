package cn.widdo.hadoop.spark.chapter01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * spark学习第一天
 *
 * @author XYL
 * @date 2023/07/05 14:25
 * @since 305.2.2.0
 */
object WordCount {

  def main(args: Array[String]): Unit = {

    //TODO 和spark建立联系
    val sparkConf: SparkConf = new SparkConf().setAppName("app")
    val sc = new SparkContext(config = sparkConf)

    //TODO 业务逻辑
    val lines: RDD[String] = sc.textFile("configurations/data/1.txt")

    val words: RDD[String] = lines.flatMap(line => line.split(" "))

    val value: RDD[(String, Iterable[String])] = words.groupBy(word => word)

    val tuples: Array[(String, Int)] = value.map(s => (s._1, s._2.size)).collect()

    println(tuples)

    //TODO 关闭连接
    sc.stop()

  }
}
