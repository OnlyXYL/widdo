package cn.widdo.autoconfigure.neo4j.actuator;

/**
 * AbstractNeo4jActuator.
 *
 * @param <R>
 * @param <T>
 * @param <O>
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/12/03 18:59
 */
public abstract class AbstractNeo4jActuator<T, R, O> implements Neo4jActuator<T, R, O> {

    /**
     * {@link Neo4jActuator}.
     */
    protected Neo4jActuator<T, R, O> neo4jActuator;
}
