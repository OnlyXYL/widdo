package cn.widdo.cache.properties.redisson;

import lombok.Data;

/**
 * 配置文件.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/07/05 17:37
 */
@Data
public class MyRedissonConfigProperties {

    /**
     * mode.
     */
    private String mode;

    /**
     * threads.
     */
    private int threads;

    /**
     * nettyThreads.
     */
    private int  nettyThreads;

    /**
     * transportMode.
     */
    private String transportMode;

    /**
     * 单节点配置.
     */
    private MyRedissonSingleServerConfigProperties singleServerConfig = new MyRedissonSingleServerConfigProperties();

    /**
     * 集群配置.
     */
    private MyRedissonClusterServerConfigProperties clusterServerConfig = new MyRedissonClusterServerConfigProperties();
}

