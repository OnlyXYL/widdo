package cn.widdo.graph.configuration;

import cn.widdo.graph.annotation.ConditionalOnNeo4jEnabled;
import cn.widdo.graph.properties.GraphProperties;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Config;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * neo4j相关配置
 *
 * @author XYL
 * @version 1.0
 * @date 2022/07/14 19:19
 */
@ConditionalOnNeo4jEnabled
public class Neo4jConfiguration {

    @Resource
    private GraphProperties neo4jProperties;

    @Bean
    public Driver driver() {

        /**
         * 配置信息
         */
        Config config = Config.builder()
                .withMaxConnectionLifetime(30, TimeUnit.MINUTES)
                .withMaxConnectionPoolSize(50)
                .withConnectionAcquisitionTimeout(2, TimeUnit.MINUTES)

                /**
                 * During a TLS handshake, the server provides a certificate to the client application. The application can choose to accept or reject this certificate based on one of the following trust strategies:
                 *
                 * {@link https://neo4j.com/docs/driver-manual/current/client-applications/#driver-connection-uris}
                 *
                 * TRUST_ALL_CERTIFICATES (default)    ----------  Accept any certificate provided by the server, regardless of CA chain.
                 *
                 * TRUST_CUSTOM_CA_SIGNED_CERTIFICATES  ----------  Accept any certificate that can be verified against a custom CA.
                 *
                 * TRUST_SYSTEM_CA_SIGNED_CERTIFICATES  ----------   Accept any certificate that can be verified against the system store.
                 *
                 */
                .withTrustStrategy(Config.TrustStrategy.trustAllCertificates())
                .build();

        return GraphDatabase.driver(neo4jProperties.getNeo4j().getUrl(), AuthTokens.basic(neo4jProperties.getNeo4j().getUsername(), neo4jProperties.getNeo4j().getPassword()), config);
    }
}