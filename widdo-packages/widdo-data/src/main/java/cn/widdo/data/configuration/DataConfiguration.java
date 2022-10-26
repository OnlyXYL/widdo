package cn.widdo.data.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * 数据处理层配置
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/09/21 16:36
 */
@Configuration
@Import({MybatisPlusConfiguration.class})
public class DataConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DataConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Widdo] |- Components [Widdo Data] Auto Configure.");
    }
}
