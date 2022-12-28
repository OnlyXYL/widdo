package cn.widdo.study.neo4j.controller;

import cn.widdo.assistant.base.BaseController;
import cn.widdo.assistant.entity.result.WebResult;
import cn.widdo.study.neo4j.service.Neo4jJdbcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

/**
 * neo4j controller.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/07/14 18:00
 */
@RequestMapping(value = "/neo4j")
@RestController
public class Neo4jController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(Neo4jController.class);

    @PostConstruct
    private void postConstruct() {
        LOG.info("[Widdo] |- Service [Widdo Study] Neo4jController.");
    }


    /**
     * service.
     */
    @Resource
    private Neo4jJdbcService neo4jJdbcService;

    /**
     * 執行查詢.
     * <p>
     * params.put("label")
     * params.put("name")
     *
     * @param params
     * @return cn.widdo.assistant.entity.result.WebResult
     * @author XYL
     * @className widdo.neo4j.controller.Neo4jController
     * @date 2022/07/15 1:01
     **/
    @PostMapping(value = "/query")
    public WebResult query(@RequestBody Map<String, Object> params) {
        return this.validateAndRun(params, (p) -> neo4jJdbcService.query(p), "label", "name");
    }

    /**
     * 執行寫操作.
     *
     * @param params
     * @return cn.widdo.assistant.entity.result.WebResult
     * @author XYL
     * @date 2022/12/02 21:22:04
     **/
    @PostMapping(value = "/write")
    public WebResult write(@RequestBody Map<String, Object> params) {
        return this.validateAndRun(params, (p) -> neo4jJdbcService.write(p), "triples");
    }

}
