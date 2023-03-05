package cn.widdo.autoconfigure.neo4j.writer;

import cn.widdo.autoconfigure.neo4j.Neo4jHelper;
import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;

import java.util.List;
import java.util.Map;

/**
 * 增强接口功能.
 *
 * @author XYL
 * @date 2023/03/02 18:14
 * @since 263.1.3.0
 */
public interface EnhanceWriter extends Neo4jHelper {

    /**
     * 打印日志.
     *
     * @param params params
     * @return Result
     * @author XYL
     * @date 2023/03/02 18:15:29
     */
    default Result<List<Map<String, Value>>> print(Map<String, Object> params) {
        return this.run(params);
    }
}
