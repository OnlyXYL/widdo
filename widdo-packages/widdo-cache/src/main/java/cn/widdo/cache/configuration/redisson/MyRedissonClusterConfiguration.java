package cn.widdo.cache.configuration.redisson;

import cn.widdo.cache.annotation.ConditionalOnRedissonEnabled;
import cn.widdo.cache.properties.redisson.MyRedissonClusterServerConfigProperties;
import cn.widdo.cache.properties.redisson.MyRedissonConfigProperties;
import cn.widdo.cache.properties.redisson.MyRedissonProperties;
import cn.widdo.cache.utils.redisson.LockerUtils;
import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.List;

/**
 * 集群方式配置.
 *
 * @author XYL
 * @date 2022/06/22 10:00
 * @since 263.1.1.0
 */
@Data
@RefreshScope
@ConditionalOnRedissonEnabled
@ConditionalOnProperty(name = "spring.redis.redisson.config.mode", havingValue = "cluster")
public class MyRedissonClusterConfiguration {

    /**
     * 属性配置.
     */
    @Resource
    private MyRedissonProperties myRedissonProperties;

    /**
     * 客户端.
     */
    private RedissonClient redissonClient;

    /**
     * 客户端.
     *
     * @return org.redisson.api.RedissonClient
     * @author XYL
     * @date 2022/11/28 11:08:31
     **/
    @Bean
    public RedissonClient redissonClient() {

        final MyRedissonConfigProperties propertiesConfig = myRedissonProperties.getConfig();

        final MyRedissonClusterServerConfigProperties clusterServerConfig = propertiesConfig.getClusterServerConfig();

        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        config.setThreads(propertiesConfig.getThreads());
        config.setNettyThreads(propertiesConfig.getNettyThreads());

        final ClusterServersConfig clusterServersConfig = config.useClusterServers();
        final List<String> nodeAddresses = clusterServerConfig.getNodeAddresses();
        clusterServersConfig.setScanInterval(clusterServerConfig.getScanInterval());
        clusterServerConfig.setNodeAddresses(nodeAddresses);

        RedissonClient redisson = Redisson.create(config);

        redissonClient = redisson;

        return redisson;
    }

    /**
     * 锁实例.
     *
     * @return cn.widdo.cache.configuration.redisson.RedissonLocker
     * @author XYL
     * @date 2022/11/28 11:08:46
     **/
    @Bean
    public RedissonLocker redissonLocker() {
        RedissonLocker redissonLocker = new RedissonLocker(redissonClient);
        LockerUtils.setLocker(redissonLocker);
        return redissonLocker;
    }

}

