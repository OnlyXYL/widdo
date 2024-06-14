package cn.widdo.autoconfigure.sql.parse.parser;

import cn.widdo.starter.sql.entity.Sql;
import cn.widdo.starter.sql.enums.DBType;
import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.hive.visitor.HiveSchemaStatVisitor;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * HiveSQLParser.
 * <p>
 * hive的sql解析器
 *
 * @author XYL
 * @date 2023/08/07 17:18
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
public class HiveSQLParser extends AbstractSQLParserDecorator<Map<String, Object>, List<Sql>> {

	private final Logger logger = LoggerFactory.getLogger(HiveSQLParser.class);

	@Override
	public boolean support(DBType dbType) {
		return DBType.HIVE.equals(dbType);
	}

	@Override
	public List<Sql> parse(Map<String, Object> params) {

		final String sql = params.get("sql").toString();

		logger.info("------------【Hive SQL 解析】，SQL：{} -------------", sql);

		return parseSQL(sql);
	}

	@Override
	protected HiveSchemaStatVisitor visitor(String sql) {
		final SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, DbType.hive);

		// 解析sql
		final SQLStatement statement = parser.parseStatement();

		final HiveSchemaStatVisitor visitor = (HiveSchemaStatVisitor) SQLUtils.createSchemaStatVisitor(DbType.hive);
		statement.accept(visitor);
		return visitor;
	}

}
