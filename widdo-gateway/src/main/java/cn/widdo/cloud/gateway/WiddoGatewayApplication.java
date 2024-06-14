package cn.widdo.cloud.gateway;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author XYL
 * @date 2022/06/10 16:31
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
@SpringBootApplication
public class WiddoGatewayApplication {

    private static final Logger LOG = LoggerFactory.getLogger(WiddoGatewayApplication.class);

    @PostConstruct
    private void postConstruct() {
        LOG.info("[Widdo] |- Cloud [Widdo Gateway] Application.");
    }

    public static void main(String[] args) {
//		SpringApplication.run(WiddoGatewayApplication.class, args);
        final ConfigurableApplicationContext context = SpringApplication.run(WiddoGatewayApplication.class, args);

        final String server = context.getEnvironment().getProperty("spring.cloud.nacos.discovery.server-addr");
        final String namespace = context.getEnvironment().getProperty("spring.cloud.nacos.discovery.namespace");
        final String group = context.getEnvironment().getProperty("spring.cloud.nacos.discovery.group");

        LOG.info("spring.cloud.nacos.server:{}，namespace:{}，group:{}", server, namespace, group);
    }

}
