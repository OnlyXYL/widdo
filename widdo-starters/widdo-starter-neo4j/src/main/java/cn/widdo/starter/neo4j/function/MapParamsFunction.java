package cn.widdo.starter.neo4j.function;

import cn.widdo.starter.neo4j.entity.result.Result;

import java.util.Map;

/**
 * MapParamsFunction.
 *
 * @author XYL
 * @since 263.1.1.0
 * @date 2022/07/15 0:05
 */
@FunctionalInterface
public interface MapParamsFunction {

    /**
     * 执行方法.
     * +
     *
     * @param params 参数
     * @return cn.widdo.entity.JsonResult
     * @author XYL
     * @className cn.widdo.starter.neo4j.function.mapParamsFunction
     * @date 2022/07/15 0:05
     **/
    Result execute(Map<String, Object> params);
}
