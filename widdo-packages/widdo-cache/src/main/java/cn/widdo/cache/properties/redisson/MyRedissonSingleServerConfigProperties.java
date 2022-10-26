package cn.widdo.cache.properties.redisson;

import lombok.Data;

/**
 * 配置文件
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/07/05 17:37
 */
@Data
public class MyRedissonSingleServerConfigProperties {

    private String password;
    private String address;
    private int database;
}
