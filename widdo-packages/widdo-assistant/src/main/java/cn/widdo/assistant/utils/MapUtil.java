package cn.widdo.assistant.utils;

import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * Map 相关的工具类.
 *
 * @author XYL
 * @since 263.1.1.0
 * @date 2022/10/20 10:43
 */
public class MapUtil {

    protected MapUtil() {
        // prevents calls from subclass
        throw new UnsupportedOperationException("避免工具类被实例化");
    }

    /**
     * 校验参数.
     *
     * @param params    参数map
     * @param checkKeys 需要校验的非空可变参数
     * @throws
     * @author XYL
     * @className cn.widdo.starter.neo4j.utils.Neo4jUtil
     * @date 2022/10/18 10:08
     **/
    public static void throwExceptionIfNull(final Map<String, ?> params,
                                            final String... checkKeys) throws Exception {
        Iterator<String> keys = params.keySet().iterator();
        Object value;
        if (checkKeys.length > 0) {
            for (int i = 0; i < checkKeys.length; ++i) {
                value = params.get(checkKeys[i]);
                if (value == null
                        || !StringUtils.hasLength(value.toString())) {
                    throw new Exception("参数" + checkKeys[i] + "不能为空");
                }
            }
        } else {
            while (keys.hasNext()) {
                String k = keys.next();
                value = params.get(k);
                if (value == null || !StringUtils.hasLength(value.toString().trim())) {
                    throw new Exception("参数" + k + "不能为空");
                }
            }
        }
    }
}
