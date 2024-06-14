package cn.widdo.assistant.function;

/**
 * CheckedFunction.
 *
 * @param <R> r
 * @param <T> t
 * @author XYL
 * @date 2023/07/28 13:53
 * @since 305.2.2.0
 */
@FunctionalInterface
public interface CheckedFunction<T, R> {

	/**
	 * apply.
	 * @param t t
	 * @return R
	 * @throws Exception exception
	 * @author XYL
	 * @date 2023/07/28 13:54:50
	 */
	R apply(T t) throws Exception;

}
