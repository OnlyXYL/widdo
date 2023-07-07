package cn.widdo.cloud.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

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
        SpringApplication.run(WiddoGatewayApplication.class, args);
    }
}
