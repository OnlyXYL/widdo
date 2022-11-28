package cn.widdo.assistant.utils;

import cn.widdo.assistant.function.BeanCopyCallback;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * bean 工具类.
 *
 * @author XYL
 * @version 1.2
 * @date 2022/11/10 18:47
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    /**
     * 拷贝集合.
     *
     * @param <S>
     * @param <T>
     * @param source 源集合
     * @param target 目的集合
     * @return java.util.List<T>
     * @throws
     * @author XYL
     * @className cn.widdo.assistant.utils.BeanUtils
     * @date 2022/11/10 19:27
     **/
    public static <S, T> List<T> copyList(final List<S> source,
                                          final Supplier<T> target) {
        return copyList(source, target, null);
    }

    /**
     * 拷贝集合.
     *
     * @param <S>
     * @param <T>
     * @param source   源集合
     * @param target   目的集合
     * @param callback
     * @return java.util.List<T>
     * @throws
     * @author XYL
     * @className cn.widdo.assistant.utils.BeanUtils
     * @date 2022/11/10 19:27
     **/
    public static <S, T> List<T> copyList(final List<S> source,
                                          final Supplier<T> target,
                                          final BeanCopyCallback<S, T> callback) {

        Assert.notEmpty(source, "源数据不能为空");

        final List<T> list = new ArrayList<>(source.size());
        for (S s : source) {
            final T t = target.get();
            copyProperties(s, t);
            list.add(t);
            if (callback != null) {
                callback.callback(s, t);
            }
        }
        return list;
    }
}
