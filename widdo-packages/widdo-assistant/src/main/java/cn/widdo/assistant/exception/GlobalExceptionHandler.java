package cn.widdo.assistant.exception;

import cn.widdo.assistant.result.WiddoResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalExceptionHandler.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/12/29 18:50
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 统一处理异常.
     *
     * @param e exception
     * @return cn.widdo.assistant.result.WiddoResult
     * @author XYL
     * @date 2022/12/29 19:01:00
     **/
    @ExceptionHandler(value = BaseException.class)
    public WiddoResult baseException(BaseException e) {
        LOG.error("[Widdo] |- packages [Widdo Assistant] |- GlobalExceptionHandler Result [Error] |- Message {} |- Trace {}.", e.getMsg(), e.getStackTrace());
        return new WiddoResult(e.getCode(), e.getMsg());
    }
}
