package cn.widdo.assistant.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 工具类
 *
 * @author XYL
 * @version 1.0
 * @date 2022/07/05 17:37
 */
@Slf4j
public class MyUtil {

    /**
     * 驼峰转下划线
     *
     * @param value 待转换值
     * @return 结果
     */
    public static String camelToUnderscore(String value) {
        if (StringUtils.isBlank(value)) {
            return value;
        }
        String[] arr = StringUtils.splitByCharacterTypeCamelCase(value);
        if (arr.length == 0) {
            return value;
        }
        StringBuilder result = new StringBuilder();
        IntStream.range(0, arr.length).forEach(i -> {
            if (i != arr.length - 1) {
                result.append(arr[i]).append("_");
            } else {
                result.append(arr[i]);
            }
        });
        return StringUtils.lowerCase(result.toString());
    }

    /**
     * 获取用户id
     *
     * @param
     * @return java.lang.String
     * @author XYL
     * @since 17:37 2021/4/21 0021
     **/
    public static String userId() {
//        String authParams = SecurityContextHolder.getContext().getAuthentication().getName();
//        return authParams.split(":")[1];

        return "";
    }

    /**
     * 获取异常信息
     *
     * @param bindingResult
     * @return java.lang.String
     * @author XYL
     * @since 15:28 2021/4/28 0028
     **/
    public static String errorMessage(BindingResult bindingResult) {
        List<String> jsonList = new ArrayList<>();
        bindingResult.getFieldErrors().stream().forEach(fieldError -> {
            jsonList.add(fieldError.getDefaultMessage());
        });

        String s = JSON.toJSONString(jsonList);
        return s;
    }

    /**
     * 回滚
     *
     * @param
     * @return void
     * @author XYL
     * @since 18:01 2021/4/29 0029
     **/
    public static void rollback() {
//        log.warn("------ [发生错误，进行回滚] ------");
//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

}