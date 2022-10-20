package cn.widdo.autoconfigure.neo4j.writer;

import cn.widdo.starter.neo4j.entity.result.Result;
import org.neo4j.driver.Driver;

import java.util.Map;

/**
 * 默认的写方法
 *
 * @author XYL
 * @version 263.1.0.0
 * @date 2022/10/14 16:37
 */
public class DefaultNeo4jWriter implements Neo4jWriter<Map<String, Object>, Result<?>> {

    private Driver driver;

    public DefaultNeo4jWriter(Driver driver) {
        this.driver = driver;
    }

    @Override
    public Result<?> write(Map<String, Object> params) {
        return null;
    }
}
