package cn.widdo.autoconfigure.neo4j.reader;

import cn.widdo.starter.neo4j.validator.ParamsValidator;
import org.neo4j.driver.Driver;

/**
 * AbstractNeo4jReader.
 *
 * @param <T> t
 * @param <R> r
 * @author XYL
 * @date 2023/03/03 0:00
 * @since 302.1.0.0
 */
public abstract class AbstractNeo4jReader<T, R> extends ParamsValidator implements Neo4jReader<T, R> {

    /**
     * neo4j driver {@link Driver}.
     */
    protected Driver driver;

    @Override
    public Driver driver() {
        return driver;
    }
}
