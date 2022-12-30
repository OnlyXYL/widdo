package cn.widdo.autoconfigure.neo4j.actuator;

import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;

import java.util.List;
import java.util.Map;

/**
 * CustomNeo4jActuator.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/12/23 11:29
 */
public class CustomNeo4jActuator extends AbstractNeo4jActuator<Map<String, Object>, Result<List<Map<String, Value>>>, WiddoResult> {

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

    @Override
    public WiddoResult wrapper(Result<List<Map<String, Value>>> result) {
        return neo4jActuator.wrapper(result);
    }
}
