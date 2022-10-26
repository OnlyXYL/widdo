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
 * @version 263.1.1.0
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
     * 获取异常信息
     *
     * @param bindingResult 异常绑定结果
     * @return java.lang.String
     * @author XYL
     * @since 15:28 2021/4/28 0028
     **/
    public static String errorMessage(BindingResult bindingResult) {
        List<String> jsonList = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> jsonList.add(fieldError.getDefaultMessage()));

        return JSON.toJSONString(jsonList);
    }

}