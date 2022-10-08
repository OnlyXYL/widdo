package cn.widdo.study.orientdb.service;

import cn.widdo.assistant.entity.result.JsonResult;

import java.util.Map;

/**
 * @author XYL
 * @version 1.0
 * @date 2022/07/15 2:08
 */
public interface OrientdbGremlinService {

    /**
     * 查询线
     *
     * @param params
     * @author XYL
     * @className cn.widdo.study.orientdb.service.OrientdbGremlinService
     * @return void
     * @throws
     * @date 2022/07/15 9:53
     **/
    JsonResult queryE(Map<String,Object> params);

    /**
     * 查询点
     *
     * @param params
     * @author XYL
     * @className cn.widdo.study.orientdb.service.OrientdbGremlinService
     * @return cn.widdo.assistant.entity.result.JsonResult
     * @throws
     * @date 2022/07/15 10:15
     **/
    JsonResult queryV(Map<String,Object> params);

    /**
     * 删除
     *
     * @param
     * @author XYL
     * @className cn.widdo.study.orientdb.service.OrientdbGremlinService
     * @return cn.widdo.assistant.entity.result.JsonResult
     * @throws
     * @date 2022/09/14 16:46
     **/
    JsonResult delete();
}
