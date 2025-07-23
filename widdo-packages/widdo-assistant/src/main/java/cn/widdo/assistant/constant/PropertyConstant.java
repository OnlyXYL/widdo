package cn.widdo.assistant.constant;

/**
 * 常量.
 *
 * @author XYL
 * @date 2022/08/15 15:03
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public class PropertyConstant {

    /**
     * enabled.
     */
    public static final String PROPERTY_ENABLED = ".enabled";

    /* ---------- 通用配置属性 ---------- */
    /**
     * spring.
     */
    public static final String PROPERTY_PREFIX_SPRING = "spring";
    /**
     * widdo.
     */
    public static final String PROPERTY_PREFIX_WIDDO = "widdo";
    /**
     * spring.cloud.
     */
    public static final String PROPERTY_SPRING_CLOUD = PROPERTY_PREFIX_SPRING + ".cloud";
    /**
     * spring.cloud.nacos.config.
     */
    public static final String PROPERTY_NACOS_CONFIG = PROPERTY_SPRING_CLOUD + ".nacos.config";
    /**
     * spring.cloud.nacos.config.server-addr.
     */
    public static final String ITEM_NACOS_ENABLED = PROPERTY_NACOS_CONFIG + ".server-addr";

    /* ---------- widdo 基础配置属性（第一层） ---------- */
    /**
     * spring.redis.
     */
    public static final String PROPERTY_SPRING_REDIS = PROPERTY_PREFIX_SPRING + ".redis";
    /**
     * spring.redis.redisson.
     */
    public static final String PROPERTY_REDIS_REDISSON = PROPERTY_SPRING_REDIS + ".redisson";
    /**
     * spring.redis.redisson.enabled.
     */
    public static final String ITEM_REDISSON_ENABLED = PROPERTY_REDIS_REDISSON + PROPERTY_ENABLED;
    /**
     * widdo.platform.
     */
    public static final String PROPERTY_WIDDO_PLATFORM = PROPERTY_PREFIX_WIDDO + ".platform";
    /**
     * widdo.platform.cache.
     */
    public static final String PROPERTY_PLATFORM_CACHE = PROPERTY_WIDDO_PLATFORM + ".cache";
    /**
     * widdo.platform.rest.
     */
    public static final String PROPERTY_PLATFORM_REST = PROPERTY_WIDDO_PLATFORM + ".rest";
    /**
     * widdo.platform.security.
     */
    public static final String PROPERTY_PLATFORM_SECURITY = PROPERTY_WIDDO_PLATFORM + ".security";
    /**
     * widdo.platform.security.captcha.
     */
    public static final String PROPERTY_SECURITY_CAPTCHA = PROPERTY_PLATFORM_SECURITY + ".captcha";
    /**
     * widdo.platform.swagger.
     */
    public static final String PROPERTY_PLATFORM_SWAGGER = PROPERTY_WIDDO_PLATFORM + ".swagger";
    /**
     * widdo.platform.swagger.enabled.
     */
    public static final String ITEM_SWAGGER_ENABLED = PROPERTY_PLATFORM_SWAGGER + PROPERTY_ENABLED;
    /**
     * widdo.platform.stamp.
     */
    public static final String PROPERTY_PLATFORM_STAMP = PROPERTY_WIDDO_PLATFORM + ".stamp";


    /* ---------- widdo 配置属性（第二层） ---------- */
    /**
     * widdo.platform.data-access-strategy.
     */
    public static final String ITEM_PLATFORM_DATA_ACCESS_STRATEGY = PROPERTY_WIDDO_PLATFORM + ".data-access-strategy";
    /**
     * widdo.platform.architecture.
     */
    public static final String ITEM_PLATFORM_ARCHITECTURE = PROPERTY_WIDDO_PLATFORM + ".architecture";
    /**
     * widdo.management.
     */
    public static final String PROPERTY_WIDDO_MANAGEMENT = PROPERTY_PREFIX_WIDDO + ".management";
    /**
     * widdo.management.nacos.
     */
    public static final String PROPERTY_MANAGEMENT_NACOS = PROPERTY_WIDDO_MANAGEMENT + ".nacos";
    /**
     * widdo.management.queue.
     */
    public static final String PROPERTY_MANAGEMENT_QUEUE = PROPERTY_WIDDO_MANAGEMENT + ".queue";
    /**
     * widdo.management.queue.kafka.enabled.
     */
    public static final String ITEM_KAFKA_ENABLED = PROPERTY_MANAGEMENT_QUEUE + ".kafka" + PROPERTY_ENABLED;
    /**
     * widdo.management.log-center.
     */
    public static final String PROPERTY_MANAGEMENT_LOG_CENTER = PROPERTY_WIDDO_MANAGEMENT + ".log-center";
    /**
     * widdo.management.log-center.server-addr.
     */
    public static final String ITEM_LOG_CENTER_ENABLED = PROPERTY_MANAGEMENT_LOG_CENTER + ".server-addr";
    /**
     * widdo.management.service.
     */
    public static final String PROPERTY_MANAGEMENT_SERVICE = PROPERTY_WIDDO_MANAGEMENT + ".service";
    /**
     * widdo.social.
     */
    public static final String PROPERTY_WIDDO_SOCIAL = PROPERTY_PREFIX_WIDDO + ".social";
    /**
     * widdo.social.justauth.
     */
    public static final String PROPERTY_SOCIAL_JUSTAUTH = PROPERTY_WIDDO_SOCIAL + ".justauth";
    /**
     * widdo.social.easemob.
     */
    public static final String PROPERTY_SOCIAL_EASEMOB = PROPERTY_WIDDO_SOCIAL + ".easemob";
    /**
     * widdo.social.wxapp.
     */
    public static final String PROPERTY_SOCIAL_WXAPP = PROPERTY_WIDDO_SOCIAL + ".wxapp";
    /**
     * widdo.social.wxmpp.
     */
    public static final String PROPERTY_SOCIAL_WXMPP = PROPERTY_WIDDO_SOCIAL + ".wxmpp";
    /**
     * widdo.integration.
     */
    public static final String PROPERTY_WIDDO_INTEGRATION = PROPERTY_PREFIX_WIDDO + ".integration";
    /**
     * widdo.integration.audit.
     */
    public static final String PROPERTY_INTEGRATION_AUDIT = PROPERTY_WIDDO_INTEGRATION + ".audit";
    /**
     * widdo.integration.audit.aliyun.
     */
    public static final String PROPERTY_AUDIT_ALIYUN = PROPERTY_INTEGRATION_AUDIT + ".aliyun";
    /**
     * widdo.integration.audit.baidu.
     */
    public static final String PROPERTY_AUDIT_BAIDU = PROPERTY_INTEGRATION_AUDIT + ".baidu";
    /**
     * widdo.integration.audit.tianyan.
     */
    public static final String PROPERTY_AUDIT_TIANYAN = PROPERTY_INTEGRATION_AUDIT + ".tianyan";
    /**
     * widdo.integration.oss.
     */
    public static final String PROPERTY_INTEGRATION_OSS = PROPERTY_WIDDO_INTEGRATION + ".oss";
    /**
     * widdo.integration.oss.minio.
     */
    public static final String PROPERTY_OSS_MINIO = PROPERTY_INTEGRATION_OSS + ".minio";
    /**
     * widdo.integration.oss.minio.endpoint.
     */
    public static final String ITEM_MINIO_ENDPOINT = PROPERTY_OSS_MINIO + ".endpoint";
    /**
     * widdo.integration.oss.minio.accessKey.
     */
    public static final String ITEM_MINIO_ACCESSKEY = PROPERTY_OSS_MINIO + ".accessKey";
    /**
     * widdo.integration.oss.minio.secretKey.
     */
    public static final String ITEM_MINIO_SECRETKEY = PROPERTY_OSS_MINIO + ".secretKey";
    /**
     * widdo.integration.influxdb.
     */
    public static final String PROPERTY_INTEGRATION_INFLUXDB = PROPERTY_WIDDO_INTEGRATION + ".influxdb";
    /**
     * widdo.integration.influxdb.url.
     */
    public static final String ITEM_INFLUXDB_URL = PROPERTY_INTEGRATION_INFLUXDB + ".url";


    /* ---------- widdo 详细配置属性（第三层） ---------- */
    /**
     * widdo.integration.influxdb.database.
     */
    public static final String ITEM_INFLUXDB_DATABASE = PROPERTY_INTEGRATION_INFLUXDB + ".database";
    /**
     * widdo.integration.influxdb.username.
     */
    public static final String ITEM_INFLUXDB_USERNAME = PROPERTY_INTEGRATION_INFLUXDB + ".username";
    /**
     * widdo.integration.influxdb.password.
     */
    public static final String ITEM_INFLUXDB_PASSWORD = PROPERTY_INTEGRATION_INFLUXDB + ".password";
    /**
     * widdo.integration.maven.
     */
    public static final String PROPERTY_INTEGRATION_MAVEN = PROPERTY_WIDDO_INTEGRATION + ".maven";
    /**
     * widdo.integration.pay.
     */
    public static final String PROPERTY_INTEGRATION_PAY = PROPERTY_WIDDO_INTEGRATION + ".pay";
    /**
     * widdo.integration.oss.alipay.
     */
    public static final String PROPERTY_PAY_ALIPAY = PROPERTY_INTEGRATION_PAY + ".alipay";
    /**
     * widdo.integration.oss.alipay.storage.
     */
    public static final String ITEM_ALIPAY_STORAGE = PROPERTY_PAY_ALIPAY + ".storage";
    /**
     * widdo.integration.oss.alipay.enabled.
     */
    public static final String ITEM_ALIPAY_ENABLED = PROPERTY_PAY_ALIPAY + PROPERTY_ENABLED;

    /* ---------- Spring 相关基础配置属性（第一层） ---------- */
    /**
     * widdo.integration.oss.wxpay.
     */
    public static final String PROPERTY_PAY_WXIPAY = PROPERTY_INTEGRATION_PAY + ".wxpay";
    /**
     * widdo.integration.oss.wxpay.enabled.
     */
    public static final String ITEM_WXPAY_ENABLED = PROPERTY_PAY_WXIPAY + PROPERTY_ENABLED;

    /* ---------- widdo 详细配置属性路径 ---------- */
    /**
     * widdo.message.
     */
    public static final String PROPERTY_WIDDO_MESSAGE = PROPERTY_PREFIX_WIDDO + ".message";
    /**
     * widdo.message.sms.
     */
    public static final String PROPERTY_MESSAGE_SMS = PROPERTY_WIDDO_MESSAGE + ".sms";
    /**
     * widdo.message.sms.verification-code.
     */
    public static final String PROPERTY_MESSAGE_SMS_VERIFICATION_CODE = PROPERTY_MESSAGE_SMS + ".verification-code";
    /**
     * widdo.message.sms.push.apns.
     */
    public static final String PROPERTY_MESSAGE_PUSH_APNS = PROPERTY_WIDDO_MESSAGE + ".push.apns";
    /**
     * widdo.message.sms.push.jpush.
     */
    public static final String PROPERTY_MESSAGE_PUSH_JPUSH = PROPERTY_WIDDO_MESSAGE + ".push.jpush";
    /**
     * widdo.websocket.
     */
    public static final String PROPERTY_WIDDO_WEBSOCKET = PROPERTY_PREFIX_WIDDO + ".websocket";
    /**
     * widdo.graph.
     */
    public static final String PROPERTY_WIDDO_GRAPH = PROPERTY_PREFIX_WIDDO + ".graph";
    /**
     * widdo.graph.neo4j.
     */
    public static final String PROPERTY_GRAPH_NEO4J = PROPERTY_WIDDO_GRAPH + ".neo4j";
    /**
     * widdo.graph.neo4j.enabled.
     */
    public static final String ITEM_NEO4j_ENABLED = PROPERTY_GRAPH_NEO4J + PROPERTY_ENABLED;
    /**
     * widdo.graph.neo4j.actuator.
     */
    public static final String ITEM_NEO4j_ACTUATOR_ENABLED = PROPERTY_GRAPH_NEO4J + ".actuator" + PROPERTY_ENABLED;
    /**
     * widdo.graph.orientdb.
     */
    public static final String PROPERTY_GRAPH_ORIENTDB = PROPERTY_WIDDO_GRAPH + ".orientdb";
    /**
     * widdo.graph.orientdb.enabled.
     */
    public static final String ITEM_ORIENTDB_ENABLED = PROPERTY_GRAPH_ORIENTDB + PROPERTY_ENABLED;
    /**
     * widdo.babelnet.
     */
    public static final String PROPERTY_WIDDO_BABELNET = PROPERTY_PREFIX_WIDDO + ".babelnet";
    /**
     * widdo.babelnet.enabled.
     */
    public static final String ITEM_BABELNET_ENABLED = PROPERTY_WIDDO_BABELNET + PROPERTY_ENABLED;
    /**
     * widdo.babelnet.actuator.
     */
    public static final String ITEM_BABELNET_ACTUATOR_ENABLED = PROPERTY_WIDDO_BABELNET + ".actuator"
            + PROPERTY_ENABLED;
    /**
     * widdo.hadoop.
     */
    public static final String PROPERTY_WIDDO_HADOOP = PROPERTY_PREFIX_WIDDO + ".hadoop";
    /**
     * widdo.hadoop.hdfs.
     */
    public static final String PROPERTY_HADOOP_HDFS = PROPERTY_WIDDO_HADOOP + ".hdfs";
    /**
     * widdo.hadoop.hdfs.nn.
     */
    public static final String PROPERTY_WIDDO_HADOOP_HDFS_NN = PROPERTY_HADOOP_HDFS + ".nn";
    /**
     * widdo.hadoop.hdfs.nn.web-addr.
     */
    public static final String ITEM_HADOOP_HDFS_NN_WEB_ADDR = PROPERTY_WIDDO_HADOOP_HDFS_NN + ".web-addr";
    /**
     * widdo.hadoop.hdfs.enabled.
     */
    public static final String ITEM_HADOOP_HDFS_ENABLED = PROPERTY_HADOOP_HDFS + PROPERTY_ENABLED;
    /**
     * widdo.hadoop.enabled.
     */
    public static final String ITEM_HADOOP_ENABLED = PROPERTY_WIDDO_HADOOP + PROPERTY_ENABLED;
    /**
     * widdo.elasticsearch.
     */
    public static final String PROPERTY_WIDDO_ELASTICSEARCH = PROPERTY_PREFIX_WIDDO + ".elasticsearch";
    /**
     * widdo.elasticsearch.enabled.
     */
    public static final String ITEM_ELASTICSEARCH_ENABLED = PROPERTY_WIDDO_ELASTICSEARCH + PROPERTY_ENABLED;
    /**
     * widdo.sql.
     */
    public static final String PROPERTY_WIDDO_SQL = PROPERTY_PREFIX_WIDDO + ".sql";
    /**
     * widdo.sql.parser.
     */
    public static final String PROPERTY_SQL_PARSER = PROPERTY_WIDDO_SQL + ".parser";
    /**
     * widdo.sql.parser.enabled.
     */
    public static final String ITEM_SQL_PARSER_ENABLED = PROPERTY_SQL_PARSER + PROPERTY_ENABLED;
    /**
     * spring.application.name.
     */
    public static final String ITEM_SPRING_APPLICATION_NAME = "spring.application.name";

    /* ---------- Spring 详细配置属性路径 ---------- */
    /**
     * spring.jpa.hibernate.ddl-auto.
     */
    public static final String ITEM_SPRING_JPA_HIBERNATE_DDL_AUTO = "spring.jpa.hibernate.ddl-auto";
    /**
     * spring.sql.init.platform.
     */
    public static final String ITEM_SPRING_SQL_INIT_PLATFORM = "spring.sql.init.platform";
    /**
     * ${.
     */
    public static final String ANNOTATION_PREFIX = "${";

    /* ---------- 注解属性通用值 ---------- */
    /**
     * }.
     */
    public static final String ANNOTATION_SUFFIX = "}";
    /**
     * ${spring.application.name}.
     */
    public static final String ANNOTATION_APPLICATION_NAME = ANNOTATION_PREFIX + ITEM_SPRING_APPLICATION_NAME
            + ANNOTATION_SUFFIX;
    /**
     * ${spring.sql.init.platform}.
     */
    public static final String ANNOTATION_SQL_INIT_PLATFORM = ANNOTATION_PREFIX + ITEM_SPRING_SQL_INIT_PLATFORM
            + ANNOTATION_SUFFIX;
    /**
     * ${widdo.platform.debezium.enabled}.
     */
    public static final String ANNOTATION_DEBEZIUM_ENABLED = "${widdo.platform.debezium.enabled}";

    protected PropertyConstant() {
        throw new UnsupportedOperationException("静态类不能被实例化");
    }

}
