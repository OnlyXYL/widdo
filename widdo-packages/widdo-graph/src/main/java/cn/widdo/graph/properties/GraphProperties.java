package cn.widdo.graph.properties;

import cn.widdo.assistant.constant.PropertyConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 图谱属性配置
 * <p>
 * 是否需要初始化，需要添加条件，保证按需实例化 todo://
 *
 * @author XYL
 * @version 1.0
 * @date 2022/08/15 15:14
 */
@ConfigurationProperties(prefix = PropertyConstant.PROPERTY_MANAGEMENT_GRAPH)
public class GraphProperties {

    private Neo4j neo4j = new Neo4j();

    private Orientdb orientdb = new Orientdb();

    public Neo4j getNeo4j() {
        return neo4j;
    }

    public Orientdb getOrientdb() {
        return orientdb;
    }

    public static class Neo4j {

        private Boolean enabled = false;

        private String url;

        private String username;

        private String password;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
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
    }

    public static class Orientdb {

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

}
