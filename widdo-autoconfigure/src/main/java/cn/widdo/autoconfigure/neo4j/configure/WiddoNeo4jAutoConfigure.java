package cn.widdo.autoconfigure.neo4j.configure;

import cn.widdo.autoconfigure.condition.WiddoNeo4j;
import cn.widdo.autoconfigure.neo4j.properties.WiddoNeo4jProperties;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Config;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * neo4j 自动配置类.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/14 14:57
 */
@WiddoNeo4j
@EnableConfigurationProperties(WiddoNeo4jProperties.class)
public class WiddoNeo4jAutoConfigure {

    private static final Logger log = LoggerFactory.getLogger(WiddoNeo4jProperties.class);

    @PostConstruct
    private void postConstruct() {
        log.info("[Widdo] |- AutoConfigure [Widdo Neo4j] Auto Configure.");
    }

    /**
     * driver instance.
     *
     * @param widdoNeo4jProperties
     * @return a driver instance
     */
    @Bean
    @ConditionalOnMissingBean(Driver.class)
    public Driver driver(WiddoNeo4jProperties widdoNeo4jProperties) {

        /*
          配置信息
         */
        Config config = Config.builder()
                .withMaxConnectionLifetime(30, TimeUnit.MINUTES)
                .withMaxConnectionPoolSize(50)
                .withConnectionAcquisitionTimeout(2, TimeUnit.MINUTES)

                /*
                  During a TLS handshake, the server provides a certificate to the client application.
                  The application can choose to accept or reject this certificate based on one of the following trust strategies:

                  {@link https://neo4j.com/docs/driver-manual/current/client-applications/#driver-connection-uris}

                  TRUST_ALL_CERTIFICATES (default)    ----------  Accept any certificate provided by the server, regardless of CA chain.

                  TRUST_CUSTOM_CA_SIGNED_CERTIFICATES  ----------  Accept any certificate that can be verified against a custom CA.

                  TRUST_SYSTEM_CA_SIGNED_CERTIFICATES  ----------   Accept any certificate that can be verified against the system store.

                 */
                .withTrustStrategy(Config.TrustStrategy.trustAllCertificates())
                .build();

        return GraphDatabase.driver(widdoNeo4jProperties.getHost().getUrl(), AuthTokens.basic(widdoNeo4jProperties.getHost().getUsername(), widdoNeo4jProperties.getHost().getPassword()), config);
    }

}
