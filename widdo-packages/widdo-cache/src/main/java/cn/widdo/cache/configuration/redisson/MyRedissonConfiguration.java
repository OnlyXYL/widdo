package cn.widdo.cache.configuration.redisson;

import cn.widdo.cache.annotation.ConditionalOnRedissonEnabled;
import cn.widdo.cache.properties.redisson.MyRedissonProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * redisson配置
 *
 * @author XYL
 * @version 1.2
 * @date 2022/09/07 18:13
 */
@Configuration
@ConditionalOnRedissonEnabled
@EnableConfigurationProperties(MyRedissonProperties.class)
@Import({MyRedissonClusterConfiguration.class, MyRedissonSingleConfiguration.class})
public class MyRedissonConfiguration {

    private Logger log = LoggerFactory.getLogger(MyRedissonConfiguration.class);
}
