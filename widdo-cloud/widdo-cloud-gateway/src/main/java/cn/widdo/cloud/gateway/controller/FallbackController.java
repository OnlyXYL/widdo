package cn.widdo.cloud.gateway.controller;

import cn.widdo.assistant.entity.result.MyResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 回调controller.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/07/07 18:08
 */
@RequestMapping(value = "/gateway")
@RestController
public class FallbackController {


    /**
     * 测试.
     *
     * @param name
     * @return cn.widdo.assistant.entity.result.MyResponse
     * @throws
     * @author XYL
     * @date 2022/11/18 0018 18:34
     **/
    @RequestMapping("/fallback/{name}")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public MyResponse systemFallback(@PathVariable String name) throws Exception {
        String response = String.format("访问%s超时或者服务不可用", name);
        return new MyResponse().message(response);
    }
}
