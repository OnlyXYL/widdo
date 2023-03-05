package cn.widdo.autoconfigure.neo4j.actuator;

import org.neo4j.driver.Driver;

/**
 * Neo4j AbstractNeo4jActuator.
 *
 * @param <T> t
 * @param <R> r
 * @author XYL
 * @date 2023/03/03 11:17
 * @since 263.1.3.0
 */
public abstract class AbstractNeo4jActuator<T, R> implements Neo4jActuator<T, R> {

    /**
     * driver.
     */
    protected Driver driver;

    @Override
    public Driver driver() {
        return driver;
    }
}
