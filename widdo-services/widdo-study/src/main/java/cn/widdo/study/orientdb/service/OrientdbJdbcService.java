package cn.widdo.study.orientdb.service;

import cn.widdo.assistant.entity.result.WebResult;

import java.util.Map;

/**
 * @author XYL
 * @version 263.1.0.0
 * @date 2022/07/15 2:05
 */
public interface OrientdbJdbcService {

    /**
     * 查询线
     *
     * @param params
     * @return void
     * @throws
     * @author XYL
     * @className widdo.orientdb.service.OrientdbJdbcService
     * @date 2022/07/15 9:53
     **/
    WebResult queryE(Map<String, Object> params);

    /**
     * 查询点
     *
     * @param params
     * @return cn.widdo.entity.JsonResult
     * @throws
     * @author XYL
     * @className widdo.orientdb.service.OrientdbJdbcService
     * @date 2022/07/15 10:15
     **/
    WebResult queryV(Map<String, Object> params);

    /**
     * 创建点
     *
     * @param params
     * @return cn.widdo.entity.JsonResult
     * @throws
     * @author XYL
     * @className widdo.orientdb.service.OrientdbJdbcService
     * @date 2022/07/15 10:38
     **/
    WebResult createV(Map<String, Object> params);

    /**
     * 创建关系
     *
     * @param params
     * @return cn.widdo.entity.JsonResult
     * @throws
     * @author XYL
     * @className widdo.orientdb.service.OrientdbJdbcService
     * @date 2022/07/15 10:39
     **/
    WebResult createE(Map<String, Object> params);

    /**
     * 删除数据
     *
     * @param
     * @return cn.widdo.entity.JsonResult
     * @throws
     * @author XYL
     * @className widdo.orientdb.service.OrientdbJdbcService
     * @date 2022/07/15 10:39
     **/
    WebResult delete();
}
