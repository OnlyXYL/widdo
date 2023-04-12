package cn.widdo.autoconfigure.neo4j.reader;

/**
 * a abstract Neo4jReader Decorator. you can have yourself implement by extends it.
 * <p>
 * like this:{@link CustomNeo4jReader}
 *
 * @param <T>
 * @param <R>
 * @author XYL
 * @date 2022/10/14 17:51
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public abstract class AbstractNeo4jReaderDecorator<T, R> extends AbstractNeo4jReader<T, R> {

    /**
     * {@link Neo4jReader}.
     */
    protected Neo4jReader<T, R> neo4jReader;
}
