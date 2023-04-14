package cn.widdo.cloud.gateway.controller;

import cn.widdo.assistant.result.WiddoResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 回调controller.
 *
 * @author XYL
 * @date 2022/07/07 18:08
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
@RequestMapping(value = "/gateway")
@RestController
public class FallbackController {

    /**
     * 测试.
     *
     * @param name name
     * @return cn.widdo.assistant.result.WiddoResult
     * @author XYL
     * @date 2022/11/18 0018 18:34
     **/
    @GetMapping("/fallback/{name}")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public WiddoResult systemFallback(@PathVariable String name) {
        String response = String.format("访问%s超时或者服务不可用", name);
        return new WiddoResult(response);
    }
}
