package cn.widdo.autoconfigure.neo4j.helper;

import cn.widdo.starter.neo4j.WiddoStarterNeo4j;
import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;
import cn.widdo.starter.neo4j.utils.Neo4jUtil;
import cn.widdo.starter.neo4j.utils.NetUtil;
import org.neo4j.driver.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * neo4j pre read write helper
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/17 17:48
 */
@SuppressWarnings({"AlibabaClassNamingShouldBeCamel", "AlibabaLowerCamelCaseVariableNaming"})
@Component
@ConditionalOnClass(WiddoStarterNeo4j.class)
public class Neo4jPreRWHelper {

    private static final Logger log = LoggerFactory.getLogger(Neo4jPreRWHelper.class);

    @Resource
    private Driver driver;

    @PostConstruct
    public void postNeo4jPreRWHelper() {
        log.debug("[Widdo] |- Starter [Neo4j Starter] |- Neo4jPreRWHelper.");
    }

    public Result<List<Map<String, Value>>> query(String cypherQL) {
        Map<String, Object> paras = new HashMap<>(2);
        paras.put("cypherQL", cypherQL);
        return this.query(paras);
    }

    public Result<List<Map<String, Value>>> query(String cypherQL, Map<String, Object> params) {
        Map<String, Object> paras = new HashMap<>(3);
        paras.put("cypherQL", cypherQL);
        paras.put("map", params);
        return this.query(paras);
    }

    public Result<List<Map<String, Value>>> execute(String cypherQL, Map<String, Object> params) {
        Map<String, Object> paras = new HashMap<>(3);
        paras.put("cypherQL", cypherQL);
        paras.put("map", params);
        return this.execute(paras);
    }

    /**
     * query cypher
     *
     * @param param 参数
     * @return cn.widdo.starter.neo4j.entity.result.Result<java.util.List < java.util.Map < java.lang.String, cn.widdo.starter.neo4j.entity.Value>>>
     * @author XYL
     * @since 22:26 2021/4/1 0001
     **/
    public Result<List<Map<String, Value>>> query(Map<String, Object> param) {
        String cqlStr = param.get("cypherQL").toString();

        Map<String, Object> map = Optional.ofNullable(param.get("map")).map(o -> (Map<String, Object>) o).orElse(null);
        log.debug("---client ip:" + NetUtil.getRealIp() + " cql:" + cqlStr.substring(0, Math.min(cqlStr.length(), 100)) + " ...");

        //打印信息
        Neo4jUtil.printCypherQL(cqlStr);

        //执行cql
        return (Result) Neo4jUtil.toExecute(driver, "query", cqlStr, map, (cql, mp, tx) -> {

            org.neo4j.driver.Result rs = mp == null ? tx.run(cql) : tx.run(cql, mp);
            return Neo4jUtil.packResult(rs);
        });
    }

    /**
     * execute cypher
     *
     * @param param 参数
     * @return cn.widdo.starter.neo4j.entity.result.Result<java.util.List < java.util.Map < java.lang.String, cn.widdo.starter.neo4j.entity.Value>>>
     * @author XYL
     * @since 22:26 2021/4/1 0001
     **/
    private Result<List<Map<String, Value>>> execute(Map<String, Object> param) {
        String cqlStr = param.get("cypherQL").toString();

        Map<String, Object> map = Optional.ofNullable(param.get("map")).map(o -> (Map<String, Object>) o).orElse(null);

        //打印信息
        Neo4jUtil.printCypherQL(cqlStr);

        //执行cql
        return (Result) Neo4jUtil.toExecute(driver, "execute", cqlStr, map, (cql, mp, tx) -> {

            org.neo4j.driver.Result rs = mp == null ? tx.run(cql) : tx.run(cql, mp);
            return Neo4jUtil.packResult(rs);
        });
    }
}
