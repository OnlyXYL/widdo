package cn.widdo.autoconfigure.neo4j.reader;

import cn.widdo.autoconfigure.neo4j.helper.Neo4jPreRWHelper;
import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;
import cn.widdo.starter.neo4j.validator.ParamsValidator;

import java.util.List;
import java.util.Map;

/**
 * 默认的 neo4j 读取器.
 * <p>
 * 默认的实现方式是 cypher
 * <p>
 * 必填参数
 * map.put("cypher","");
 * <p>
 * 可选参数
 * map.put("params","");
 * <p>
 * 需要校验参数
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/14 16:33
 */
@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
public class DefaultNeo4jReader extends ParamsValidator implements Neo4jReader<Map<String, Object>, Result<List<Map<String, Value>>>> {

    /**
     * {@link Neo4jPreRWHelper}.
     */
    private final Neo4jPreRWHelper neo4jPreRWHelper;

    /**
     * constructor has no param,at the same time, if you create instance by this constructor,
     * it will throw exception typed {@link UnsupportedOperationException}.
     */
    protected DefaultNeo4jReader() {
        throw new UnsupportedOperationException();
    }

    /**
     * constructor has one param called {@link Neo4jPreRWHelper}.
     *
     * @param neo4jPreRWHelper neo4jPreRWHelper
     */
    private DefaultNeo4jReader(final Neo4jPreRWHelper neo4jPreRWHelper) {
        this.neo4jPreRWHelper = neo4jPreRWHelper;
    }

    @Override
    public Result<List<Map<String, Value>>> query(Map<String, Object> params) {
        return this.validateAndRun(params, neo4jPreRWHelper::query, "cypherQL");
    }
}
