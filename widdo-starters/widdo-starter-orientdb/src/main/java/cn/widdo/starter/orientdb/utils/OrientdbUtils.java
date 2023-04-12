package cn.widdo.starter.orientdb.utils;

import com.orientechnologies.orient.core.db.ODatabasePool;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.sql.executor.OResultSet;
import org.apache.tinkerpop.gremlin.orientdb.OrientGraph;
import org.apache.tinkerpop.gremlin.orientdb.OrientGraphFactory;

import java.util.Objects;

/**
 * orientdb utils.
 *
 * @author XYL
 * @date 2022/07/15 2:10
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public class OrientdbUtils {

    protected OrientdbUtils() {
        throw new UnsupportedOperationException(OrientdbUtils.class.getName() + "不能被实例化");
    }

    /**
     * orientdb 创建客户端.
     *
     * @param url like--->remote:10.0.47.51
     * @return an orientdb instance
     */
    public static OrientDB createClient(String url) {

        return new OrientDB(url, OrientDBConfig.defaultConfig());
    }

    /**
     * an OrientDB instance.
     *
     * @param url           url
     * @param defaultConfig defaultConfig
     * @return an OrientDB instance
     */
    public static OrientDB createClient(String url, OrientDBConfig defaultConfig) {

        if (Objects.isNull(defaultConfig)) {
            return new OrientDB(url, OrientDBConfig.defaultConfig());
        } else {
            return new OrientDB(url, defaultConfig);
        }
    }

    /**
     * orientdb，建立连接.
     *
     * @param orient   orient
     * @param database database
     * @param userName username
     * @param passWord password
     * @return an ODatabasePool instance
     */
    public static ODatabasePool connect(OrientDB orient, String database, String userName, String passWord) {
        return new ODatabasePool(orient, database, userName, passWord);
    }


    /**
     * 数据库资源关闭操作.
     *
     * @param oResultSet oResultSet
     * @param pool       pool
     * @param orientDB   orientDB
     */
    public static void close(OResultSet oResultSet, ODatabasePool pool, OrientDB orientDB) {

        if (!Objects.isNull(oResultSet)) {

            oResultSet.close();
        }

        if (!Objects.isNull(pool)) {
            pool.close();
        }

        if (!Objects.isNull(orientDB)) {
            orientDB.close();
        }

    }

    /**
     * close OrientGraph.
     *
     * @param orientGraph orientGraph
     */
    public static void close(OrientGraph orientGraph) {
        orientGraph.close();
    }


    /**
     * 打开数据库连接，有事务.
     *
     * @param orientGraphFactory orientGraphFactory
     * @return an OrientGraph instance
     */
    public static OrientGraph openTx(OrientGraphFactory orientGraphFactory) {

        return orientGraphFactory.getTx();
    }


    /**
     * 打开数据库连接，没有事务.
     *
     * @param orientGraphFactory orientGraphFactory
     * @return an OrientGraph instance
     */
    public static OrientGraph openNoTx(OrientGraphFactory orientGraphFactory) {

        return orientGraphFactory.getNoTx();
    }
}
