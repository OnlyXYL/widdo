package cn.widdo.study.neo4j.service.impl;

import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.assistant.utils.BeanUtils;
import cn.widdo.autoconfigure.neo4j.actuator.Neo4jActuator;
import cn.widdo.autoconfigure.result.WiddoResultInterface;
import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;
import cn.widdo.study.neo4j.service.Neo4jJdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * neo4j jdbc service 实现类.
 *
 * @author XYL
 * @date 2022/07/15 0:02
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
@Service
public class Neo4jJdbcServiceImpl implements Neo4jJdbcService {

    /**
     * neo4j执行器.
     * 使用构造方法注入，如果只有一个构造方法，那么 @Autowired注解可以省略；如果有多个构造方法，
     * 需要添加@Autowired注解来明确指定到底使用哪个构造方法
     */
    private final Neo4jActuator<Map<String, Object>, Result<List<Map<String, Value>>>> neo4jActuator;

    @Autowired
    public Neo4jJdbcServiceImpl(final Neo4jActuator neo4jActuator) {
        this.neo4jActuator = neo4jActuator;
    }

    @Override
    public WiddoResult read(Map<String, Object> params) {

        final Map<String, Object> map = BeanUtils.cast(params.get("params"));

        final String cypher = params.get("cypher").toString();

        //封裝參數
        Map<String, Object> cypherParam = cypherWithParams(cypher, map);

        final Result<List<Map<String, Value>>> result = neo4jActuator.read(cypherParam);

        return WiddoResultInterface.NEO4j.ALL.wrapper(result);
    }

    @Override
    public WiddoResult write(Map<String, Object> params) {

        final Map<String, Object> map = BeanUtils.cast(params.get("params"));

        final String cypher = params.get("cypher").toString();

        //封裝參數
        Map<String, Object> cypherParam = cypherWithParams(cypher, map);

        final Result<List<Map<String, Value>>> result = neo4jActuator.write(cypherParam);

        return WiddoResultInterface.NEO4j.ALL.wrapper(result);
    }

    @Override
    public WiddoResult run(Map<String, Object> params) {
        final Map<String, Object> map = BeanUtils.cast(params.get("params"));

        final String cypher = params.get("cypher").toString();

        //封裝參數
        Map<String, Object> cypherParam = cypherWithParams(cypher, map);

        final Result<List<Map<String, Value>>> result = neo4jActuator.run(cypherParam);

        return WiddoResultInterface.NEO4j.ALL.wrapper(result);
    }

    @Override
    public WiddoResult writeTriples(Map<String, Object> params) {

        /*
          you can replace the cypher with procedure
          <code>
             call widdo.triple.write()
          </code>
          {@see cn.widdo.starter.neo4j.plugins.procedures.TripleProcedure}
         */
        String cypher = """
                UNWIND $triples AS triple \
                CALL apoc.merge.node(triple.start.labels, triple.start.match,triple.start.onCreate,triple.start.onMatch) YIELD node as startNode \
                CALL apoc.merge.node(triple.end.labels, triple.end.match,triple.end.onCreate,triple.end.onMatch) YIELD node as endNode \
                CALL apoc.merge.relationship(startNode, triple.relation.relType, triple.relation.match, triple.relation.onCreate, endNode, triple.relation.onMatch) YIELD rel \
                RETURN collect(id(rel)) as relationshipIds""";

        //封裝參數
        Map<String, Object> cypherParam = cypherWithParams(cypher, params);

        final Result<List<Map<String, Value>>> write = neo4jActuator.write(cypherParam);

        return WiddoResultInterface.NEO4j.ALL.wrapper(write);
    }

    @Override
    public WiddoResult delete(Map<String, Object> params) {

        final String label = Optional.ofNullable(params.get("label")).map(s -> {
            final String string = s.toString();
            if (StringUtils.hasLength(string)) {
                return ":" + s;
            }
            return "";
        }).orElse("");

        final List<Long> ids = Optional.ofNullable(params.get("ids")).map(BeanUtils::<List<Long>>cast).orElse(null);
        String matchCypher;

        Map<String, Object> map = null;

        if (ids == null || ids.size() == 0) {
            matchCypher = String.format(" MATCH (n%s)-[r]-()\n", label);
        } else {
            matchCypher = String.format(" MATCH (n%s)-[r]-() where id(n) in $ids\n", label);

            map = new HashMap<>(1);
            map.put("ids", ids);
        }

        String deleteRelCypher = "CALL {WITH r DELETE r} IN TRANSACTIONS OF 10000 ROWS\n";

        String deleteNodeCypher = "WITH distinct n CALL {WITH n DELETE n} IN TRANSACTIONS OF 10000 ROWS";

        final String cypher = String.format("%s %s %s", matchCypher, deleteRelCypher, deleteNodeCypher);

        //封裝參數
        Map<String, Object> cypherParam = cypherWithParams(cypher, map);

        final Result<List<Map<String, Value>>> result = neo4jActuator.run(cypherParam);

        return WiddoResultInterface.NEO4j.ALL.wrapper(result);
    }
}
