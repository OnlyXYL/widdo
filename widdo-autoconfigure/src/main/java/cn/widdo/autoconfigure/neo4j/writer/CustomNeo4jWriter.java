package cn.widdo.autoconfigure.neo4j.writer;

import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;

import java.util.List;
import java.util.Map;

/**
 * CustomNeo4jWriter.
 *
 * @author XYL
 * @date 2022/12/06 11:45
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public final class CustomNeo4jWriter
		extends AbstractNeo4jWriterDecorator<Map<String, Object>, Result<List<Map<String, Value>>>> {

	/**
	 * constructor has one param called {@link Neo4jWriter}.
	 * @param neo4jWriter neo4jWriter
	 */
	private CustomNeo4jWriter(final Neo4jWriter neo4jWriter) {
		this.neo4jWriter = neo4jWriter;
	}

	@Override
	public Result<List<Map<String, Value>>> write(Map<String, Object> params) {
		return neo4jWriter.write(params);
	}

}
