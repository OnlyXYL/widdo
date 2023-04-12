package cn.widdo.study.orientdb.service;

import cn.widdo.assistant.result.WiddoResult;

import java.util.Map;

/**
 * orientdb gremlin service.
 *
 * @author XYL
 * @date 2022/07/15 2:08
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public interface OrientdbGremlinService {

    /**
     * 查询线.
     *
     * @param params params
     * @return cn.widdo.assistant.result.WiddoResult
     * @author XYL
     * @date 2022/07/15 9:53
     **/
    WiddoResult queryE(Map<String, Object> params);

    /**
     * 查询点.
     *
     * @param params params
     * @return cn.widdo.assistant.result.WiddoResult
     * @author XYL
     * @date 2022/07/15 10:15
     **/
    WiddoResult queryV(Map<String, Object> params);

    /**
     * 删除.
     *
     * @return cn.widdo.assistant.result.WiddoResult
     * @author XYL
     * @date 2022/09/14 16:46
     **/
    WiddoResult delete();
}
