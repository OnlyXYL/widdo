package cn.widdo.study.neo4j.controller;

import cn.widdo.assistant.entity.result.JsonResult;
import cn.widdo.kernel.base.controller.BaseController;
import cn.widdo.study.neo4j.service.Neo4jGremlinService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author XYL
 * @version 1.0
 * @date 2022/07/15 1:35
 */
@RequestMapping(value = "/neo4j/gremlin")
@RestController
public class Neo4jGremlinController extends BaseController {

    @Resource
    private Neo4jGremlinService neo4jGremlinService;

    /**
     * @param params
     * @return cn.widdo.entity.JsonResult
     * @throws
     * @author XYL
     * @className widdo.neo4j.controller.Neo4jGremlinController
     * @date 2022/07/15 2:04
     **/
    @PostMapping(value = "/query")
    public JsonResult query(@RequestBody Map<String, Object> params) {

        final List<String> checkParams = checkParams("label", "name");

        return this.toExecute(params, checkParams, (p) -> neo4jGremlinService.query(p));
    }
}
