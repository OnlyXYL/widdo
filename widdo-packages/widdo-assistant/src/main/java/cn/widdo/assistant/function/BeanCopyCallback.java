package cn.widdo.assistant.function;

/**
 * bean 复制回调.
 *
 * @param <S>
 * @param <T>
 * @author XYL
 * @version 1.2
 * @date 2022/11/10 19:23
 */
@FunctionalInterface
public interface BeanCopyCallback<S, T> {

    /**
     * 回调方法.
     *
     * @param s
     * @param t
     * @throws
     * @author XYL
     * @className cn.widdo.assistant.function.BeanCopyCallback
     * @date 2022/11/10 19:24
     **/
    void callback(S s, T t);
}
