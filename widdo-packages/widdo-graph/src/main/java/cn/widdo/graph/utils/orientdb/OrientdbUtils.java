package cn.widdo.graph.utils.orientdb;

import com.orientechnologies.orient.core.db.ODatabasePool;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.sql.executor.OResultSet;
import org.apache.tinkerpop.gremlin.orientdb.OrientGraph;
import org.apache.tinkerpop.gremlin.orientdb.OrientGraphFactory;

import java.util.Objects;

/**
 * @author XYL
 * @version 1.0
 * @date 2022/07/15 2:10
 */
public class OrientdbUtils {

    /**
     * orientdb 创建客户端
     *
     * @param url like--->remote:10.0.47.51
     * @return
     */
    public static OrientDB createClient(String url) {
        OrientDB orient = new OrientDB(url, OrientDBConfig.defaultConfig());

        return orient;
    }

    public static OrientDB createClient(String url, OrientDBConfig defaultConfig) {

        if (Objects.isNull(defaultConfig)) {
            OrientDB orient = new OrientDB(url, OrientDBConfig.defaultConfig());
            return orient;
        } else {
            OrientDB orient = new OrientDB(url, defaultConfig);
            return orient;
        }
    }

    /**
     * orientdb，建立连接
     *
     * @param database
     * @param userName
     * @param passWord
     * @return
     */
    public static ODatabasePool connect(OrientDB orient, String database, String userName, String passWord) {

        ODatabasePool pool = new ODatabasePool(orient, database, userName, passWord);

        return pool;
    }


    /**
     * 数据库资源关闭操作
     *
     * @param oResultSet
     * @param pool
     * @param orientDB
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

    public static void close(OrientGraph orientGraph) {

        orientGraph.close();
    }


    /**
     * 打开数据库连接，有事务
     *
     * @param orientGraphFactory
     * @return
     */
    public static OrientGraph openTx(OrientGraphFactory orientGraphFactory) {

        OrientGraph noTx = orientGraphFactory.getTx();

        return noTx;
    }


    /**
     * 打开数据库连接，没有事务
     *
     * @param orientGraphFactory
     * @return
     */
    public static OrientGraph openNoTx(OrientGraphFactory orientGraphFactory) {

        OrientGraph noTx = orientGraphFactory.getNoTx();

        return noTx;
    }
}
