package cn.widdo.study.orientdb.service;

import cn.widdo.assistant.entity.result.WebResult;

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
     * @return void
     * @throws
     * @author XYL
     * @className cn.widdo.study.orientdb.service.OrientdbGremlinService
     * @date 2022/07/15 9:53
     **/
    WebResult queryE(Map<String, Object> params);

    /**
     * 查询点.
     *
     * @param params
     * @return cn.widdo.assistant.entity.result.JsonResult
     * @throws
     * @author XYL
     * @className cn.widdo.study.orientdb.service.OrientdbGremlinService
     * @date 2022/07/15 10:15
     **/
    WebResult queryV(Map<String, Object> params);

    /**
     * 删除.
     *
     * @param
     * @return cn.widdo.assistant.entity.result.JsonResult
     * @throws
     * @author XYL
     * @className cn.widdo.study.orientdb.service.OrientdbGremlinService
     * @date 2022/09/14 16:46
     **/
    WebResult delete();
}
