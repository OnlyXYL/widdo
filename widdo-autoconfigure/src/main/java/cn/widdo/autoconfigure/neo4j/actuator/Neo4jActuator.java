package cn.widdo.autoconfigure.neo4j.actuator;

import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;

import java.util.List;
import java.util.Map;

/**
 * neo4j 执行器.
 *
 * @param <T>
 * @author XYL
 * @version 1.2
 * @date 2022/10/18 11:37
 */
public interface Neo4jActuator<T> {

    /**
     * neo4j 执行器 读方法.
     *
     * @param params 参数
     * @return R
     * @author XYL
     * @className cn.widdo.autoconfigure.neo4j.actuator.Neo4jActuator
     * @date 2022/10/19 0:28
     **/
    Result<List<Map<String, Value>>> read(T params);

    /**
     * neo4j 执行器 写方法.
     *
     * @param params 参数
     * @return R
     * @author XYL
     * @className cn.widdo.autoconfigure.neo4j.actuator.Neo4jActuator
     * @date 2022/10/19 0:29
     **/
    Result<List<Map<String, Value>>> write(T params);

}
