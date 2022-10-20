package cn.widdo.autoconfigure.neo4j.reader;

/**
 * read interface
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/14 16:07
 */
public interface Neo4jReader<T, R> {

    /**
     * 读数据
     *
     * @param params
     * @return R
     * @author XYL
     * @className cn.widdo.autoconfigure.neo4j.read.ReadInterface
     * @date 2022/10/14 16:11
     **/
    R query(T params);
}
