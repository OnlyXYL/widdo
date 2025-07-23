package cn.widdo;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 注册中心启动类.
 *
 * @author XYL
 * @date 2025/07/20 23:31:45
 * @since 353.0.0.0
 */
@SpringBootApplication
public class WiddoRegisterApplication {

    private static final Logger LOG = LoggerFactory.getLogger(WiddoRegisterApplication.class);

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("#############################################");
        LOG.info("[Widdo] |- Service [Widdo Register] Application.");
        LOG.info("#############################################");
    }
}
