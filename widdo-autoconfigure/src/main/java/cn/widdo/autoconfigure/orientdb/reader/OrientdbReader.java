package cn.widdo.autoconfigure.orientdb.reader;

/**
 * orientdb reader.
 *
 * @param <T>
 * @param <R>
 * @author XYL
 * @date 2022/10/14 16:42
 * @since 263.1.1.0
 */
public interface OrientdbReader<T, R> {

    /**
     * method to query from orientdb.
     *
     * @param t params to query
     * @return R  result of the method
     * @author XYL
     * @date 2023/02/28 16:11:24
     * @since 302.1.0.0
     */
    R query(T t);
}
