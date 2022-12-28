package cn.widdo.autoconfigure.neo4j.actuator;

/**
 * neo4j 执行器.
 *
 * @param <T>
 * @param <R>
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/18 11:37
 */
public interface Neo4jActuator<T, R> {

    /**
     * neo4j 执行器 读方法.
     *
     * @param params 参数
     * @return R
     * @author XYL
     * @className cn.widdo.autoconfigure.neo4j.actuator.Neo4jActuator
     * @date 2022/10/19 0:28
     **/
    R read(T params);

    /**
     * neo4j 执行器 写方法.
     *
     * @param params 参数
     * @return R
     * @author XYL
     * @className cn.widdo.autoconfigure.neo4j.actuator.Neo4jActuator
     * @date 2022/10/19 0:29
     **/
    R write(T params);

}
