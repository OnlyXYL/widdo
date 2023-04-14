package cn.widdo.autoconfigure.neo4j.reader;

import cn.widdo.starter.neo4j.constant.Neo4jConstants;
import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;
import org.neo4j.driver.Driver;

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
 * @date 2022/10/14 16:33
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public class DefaultNeo4jReader extends AbstractNeo4jReader<Map<String, Object>, Result<List<Map<String, Value>>>> {

    /**
     * constructor has no param,at the same time, if you create instance by this constructor,
     * it will throw exception typed {@link UnsupportedOperationException}.
     */
    protected DefaultNeo4jReader() {
        throw new UnsupportedOperationException();
    }

    /**
     * constructor has one param called {@link Driver}.
     *
     * @param driver driver
     */
    private DefaultNeo4jReader(final Driver driver) {
        this.driver = driver;
    }

    @Override
    public Result<List<Map<String, Value>>> read(Map<String, Object> params) {
        return this.validateAndRun(params, this::query, Neo4jConstants.PARAM_CYPHER_QL);
    }
}
