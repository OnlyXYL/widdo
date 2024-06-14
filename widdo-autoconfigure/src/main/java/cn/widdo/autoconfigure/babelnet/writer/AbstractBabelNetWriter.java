package cn.widdo.autoconfigure.babelnet.writer;

import cn.widdo.starter.neo4j.validator.ParamsValidator;

/**
 * abstract babelNet writer.
 *
 * @param <T> t
 * @param <R> r
 * @author XYL
 * @date 2023/03/15 10:34
 * @since 302.1.0.0
 */
public abstract class AbstractBabelNetWriter<T, R> extends ParamsValidator implements BabelNetWriter<T, R> {

}
