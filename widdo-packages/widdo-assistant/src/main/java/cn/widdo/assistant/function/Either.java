package cn.widdo.assistant.function;

import cn.widdo.assistant.utils.MapUtil;
import lombok.ToString;
import org.springframework.data.util.Pair;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Either.
 * <p>
 * 处理lambda异常
 *
 * @param <R>
 * @param <L>
 * @author XYL
 * @date 2023/07/28 13:53
 * @since 305.2.2.0
 */
@ToString
public final class Either<L, R> {

	/**
	 * result of left which is exception.
	 */
	private final L left;

	/**
	 * result of right which is the result.
	 */
	private final R right;

	/**
	 * constructor has two params.
	 * @param left
	 * @param right
	 */
	private Either(final L left, final R right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * left result.
	 * @param value
	 * @param <L>
	 * @param <R>
	 * @return Either
	 */
	public static <L, R> Either<L, R> left(L value) {
		return new Either(value, null);
	}

	/**
	 * right result,which is correctly.
	 * @param value
	 * @param <L>
	 * @param <R>
	 * @return Either
	 */
	public static <L, R> Either<L, R> right(R value) {
		return new Either(null, value);
	}

	/**
	 * getLeft.
	 * @return Optional
	 */
	public Optional<L> getLeft() {
		return Optional.ofNullable(left);
	}

	/**
	 * getRight.
	 * @return Optional
	 */
	public Optional<R> getRight() {
		return Optional.ofNullable(right);
	}

	/**
	 * check the left result.
	 * @return boolean
	 */
	public boolean isLeft() {
		return left != null;
	}

	/**
	 * check the right result.
	 * @return boolean
	 */
	public boolean isRight() {
		return right != null;
	}

	/**
	 * @param mapper
	 * @param <T>
	 * @return Optional
	 */
	public <T> Optional<T> mapLeft(Function<? super L, T> mapper) {
		if (isLeft()) {
			return Optional.of(mapper.apply(left));
		}
		return Optional.empty();
	}

	/**
	 * mapRight.
	 * @param <T> t
	 * @param mapper
	 * @return java.util.Optional<T>
	 * @author XYL
	 * @date 2023/07/31 12:52:44
	 */
	public <T> Optional<T> mapRight(Function<? super R, T> mapper) {
		if (isRight()) {
			return Optional.of(mapper.apply(right));
		}
		return Optional.empty();
	}

	/**
	 * lamber 抛出异常 . 发生异常时,流的处理会立即停止
	 * @param <R> r
	 * @param <T> t
	 * @param function
	 * @return java.util.function.Function<T, R>
	 * @author XYL
	 * @date 2023/07/28 13:56:07
	 */
	public static <T, R> Function<T, R> warp(CheckedFunction<T, R> function) {
		return t -> {
			try {
				return function.apply(t);
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		};
	}

	/**
	 * lamber 抛出异常. 发生异常时,流的处理会继续 不保存原始值
	 * @param <R> t
	 * @param <T> t
	 * @param function
	 * @return java.util.function.Function<T, cn.widdo.assistant.function.Either>
	 * @author XYL
	 * @date 2023/07/28 13:56:24
	 */
	public static <T, R> Function<T, Either> lift(CheckedFunction<T, R> function) {
		return t -> {
			try {
				return Either.right(function.apply(t));
			}
			catch (Exception e) {
				return Either.left(e);
			}
		};
	}

	/**
	 * lamber 抛出异常. 发生异常时,流的处理会继续 异常和原始值都保存在左侧
	 * @param <R> r
	 * @param <T> t
	 * @param function
	 * @return java.util.function.Function<T, cn.widdo.assistant.function.Either>
	 * @author XYL
	 * @date 2023/07/28 13:56:37
	 */
	public static <T, R> Function<T, Either> liftWithValue(CheckedFunction<T, R> function) {
		return t -> {
			try {
				return Either.right(function.apply(t));
			}
			catch (Exception ex) {
				return Either.left(Pair.of(ex, t));
			}
		};
	}

	/**
	 * liftWithValue.
	 * @param <T> t
	 * @param <R> r
	 * @param function
	 * @param checkKeys
	 * @return java.util.function.Function<T, cn.widdo.assistant.function.Either>
	 * @author XYL
	 * @date 2023/07/31 12:54:53
	 */
	public static <T, R> Function<T, Either> liftWithValue(CheckedFunction<T, R> function, final String... checkKeys) {
		return t -> {
			try {

				MapUtil.throwExceptionIfNull((Map<String, ?>) t, checkKeys);

				return Either.right(function.apply(t));
			}
			catch (Exception ex) {
				return Either.left(Pair.of(ex, t));
			}
		};
	}

}
