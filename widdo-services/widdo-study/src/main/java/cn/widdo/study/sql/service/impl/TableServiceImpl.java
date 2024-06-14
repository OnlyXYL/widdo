package cn.widdo.study.sql.service.impl;

import cn.widdo.assistant.result.IResultInterface;
import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.autoconfigure.sql.parse.configuration.SQLParserProvider;
import cn.widdo.autoconfigure.sql.parse.parser.HiveSQLParser;
import cn.widdo.starter.sql.entity.Sql;
import cn.widdo.study.sql.service.TableService;
import com.alibaba.druid.DbType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * TableServiceImpl.
 *
 * @author XYL
 * @date 2023/08/03 10:23
 * @since 305.2.2.0
 *
 */
@Service
public class TableServiceImpl implements TableService {

	private static final Logger log = LoggerFactory.getLogger(TableServiceImpl.class);

	@Override
	public WiddoResult parser(Map<String, Object> params) {

		final String sql = params.get("sql").toString();

		final HiveSQLParser parser = SQLParserProvider.<HiveSQLParser>parser(DbType.hive);

		final List<Sql> parse = parser.parse(params);

		return WiddoResult.response(IResultInterface.StudyResultEnum.SUCCESS, parse);
	}

}
