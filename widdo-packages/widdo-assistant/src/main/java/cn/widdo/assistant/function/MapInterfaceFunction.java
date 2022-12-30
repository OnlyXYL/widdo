package cn.widdo.assistant.function;

import cn.widdo.assistant.result.WiddoResult;

import java.util.Map;

/**
 * map 类型参数处理的函数式接口.
 *
 * @author XYL
 * @version 1.2
 * @date 2022/10/20 10:37
 */
@FunctionalInterface
public interface MapInterfaceFunction {

    /**
     * 函数式接口，用来在controller层调用service，统一封装参数预处理功能.
     *
     * @param params    参数
     * @return cn.widdo.assistant.result.WiddoResult
     * @author XYL
     * @className cn.widdo.assistant.function.MapInterfaceFunction
     * @date 2022/10/20 10:39
     **/
    WiddoResult execute(Map<String, Object> params);
}
