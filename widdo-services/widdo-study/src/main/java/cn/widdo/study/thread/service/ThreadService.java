package cn.widdo.study.thread.service;

import cn.widdo.assistant.result.WiddoResult;

import java.util.Map;

/**
 * ThreadService
 *
 * @author XYL
 * @date 2024/02/26 18:55
 * @since 305.2.2.0
 */
public interface ThreadService {
    
    /**
     * 线程等待
     *
     * @param params
     *
     * @author XYL
     * @date 2024/02/26 18:56:09
     * @return cn.widdo.assistant.result.WiddoResult
     */
    WiddoResult runnable(Map<String, Object> params);

    /**
     * callable
     *
     * @param params
     *
     * @author XYL
     * @date 2024/02/26 19:03:37
     * @return cn.widdo.assistant.result.WiddoResult
     */
    WiddoResult callable(Map<String, Object> params);
}
