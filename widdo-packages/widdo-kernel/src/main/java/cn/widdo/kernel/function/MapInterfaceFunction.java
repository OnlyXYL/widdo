package cn.widdo.kernel.function;

import cn.widdo.assistant.entity.result.JsonResult;

import java.util.Map;

/**
 * @author XYL
 * @version 1.0
 * @date 2022/07/15 0:05
 */
@FunctionalInterface
public interface MapInterfaceFunction {

    /**
     * 执行方法
     *
     * @param params
     * @return cn.widdo.entity.JsonResult
     * @throws
     * @author XYL
     * @className cn.widdo.base.function.MapInterfaceFunction
     * @date 2022/07/15 0:05
     **/
    JsonResult execute(Map<String, Object> params);
}
