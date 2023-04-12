package cn.widdo.autoconfigure.neo4j;

import cn.widdo.assistant.utils.BeanUtils;
import cn.widdo.starter.neo4j.constant.Neo4jConstants;
import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;
import cn.widdo.starter.neo4j.utils.Neo4jUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Neo4j helper.
 *
 * @author XYL
 * @date 2023/03/02 16:55
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public interface Neo4jHelper extends Runner {

    /**
     * query.
     *
     * @param params params
     * @return cn.widdo.starter.neo4j.entity.result.Result<java.util.List < java.util.Map < java.lang.String, cn.widdo.starter.neo4j.entity.Value>>>
     * @author XYL
     * @date 2023/03/02 17:21:30
     */
    default Result<List<Map<String, Value>>> query(Map<String, Object> params) {

        String cqlStr = params.get(Neo4jConstants.PARAM_CYPHER_QL).toString();

        Map<String, Object> map = Optional.ofNullable(params.get(Neo4jConstants.PARAM_MAP))
                .map(BeanUtils::<Map<String, Object>>cast)
                .orElse(null);

        //打印信息
        Neo4jUtil.printCQL(cqlStr);

        //执行cql
        return (Result) Neo4jUtil.toExecute(this.driver(), Neo4jConstants.RUNNER_READ, cqlStr, map, (cql, mp, tx) -> {

            org.neo4j.driver.Result rs = mp == null ? tx.run(cql) : tx.run(cql, mp);
            return Neo4jUtil.packResult(rs);
        });
    }

    /**
     * execute.
     *
     * @param params params
     * @return cn.widdo.starter.neo4j.entity.result.Result<java.util.List < java.util.Map < java.lang.String, cn.widdo.starter.neo4j.entity.Value>>>
     * @author XYL
     * @date 2023/03/02 17:21:39
     */
    default Result<List<Map<String, Value>>> execute(Map<String, Object> params) {

        String cqlStr = params.get(Neo4jConstants.PARAM_CYPHER_QL).toString();

        Map<String, Object> map = Optional.ofNullable(params.get(Neo4jConstants.PARAM_MAP))
                .map(BeanUtils::<Map<String, Object>>cast)
                .orElse(null);

        //打印信息
        Neo4jUtil.printCQL(cqlStr);

        //执行cql
        return (Result) Neo4jUtil.toExecute(this.driver(), Neo4jConstants.RUNNER_WRITE, cqlStr, map, (cql, mp, tx) -> {

            org.neo4j.driver.Result rs = mp == null ? tx.run(cql) : tx.run(cql, mp);
            return Neo4jUtil.packResult(rs);
        });
    }
}
