package cn.widdo.study.neo4j.service.impl;

import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.study.neo4j.service.Neo4jGremlinService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Neo4jGremlinServiceImpl.
 *
 * @author XYL
 * @date 2022/07/15 1:37
 * @since 263.1.1.0
 */
@Service
public class Neo4jGremlinServiceImpl implements Neo4jGremlinService {

	@Override
	public WiddoResult query(Map<String, Object> params) {

		// final GraphTraversalSource g = new GraphTraversalSource(null);

		// <p>GraphTraversalSource g =
		// traversal().withRemote(DriverRemoteConnection.using("localhost",8182,"g"));</p>
		// final Object next = g.V().hasLabel("label").select("name").next();

		return null;
	}

}
