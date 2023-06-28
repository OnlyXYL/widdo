package cn.widdo.autoconfigure.neo4j.actuator;

/**
 * AbstractNeo4jActuator.
 *
 * @param <R>
 * @param <T>
 * @author XYL
 * @date 2022/12/03 18:59
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public abstract class AbstractNeo4jActuatorDecorator<T, R> extends AbstractNeo4jActuator<T, R> {

    /**
     * {@link Neo4jActuator}.
     */
    protected Neo4jActuator<T, R> neo4jActuator;
}
