package cn.widdo.starter.neo4j.function;

import cn.widdo.starter.neo4j.entity.result.Result;
import org.neo4j.driver.TransactionContext;

import java.util.Map;

/**
 * cql回调执行.
 *
 * @author XYL
 * @date 2022/07/15 0:02
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
@FunctionalInterface
public interface CQLFunction {

	/**
	 * sql执行.
	 * @param cql cql
	 * @param map 参数
	 * @param tx 事务
	 * @return cn.widdo.starter.neo4j.entity.result.Result
	 * @author XYL
	 * @className cn.widdo.autoconfigure.neo4j.function.CQLFunction
	 * @date 2022/07/15 0:27
	 **/
	Result<?> execute(String cql, Map<String, Object> map, TransactionContext tx);

}
