package cn.widdo.cache.properties.redisson;

import lombok.Data;

import java.util.List;

/**
 * 集群配置参数.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/07/05 17:37
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
