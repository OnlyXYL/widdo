package cn.widdo.autoconfigure.orientdb.properties;

import cn.widdo.assistant.constant.PropertyConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * widdo orientdb properties
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/14 15:26
 */
@ConfigurationProperties(prefix = PropertyConstant.PROPERTY_GRAPH_ORIENTDB)
public class WiddoOrientdbProperties {

    private Boolean enabled = false;

    private String uri;

    private String url;

    private String username;

    private String password;

    private String database;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
