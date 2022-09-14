package cn.widdo.study.neo4j.service.impl;

import cn.widdo.assistant.entity.result.JsonResult;
import cn.widdo.graph.entity.neo4j.Value;
import cn.widdo.graph.entity.neo4j.result.Result;
import cn.widdo.study.neo4j.service.Neo4jJdbcService;
import cn.widdo.study.neo4j.utils.Neo4jUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * neo4j jdbc service 实现类
 *
 * @author XYL
 * @version 1.0
 * @date 2022/07/15 0:02
 */
@Service
public class Neo4jJdbcServiceImpl implements Neo4jJdbcService {

    @Resource
    private Neo4jUtils neo4jUtils;

    @Override
    public JsonResult query(Map<String, Object> params) {

        final String label = params.get("label").toString();

        final String name = params.get("name").toString();

        final String cypher = String.format("match (s:%s{name:$name}) return s", label);

        final HashMap<String, Object> cypherParam = new HashMap<>(1);
        cypherParam.put("name", name);

        final Result<List<Map<String, Value>>> query = neo4jUtils.query(cypher, params);

        return JsonResult.success(query);
    }
}
