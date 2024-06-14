package cn.widdo.hadoop.neo4j

import org.apache.spark.sql.{Dataset, SaveMode, SparkSession}

import scala.util.Random

/**
 * Neo4jSparkWrite.
 *
 * @author XYL
 * @date 2023/02/09 16:12
 * @since 263.1.2.1
 */
object Neo4jSparkWrite {

  //样例类。樣例類的定義，需要放在方法的作用域之外（即java的成員變量位置）
  case class Person(name: String, age: Int)

  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .master("local")
      .appName("Neo4jSparkWrite")
      .getOrCreate()

    //使用SparkSession中的隱式轉換。需要放在獲取spark對象（SparkSession對象）語句之後
    import spark.implicits._

    val rand = Random

    //通過使用SparkSession的隱式轉換，把RDD轉爲DatasetHolder,同時使用DatasetHolder中的toDS()方法轉換爲Dataset.
    val ds: Dataset[Person] = (1 to 10)
      .map(i => {
        if (1 == i) {
          Person(name = "張三", age = rand.nextInt(100))
        } else {
          Person(name = "張三" + i, age = rand.nextInt(100))
        }
      }).toDS()

    ds.write
      .format("org.neo4j.spark.DataSource")
      //等同于merge
      .mode(SaveMode.Overwrite)
      .option("url", "bolt://localhost:7687")
      .option("authentication.basic.username", "neo4j")
      .option("authentication.basic.password", "widdo_neo4j")
      .option("labels", "Person")
      //SaveMode為OverWrite時，需要指定node.keys.個人理解為：因爲OverWrite等同於Merge。因此，需要指定一個屬性，用來進行查詢。同時，該屬性需要有唯一約束
      .option("node.keys", "name")
      //創建唯一約束，在保存數據之前，會先創建唯一約束；同理，schema.optimization.type為INDEX時，創建索引。如果已經存在，則會直接跳過，保持原始狀態
      .option("schema.optimization.type", "NODE_CONSTRAINTS")
      .save()

    spark.close()
  }
}
