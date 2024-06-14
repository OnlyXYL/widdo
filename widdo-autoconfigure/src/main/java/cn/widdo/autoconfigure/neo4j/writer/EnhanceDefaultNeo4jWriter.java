package cn.widdo.autoconfigure.neo4j.writer;

import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;

import java.util.List;
import java.util.Map;

/**
 * EnhanceDefaultNeo4jWriter. enhance {@link DefaultNeo4jWriter} which is the default
 * writer of neo4j
 *
 * @author XYL
 * @date 2023/03/01 17:38
 * @since 263.1.3.0
 */
@SuppressWarnings("ALL")
public class EnhanceDefaultNeo4jWriter
		extends AbstractNeo4jWriterDecorator<Map<String, Object>, Result<List<Map<String, Value>>>> {

	/**
	 * constructor has one param typed {@link Neo4jWriter}.
	 * @param neo4jWriter neo4jWriter
	 */
	public EnhanceDefaultNeo4jWriter(final Neo4jWriter neo4jWriter) {
		this.driver = neo4jWriter.driver();
		this.neo4jWriter = neo4jWriter;
	}

	@Override
	public Result<List<Map<String, Value>>> write(Map<String, Object> params) {
		return neo4jWriter.write(params);
	}

}
