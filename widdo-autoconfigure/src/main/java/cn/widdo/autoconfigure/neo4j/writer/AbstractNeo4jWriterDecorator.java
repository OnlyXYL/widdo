package cn.widdo.autoconfigure.neo4j.writer;

import cn.widdo.starter.neo4j.entity.result.Result;

import java.util.Map;

/**
 * 抽象neo4j装饰器---写
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/15 1:02
 */
public abstract class AbstractNeo4jWriterDecorator implements Neo4jWriter<Map<String,Object>, Result<?>>{

    protected Neo4jWriter<Map<String,Object>,Result<?>> neo4jWriter;

    public AbstractNeo4jWriterDecorator(Neo4jWriter<Map<String, Object>, Result<?>> neo4jWriter) {
        this.neo4jWriter = neo4jWriter;
    }

    @Override
    public Result<?> write(Map<String, Object> params) {
        return neo4jWriter.write(params);
    }
}
