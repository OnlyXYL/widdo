package cn.widdo.autoconfigure.babelnet.reader;

import cn.widdo.autoconfigure.babelnet.Runner;

/**
 * BabelNet Reader.
 *
 * @param <T>
 * @param <R>
 * @author XYL
 * @date 2022/12/02 18:49
 * @since 302.1.0.0
 */
public interface BabelNetReader<T, R> extends Runner {

    /**
     * query.
     *
     * @param params params
     * @return R
     * @author XYL
     * @date 2022/12/02 18:49:50
     **/
    R read(T params);
}
