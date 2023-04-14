package cn.widdo.assistant.base;

import cn.widdo.assistant.function.MapInterfaceFunction;
import cn.widdo.assistant.result.IResultInterface;
import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.assistant.utils.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * controller基础层.
 *
 * @author XYL
 * @date 2022/10/20 10:39
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public class BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    /**
     * 校验参数并执行.
     *
     * @param params    param typed {@link Map}
     * @param function  param typed {@link MapInterfaceFunction}
     * @param checkKeys param typed {@link String...}
     * @return cn.widdo.assistant.entity.result.WebResult
     * @author XYL
     * @className cn.widdo.assistant.base.BaseController
     * @date 2022/10/20 11:13
     **/
    protected WiddoResult validateAndRun(final Map<String, Object> params,
                                         final MapInterfaceFunction function,
                                         final String... checkKeys) {
        try {
            //校验是否为空
            MapUtil.throwExceptionIfNull(params, checkKeys);

            return function.execute(params);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.debug("[Widdo] |- Packages [Widdo assistant] |- BaseController Result [Error] |- Message {} |- Trace {}.",
                    e.getMessage(), e.getStackTrace());

            return WiddoResult.response(IResultInterface.SysResultEnum.PARAMS_ERROR);
        }
    }
}
