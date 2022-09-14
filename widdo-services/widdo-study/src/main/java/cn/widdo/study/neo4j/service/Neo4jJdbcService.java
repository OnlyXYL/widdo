package cn.widdo.study.neo4j.service;

import cn.widdo.assistant.entity.result.JsonResult;

import java.util.Map;

/**
 * neo4j jdbc service
 *
 * @author XYL
 * @version 1.0
 * @date 2022/07/15 0:02
 */
public interface Neo4jJdbcService {

    /**
     * 查詢
     *
     * @param params
     * @return cn.widdo.entity.JsonResult
     * @throws
     * @author XYL
     * @className widdo.neo4j.service.Neo4jJdbcService
     * @date 2022/07/15 1:05
     **/
    JsonResult query(Map<String, Object> params);
}
