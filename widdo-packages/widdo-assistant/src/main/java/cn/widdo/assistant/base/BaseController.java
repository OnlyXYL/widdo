package cn.widdo.assistant.base;

import cn.widdo.assistant.entity.result.WebResult;
import cn.widdo.assistant.enums.ResponseCode;
import cn.widdo.assistant.function.MapInterfaceFunction;
import cn.widdo.assistant.utils.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * controller基础层
 *
 * @author XYL
 * @version 1.2
 * @date 2022/10/20 10:39
 */
public class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    /**
     * 校验参数并执行
     *
     * @param params
     * @param function
     * @param checkKeys
     * @return cn.widdo.assistant.entity.result.WebResult
     * @throws
     * @author XYL
     * @className cn.widdo.assistant.base.BaseController
     * @date 2022/10/20 11:13
     **/
    protected WebResult validateAndRun(Map<String, Object> params,
                             MapInterfaceFunction function,
                             String... checkKeys) {
        try {
            //校验是否为空
            MapUtil.throwExceptionIfNull(params, checkKeys);

            return function.execute(params);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("[Widdo] |- Packages [Widdo assistant] |- BaseController Result [Error] |- Message {} |- Trace {}.", e.getMessage(), e.getStackTrace());
            return WebResult.build(ResponseCode.PARAMS_ERROR);
        }
    }
}
