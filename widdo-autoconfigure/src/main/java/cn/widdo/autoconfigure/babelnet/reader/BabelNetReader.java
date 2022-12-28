package cn.widdo.autoconfigure.babelnet.reader;

/**
 * BabelNet Reader.
 *
 * @param <T>
 * @param <R>
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/12/02 18:49
 */
public interface BabelNetReader<T, R> {

    /**
     * query.
     *
     * @param params
     * @return R
     * @author XYL
     * @date 2022/12/02 18:49:50
     **/
    R query(T params);
}
