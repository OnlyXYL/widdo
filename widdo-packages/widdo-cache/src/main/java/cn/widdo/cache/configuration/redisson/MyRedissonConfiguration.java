package cn.widdo.cache.configuration.redisson;

import cn.widdo.cache.annotation.ConditionalOnRedissonEnabled;
import cn.widdo.cache.properties.redisson.MyRedissonProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * redisson配置.
 *
 * @author XYL
 * @date 2022/09/07 18:13
 * @since 302.1.0.0
 */
@Configuration
@ConditionalOnRedissonEnabled
@EnableConfigurationProperties(MyRedissonProperties.class)
@Import({MyRedissonClusterConfiguration.class, MyRedissonSingleConfiguration.class})
public class MyRedissonConfiguration {

    private final Logger log = LoggerFactory.getLogger(MyRedissonConfiguration.class);

    @PostConstruct
    public final void postConstruct() {
        log.info("[Widdo] |- Components [Widdo Redisson] Auto Configure.");
    }
}
