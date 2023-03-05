package cn.widdo.autoconfigure.neo4j.reader;

import cn.widdo.autoconfigure.neo4j.Neo4jHelper;

/**
 * read interface.
 *
 * @param <T>
 * @param <R>
 * @author XYL
 * @date 2022/10/14 16:07
 * @since 263.1.1.0
 */
public interface Neo4jReader<T, R> extends Reader, Neo4jHelper {

    /**
     * 读数据.
     *
     * @param params 参数
     * @return R
     * @author XYL
     * @className cn.widdo.autoconfigure.neo4j.read.ReadInterface
     * @date 2022/10/14 16:11
     **/
    R read(T params);
}
