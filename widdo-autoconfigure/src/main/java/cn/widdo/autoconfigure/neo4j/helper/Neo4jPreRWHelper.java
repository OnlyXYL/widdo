package cn.widdo.autoconfigure.neo4j.helper;

import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;
import cn.widdo.starter.neo4j.utils.Neo4jUtil;
import cn.widdo.starter.neo4j.utils.NetUtil;
import org.neo4j.driver.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * neo4j pre read write helper.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/17 17:48
 */
@SuppressWarnings({"AlibabaClassNamingShouldBeCamel", "AlibabaLowerCamelCaseVariableNaming"})
public class Neo4jPreRWHelper {

    private static final Logger log = LoggerFactory.getLogger(Neo4jPreRWHelper.class);

    /**
     * driver.
     */
    private Driver driver;

    /**
     * constructor has one params called {@link Driver}.
     *
     * @param driver    the driver instance of neo4j
     */
    public Neo4jPreRWHelper(final Driver driver) {
        this.driver = driver;
    }

    @PostConstruct
    public final void postNeo4jPreRWHelper() {
        log.debug("[Widdo] |- Starter [Neo4j Starter] |- Neo4jPreRWHelper.");
    }

    /**
     * 执行neo4j writer.
     *
     * @param cypherQL  the neo4j cypher
     * @return a result type of {@link Result}
     */
    public Result<List<Map<String, Value>>> query(String cypherQL) {
        Map<String, Object> paras = new HashMap<>(2);
        paras.put("cypherQL", cypherQL);
        return this.query(paras);
    }

    /**
     * 执行neo4j reader.
     *
     * @param cypherQL  the cypher of neo4j
     * @param params    the params of neo4j cypher
     * @return a result type of {@link Result}
     */
    public Result<List<Map<String, Value>>> query(String cypherQL, Map<String, Object> params) {
        Map<String, Object> paras = new HashMap<>(3);
        paras.put("cypherQL", cypherQL);
        paras.put("map", params);
        return this.query(paras);
    }

    /**
     * 执行neo4j writer.
     *
     * @param cypherQL  the cypher of neo4j
     * @param params    the params of neo4j cypher
     * @return a result type of {@link Result}
     */
    public Result<List<Map<String, Value>>> execute(String cypherQL, Map<String, Object> params) {
        Map<String, Object> paras = new HashMap<>(3);
        paras.put("cypherQL", cypherQL);
        paras.put("map", params);
        return this.execute(paras);
    }

    /**
     * query cypher.
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
        Neo4jUtil.printCQL(cqlStr);

        //执行cql
        return (Result) Neo4jUtil.toExecute(driver, "query", cqlStr, map, (cql, mp, tx) -> {

            org.neo4j.driver.Result rs = mp == null ? tx.run(cql) : tx.run(cql, mp);
            return Neo4jUtil.packResult(rs);
        });
    }

    /**
     * execute cypher.
     *
     * @param param 参数
     * @return cn.widdo.starter.neo4j.entity.result.Result<java.util.List < java.util.Map < java.lang.String, cn.widdo.starter.neo4j.entity.Value>>>
     * @author XYL
     * @since 22:26 2021/4/1 0001
     **/
    public Result<List<Map<String, Value>>> execute(Map<String, Object> param) {
        String cqlStr = param.get("cypherQL").toString();

        Map<String, Object> map = Optional.ofNullable(param.get("map")).map(o -> (Map<String, Object>) o).orElse(null);

        //打印信息
        Neo4jUtil.printCQL(cqlStr);

        //执行cql
        return (Result) Neo4jUtil.toExecute(driver, "execute", cqlStr, map, (cql, mp, tx) -> {

            org.neo4j.driver.Result rs = mp == null ? tx.run(cql) : tx.run(cql, mp);
            return Neo4jUtil.packResult(rs);
        });
    }
}
