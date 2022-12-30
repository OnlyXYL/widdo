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
    private final Host host = new Host();

    /**
     * actuator.
     */
    private final Actuator actuator = new Actuator();

    /**
     * get enabled.
     *
     * @return a result type of boolean,which tell you if the neo4j is open or close.
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * set enabled.
     *
     * @param enabled the switch of neo4j
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * get host.
     *
     * @return a result type of {@link Host}
     */
    public Host getHost() {
        return host;
    }

    /**
     * get Actuator.
     *
     * @return an instance of actuator
     */
    public Actuator getActuator() {
        return actuator;
    }

    public static class Host {

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
         *
         * @return a result type of String
         */
        public String getUrl() {
            return url;
        }

        /**
         * set url.
         *
         * @param url the url of neo4j server
         */
        public void setUrl(String url) {
            this.url = url;
        }

        /**
         * get username.
         *
         * @return a result type of String
         */
        public String getUsername() {
            return username;
        }

        /**
         * set username.
         *
         * @param username the username of neo4j server
         */
        public void setUsername(String username) {
            this.username = username;
        }

        /**
         * get password.
         *
         * @return a result type of String
         */
        public String getPassword() {
            return password;
        }

        /**
         * set password.
         *
         * @param password the password of neo4j server
         */
        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class Actuator {

        /**
         * 开启读写开关.
         */
        private Boolean enable = false;

        /**
         * the className of {@link cn.widdo.autoconfigure.neo4j.actuator.Neo4jActuator}.
         */
        private String className;

        /**
         * reader.
         */
        private final Reader reader = new Reader();

        /**
         * writer.
         */
        private final Writer writer = new Writer();

        /**
         * return the switch of neo4j actuator.
         *
         * @return the result of switch
         */
        public Boolean getEnable() {
            return enable;
        }

        /**
         * set the switch of neo4j actuator.
         *
         * @param enable the result of switch
         */
        public void setEnable(Boolean enable) {
            this.enable = enable;
        }

        /**
         * get the className of {@link cn.widdo.autoconfigure.neo4j.actuator.Neo4jActuator}.
         *
         * @return the classname of {@link cn.widdo.autoconfigure.neo4j.actuator.Neo4jActuator}
         */
        public String getClassName() {
            return className;
        }

        /**
         * set className of {@link cn.widdo.autoconfigure.neo4j.actuator.Neo4jActuator}.
         *
         * @param className set className of {@link cn.widdo.autoconfigure.neo4j.actuator.Neo4jActuator}
         */
        public void setClassName(String className) {
            this.className = className;
        }

        /**
         * get reader.
         *
         * @return an instance of reader
         */
        public Reader getReader() {
            return reader;
        }

        /**
         * get writer.
         *
         * @return an instance of writer
         */
        public Writer getWriter() {
            return writer;
        }
    }

    public static class Reader {

        /**
         * className.
         */
        private String className;

        /**
         * get className.
         *
         * @return a result type of String
         */
        public String getClassName() {
            return className;
        }

        /**
         * set className.
         *
         * @param className the className of neo4j Reader
         */
        public void setClassName(String className) {
            this.className = className;
        }
    }

    public static class Writer {

        /**
         * className.
         */
        private String className;

        /**
         * get className.
         *
         * @return a result type of String
         */
        public String getClassName() {
            return className;
        }

        /**
         * set className.
         *
         * @param className the className of Neo4j Writer
         */
        public void setClassName(String className) {
            this.className = className;
        }
    }
}
