package cn.widdo.autoconfigure.neo4j.reader;

import cn.widdo.starter.neo4j.entity.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Reader 装饰器，对传入的Reader进行装饰。当默认方法不足时，可以通过该方式对原方法进行增强
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/15 1:20
 */
public class CustomNeo4jReader extends AbstractNeo4jReaderDecorator {

    private Logger logger = LoggerFactory.getLogger(CustomNeo4jReader.class);

    public CustomNeo4jReader(Neo4jReader neo4jReader) {
        super(neo4jReader);
    }

    @Override
    public Result<?> query(Map<String, Object> params){
        return neo4jReader.query(params);
    }

    private void test() {
        //todo: 可以添加新的方法
        logger.info("[Widdo] |- Autoconfigure [Custom Neo4j Reader].");
    }

}
