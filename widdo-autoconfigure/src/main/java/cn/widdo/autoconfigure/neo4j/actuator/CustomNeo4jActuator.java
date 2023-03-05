package cn.widdo.autoconfigure.neo4j.actuator;

import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;

import java.util.List;
import java.util.Map;

/**
 * CustomNeo4jActuator.
 *
 * @author XYL
 * @date 2022/12/23 11:29
 * @since 263.1.1.0
 */
public class CustomNeo4jActuator extends AbstractNeo4jActuatorDecorator<Map<String, Object>, Result<List<Map<String, Value>>>> {

    /**
     * constructor has one param called {@link Neo4jActuator}.
     *
     * @param neo4jActuator {@link Neo4jActuator}
     */
    public CustomNeo4jActuator(final Neo4jActuator neo4jActuator) {
        this.neo4jActuator = neo4jActuator;
    }

    @Override
    public Result<List<Map<String, Value>>> read(Map<String, Object> params) {
        return neo4jActuator.read(params);
    }

    @Override
    public Result<List<Map<String, Value>>> write(Map<String, Object> params) {
        return neo4jActuator.write(params);
    }
}
