package cn.widdo.autoconfigure.orientdb.configure;

import cn.widdo.autoconfigure.condition.ConditionalOnOrientdbEnabled;
import cn.widdo.autoconfigure.orientdb.properties.WiddoOrientdbProperties;
import cn.widdo.starter.orientdb.WiddoStarterOrientdb;
import cn.widdo.starter.orientdb.utils.OrientdbUtils;
import com.orientechnologies.orient.client.remote.OStorageRemote;
import com.orientechnologies.orient.core.config.OGlobalConfiguration;
import com.orientechnologies.orient.core.db.ODatabasePool;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import org.apache.tinkerpop.gremlin.orientdb.OrientGraphFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * widdo orientdb configure
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/14 15:23
 */
@ConditionalOnOrientdbEnabled
@ConditionalOnClass(WiddoStarterOrientdb.class)
@EnableConfigurationProperties(WiddoOrientdbProperties.class)
public class WiddoOrientdbConfigure {

    private static final Logger log = LoggerFactory.getLogger(WiddoOrientdbConfigure.class);

    @PostConstruct
    private void postConstruct() {
        log.info("[Widdo] |- AutoConfigure [Widdo Orientdb] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean(OrientGraphFactory.class)
    public OrientGraphFactory orientGraphFactory(WiddoOrientdbProperties widdoOrientdbProperties) {
        return new OrientGraphFactory(widdoOrientdbProperties.getUri(), widdoOrientdbProperties.getUrl(), widdoOrientdbProperties.getPassword()).setupPool(1, 50);
    }

    @Bean
    @ConditionalOnMissingBean(ODatabasePool.class)
    public ODatabasePool oDatabasePool(WiddoOrientdbProperties widdoOrientdbProperties) {

        OrientDBConfig config = OrientDBConfig.builder()
                //设置负载均衡策略
                .addConfig(OGlobalConfiguration.CLIENT_CONNECTION_STRATEGY, OStorageRemote.CONNECTION_STRATEGY.ROUND_ROBIN_REQUEST.toString())
                //设置session超时时间
                .addConfig(OGlobalConfiguration.NETWORK_TOKEN_EXPIRE_TIMEOUT, 30)
//                .addConfig(OGlobalConfiguration.CLIENT_CHANNEL_MAX_POOL, 20)
                .addConfig(OGlobalConfiguration.DB_POOL_MAX, 20)
                .addConfig(OGlobalConfiguration.DB_POOL_MIN, 10)
                .build();

        //1.创建客户端
        //noinspection AlibabaLowerCamelCaseVariableNaming
        @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming") OrientDB orientDB = OrientdbUtils.createClient(widdoOrientdbProperties.getUrl(), config);

        //2. 建立连接
        ODatabasePool pool = OrientdbUtils.connect(orientDB, widdoOrientdbProperties.getDatabase(), widdoOrientdbProperties.getUsername(), widdoOrientdbProperties.getPassword());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("关闭OrientDB连接");
            pool.close();
        }));

        return pool;

    }
}
