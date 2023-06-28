package cn.widdo.study.exception.controller;

import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.study.exception.service.ExceptionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ExceptionController.
 *
 * @author XYL
 * @date 2023/05/15 11:39
 * @since 305.2.0.1
 */
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

    /**
     * ExceptionService.
     */
    private ExceptionService exceptionService;

    /**
     * constructor has one param typed {@link ExceptionService}.
     *
     * @param exceptionService
     */
    public ExceptionController(final ExceptionService exceptionService) {
        this.exceptionService = exceptionService;
    }

    /**
     * 异常测试方法.
     *
     * @return cn.widdo.assistant.result.WiddoResult
     * @author XYL
     * @date 2023/06/27 17:25:09
     */
    @GetMapping(value = "/test")
    public WiddoResult exception() {
        return exceptionService.test();
    }
}
