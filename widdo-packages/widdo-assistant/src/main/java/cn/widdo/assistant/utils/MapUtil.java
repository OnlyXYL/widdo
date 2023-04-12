package cn.widdo.assistant.utils;

import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * Map 相关的工具类.
 *
 * @author XYL
 * @date 2022/10/20 10:43
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
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
     * @throws Exception exception
     * @author XYL
     * @className cn.widdo.starter.neo4j.utils.Neo4jUtil
     * @date 2022/10/18 10:08
     **/
    public static void throwExceptionIfNull(final Map<String, ?> params,
                                            final String... checkKeys) throws Exception {
        Iterator<String> keys = params.keySet().iterator();
        Object value;
        if (checkKeys.length > 0) {
            for (String checkKey : checkKeys) {
                value = params.get(checkKey);
                if (value == null
                        || !StringUtils.hasLength(value.toString())) {
                    throw new Exception("参数" + checkKey + "不能为空");
                }
            }
        }
    }
}
