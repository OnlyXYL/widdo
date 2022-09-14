package cn.widdo.kernel.base.controller;

import cn.widdo.assistant.entity.result.JsonResult;
import cn.widdo.assistant.enums.ResponseCode;
import cn.widdo.assistant.utils.MyUtil;
import cn.widdo.kernel.function.ListInterfaceFunction;
import cn.widdo.kernel.function.MapInterfaceFunction;
import cn.widdo.kernel.function.ObjectInterfaceFunction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 基础controller
 *
 * @author XYL
 * @version 1.0
 * @date 2022/07/15 0:06
 */
@Slf4j
public class BaseController {

    /**
     * 校验非空
     *
     * @param params
     * @param args
     * @return void
     * @throws
     * @author XYL
     * @className cn.widdo.base.controller.BaseController
     * @date 2022/07/15 0:09
     **/
    public void checkNull(Map<String, Object> params, List<String> args) {

        for (int i = 0; i < args.size(); i++) {
            final String arg = args.get(i);
            Object o = params.get(arg);
            final String orElse = Optional.ofNullable(o).map(Object::toString).orElse("");
            if (StringUtils.isBlank(orElse)) {
                throw new RuntimeException(arg + "参数不能为空！");
            }
        }
    }

    /**
     * 参数去首位空格
     *
     * @param params
     * @return void
     * @throws
     * @author XYL
     * @className cn.widdo.base.controller.BaseController
     * @date 2022/07/15 0:09
     **/
    public void replaceBlank(Map<String, Object> params) {
        params.forEach((key, value) -> {

            if (value instanceof String) {
//                String vTrim = Optional.ofNullable(value).map(Object::toString).orElse("").replaceAll("\\s+", "");
                String vTrim = ((String) value).trim();
                params.put(key, vTrim);
            }
        });
    }

    /**
     * 可变参转list
     *
     * @param args
     * @return java.util.List<java.lang.String>
     * @throws
     * @author XYL
     * @className cn.widdo.base.controller.BaseController
     * @date 2022/07/15 0:09
     **/
    public List<String> checkParams(String... args) {
        final List<String> list = Arrays.asList(args);
        return list;
    }

    /**
     * map类型接口
     *
     * @param params
     * @param notNull
     * @param fun
     * @return cn.widdo.entity.JsonResult
     * @throws
     * @author XYL
     * @className cn.widdo.base.controller.BaseController
     * @date 2022/07/15 0:09
     **/
    public JsonResult toExecute(Map<String, Object> params,
                                List<String> notNull,
                                MapInterfaceFunction fun) {
        try {

            //参数校验
            checkNull(params, notNull);

            //参数去空格
            replaceBlank(params);

            return fun.execute(params);
        } catch (Exception e) {
            e.printStackTrace();

            log.error("------ [参数校验]，结果：[Error]，信息：[{}]", e.getMessage());

            return JsonResult.build(ResponseCode.PARAMS_ERROR);
        }
    }

    /**
     * list类型接口
     *
     * @param params
     * @param notNull
     * @param fun
     * @return cn.widdo.entity.JsonResult
     * @throws
     * @author XYL
     * @className cn.widdo.base.controller.BaseController
     * @date 2022/07/15 0:08
     **/
    public JsonResult toExecute(List<Map<String, Object>> params,
                                List<String> notNull,
                                ListInterfaceFunction fun) {
        try {

            for (Map<String, Object> map : params) {
                //参数校验
                checkNull(map, notNull);

                //参数去空格
                replaceBlank(map);
            }
            return fun.execute(params);
        } catch (Exception e) {
            e.printStackTrace();

            log.error("------ [参数校验]，结果：[Error]，信息：[{}]", e.getMessage());

            return JsonResult.build(ResponseCode.PARAMS_ERROR);
        }
    }

    /**
     * 对象类型接口
     *
     * @param obj
     * @param bindingResult
     * @param fun
     * @return cn.widdo.entity.JsonResult
     * @throws
     * @author XYL
     * @className cn.widdo.base.controller.BaseController
     * @date 2022/07/15 0:08
     **/
    public JsonResult toExecute(Object obj,
                                BindingResult bindingResult,
                                ObjectInterfaceFunction fun) {

        if (bindingResult.hasErrors()) {

            final String errorMessage = MyUtil.errorMessage(bindingResult);

            log.error("------ [参数校验]，结果：[Error]，信息：[{}]", errorMessage);

            return JsonResult.build(ResponseCode.PARAMS_ERROR);
        }

        return fun.execute(obj);
    }
}
