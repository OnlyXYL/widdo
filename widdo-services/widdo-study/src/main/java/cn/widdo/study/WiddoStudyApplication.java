package cn.widdo.study;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类.
 *
 * @author XYL
 * @date 2022/08/15 16:49
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
@SpringBootApplication
public class WiddoStudyApplication {

    private static final Logger LOG = LoggerFactory.getLogger(WiddoStudyApplication.class);

    @PostConstruct
    private void postConstruct() {
        LOG.info("#############################################");
        LOG.info("[Widdo] |- Service [Widdo Study] Application.");
        LOG.info("#############################################");
    }

    public static void main(String[] args) {
        SpringApplication.run(WiddoStudyApplication.class, args);
    }
}
