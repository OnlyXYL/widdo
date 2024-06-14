package cn.widdo.starter.sql.entity;

import java.util.List;

/**
 * Sql.
 *
 * @author XYL
 * @date 2023/09/06 15:34
 * @since 305.2.2.0
 */
public class Sql {

	/**
	 * 数据库.
	 */
	private String databaseName;

	/**
	 * 表名.
	 */
	private String tableName;

	/**
	 * 操作:{@link cn.widdo.starter.sql.enums.DBModeTypeEnum}.
	 */
	private String mode;

	/**
	 * 列.
	 */
	private List<String> columns;

	/**
	 * constructor has no params.
	 */
	public Sql() {
	}

	/**
	 * constructor has four params.
	 * @param databaseName databaseName
	 * @param tableName tableName
	 * @param mode mode
	 * @param columns columns
	 */
	public Sql(final String databaseName, final String tableName, final String mode, final List<String> columns) {
		this.databaseName = databaseName;
		this.tableName = tableName;
		this.mode = mode;
		this.columns = columns;
	}

	/**
	 * return tableName.
	 * @return a tableName type of String
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * set tableName.
	 * @param tableName tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * return sql mode,like: select、insert、update、delete etc.
	 * @return return sql mode type of String
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * set sql mode.
	 * @param mode mode.
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * get databaseName.
	 * @return a databaseName type of String
	 */
	public String getDatabaseName() {
		return databaseName;
	}

	/**
	 * set databaseName.
	 * @param databaseName databaseName
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	/**
	 * get table columns.
	 * @return return columns of a table
	 */
	public List<String> getColumns() {
		return columns;
	}

	/**
	 * set columns of a table.
	 * @param columns columns
	 */
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

}
