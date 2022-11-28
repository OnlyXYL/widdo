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
public class MyRedissonSingleServerConfigProperties {

    /**
     * 密码.
     */
    private String password;

    /**
     * 地址.
     */
    private String address;

    /**
     * 数据库.
     */
    private int database;
}
