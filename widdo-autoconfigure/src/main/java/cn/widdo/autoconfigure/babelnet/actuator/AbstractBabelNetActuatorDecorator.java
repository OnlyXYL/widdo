package cn.widdo.autoconfigure.babelnet.actuator;

import cn.widdo.autoconfigure.babelnet.reader.AbstractBabelNetReader;

/**
 * AbstractBabelNetActuatorDecorator.
 *
 * @param <R> r
 * @param <T> t
 * @author XYL
 * @date 2023/03/15 15:22
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public abstract class AbstractBabelNetActuatorDecorator<T, R> extends AbstractBabelNetReader<T, R> {

    /**
     * BabelNetActuator.
     */
    protected BabelNetActuator<T, R> babelNetActuator;
}
