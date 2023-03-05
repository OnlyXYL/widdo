package cn.widdo.autoconfigure.neo4j.writer;

import cn.widdo.starter.neo4j.validator.ParamsValidator;
import org.neo4j.driver.Driver;

/**
 * AbstractNeo4jWriter.
 *
 * @param <T> t
 * @param <R> r
 * @author XYL
 * @date 2023/03/02 17:01
 * @since 263.1.3.0
 */
public abstract class AbstractNeo4jWriter<T, R> extends ParamsValidator implements Neo4jWriter<T, R> {

    /**
     * driver.
     */
    protected Driver driver;

    @Override
    public Driver driver() {
        return driver;
    }
}
