package cn.widdo.hadoop.neo4j

import org.apache.spark.sql.{DataFrameReader, SparkSession}

import scala.io.StdIn

/**
 * Neo4jSpark.
 *
 * @author XYL
 * @date 2023/02/08 17:34
 * @since 263.1.2.1
 */
object Neo4jSparkRead {

  def main(args: Array[String]): Unit = {

    val sp: SparkSession = sparkSession()

    val df: DataFrameReader = dataFrameReader(spark = sp)

    run(sp, df)

    //    runFromInput(sp, df)

  }

  def runFromInput(sp: SparkSession, df: DataFrameReader): Unit = {
    print("请输入Neo4j Cypher:")

    //接收控制台输入的cypher参数
    val cypherInput = StdIn.readLine()

    /**
     * 指定函数参数名。
     * 一般情况下函数调用，参数就按照函数定义时的参数顺序一个个传递。但是我们也可以通过指定函数的参数名，并且不需要按照顺序向函数传递参数。
     *
     * callProcedure(df,"Person");
     * 或
     * callProcedure(label = "Person", df = df);
     */
    try {
      callFromInput(cypher = cypherInput, df = df)
    } catch {
      case _: Exception =>
        println("遇到异常，需要关闭 SparkSession!")
    } finally {
      println("close SparkSession before exiting...")
      sp.close()
    }
  }

  def run(sp: SparkSession, df: DataFrameReader): Unit = {
    /**
     * 指定函数参数名。
     * 一般情况下函数调用，参数就按照函数定义时的参数顺序一个个传递。但是我们也可以通过指定函数的参数名，并且不需要按照顺序向函数传递参数。
     *
     * callProcedure(df,"Person");
     * 或
     * callProcedure(label = "Person", df = df);
     */
    try {
      callProcedure(label = "Person", df = df)
    } catch {
      case _: Exception =>
        println("遇到异常，需要关闭 SparkSession!")
    } finally {
      println("close SparkSession before exiting...")
      sp.close()
    }
  }

  /**
   * sparkSession.
   *
   * @return an instance typed of sparkSession
   */
  def sparkSession(): SparkSession = {
    val spark = SparkSession
      .builder()
      .master("local")
      .appName("Neo4jSparkRead")
      .getOrCreate()

    spark
  }

  def dataFrameReader(spark: SparkSession): DataFrameReader = {
    val df = spark.read.format("org.neo4j.spark.DataSource")
      .option("url", "bolt://localhost:7687")
      .option("authentication.basic.username", "neo4j")
      .option("authentication.basic.password", "widdo_neo4j")

    df
  }

  def query(df: DataFrameReader): Unit = {
    df
      .option("query", "MATCH (n:Person) return n")
      .load()
      .show()
  }

  def query1(df: DataFrameReader): Unit = {
    df
      .option("labels", ":Person")
      .load()
      .show()
  }

  /**
   * 调用neo4j procedure.
   *
   * @param df    df
   * @param label label
   */
  def callProcedure(df: DataFrameReader, label: String): Unit = {
    df
      .option("query", "call widdo.node.count('" + label + "') yield count return count")
      .load()
      .show()
  }

  /**
   * 执行neo4j cypher
   * @param df  df
   * @param cypher  cypher
   */
  def callFromInput(df: DataFrameReader, cypher: String): Unit = {
    df
      .option("query", cypher)
      .load()
      .show()
  }
}
