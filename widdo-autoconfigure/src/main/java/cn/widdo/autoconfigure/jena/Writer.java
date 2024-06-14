package cn.widdo.autoconfigure.jena;

/**
 * writer.
 *
 * @param <R> r
 * @param <T> t
 * @author XYL
 * @date 2023/03/13 18:52
 * @since 302.1.0.0
 */
@SuppressWarnings({ "AlibabaClassNamingShouldBeCamel" })
public interface Writer<T, R> extends JRunner {

	/**
	 * writer.
	 * @param params params
	 * @return R r
	 * @author XYL
	 * @date 2023/03/13 18:59:10
	 */
	R write(T params);

}
