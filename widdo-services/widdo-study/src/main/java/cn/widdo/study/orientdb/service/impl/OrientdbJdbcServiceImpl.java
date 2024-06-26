package cn.widdo.study.orientdb.service.impl;

import cn.widdo.assistant.result.IResultInterface;
import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.autoconfigure.orientdb.configure.WiddoOrientdbConfigure;
import cn.widdo.starter.orientdb.utils.OrientdbUtils;
import cn.widdo.study.orientdb.service.OrientdbJdbcService;
import com.orientechnologies.orient.core.db.ODatabasePool;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.sql.executor.OResultSet;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * OrientdbJdbcServiceImpl.
 *
 * @author XYL
 * @date 2022/07/15 2:05
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
@Service
@ConditionalOnBean({ WiddoOrientdbConfigure.class })
public class OrientdbJdbcServiceImpl implements OrientdbJdbcService {

	/**
	 * pool.
	 */
	@Resource
	private ODatabasePool pool;

	@Override
	public WiddoResult queryE(Map<String, Object> params) {
		return WiddoResult.response(IResultInterface.StudyResultEnum.SUCCESS);
	}

	@Override
	public WiddoResult queryV(Map<String, Object> params) {
		return WiddoResult.response(IResultInterface.StudyResultEnum.SUCCESS);
	}

	@Override
	public WiddoResult createV(Map<String, Object> params) {

		final String label = params.get("label").toString();

		params.put("date", System.currentTimeMillis());

		OResultSet oResultSet = null;

		try (ODatabaseSession session = pool.acquire()) {

			// 1. 判断class是否存在
			final OClass oClass = session.getClass(label);

			if (oClass == null) {
				// 创建class
				final String createClassSql = String.format("create class %s extends V", label);

				oResultSet = session.execute("sql", createClassSql);

				oResultSet.close();
			}

			final String format = String
					.format("create vertex %s set name = :name, age = :age, city = :city, date = :date", label);

			oResultSet = session.execute("sql", format, params);

			oResultSet.stream().forEach(s -> System.out.println(s.toJSON()));

			oResultSet.close();

		}
		catch (Exception e) {

			if (oResultSet != null) {
				oResultSet.close();
			}
		}

		return WiddoResult.response(IResultInterface.StudyResultEnum.SUCCESS);
	}

	@Override
	public WiddoResult createE(Map<String, Object> params) {

		final String vLabel = params.get("vLabel").toString();
		final String eLabel = params.get("eLabel").toString();

		params.put("date", System.currentTimeMillis());

		OResultSet oResultSet = null;

		try (ODatabaseSession session = pool.acquire()) {

			// 1. 判断class是否存在
			final OClass oClass = session.getClass(eLabel);

			if (oClass == null) {
				// 创建class
				final String createClassSql = String.format("create class %s extends E", eLabel);

				oResultSet = session.execute("sql", createClassSql);

				oResultSet.close();
			}

			final String format = String.format(
					"create edge %s from (select from %s where name = :source) to (select from %s where name = :target) set date = :date",
					eLabel, vLabel, vLabel);

			oResultSet = session.execute("sql", format, params);

			oResultSet.stream().forEach(s -> System.out.println(s.toJSON()));

			oResultSet.close();

		}
		catch (Exception e) {
			if (oResultSet != null) {
				oResultSet.close();
			}
		}

		return WiddoResult.response(IResultInterface.StudyResultEnum.SUCCESS);
	}

	@Override
	public WiddoResult delete() {
		try (ODatabaseSession session = pool.acquire()) {
			// 执行删除
			// <p>OResultSet deleteEdgeRs = session.command("delete Edge where kngraphId =
			// ?", kngraphId);</p>
			OResultSet deleteEdgeRs = session.command("delete Edge where 1 = 1");

			OResultSet deleteNodeRs = session.command("delete Vertex from v  where 1 = 1");

			// 关闭资源
			OrientdbUtils.close(deleteNodeRs, null, null);

			OrientdbUtils.close(deleteEdgeRs, null, null);
		}

		return WiddoResult.response(IResultInterface.StudyResultEnum.SUCCESS);
	}

}
