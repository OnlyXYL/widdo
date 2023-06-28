package cn.widdo.study.neo4j.service;

import cn.widdo.assistant.result.WiddoResult;

import java.util.HashMap;
import java.util.Map;

/**
 * neo4j jdbc service.
 *
 * @author XYL
 * @date 2022/07/15 0:02
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public interface Neo4jJdbcService {

    /**
     * 封裝參數.
     *
     * @param cypher cypher
     * @param map    map
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author XYL
     * @date 2023/03/01 13:33:47
     */
    default Map<String, Object> cypherWithParams(String cypher, Map<String, Object> map) {
        Map<String, Object> cypherParam = new HashMap<>(2);
        cypherParam.put("cypherQL", cypher);
        cypherParam.put("map", map);
        return cypherParam;
    }

    /**
     * 查詢.
     *
     * @param params param typed map
     * @return cn.widdo.assistant.result.WiddoResult
     * @author XYL
     * @className widdo.neo4j.service.Neo4jJdbcService
     * @date 2022/07/15 1:05
     **/
    WiddoResult read(Map<String, Object> params);

    /**
     * neo4j write.
     *
     * @param params params
     * @return cn.widdo.assistant.result.WiddoResult
     * @author XYL
     * @date 2023/03/01 12:02:14
     */
    WiddoResult write(Map<String, Object> params);

    /**
     * 隐性事务.
     *
     * @param params
     *
     * @author XYL
     * @date 2023/06/28 11:49:43
     * @return cn.widdo.assistant.result.WiddoResult
     */
    WiddoResult run(Map<String, Object> params);

    /**
     * 寫.
     *
     * @param params param typed map
     * @return cn.widdo.assistant.result.WiddoResult
     * @author XYL
     * @date 2022/12/02 21:05:22
     **/
    WiddoResult writeTriples(Map<String, Object> params);

    /**
     * delete.
     *
     * @param params param typed map
     * @return cn.widdo.assistant.result.WiddoResult
     * @author XYL
     * @date 2023/02/27 18:42:25
     */
    WiddoResult delete(Map<String, Object> params);

}
