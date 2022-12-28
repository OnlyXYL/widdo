package cn.widdo.autoconfigure.neo4j.writer;

import cn.widdo.starter.neo4j.entity.result.Result;

import java.util.Map;

/**
 * CustomNeo4jWriter.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/12/06 11:45
 */
public class CustomNeo4jWriter extends AbstractNeo4jWriterDecorator<Map<String, Object>, Result<?>> {

    /**
     * constructor has one param called {@link Neo4jWriter}.
     *
     * @param neo4jWriter neo4jWriter
     */
    public CustomNeo4jWriter(final Neo4jWriter neo4jWriter) {
        this.neo4jWriter = neo4jWriter;
    }

    @Override
    public Result<?> write(Map<String, Object> params) {
        return neo4jWriter.write(params);
    }
}
