package cn.widdo.data.configuration;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * 数据处理层配置.
 *
 * @author XYL
 * @date 2022/09/21 16:36
 * @since 263.1.1.0
 */
@Configuration
@Import({MybatisPlusConfiguration.class})
public class DataConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DataConfiguration.class);

    @PostConstruct
    public final void postConstruct() {
        log.info("[Widdo] |- Components [Widdo Data] Auto Configure.");
    }
}
