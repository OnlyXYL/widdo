package cn.widdo.study.orientdb.controller;

import cn.widdo.assistant.entity.result.JsonResult;
import cn.widdo.graph.configuration.OrientdbConfiguration;
import cn.widdo.kernel.base.controller.BaseController;
import cn.widdo.study.orientdb.service.OrientdbJdbcService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * orientdb controller
 *
 * @author XYL
 * @version 1.0
 * @date 2022/07/14 18:01
 */
@RequestMapping(value = "/orientdb/jdbc")
@RestController
@ConditionalOnBean({OrientdbConfiguration.class})
public class OrientdbJdbcController extends BaseController {

    @Resource
    private OrientdbJdbcService orientdbJdbcService;

    /**
     * 创建点
     * <p>
     * params.put("name")
     * params.put("age")
     * params.put("city")
     *
     * @param params
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @throws
     * @author XYL
     * @className widdo.orientdb.controller.OrientdbJdbcController
     * @date 2022/07/15 11:43
     **/
    @PostMapping(value = "/create/v")
    public JsonResult createV(@RequestBody Map<String, Object> params) {

        final List<String> checkParams = checkParams("name", "age", "city");

        return this.toExecute(params, checkParams, p -> orientdbJdbcService.createV(p));
    }

    /**
     * 创建关系
     * <p>
     * params.put("label")
     * params.put("source")
     * params.put("target")
     *
     * @param params
     * @return cn.widdo.entity.JsonResult
     * @throws
     * @author XYL
     * @className widdo.orientdb.controller.OrientdbJdbcController
     * @date 2022/07/15 11:46
     **/
    @PostMapping(value = "/create/e")
    public JsonResult createE(@RequestBody Map<String, Object> params) {

        final List<String> checkParams = checkParams("label", "source", "target");

        return this.toExecute(params, checkParams, p -> orientdbJdbcService.createE(p));
    }
}
