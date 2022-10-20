package cn.widdo.autoconfigure.neo4j.properties;

import cn.widdo.assistant.constant.PropertyConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * widdo neo4j properties
 *
 * @author XYL
 * @version 263.1.0.0
 * @date 2022/10/14 15:10
 */
@ConfigurationProperties(prefix = PropertyConstant.PROPERTY_GRAPH_NEO4J)
public class WiddoNeo4jProperties {

    /**
     * 开启neo4j开关
     */
    private Boolean enabled = false;

    /**
     * host相关配置
     */
    private Host host = new Host();

    /**
     * 开启读写开关
     */
    private Boolean rw = false;

    private Reader reader = new Reader();

    private Writer writer = new Writer();

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Host getHost() {
        return host;
    }

    public boolean isRw() {
        return rw;
    }

    public void setRw(boolean rw) {
        this.rw = rw;
    }

    public Reader getReader() {
        return reader;
    }

    public Writer getWriter() {
        return writer;
    }

    public class Host {

        private String url;

        private String username;

        private String password;

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

    public class Reader {

        private String className;

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }

    public class Writer {
        private String className;

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }
}
