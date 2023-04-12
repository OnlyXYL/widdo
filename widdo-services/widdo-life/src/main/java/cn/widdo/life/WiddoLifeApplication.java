package cn.widdo.life;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 主函数.
 *
 * @author XYL
 * @date 2022/08/15 14:51
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
@SpringBootApplication
public class WiddoLifeApplication {

    private static final Logger LOG = LoggerFactory.getLogger(WiddoLifeApplication.class);

    @PostConstruct
    private void postConstruct() {
        LOG.info("[Widdo] |- Service [Widdo Life] Application.");
    }

    public static void main(String[] args) {
        SpringApplication.run(WiddoLifeApplication.class, args);
    }
}
