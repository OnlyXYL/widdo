package cn.widdo.autoconfigure.sql.parse.parser;

import cn.widdo.starter.sql.enums.DBType;
import org.apache.dubbo.common.extension.SPI;

/**
 * SQLParser.
 *
 * @param <T> t
 * @param <R> r
 * @author XYL
 * @date 2023/08/07 17:11
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
@SPI("hive")
public interface SQLParser<T, R> {

	/**
	 * 解析器类型.
	 * @param dbType dbType
	 * @return boolean
	 * @author XYL
	 * @date 2023/08/07 17:18:12
	 */
	boolean support(DBType dbType);

	/**
	 * 解析sql.
	 * @param params 参数
	 * @return R 返回值
	 * @author XYL
	 * @date 2023/08/07 17:12:32
	 */
	R parse(T params);

}
