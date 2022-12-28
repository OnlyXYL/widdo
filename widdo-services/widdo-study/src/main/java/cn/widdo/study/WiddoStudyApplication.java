package cn.widdo.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * 启动类.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/08/15 16:49
 */
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
