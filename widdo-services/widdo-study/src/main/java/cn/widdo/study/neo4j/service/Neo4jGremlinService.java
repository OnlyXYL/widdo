package cn.widdo.study.neo4j.service;

import cn.widdo.assistant.entity.result.JsonResult;

import java.util.Map;

/**
 * neo4j gremlin service
 *
 * @author XYL
 * @version 1.0
 * @date 2022/07/15 1:37
 */
public interface Neo4jGremlinService {


    /**
     * gremlin query
     *
     * @param params
     * @author XYL
     * @className widdo.neo4j.service.Neo4jJdbcService
     * @return cn.widdo.entity.JsonResult
     * @throws
     * @date 2022/07/15 1:36
     **/
    JsonResult query(Map<String, Object> params);
}
