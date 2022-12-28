package cn.widdo.study.neo4j.service;

import cn.widdo.assistant.entity.result.WebResult;

import java.util.Map;

/**
 * neo4j jdbc service.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/07/15 0:02
 */
public interface Neo4jJdbcService {

    /**
     * 查詢.
     *
     * @param params
     * @return cn.widdo.assistant.entity.result.WebResult
     * @author XYL
     * @className widdo.neo4j.service.Neo4jJdbcService
     * @date 2022/07/15 1:05
     **/
    WebResult query(Map<String, Object> params);

    /**
     * 寫.
     *
     * @param params
     * @return cn.widdo.assistant.entity.result.WebResult
     * @author XYL
     * @date 2022/12/02 21:05:22
     **/
    WebResult write(Map<String, Object> params);
}
