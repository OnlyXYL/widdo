package cn.widdo.autoconfigure.neo4j.reader;

import cn.widdo.starter.neo4j.entity.result.Result;
import cn.widdo.starter.neo4j.validator.ParamsValidator;

import java.util.Map;

/**
 * neo4j 读取数据装饰器
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/14 17:51
 */
public abstract class AbstractNeo4jReaderDecorator extends ParamsValidator implements Neo4jReader<Map<String, Object>, Result<?>> {

    protected Neo4jReader<Map<String, Object>, Result<?>> neo4jReader;

    public AbstractNeo4jReaderDecorator(Neo4jReader neo4jReader) {
        this.neo4jReader = neo4jReader;
    }

    @Override
    public Result<?> query(Map<String, Object> params) {
        return neo4jReader.query(params);
    }
}
