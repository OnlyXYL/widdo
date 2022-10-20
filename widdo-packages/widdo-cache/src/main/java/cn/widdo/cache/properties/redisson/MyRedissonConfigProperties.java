package cn.widdo.cache.properties.redisson;

import lombok.Data;

/**
 * 配置文件
 *
 * @author XYL
 * @version 263.1.0.0
 * @date 2022/07/05 17:37
 */
@Data
public class MyRedissonConfigProperties {

    private String mode;
    private int threads;
    private int  nettyThreads;
    private String transportMode;

    private MyRedissonSingleServerConfigProperties singleServerConfig = new MyRedissonSingleServerConfigProperties();

    private MyRedissonClusterServerConfigProperties clusterServerConfig = new MyRedissonClusterServerConfigProperties();
}

