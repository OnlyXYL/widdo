package cn.widdo.autoconfigure.babelnet.properties;

import cn.widdo.assistant.constant.PropertyConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * WiddoBabelNetProperties.
 *
 * @author XYL
 * @date 2023/03/15 13:54
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
@ConfigurationProperties(prefix = PropertyConstant.PROPERTY_MANAGEMENT_BABELNET)
public class WiddoBabelNetProperties {

    /**
     * 开启babelnet开关.
     */
    private Boolean enabled = false;

    /**
     * apiType.
     */
    private String apiType;

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
     * return api type.
     *
     * @return a result type of String
     */
    public String getApiType() {
        return apiType;
    }

    /**
     * actuator.
     *
     * @return {@link Actuator}
     */
    public Actuator getActuator() {
        return actuator;
    }

    /**
     * set babelNet actuator to enabled.
     *
     * @param enabled enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * set babelNet apiType.
     *
     * @param apiType apiType
     */
    public void setApiType(String apiType) {
        this.apiType = apiType;
    }

    public static class Actuator {

        /**
         * 开启读写开关.
         */
        private Boolean enable = false;

        /**
         * the className of {@link cn.widdo.autoconfigure.babelnet.actuator.BabelNetActuator}.
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
         * get the className of {@link cn.widdo.autoconfigure.babelnet.actuator.BabelNetActuator}.
         *
         * @return the classname of {@link cn.widdo.autoconfigure.babelnet.actuator.BabelNetActuator}
         */
        public String getClassName() {
            return className;
        }

        /**
         * set className of {@link cn.widdo.autoconfigure.babelnet.actuator.BabelNetActuator}.
         *
         * @param className set className of {@link cn.widdo.autoconfigure.babelnet.actuator.BabelNetActuator}
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
