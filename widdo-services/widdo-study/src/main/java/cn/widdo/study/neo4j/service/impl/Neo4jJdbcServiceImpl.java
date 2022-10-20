package cn.widdo.study.neo4j.service.impl;

import cn.widdo.assistant.entity.result.WebResult;
import cn.widdo.autoconfigure.neo4j.actuator.Neo4jActuator;
import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;
import cn.widdo.study.neo4j.service.Neo4jJdbcService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * neo4j jdbc service 实现类
 *
 * @author XYL
 * @version 263.1.0.0
 * @date 2022/07/15 0:02
 */
@Service
public class Neo4jJdbcServiceImpl implements Neo4jJdbcService {

    @Resource
    private Neo4jActuator neo4jActuator;

    @Override
    public WebResult query(Map<String, Object> params) {

        final String label = params.get("label").toString();

        final String cypher = String.format("match (s:%s{name:$name}) return s", label);

        Map<String, Object> cypherParam = new HashMap<>(2);
        cypherParam.put("cypherQL", cypher);

        params.remove("label");
        cypherParam.put("params", params);

        final Result<List<Map<String, Value>>> result = neo4jActuator.read(cypherParam);

        return WebResult.success(result);
    }
}
