package cn.widdo.autoconfigure.neo4j.properties;

import cn.widdo.assistant.constant.PropertyConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * widdo neo4j properties.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/14 15:10
 */
@ConfigurationProperties(prefix = PropertyConstant.PROPERTY_GRAPH_NEO4J)
public class WiddoNeo4jProperties {

    /**
     * 开启neo4j开关.
     */
    private Boolean enabled = false;

    /**
     * host相关配置.
     */
    private Host host = new Host();

    /**
     * 开启读写开关.
     */
    private Boolean rw = false;

    /**
     * reader.
     */
    private Reader reader = new Reader();

    /**
     * writer.
     */
    private Writer writer = new Writer();

    /**
     * get enabled.
     * @return a result type of boolean,which tell you if the neo4j is open or close.
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * set enabled.
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * get host.
     * @return a result type of {@link Host}
     */
    public Host getHost() {
        return host;
    }

    /**
     * check rw.
     * @return a result type of boolean,which tell you if the rw is open or close.
     */
    public boolean isRw() {
        return rw;
    }

    /**
     * set rw.
     * @param rw
     */
    public void setRw(boolean rw) {
        this.rw = rw;
    }

    /**
     * get reader.
     * @return a result type of {@link Reader}
     */
    public Reader getReader() {
        return reader;
    }

    /**
     * get writer.
     * @return a result type of {@link Writer}
     */
    public Writer getWriter() {
        return writer;
    }

    public class Host {

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
         * get url.
         * @return a result type of String
         */
        public String getUrl() {
            return url;
        }

        /**
         * set url.
         * @param url
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
         * @param username
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
         * @param password
         */
        public void setPassword(String password) {
            this.password = password;
        }
    }

    public class Reader {

        /**
         * className.
         */
        private String className;

        /**
         * get className.
         * @return a result type of String
         */
        public String getClassName() {
            return className;
        }

        /**
         * set className.
         * @param className
         */
        public void setClassName(String className) {
            this.className = className;
        }
    }

    public class Writer {

        /**
         * className.
         */
        private String className;

        /**
         * get className.
         * @return a result type of String
         */
        public String getClassName() {
            return className;
        }

        /**
         * set className.
         * @param className
         */
        public void setClassName(String className) {
            this.className = className;
        }
    }
}
