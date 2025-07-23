package cn.widdo.autoconfigure.elasticsearch.properties;

import cn.widdo.assistant.constant.PropertyConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * WiddoElasticsearchProperties.
 *
 * @author XYL
 * @date 2023/10/27 15:45
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
@ConfigurationProperties(prefix = PropertyConstant.PROPERTY_WIDDO_ELASTICSEARCH)
public class WiddoElasticsearchProperties {

    /**
     * 开关.
     */
    private Boolean enabled;

    /**
     * hosts.集群时逗号分割.
     */
    private String hosts;

    /**
     * 用户名.
     */
    private String username;

    /**
     * 密码.
     */
    private String password;

    /**
     * 获取开关.
     *
     * @return 返回开关结果
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置开关.
     *
     * @param enabled true or false
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取hosts.
     *
     * @return 字符串
     */
    public String getHosts() {
        return hosts;
    }

    /**
     * 设置hosts.
     *
     * @param hosts hosts
     */
    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    /**
     * 获取用户名.
     *
     * @return 返回用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名.
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码.
     *
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置秘密.
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
