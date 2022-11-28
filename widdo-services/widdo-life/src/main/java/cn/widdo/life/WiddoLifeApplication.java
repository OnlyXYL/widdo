package cn.widdo.life;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * 主函数.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/08/15 14:51
 */
@SpringBootApplication
public class WiddoLifeApplication {

    private static final Logger LOG = LoggerFactory.getLogger(WiddoLifeApplication.class);

    @PostConstruct
    private void postConstruct() {
        LOG.info("[Widdo] |- AutoConfigure [Widdo Neo4j] Actuator.");
    }

    public static void main(String[] args) {
        SpringApplication.run(WiddoLifeApplication.class, args);
    }
}
