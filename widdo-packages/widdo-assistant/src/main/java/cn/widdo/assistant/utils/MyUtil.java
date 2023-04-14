package cn.widdo.assistant.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 工具类.
 *
 * @author XYL
 * @date 2022/07/05 17:37
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public class MyUtil {

    protected MyUtil() {
        throw new UnsupportedOperationException("静态工具类不能被实例化");
    }

    /**
     * 驼峰转下划线.
     *
     * @param value 待转换值
     * @return 结果
     */
    public static String camelToUnderscore(final String value) {
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
     * 获取异常信息.
     *
     * @param bindingResult 异常绑定结果
     * @return java.lang.String
     * @author XYL
     * @since 15:28 2021/4/28 0028
     **/
    public static String errorMessage(final BindingResult bindingResult) {
        List<String> jsonList = new ArrayList<>();
        bindingResult.getFieldErrors()
                .forEach(e -> jsonList.add(e.getDefaultMessage()));
        return JSON.toJSONString(jsonList);
    }

}
