package cn.widdo.autoconfigure.neo4j.writer;

import cn.widdo.autoconfigure.neo4j.helper.Neo4jPreRWHelper;
import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;
import cn.widdo.starter.neo4j.validator.ParamsValidator;

import java.util.List;
import java.util.Map;

/**
 * 默认的写方法.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/14 16:37
 */
@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
public class DefaultNeo4jWriter extends ParamsValidator implements Neo4jWriter<Map<String, Object>, Result<List<Map<String, Value>>>> {

    /**
     * {@link Neo4jPreRWHelper}.
     */
    private final Neo4jPreRWHelper neo4jPreRWHelper;

    /**
     * constructor has one param called {@link Neo4jPreRWHelper}.
     *
     * @param neo4jPreRWHelper  {@link Neo4jPreRWHelper}
     */
    public DefaultNeo4jWriter(final Neo4jPreRWHelper neo4jPreRWHelper) {
        this.neo4jPreRWHelper = neo4jPreRWHelper;
    }

    @Override
    public Result<List<Map<String, Value>>> write(Map<String, Object> params) {
        return this.validateAndRun(params, neo4jPreRWHelper::execute, "cypherQL");
    }
}
