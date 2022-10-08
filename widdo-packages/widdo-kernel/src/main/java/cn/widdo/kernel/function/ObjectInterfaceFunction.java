package cn.widdo.kernel.function;

import cn.widdo.assistant.entity.result.JsonResult;

/**
 * object function interface
 *
 * @author XYL
 * @version 1.0
 * @date 2022/07/15 0:05
 */
@FunctionalInterface
public interface ObjectInterfaceFunction {

    /**
     * 对象类型
     *
     * @param object
     * @return JsonResult
     * @throws
     * @author XYL
     * @className cn.widdo.base.function.ObjectInterfaceFunction
     * @date 2022/07/15 0:05
     **/
    JsonResult execute(Object object);
}
