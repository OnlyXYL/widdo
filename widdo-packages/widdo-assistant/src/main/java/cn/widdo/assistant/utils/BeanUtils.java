package cn.widdo.assistant.utils;

import cn.widdo.assistant.function.BeanCopyCallback;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * bean 工具类.
 *
 * @author XYL
 * @date 2022/11/10 18:47
 * @since 263.1.1.0
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    /**
     * 拷贝集合.
     *
     * @param <S>    源集合中数据类型
     * @param <T>    目标集合数据类型
     * @param source 源集合
     * @param target 目的集合
     * @return java.util.List<T>
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
     * @param <S>      源集合中数据类型
     * @param <T>      目标集合数据类型
     * @param source   源集合
     * @param target   目的集合
     * @param callback 回调方法
     * @return java.util.List<T>
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

    /**
     * 類型轉換.
     *
     * @param <T> t
     * @param obj obj
     * @return T
     * @throws UnsupportedOperationException 不支持的類型轉換異常
     * @author XYL
     * @date 2023/03/01 14:32:54
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {

        if (obj instanceof Map) {
            return (T) obj;
        }

        if (obj instanceof List) {
            return (T) obj;
        }

        throw new UnsupportedOperationException("can`t cast.");
    }
}
