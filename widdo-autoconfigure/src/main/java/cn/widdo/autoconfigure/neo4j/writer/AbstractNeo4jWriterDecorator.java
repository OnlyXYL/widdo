package cn.widdo.autoconfigure.neo4j.writer;

/**
 * AbstractNeo4jWriterDecorator,you can have yourself Neo4jWriter by extends it.
 * <p>
 * like this:{@link CustomNeo4jWriter}
 *
 * @param <T>
 * @param <R>
 * @author XYL
 * @date 2022/10/15 1:02
 * @since 263.1.1.0
 */
public abstract class AbstractNeo4jWriterDecorator<T, R> extends AbstractNeo4jWriter<T, R> {

	/**
	 * {@link Neo4jWriter}.
	 */
	protected Neo4jWriter<T, R> neo4jWriter;

}
