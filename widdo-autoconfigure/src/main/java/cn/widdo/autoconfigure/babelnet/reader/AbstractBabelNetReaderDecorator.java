package cn.widdo.autoconfigure.babelnet.reader;

import cn.widdo.starter.neo4j.validator.ParamsValidator;

/**
 * BabelNet 读取数据装饰器.
 *
 * @param <T> params
 * @param <R> return something
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/12/02 18:50
 */
public abstract class AbstractBabelNetReaderDecorator<T, R> extends ParamsValidator implements BabelNetReader<T, R> {

    /**
     * {@link BabelNetReader}.
     */
    protected BabelNetReader<T, R> babelNetReader;

}
