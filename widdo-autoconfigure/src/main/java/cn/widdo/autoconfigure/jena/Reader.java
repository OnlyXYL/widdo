package cn.widdo.autoconfigure.jena;

/**
 * reader.
 *
 * @param <R> r
 * @param <T> t
 * @author XYL
 * @date 2023/03/13 19:01
 * @since 302.1.0.0
 */
@SuppressWarnings({ "AlibabaClassNamingShouldBeCamel" })
public interface Reader<T, R> extends JRunner {

	/**
	 * read.
	 * @param params params
	 * @return R r
	 * @author XYL
	 * @date 2023/03/13 19:12:37
	 */
	R read(T params);

}
