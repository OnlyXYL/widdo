package cn.widdo.study.thread.controller;

import cn.widdo.assistant.base.BaseController;
import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.study.thread.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ThreadController
 *
 * @author XYL
 * @date 2024/02/26 18:53
 * @since 305.2.2.0
 */
@RestController
@RequestMapping(value = "/thread")
public class ThreadController extends BaseController {

    private final ThreadService threadService;


    @Autowired
    public ThreadController(ThreadService threadService) {
        this.threadService = threadService;
    }

    /**
     * runnable
     *
     * @param params
     *
     * @author XYL
     * @date 2024/02/26 19:14:29
     * @return cn.widdo.assistant.result.WiddoResult
     */
    @PostMapping(value = "runnable")
    public WiddoResult runnable(@RequestBody Map<String, Object> params) {
        return this.validateAndRun(params, threadService::runnable);
    }

    /**
     * callable
     *
     * @param params
     *
     * @author XYL
     * @date 2024/02/26 19:14:29
     * @return cn.widdo.assistant.result.WiddoResult
     */
    @PostMapping(value = "callable")
    public WiddoResult callable(@RequestBody Map<String, Object> params) {
        return this.validateAndRun(params, threadService::callable);
    }
}
