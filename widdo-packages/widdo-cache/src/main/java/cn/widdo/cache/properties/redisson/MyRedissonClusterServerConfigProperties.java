package cn.widdo.cache.properties.redisson;

import lombok.Data;

import java.util.List;

/**
 * 集群配置参数.
 *
 * @author XYL
 * @date 2022/07/05 17:37
 * @since 302.1.0.0
 */
@Data
public class MyRedissonClusterServerConfigProperties {

    /**
     * nodeAddresses.
     */
    private List<String> nodeAddresses;

    /**
     * scanInterval.
     */
    private int scanInterval;
}
