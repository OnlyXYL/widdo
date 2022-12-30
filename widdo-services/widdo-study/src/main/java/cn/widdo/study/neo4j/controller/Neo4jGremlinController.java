package cn.widdo.study.neo4j.controller;

import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.autoconfigure.base.BaseController;
import cn.widdo.study.neo4j.service.Neo4jGremlinService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Neo4jGremlinController.
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/07/15 1:35
 */
@RequestMapping(value = "/neo4j/gremlin")
@RestController
public class Neo4jGremlinController extends BaseController {

    /**
     * neo4jGremlinService.
     */
    @Resource
    private Neo4jGremlinService neo4jGremlinService;

    /**
     * query.
     *
     * @param params
     * @return cn.widdo.entity.JsonResult
     * @throws
     * @author XYL
     * @className widdo.neo4j.controller.Neo4jGremlinController
     * @date 2022/07/15 2:04
     **/
    @PostMapping(value = "/query")
    public WiddoResult query(@RequestBody Map<String, Object> params) {
        return this.validateAndRun(params, (p) -> neo4jGremlinService.query(p), "label", "name");
    }
}
