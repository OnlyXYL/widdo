package cn.widdo.autoconfigure.neo4j.actuator;

/**
 * neo4j Actuator.
 *
 * @param <T> params of method
 * @param <R> output of neo4j
 * @param <O> output of method
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/18 11:37
 */
public interface Neo4jActuator<T, R, O> {

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

    /**
     * wrapper result.
     *
     * @param r the result of neo4j
     * @return O    the result of web
     * @author XYL
     * @date 2022/12/30 01:36:52
     **/
    O wrapper(R r);
}
