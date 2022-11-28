package cn.widdo.autoconfigure.neo4j.reader;

import cn.widdo.starter.neo4j.entity.result.Result;
import cn.widdo.starter.neo4j.validator.ParamsValidator;

import java.util.Map;

/**
 * neo4j 读取数据装饰器.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/14 17:51
 */
public abstract class AbstractNeo4jReaderDecorator extends ParamsValidator implements Neo4jReader<Map<String, Object>, Result<?>> {

    /**
     * {@link Neo4jReader}.
     */
    private Neo4jReader<Map<String, Object>, Result<?>> neo4jReader;

    /**
     * get {@link Neo4jReader}.
     *
     * @return a Neo4jReader instance.
     */
    protected Neo4jReader<Map<String, Object>, Result<?>> getNeoReader() {
        return neo4jReader;
    }

    /**
     * constructor has one params called {@link Neo4jReader}.
     *
     * @param neo4jReader
     */
    public AbstractNeo4jReaderDecorator(final Neo4jReader neo4jReader) {
        this.neo4jReader = neo4jReader;
    }

    @Override
    public Result<?> query(Map<String, Object> params) {
        return neo4jReader.query(params);
    }
}
