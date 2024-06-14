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

    private Boolean enabled;

    /**
     * uris.集群时逗号分割.
     */
    private String uris;

    /**
     * 用户名.
     */
    private String username;

    /**
     * 密码.
     */
    private String passsword;

    /**
     * 构造方法.
     *
     * @param enabled   enabled
     * @param uris      uris
     * @param username  username
     * @param passsword password
     */
    public WiddoElasticsearchProperties(Boolean enabled, String uris, String username, String passsword) {
        this.enabled = enabled;
        this.uris = uris;
        this.username = username;
        this.passsword = passsword;
    }

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
     * 获取uris.
     *
     * @return 字符串
     */
    public String getUris() {
        return uris;
    }

    /**
     * 设置uris.
     *
     * @param uris uris
     */
    public void setUris(String uris) {
        this.uris = uris;
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
    public String getPasssword() {
        return passsword;
    }

    /**
     * 设置秘密.
     *
     * @param passsword 密码
     */
    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }
}
