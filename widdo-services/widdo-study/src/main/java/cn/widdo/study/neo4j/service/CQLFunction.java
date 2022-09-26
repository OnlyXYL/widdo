package cn.widdo.study.neo4j.service;

import cn.widdo.graph.entity.neo4j.result.Result;
import org.neo4j.driver.Transaction;

import java.util.Map;

/**
 * cql回调执行
 *
 * @author XYL
 * @version 1.0
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
     * @return cn.widdo.entity.neo4j.result.Result
     * @throws
     * @author XYL
     * @className widdo.neo4j.service.CQLFunction
     * @date 2022/07/15 0:27
     **/
    Result execute(String cql, Map<String, Object> map, Transaction tx);
}
