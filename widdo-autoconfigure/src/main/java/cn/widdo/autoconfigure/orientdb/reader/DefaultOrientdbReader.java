package cn.widdo.autoconfigure.orientdb.reader;

import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;
import cn.widdo.starter.neo4j.validator.ParamsValidator;

import java.util.List;
import java.util.Map;

/**
 * default implementation of orientdbReader.
 *
 * @author XYL
 * @since 263.1.1.0
 * @date 2022/10/14 16:43
 */
public class DefaultOrientdbReader  extends ParamsValidator implements OrientdbReader<Map<String, Object>, Result<List<Map<String, Value>>>> {

    @Override
    public Result<List<Map<String, Value>>> query(Map<String, Object> map) {
        return null;
    }
}
