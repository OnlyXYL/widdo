package cn.widdo.autoconfigure.babelnet.actuator;

import cn.widdo.autoconfigure.babelnet.reader.BabelNetReader;
import cn.widdo.autoconfigure.babelnet.writer.BabelNetWriter;

/**
 * BabelNetActuator.
 *
 * @param <R> r
 * @param <T> t
 * @author XYL
 * @date 2023/03/15 15:20
 * @since 302.1.0.0
 */
public interface BabelNetActuator<T, R> extends BabelNetReader<T, R>, BabelNetWriter<T, R> {

}
