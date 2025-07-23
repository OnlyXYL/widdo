package cn.widdo.study.thread.service;

import cn.widdo.assistant.result.WiddoResult;

import java.util.Map;

/**
 * ThreadService.
 *
 * @author XYL
 * @date 2024/02/26 18:55
 * @since 305.2.2.0
 */
public interface ThreadService {

    /**
     * 线程等待.
     *
     * @param params
     * @return cn.widdo.assistant.result.WiddoResult
     * @author XYL
     * @date 2024/02/26 18:56:09
     */
    WiddoResult runnable(Map<String, Object> params);

    /**
     * callable.
     *
     * @param params
     * @return cn.widdo.assistant.result.WiddoResult
     * @author XYL
     * @date 2024/02/26 19:03:37
     */
    WiddoResult callable(Map<String, Object> params);

    /**
     * 重排序.
     *
     * @param params
     * @return cn.widdo.assistant.result.WiddoResult
     * @author XYL
     * @date 2024/06/14 16:19:54
     */
    WiddoResult reorder(Map<String, Object> params);
}
