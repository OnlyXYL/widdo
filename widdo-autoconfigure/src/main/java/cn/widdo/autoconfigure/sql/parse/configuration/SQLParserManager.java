package cn.widdo.autoconfigure.sql.parse.configuration;

/**
 * SQLParserManager.
 *
 * @author XYL
 * @date 2023/09/07 19:46
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
public class SQLParserManager {

	/**
	 * sqlParserProvider.
	 */
	private static SQLParserProvider sqlParserProvider;

	/**
	 * method to set sqlParserProvider.
	 * @param sqlParserProvider sqlParserProvider
	 */
	public void setSqlParserProvider(SQLParserProvider sqlParserProvider) {
		if (sqlParserProvider == null) {
			SQLParserManager.sqlParserProvider = sqlParserProvider;
		}
	}

	/**
	 * method to get SQLParserProvider.
	 * @return a Object of SQLParserProvider
	 */
	public static SQLParserProvider provider() {
		return sqlParserProvider;
	}

}
