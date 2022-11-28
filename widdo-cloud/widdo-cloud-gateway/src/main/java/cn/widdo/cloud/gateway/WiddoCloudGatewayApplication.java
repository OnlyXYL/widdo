package cn.widdo.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/06/10 16:31
 */
@SpringBootApplication
public class WiddoCloudGatewayApplication {
    protected WiddoCloudGatewayApplication() {
        throw new UnsupportedOperationException("");
    }

    public static void main(String[] args) {
        SpringApplication.run(WiddoCloudGatewayApplication.class, args);
    }
}
