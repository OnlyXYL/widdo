package cn.widdo.cloud.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/06/10 16:31
 */
@SpringBootApplication
public class WiddoCloudGatewayApplication {

    private static final Logger LOG = LoggerFactory.getLogger(WiddoCloudGatewayApplication.class);

    @PostConstruct
    private void postConstruct() {
        LOG.info("[Widdo] |- Cloud [Widdo Gateway] Application.");
    }


    public static void main(String[] args) {
        SpringApplication.run(WiddoCloudGatewayApplication.class, args);
    }
}
