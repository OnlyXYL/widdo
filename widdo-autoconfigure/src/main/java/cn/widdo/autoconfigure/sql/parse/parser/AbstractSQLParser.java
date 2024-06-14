package cn.widdo.autoconfigure.sql.parse.parser;

import cn.widdo.starter.neo4j.validator.ParamsValidator;
import cn.widdo.starter.sql.entity.Sql;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * AbstractSQLParser.
 * <p>
 * 用来提取sql中的表明、字段名等，使用druid来实现，但是原始api实现的不友好
 *
 * @param <T> t
 * @param <R> r
 * @author XYL
 * @date 2023/08/07 16:17
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
public abstract class AbstractSQLParser<T, R> extends ParamsValidator implements SQLParser<T, R> {

	/**
	 * vistor.
	 * @param sql sql
	 * @return com.alibaba.druid.sql.visitor.SchemaStatVisitor
	 * @author XYL
	 * @date 2023/08/07 18:57:02
	 */
	protected abstract SchemaStatVisitor visitor(String sql);

	/**
	 * 解析sql.
	 * @param tables tables
	 * @param columnList columnList
	 * @return java.util.List<cn.widdo.starter.sql.entity.Sql>
	 * @author XYL
	 * @date 2023/09/06 15:44:15
	 */
	protected List<Sql> convertSQL(Map<TableStat.Name, TableStat> tables, Collection<TableStat.Column> columnList) {

		final Map<String, List<String>> tableColumnMap = columnList.stream()
				.collect(Collectors.groupingBy(TableStat.Column::getTable)).entrySet().stream()
				.collect(Collectors.toMap(k -> tableName(k.getKey()), v -> columns(v.getValue())));

		return tables.entrySet().stream().map(s -> creatSQLObject(s, tableColumnMap)).collect(Collectors.toList());
	}

	/**
	 * 解析SQL.
	 * <p>
	 * 考虑到多个sql语句同时执行的情况。首先需要使用分号对sql进行切分
	 * @param sql
	 * @return java.util.List<cn.widdo.starter.sql.entity.Sql>
	 * @author XYL
	 * @date 2023/09/07 11:15:59
	 */
	protected List<Sql> parseSQL(String sql) {
		try {
			// sql前处理
			return Arrays.asList(sql.split(";")).stream().filter(StringUtils::isNotBlank)
					.flatMap(s -> singleSql(s).stream()).collect(Collectors.toList());
		}
		catch (Exception e) {
			throw new RuntimeException("SQL语句存在问题");
		}
	}

	/**
	 * 处理单个sql语句.
	 * <p>
	 * 分号分割之后的每个单独sql语句
	 * @param sql
	 * @return java.util.List<cn.widdo.starter.sql.entity.Sql>
	 * @author XYL
	 * @date 2023/09/07 11:15:33
	 */
	private List<Sql> singleSql(String sql) {

		final SchemaStatVisitor visitor = visitor(sql);

		return convertSQL(visitor.getTables(), visitor.getColumns());
	}

	/**
	 * 封装Sql对象.
	 * @param entry
	 * @param tableColumnMap
	 * @return cn.widdo.starter.sql.entity.Sql
	 * @author XYL
	 * @date 2023/09/07 00:28:36
	 */
	private Sql creatSQLObject(Map.Entry<TableStat.Name, TableStat> entry, Map<String, List<String>> tableColumnMap) {

		// table name
		final String name = entry.getKey().getName();

		String databaseName = null;
		if (name.contains(".")) {
			databaseName = name.split("\\.")[0];
		}

		final String mode = entry.getValue().toString();

		return new Sql(databaseName, name, mode, tableColumnMap.get(name));
	}

	/**
	 * 解析表名.
	 * @param talbe
	 * @return java.lang.String
	 * @author XYL
	 * @date 2023/09/06 16:02:58
	 */
	private String tableName(String talbe) {
		if (talbe.contains(".")) {
			return talbe.split("\\.")[1];
		}
		return talbe;
	}

	/**
	 * 解析列.
	 * @param columns
	 * @return java.util.List<java.lang.String>
	 * @author XYL
	 * @date 2023/09/06 16:03:12
	 */
	private List<String> columns(List<TableStat.Column> columns) {
		return columns.stream().map(c -> c.getName()).collect(Collectors.toList());
	}

}
