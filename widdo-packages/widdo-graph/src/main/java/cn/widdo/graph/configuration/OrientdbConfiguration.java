package cn.widdo.graph.configuration;

import cn.widdo.graph.annotation.ConditionalOnOrientdbEnabled;
import cn.widdo.graph.properties.GraphProperties;
import cn.widdo.graph.utils.orientdb.OrientdbUtils;
import com.orientechnologies.orient.client.remote.OStorageRemote;
import com.orientechnologies.orient.core.config.OGlobalConfiguration;
import com.orientechnologies.orient.core.db.ODatabasePool;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import org.apache.tinkerpop.gremlin.orientdb.OrientGraphFactory;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * orientdb配置
 *
 * @author XYL
 * @version 1.0
 * @date 2022/08/15 15:28
 */
@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
@ConditionalOnOrientdbEnabled
public class OrientdbConfiguration {

    @Resource
    private GraphProperties graphProperties;

    @Bean
    public OrientGraphFactory orientGraphFactory() {
        return new OrientGraphFactory(graphProperties.getOrientdb().getUri(), graphProperties.getOrientdb().getUrl(), graphProperties.getOrientdb().getPassword()).setupPool(1, 50);
    }

    @Bean
    public ODatabasePool oDatabasePool() {

        OrientDBConfig config = OrientDBConfig.builder()
                //设置负载均衡策略
                .addConfig(OGlobalConfiguration.CLIENT_CONNECTION_STRATEGY, OStorageRemote.CONNECTION_STRATEGY.ROUND_ROBIN_REQUEST.toString())
                //设置session超时时间
                .addConfig(OGlobalConfiguration.NETWORK_TOKEN_EXPIRE_TIMEOUT, 30)
//                .addConfig(OGlobalConfiguration.CLIENT_CHANNEL_MAX_POOL, 20)
                .addConfig(OGlobalConfiguration.DB_POOL_MAX,20)
                .addConfig(OGlobalConfiguration.DB_POOL_MIN,10)
                .build();

        //1.创建客户端
        OrientDB orientDB = OrientdbUtils.createClient(graphProperties.getOrientdb().getUrl(), config);

        //2. 建立连接
        ODatabasePool pool = OrientdbUtils.connect(orientDB, graphProperties.getOrientdb().getDatabase(), graphProperties.getOrientdb().getUsername(), graphProperties.getOrientdb().getPassword());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("关闭OrientDB连接");
            pool.close();
        }));

        return pool;

    }
}
