package cn.widdo.autoconfigure.orientdb.properties;

import cn.widdo.assistant.constant.PropertyConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * widdo orientdb properties.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/14 15:26
 */
@ConfigurationProperties(prefix = PropertyConstant.PROPERTY_GRAPH_ORIENTDB)
public class WiddoOrientdbProperties {

    /**
     * enabled.
     */
    private Boolean enabled = false;

    /**
     * uri.
     */
    private String uri;

    /**
     * url.
     */
    private String url;

    /**
     * username.
     */
    private String username;

    /**
     * password.
     */
    private String password;

    /**
     * database.
     */
    private String database;

    /**
     * get enabled.
     * @return a result type of boolean
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * set enabled.
     * @param enabled   the switch of orientdb
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * get uri.
     * @return a result type of String
     */
    public String getUri() {
        return uri;
    }

    /**
     * set uri.
     * @param uri   the uri of orientdb server
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * get url.
     * @return a result type of String
     */
    public String getUrl() {
        return url;
    }

    /**
     * set url.
     * @param url   the url of orientdb server
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * get username.
     * @return a result type of String
     */
    public String getUsername() {
        return username;
    }

    /**
     * set username.
     * @param username  the username of orientdb server
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * get password.
     * @return a result type of String
     */
    public String getPassword() {
        return password;
    }

    /**
     * set password.
     * @param password  the password of orientdb server
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * get database.
     * @return a result type of String
     */
    public String getDatabase() {
        return database;
    }

    /**
     * set database.
     * @param database  the database of orientdb server
     */
    public void setDatabase(String database) {
        this.database = database;
    }
}
