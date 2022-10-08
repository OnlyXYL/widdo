package cn.widdo.study.neo4j.utils;

import cn.widdo.graph.entity.neo4j.Value;
import cn.widdo.graph.entity.neo4j.result.Result;
import cn.widdo.study.neo4j.service.helper.Neo4jServiceHelper;
import cn.widdo.study.neo4j.service.helper.NetUtils;
import org.neo4j.driver.Driver;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author XYL
 * @version 1.0
 * @since 2021/4/1 0001 21:26
 */
@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
@Component
public class Neo4jUtils {

    @Resource
    private Neo4jServiceHelper neo4jServiceHelper;

    @Resource
    private Driver driver;

    public Result<List<Map<String, Value>>> query(String cypherQL) {
        Map<String, Object> paras = new HashMap<>(2);
        paras.put("cypherQL", cypherQL);
        Result<List<Map<String, Value>>> result = this.execute(paras);
        return result;
    }

    public Result<List<Map<String, Value>>> query(String cypherQL, Map<String, Object> params) {
        Map<String, Object> paras = new HashMap<>(3);
        paras.put("cypherQL", cypherQL);
        paras.put("map", params);
        Result<List<Map<String, Value>>> result = this.query(paras);
        return result;
    }

    public Result<List<Map<String, Value>>> execute(String cypherQL, Map<String, Object> params) {
        Map<String, Object> paras = new HashMap<>(3);
        paras.put("cypherQL", cypherQL);
        paras.put("map", params);
        Result<List<Map<String, Value>>> result = this.execute(paras);
        return result;
    }

    /**
     * query cypher
     *
     * @param param
     * @return top.wikl.neo4j.entity.result.Result<java.util.List < java.util.Map < java.lang.String, top.wikl.neo4j.entity.Value>>>
     * @author XYL
     * @since 22:26 2021/4/1 0001
     **/
    private Result<List<Map<String, Value>>> query(Map<String, Object> param) {
        Object cqlStrObj = param.get("cypherQL");
        String cqlStr = Optional.ofNullable(cqlStrObj).map(Object::toString).orElse(null);

        Object mapObj = param.get("map");
        Map<String, Object> map = Optional.ofNullable(mapObj).map(o -> (Map<String, Object>) o).orElse(null);
        System.out.println("---client ip:" + NetUtils.getRealIp() + " cql:" + cqlStr.substring(0, cqlStr.length() >= 100 ? 100 : cqlStr.length()) + " ...");
        //执行cql
        Result rsExecute = neo4jServiceHelper.toExecute(driver, "query", cqlStr, map, (cql, mp, tx) -> {

            org.neo4j.driver.Result rs = mp == null ? tx.run(cql) : tx.run(cql, mp);
            return neo4jServiceHelper.packResult(rs);
        });
        return rsExecute;
    }

    /**
     * execute cypher
     *
     * @param param
     * @return top.wikl.neo4j.entity.result.Result<java.util.List < java.util.Map < java.lang.String, top.wikl.neo4j.entity.Value>>>
     * @author XYL
     * @since 22:26 2021/4/1 0001
     **/
    private Result<List<Map<String, Value>>> execute(Map<String, Object> param) {
        Object cqlStrObj = param.get("cypherQL");
        String cqlStr = Optional.ofNullable(cqlStrObj).map(Object::toString).orElse(null);

        Object mapObj = param.get("map");
        Map<String, Object> map = Optional.ofNullable(mapObj).map(o -> (Map<String, Object>) o).orElse(null);
        System.out.println("---client ip:" + NetUtils.getRealIp() + " cql:" + cqlStr.substring(0, cqlStr.length() >= 100 ? 100 : cqlStr.length()) + " ...");
        //执行cql
        Result rsExecute = neo4jServiceHelper.toExecute(driver, "execute", cqlStr, map, (cql, mp, tx) -> {

            org.neo4j.driver.Result rs = mp == null ? tx.run(cql) : tx.run(cql, mp);
            return neo4jServiceHelper.packResult(rs);
        });
        return rsExecute;
    }

}
