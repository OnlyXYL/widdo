package cn.widdo.study.orientdb.service;

import cn.widdo.assistant.result.WiddoResult;

import java.util.Map;

/**
 * orientdb gremlin service.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/07/15 2:08
 */
public interface OrientdbGremlinService {

    /**
     * 查询线.
     *
     * @param params
     * @return cn.widdo.assistant.result.WiddoResult
     * @author XYL
     * @className cn.widdo.study.orientdb.service.OrientdbGremlinService
     * @date 2022/07/15 9:53
     **/
    WiddoResult queryE(Map<String, Object> params);

    /**
     * 查询点.
     *
     * @param params
     * @return cn.widdo.assistant.result.WiddoResult
     * @author XYL
     * @className cn.widdo.study.orientdb.service.OrientdbGremlinService
     * @date 2022/07/15 10:15
     **/
    WiddoResult queryV(Map<String, Object> params);

    /**
     * 删除.
     *
     * @param
     * @return cn.widdo.assistant.result.WiddoResult
     * @author XYL
     * @className cn.widdo.study.orientdb.service.OrientdbGremlinService
     * @date 2022/09/14 16:46
     **/
    WiddoResult delete();
}
