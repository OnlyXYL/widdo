package cn.widdo.autoconfigure.sql.parse.parser;

/**
 * AbstractSQLParserDecorator.
 *
 * @param <T> t
 * @param <R> r
 * @author XYL
 * @date 2023/08/07 17:19
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
public abstract class AbstractSQLParserDecorator<T, R> extends AbstractSQLParser<T, R> {

	/**
	 * {@link SQLParser}.
	 */
	protected SQLParser<T, R> sqlParser;

}
