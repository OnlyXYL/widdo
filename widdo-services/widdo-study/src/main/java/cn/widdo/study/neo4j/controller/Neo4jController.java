package cn.widdo.study.neo4j.controller;

import cn.widdo.assistant.entity.result.JsonResult;
import cn.widdo.kernel.base.controller.BaseController;
import cn.widdo.study.neo4j.service.Neo4jJdbcService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * neo4j controller.
 *
 * @author XYL
 * @version 1.0
 * @date 2022/07/14 18:00
 */
@RequestMapping(value = "/neo4j")
@RestController
public class Neo4jController extends BaseController {

    @Resource
    private Neo4jJdbcService neo4jJdbcService;

    /**
     * 執行查詢
     *
     * params.put("label")
     * params.put("name")
     *
     * @param params
     * @return cn.widdo.entity.neo4j.result.Result<java.util.List < java.util.Map < java.lang.String, cn.widdo.entity.neo4j.Value>>>
     * @throws
     * @author XYL
     * @className widdo.neo4j.controller.Neo4jController
     * @date 2022/07/15 1:01
     **/
    @PostMapping(value = "/query")
    public JsonResult query(@RequestBody Map<String, Object> params) {

        final List<String> checkParams = checkParams("label", "name");

        return this.toExecute(params, checkParams, (p) -> neo4jJdbcService.query(p));
    }
}
