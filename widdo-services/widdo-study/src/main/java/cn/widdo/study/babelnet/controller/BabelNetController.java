package cn.widdo.study.babelnet.controller;

import cn.widdo.assistant.base.BaseController;
import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.study.babelnet.service.BabelNetService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * babelNet controller.
 *
 * @author XYL
 * @date 2022/11/30 19:02
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
@RequestMapping(value = "/babelnet")
@RestController
public class BabelNetController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(BabelNetController.class);

    @PostConstruct
    private void postConstruct() {
        LOG.info("[Widdo] |- Service [Widdo Study] BabelNetController.");
    }

    /**
     * BabelNetService.
     */
    private final BabelNetService babelNetService;

    @Autowired
    public BabelNetController(final BabelNetService babelNetService) {
        this.babelNetService = babelNetService;
    }

    /**
     * babelNet query.
     *
     * @param params params
     * @return cn.widdo.assistant.result.WiddoResult
     * @author XYL
     * @date 2023/03/15 13:39:48
     */
    @PostMapping(value = "/query")
    public WiddoResult query(@RequestBody Map<String, Object> params) {
        return this.validateAndRun(params, babelNetService::query, "lemma");
    }
}
