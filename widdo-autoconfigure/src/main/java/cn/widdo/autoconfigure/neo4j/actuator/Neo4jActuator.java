package cn.widdo.autoconfigure.neo4j.actuator;

import cn.widdo.autoconfigure.neo4j.reader.Neo4jReader;
import cn.widdo.autoconfigure.neo4j.writer.Neo4jWriter;

/**
 * neo4j Actuator.
 *
 * @param <T> params of method
 * @param <R> output of neo4j
 * @author XYL
 * @date 2022/10/18 11:37
 * @since 263.1.1.0
 */
public interface Neo4jActuator<T, R> extends Neo4jReader<T, R>, Neo4jWriter<T, R> {
}
