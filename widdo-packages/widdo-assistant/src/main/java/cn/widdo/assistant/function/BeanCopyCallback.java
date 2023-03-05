package cn.widdo.assistant.function;

/**
 * bean 复制回调.
 *
 * @param <S>
 * @param <T>
 * @author XYL
 * @since 263.1.1.0
 * @date 2022/11/10 19:23
 */
@FunctionalInterface
public interface BeanCopyCallback<S, T> {

    /**
     * 回调方法.
     *
     * @param s s
     * @param t t
     * @author XYL
     * @className cn.widdo.assistant.function.BeanCopyCallback
     * @date 2022/11/10 19:24
     **/
    void callback(S s, T t);
}
