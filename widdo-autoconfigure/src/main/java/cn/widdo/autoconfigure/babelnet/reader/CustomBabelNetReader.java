package cn.widdo.autoconfigure.babelnet.reader;

import cn.widdo.starter.neo4j.entity.result.Result;

import java.util.Map;

/**
 * a custom BabelNetReader by extends {@link AbstractBabelNetReaderDecorator}.
 *
 * @author XYL`
 * @since 263.1.1.0
 * @date 2022/12/09 11:42
 */
public class CustomBabelNetReader extends AbstractBabelNetReaderDecorator<Map<String, Object>, Result<?>> {

    /**
     * constructor has one param called {@link BabelNetReader}.
     * @param babelNetReader
     */
    public CustomBabelNetReader(final BabelNetReader babelNetReader) {
        this.babelNetReader = babelNetReader;
    }

    @Override
    public Result<?> query(Map<String, Object> params) {
        return babelNetReader.query(params);
    }
}
