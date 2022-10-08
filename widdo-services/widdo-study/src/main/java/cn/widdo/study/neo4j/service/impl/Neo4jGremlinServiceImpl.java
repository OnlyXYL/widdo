package cn.widdo.study.neo4j.service.impl;

import cn.widdo.assistant.entity.result.JsonResult;
import cn.widdo.study.neo4j.service.Neo4jGremlinService;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * @author XYL
 * @version 1.0
 * @date 2022/07/15 1:37
 */
@Service
public class Neo4jGremlinServiceImpl implements Neo4jGremlinService {

    @Override
    public JsonResult query(Map<String, Object> params) {

        final GraphTraversalSource g = new GraphTraversalSource(null);

        /**
         * <p>GraphTraversalSource g = traversal().withRemote(DriverRemoteConnection.using("localhost",8182,"g"));</p>
         */
        final Object next = g.V().hasLabel("label").select("name").next();

        return null;
    }
}
