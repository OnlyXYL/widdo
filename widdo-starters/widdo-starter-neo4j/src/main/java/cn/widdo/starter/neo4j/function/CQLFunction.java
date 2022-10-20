package cn.widdo.starter.neo4j.function;

import cn.widdo.starter.neo4j.entity.result.Result;
import org.neo4j.driver.Transaction;

import java.util.Map;

/**
 * cql回调执行
 *
 * @author XYL
 * @version 263.1.0.0
 * @date 2022/07/15 0:02
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
@FunctionalInterface
public interface CQLFunction {

    /**
     * sql执行
     *
     * @param cql cql
     * @param map 参数
     * @param tx  事务
     * @return cn.widdo.starter.neo4j.entity.result.Result
     * @author XYL
     * @className cn.widdo.autoconfigure.neo4j.function.CQLFunction
     * @date 2022/07/15 0:27
     **/
    Result<?> execute(String cql, Map<String, Object> map, Transaction tx);
}
