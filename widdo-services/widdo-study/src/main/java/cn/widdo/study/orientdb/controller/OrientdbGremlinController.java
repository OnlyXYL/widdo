package cn.widdo.study.orientdb.controller;

import cn.widdo.assistant.entity.result.JsonResult;
import cn.widdo.graph.configuration.OrientdbConfiguration;
import cn.widdo.kernel.base.controller.BaseController;
import cn.widdo.study.orientdb.service.OrientdbGremlinService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * orientdb gremlin controller
 *
 * @author XYL
 * @version 1.0
 * @date 2022/07/15 11:39
 */
@RequestMapping(value = "/orientdb/gremlin")
@RestController
@ConditionalOnBean({OrientdbConfiguration.class})
public class OrientdbGremlinController extends BaseController {

    @Resource
    private OrientdbGremlinService orientdbGremlinService;

    /**
     * 查询点
     *
     * params.put("key")
     * params.put("value")
     * params.put("label")
     *-
     * @param params
     * @author XYL
     * @className widdo.orientdb.controller.OrientdbGremlinController
     * @return cn.widdo.entity.JsonResult
     * @throws
     * @date 2022/07/15 11:49
     **/
    @PostMapping(value = "/query/v")
    public JsonResult queryV(@RequestBody Map<String,Object> params){

        final List<String> checkParams = checkParams("key", "value", "label");

        return this.toExecute(params,checkParams,p->orientdbGremlinService.queryV(p));
    }
}
