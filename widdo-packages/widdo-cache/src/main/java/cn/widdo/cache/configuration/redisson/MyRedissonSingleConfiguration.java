package cn.widdo.cache.configuration.redisson;

import cn.widdo.cache.annotation.ConditionalOnRedissonEnabled;
import cn.widdo.cache.properties.redisson.MyRedissonConfigProperties;
import cn.widdo.cache.properties.redisson.MyRedissonProperties;
import cn.widdo.cache.properties.redisson.MyRedissonSingleServerConfigProperties;
import cn.widdo.cache.utils.redisson.LockerUtils;
import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.config.TransportMode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * 单机配置.
 *
 * @author XYL
 * @since 263.1.1.0
 * @date 2022/06/21 19:29
 */
@Data
@ConditionalOnRedissonEnabled
@ConditionalOnProperty(name = "spring.redis.redisson.config.mode", havingValue = "single")
public class MyRedissonSingleConfiguration {

    /**
     * 属性配置.
     */
    private MyRedissonProperties myRedissonProperties;

    /**
     * 客户端.
     */
    private RedissonClient redissonClient;

    /**
     * 客户端实例.
     *
     * @return org.redisson.api.RedissonClient
     * @author XYL
     * @date 2022/11/28 10:42:48
     **/
    @Bean
    public RedissonClient redissonClient() {

        final MyRedissonConfigProperties propertiesConfig = myRedissonProperties.getConfig();

        final MyRedissonSingleServerConfigProperties singleServerConfig = propertiesConfig.getSingleServerConfig();

        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        config.setThreads(propertiesConfig.getThreads());
        config.setNettyThreads(propertiesConfig.getNettyThreads());
        final SingleServerConfig serverConfig = config.useSingleServer();
        serverConfig.setAddress(singleServerConfig.getAddress());
        serverConfig.setPassword(singleServerConfig.getPassword());
        serverConfig.setDatabase(singleServerConfig.getDatabase());

        RedissonClient redisson = Redisson.create(config);

        redissonClient = redisson;

        return redisson;
    }

    /**
     * 锁实例.
     *
     * @return cn.widdo.cache.configuration.redisson.RedissonLocker
     * @author XYL
     * @date 2022/11/28 10:42:54
     **/
    @Bean
    public RedissonLocker redissonLocker() {
        RedissonLocker redissonLocker = new RedissonLocker(redissonClient);
        LockerUtils.setLocker(redissonLocker);
        return redissonLocker;
    }


}
