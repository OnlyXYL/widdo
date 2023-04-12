package cn.widdo.autoconfigure.neo4j.writer;

import cn.widdo.starter.neo4j.constant.Neo4jConstants;
import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;
import org.neo4j.driver.Driver;

import java.util.List;
import java.util.Map;

/**
 * 默认的写方法.
 *
 * @author XYL
 * @date 2022/10/14 16:37
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public class DefaultNeo4jWriter extends AbstractNeo4jWriter<Map<String, Object>, Result<List<Map<String, Value>>>> {

    /**
     * constructor has no param,at the same time, if you create instance by this constructor,
     * it will throw exception typed {@link UnsupportedOperationException}.
     */
    protected DefaultNeo4jWriter() {
        throw new UnsupportedOperationException();
    }

    /**
     * constructor has one param called {@link Driver}.
     *
     * @param driver {@link Driver}
     */
    private DefaultNeo4jWriter(final Driver driver) {
        this.driver = driver;
    }

    @Override
    public Result<List<Map<String, Value>>> write(Map<String, Object> params) {
        return this.validateAndRun(params, this::execute, Neo4jConstants.PARAM_CYPHER_QL);
    }

}
