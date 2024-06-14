package cn.widdo.autoconfigure.babelnet.reader;

import cn.widdo.starter.neo4j.validator.ParamsValidator;

/**
 * abstract babelNet reader.
 *
 * @param <T> t
 * @param <R> r
 * @author XYL
 * @date 2023/03/15 10:30
 * @since 302.1.0.0
 */
public abstract class AbstractBabelNetReader<T, R> extends ParamsValidator implements BabelNetReader<T, R> {

}
