package cn.widdo.autoconfigure.sql.properties;

import cn.widdo.assistant.constant.PropertyConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * WiddoSQLProperties.
 *
 * @author XYL
 * @date 2023/08/07 16:49
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
@ConfigurationProperties(prefix = PropertyConstant.PROPERTY_WIDDO_SQL)
public class WiddoSQLProperties {

	/**
	 * dataType {@link cn.widdo.starter.sql.enums.DBType}.
	 */
	private String dbType;

	/**
	 * parser.
	 */
	private final Parser parser = new Parser();

	/**
	 * get sql parse.
	 * @return return a parser of sql
	 */
	public Parser getParser() {
		return parser;
	}

	/**
	 * get dbType.
	 * @return return a dbType
	 */
	public String getDbType() {
		return dbType;
	}

	/**
	 * set dbType.
	 * @param dbType
	 */
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public static class Parser {

		/**
		 * 开启读写开关.
		 */
		private Boolean enable = false;

		/**
		 * get flag of sql parser.
		 * @return a result type of Boolean
		 */
		public Boolean getEnable() {
			return enable;
		}

		/**
		 * set flag of sql parser.
		 * @param enable
		 */
		public void setEnable(Boolean enable) {
			this.enable = enable;
		}

	}

}
