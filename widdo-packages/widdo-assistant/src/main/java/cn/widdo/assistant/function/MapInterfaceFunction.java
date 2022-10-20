package cn.widdo.assistant.function;

import cn.widdo.assistant.entity.result.WebResult;

import java.util.Map;

/**
 * map 类型参数处理的函数式接口
 *
 * @author XYL
 * @version 1.2
 * @date 2022/10/20 10:37
 */
@FunctionalInterface
public interface MapInterfaceFunction {

    /**
     * @param params
     * @return cn.widdo.assistant.entity.result.WebResult
     * @throws
     * @author XYL
     * @className cn.widdo.assistant.function.MapInterfaceFunction
     * @date 2022/10/20 10:39
     **/
    WebResult execute(Map<String, Object> params);
}
