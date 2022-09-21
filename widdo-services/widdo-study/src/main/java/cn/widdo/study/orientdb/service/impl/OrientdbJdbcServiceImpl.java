package cn.widdo.study.orientdb.service.impl;

import cn.widdo.assistant.entity.result.JsonResult;
import cn.widdo.graph.configuration.OrientdbConfiguration;
import cn.widdo.graph.utils.orientdb.OrientdbUtils;
import cn.widdo.study.orientdb.service.OrientdbJdbcService;
import com.orientechnologies.orient.core.db.ODatabasePool;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.sql.executor.OResultSet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author XYL
 * @version 1.0
 * @date 2022/07/15 2:05
 */
@Service
@ConditionalOnBean({OrientdbConfiguration.class})
public class OrientdbJdbcServiceImpl implements OrientdbJdbcService {

    @Resource
    private ODatabasePool pool;

    @Override
    public JsonResult queryE(Map<String, Object> params) {
        return JsonResult.success();
    }

    @Override
    public JsonResult queryV(Map<String, Object> params) {
        return JsonResult.success();
    }

    @Override
    public JsonResult createV(Map<String, Object> params) {

        final String label = params.get("label").toString();

        params.put("date", System.currentTimeMillis());

        OResultSet oResultSet = null;

        try (final ODatabaseSession session = pool.acquire();) {

            //1. 判断class是否存在
            final OClass oClass = session.getClass(label);

            if (oClass == null) {
                //创建class
                final String createClassSql = String.format("create class %s extends V", label);

                oResultSet = session.execute("sql", createClassSql);

                oResultSet.close();
            }

            final String format = String.format("create vertex %s set name = :name, age = :age, city = :city, date = :date", label);

            oResultSet = session.execute("sql", format, params);

            oResultSet.stream().forEach(s -> System.out.println(s.toJSON()));

            oResultSet.close();

        } catch (Exception e) {

            if (oResultSet != null) {
                oResultSet.close();
            }
        }

        return JsonResult.success();
    }

    @Override
    public JsonResult createE(Map<String, Object> params) {

        final String vLabel = params.get("vLabel").toString();
        final String eLabel = params.get("eLabel").toString();

        params.put("date", System.currentTimeMillis());

        OResultSet oResultSet = null;

        try (final ODatabaseSession session = pool.acquire();) {

            //1. 判断class是否存在
            final OClass oClass = session.getClass(eLabel);

            if (oClass == null) {
                //创建class
                final String createClassSql = String.format("create class %s extends E", eLabel);

                oResultSet = session.execute("sql", createClassSql);

                oResultSet.close();
            }

            final String format = String.format("create edge %s from (select from %s where name = :source) to (select from %s where name = :target) set date = :date", eLabel, vLabel, vLabel);

            oResultSet = session.execute("sql", format, params);

            oResultSet.stream().forEach(s -> System.out.println(s.toJSON()));

            oResultSet.close();

        } catch (Exception e) {
            if (oResultSet != null) {
                oResultSet.close();
            }
        }

        return JsonResult.success();
    }

    @Override
    public JsonResult delete() {
        try (ODatabaseSession session = pool.acquire()) {
            //执行删除
//            OResultSet deleteEdgeRs = session.command("delete Edge where kngraphId = ?", kngraphId);
            OResultSet deleteEdgeRs = session.command("delete Edge where 1 = 1");

            OResultSet deleteNodeRs = session.command("delete Vertex from v  where 1 = 1");

            //关闭资源
            OrientdbUtils.close(deleteNodeRs, null, null);

            OrientdbUtils.close(deleteEdgeRs, null, null);
        }

        return JsonResult.success();
    }
}



