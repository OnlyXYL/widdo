package cn.widdo.kernel.function;

import cn.widdo.assistant.entity.result.JsonResult;

import java.util.List;
import java.util.Map;

/**
 * @author XYL
 * @version 1.0
 * @date 2022/07/15 0:06
 */
@FunctionalInterface
public interface ListInterfaceFunction {

    /**
     * 执行方法
     *
     * @param params
     * @return cn.widdo.entity.JsonResult
     * @throws
     * @author XYL
     * @className cn.widdo.base.function.ListInterfaceFunction
     * @date 2022/07/15 0:06
     **/
    JsonResult execute(List<Map<String, Object>> params);
}
