package cn.widdo.cache.properties.redisson;

import lombok.Data;

import java.util.List;

/**
 * 集群配置参数
 *
 * @author XYL
 * @version 1.0
 * @date 2022/07/05 17:37
 */
@Data
public class MyRedissonClusterServerConfigProperties {

    private List<String> nodeAddresses;

    private int scanInterval;
}
