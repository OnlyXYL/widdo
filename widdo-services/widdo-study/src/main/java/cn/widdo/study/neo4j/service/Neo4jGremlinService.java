package cn.widdo.study.neo4j.service;

import cn.widdo.assistant.result.WiddoResult;

import java.util.Map;

/**
 * neo4j gremlin service.
 *
 * @author XYL
 * @date 2022/07/15 1:37
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public interface Neo4jGremlinService {

	/**
	 * gremlin query.
	 * @param params params
	 * @return cn.widdo.assistant.result.WiddoResult
	 * @author XYL
	 * @className widdo.neo4j.service.Neo4jJdbcService
	 * @date 2022/07/15 1:36
	 **/
	WiddoResult query(Map<String, Object> params);

}
