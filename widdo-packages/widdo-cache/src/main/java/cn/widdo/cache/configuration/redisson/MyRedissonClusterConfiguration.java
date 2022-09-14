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
 * 集群方式配置
 *
 * @author XYL
 * @version 1.0
 * @date 2022/06/22 10:00
 */
@Data
@RefreshScope
@ConditionalOnRedissonEnabled
@ConditionalOnProperty(name = "spring.redis.redisson.config.mode", havingValue = "cluster")
public class MyRedissonClusterConfiguration {

    @Resource
    private MyRedissonProperties myRedissonProperties;

    private RedissonClient redissonClient;

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

    @Bean
    public RedissonLocker redissonLocker(){
        RedissonLocker redissonLocker = new RedissonLocker(redissonClient);
        LockerUtils.setLocker(redissonLocker);
        return redissonLocker;
    }

}

