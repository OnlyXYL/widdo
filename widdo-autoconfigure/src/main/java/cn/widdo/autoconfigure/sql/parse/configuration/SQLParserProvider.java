package cn.widdo.autoconfigure.sql.parse.configuration;

import cn.widdo.autoconfigure.sql.parse.parser.SQLParser;
import com.alibaba.druid.DbType;
import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * SQLParserProvider.
 * <p>
 * SQL解析管理器。主要用来初始化各种解析器
 *
 * @author XYL
 * @date 2023/09/07 11:22
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
public class SQLParserProvider {

	/**
	 * constructor has on params.
	 */
	protected SQLParserProvider() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 获取SQL解析器.
	 * @param dbType
	 * @param <T> t
	 * @return cn.widdo.autoconfigure.sql.parse.parser.SQLParser
	 * @author XYL
	 * @date 2023/09/07 19:38:36
	 * @since 305.2.2.0
	 */
	public static <T extends SQLParser> T parser(DbType dbType) {

		final ExtensionLoader<SQLParser> extensionLoader = ExtensionLoader.getExtensionLoader(SQLParser.class);

		return (T) extensionLoader.getExtension(dbType.name());
	}

}
