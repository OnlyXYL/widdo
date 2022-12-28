package cn.widdo.autoconfigure.neo4j.reader;

import cn.widdo.starter.neo4j.validator.ParamsValidator;

/**
 * a abstract Neo4jReader Decorator. you can have yourself implement by extends it.
 * <p>
 * like this:{@link CustomNeo4jReader}
 *
 * @param <T>
 * @param <R>
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/14 17:51
 */
public abstract class AbstractNeo4jReaderDecorator<T, R> extends ParamsValidator implements Neo4jReader<T, R> {

    /**
     * {@link Neo4jReader}.
     */
    protected Neo4jReader<T, R> neo4jReader;
}
