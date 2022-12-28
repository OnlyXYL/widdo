package cn.widdo.autoconfigure.neo4j.writer;

/**
 * neo4j writer.
 *
 * @param <R>
 * @param <T>
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/14 16:36
 */
public interface Neo4jWriter<T, R> {

    /**
     * 写方法.
     *
     * @param params 参数
     * @return R
     * @author XYL
     * @className cn.widdo.autoconfigure.neo4j.write.Neo4jWriter
     * @date 2022/10/14 16:37
     **/
    R write(T params);
}
