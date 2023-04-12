package cn.widdo.autoconfigure.babelnet.writer;

import cn.widdo.autoconfigure.babelnet.Runner;

/**
 * babelNet writer.
 *
 * @param <R> r
 * @param <T> t
 * @author XYL
 * @date 2023/03/15 10:33
 * @since 302.1.0.0
 */
public interface BabelNetWriter<T, R> extends Runner {

    /**
     * write.
     *
     * @param params params
     * @return R
     * @author XYL
     * @date 2023/03/15 10:34:23
     */
    R write(T params);
}
