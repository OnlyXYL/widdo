package cn.widdo.assistant.constant;

/**
 * 常量.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/08/15 15:03
 */
@SuppressWarnings("AlibabaConstantFieldShouldBeUpperCase")
public class PropertyConstant {
    protected PropertyConstant() {
        throw new UnsupportedOperationException("静态类不能被实例化");
    }

    /* ---------- 通用配置属性 ---------- */

    /**
     * enabled.
     */
    public static final String PROPERTY_ENABLED = ".enabled";

    /**
     * spring.
     */
    public static final String PROPERTY_PREFIX_SPRING = "spring";

    /**
     * widdo.
     */
    public static final String PROPERTY_PREFIX_SMART = "widdo";

    /**
     * spring.cloud.
     */
    public static final String PROPERTY_SPRING_CLOUD = PROPERTY_PREFIX_SPRING + ".cloud";

    /**
     * spring.redis.
     */
    public static final String PROPERTY_SPRING_REDIS = PROPERTY_PREFIX_SPRING + ".redis";


    /* ---------- widdo 基础配置属性（第一层） ---------- */

    /**
     * widdo.platform.
     */
    public static final String PROPERTY_SMART_PLATFORM = PROPERTY_PREFIX_SMART + ".platform";
    /**
     * widdo.management.
     */
    public static final String PROPERTY_SMART_MANAGEMENT = PROPERTY_PREFIX_SMART + ".management";

    /**
     * widdo.social.
     */
    public static final String PROPERTY_SMART_SOCIAL = PROPERTY_PREFIX_SMART + ".social";

    /**
     * widdo.integration.
     */
    public static final String PROPERTY_SMART_INTEGRATION = PROPERTY_PREFIX_SMART + ".integration";
    /**
     * widdo.message.
     */
    public static final String PROPERTY_SMART_MESSAGE = PROPERTY_PREFIX_SMART + ".message";
    /**
     * widdo.websocket.
     */
    public static final String PROPERTY_SMART_WEBSOCKET = PROPERTY_PREFIX_SMART + ".websocket";


    /* ---------- widdo 配置属性（第二层） ---------- */

    /**
     * widdo.platform.cache.
     */
    public static final String PROPERTY_PLATFORM_CACHE = PROPERTY_SMART_PLATFORM + ".cache";
    /**
     * widdo.platform.rest.
     */
    public static final String PROPERTY_PLATFORM_REST = PROPERTY_SMART_PLATFORM + ".rest";

    /**
     * widdo.platform.security.
     */
    public static final String PROPERTY_PLATFORM_SECURITY = PROPERTY_SMART_PLATFORM + ".security";

    /**
     * widdo.platform.swagger.
     */
    public static final String PROPERTY_PLATFORM_SWAGGER = PROPERTY_SMART_PLATFORM + ".swagger";

    /**
     * widdo.platform.stamp.
     */
    public static final String PROPERTY_PLATFORM_STAMP = PROPERTY_SMART_PLATFORM + ".stamp";

    /**
     * widdo.management.nacos.
     */
    public static final String PROPERTY_MANAGEMENT_NACOS = PROPERTY_SMART_MANAGEMENT + ".nacos";

    /**
     * widdo.management.queue.
     */
    public static final String PROPERTY_MANAGEMENT_QUEUE = PROPERTY_SMART_MANAGEMENT + ".queue";


    /**
     * widdo.management.log-center.
     */
    public static final String PROPERTY_MANAGEMENT_LOG_CENTER = PROPERTY_SMART_MANAGEMENT + ".log-center";

    /**
     * widdo.management.service.
     */
    public static final String PROPERTY_MANAGEMENT_SERVICE = PROPERTY_SMART_MANAGEMENT + ".service";

    /**
     * widdo.management.graph.
     */
    public static final String PROPERTY_MANAGEMENT_GRAPH = PROPERTY_SMART_MANAGEMENT + ".graph";
    /**
     * widdo.social.justauth.
     */
    public static final String PROPERTY_SOCIAL_JUSTAUTH = PROPERTY_SMART_SOCIAL + ".justauth";

    /**
     * widdo.social.easemob.
     */
    public static final String PROPERTY_SOCIAL_EASEMOB = PROPERTY_SMART_SOCIAL + ".easemob";

    /**
     * widdo.social.wxapp.
     */
    public static final String PROPERTY_SOCIAL_WXAPP = PROPERTY_SMART_SOCIAL + ".wxapp";

    /**
     * widdo.social.wxmpp.
     */
    public static final String PROPERTY_SOCIAL_WXMPP = PROPERTY_SMART_SOCIAL + ".wxmpp";
    /**
     * widdo.integration.audit.
     */
    public static final String PROPERTY_INTEGRATION_AUDIT = PROPERTY_SMART_INTEGRATION + ".audit";

    /**
     * widdo.integration.oss.
     */
    public static final String PROPERTY_INTEGRATION_OSS = PROPERTY_SMART_INTEGRATION + ".oss";

    /**
     * widdo.integration.influxdb.
     */
    public static final String PROPERTY_INTEGRATION_INFLUXDB = PROPERTY_SMART_INTEGRATION + ".influxdb";

    /**
     * widdo.integration.maven.
     */
    public static final String PROPERTY_INTEGRATION_MAVEN = PROPERTY_SMART_INTEGRATION + ".maven";

    /**
     * widdo.integration.pay.
     */
    public static final String PROPERTY_INTEGRATION_PAY = PROPERTY_SMART_INTEGRATION + ".pay";
    /**
     * widdo.message.sms.
     */
    public static final String PROPERTY_MESSAGE_SMS = PROPERTY_SMART_MESSAGE + ".sms";

    /**
     * widdo.message.sms.verification-code.
     */
    public static final String PROPERTY_MESSAGE_SMS_VERIFICATION_CODE = PROPERTY_MESSAGE_SMS + ".verification-code";

    /**
     * widdo.message.sms.push.apns.
     */
    public static final String PROPERTY_MESSAGE_PUSH_APNS = PROPERTY_SMART_MESSAGE + ".push.apns";

    /**
     * widdo.message.sms.push.jpush.
     */
    public static final String PROPERTY_MESSAGE_PUSH_JPUSH = PROPERTY_SMART_MESSAGE + ".push.jpush";


    /* ---------- smart 详细配置属性（第三层） ---------- */

    /**
     * widdo.platform.security.captcha.
     */
    public static final String PROPERTY_SECURITY_CAPTCHA = PROPERTY_PLATFORM_SECURITY + ".captcha";
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
     * widdo.integration.oss.minio.
     */
    public static final String PROPERTY_OSS_MINIO = PROPERTY_INTEGRATION_OSS + ".minio";

    /**
     * widdo.integration.oss.alipay.
     */
    public static final String PROPERTY_PAY_ALIPAY = PROPERTY_INTEGRATION_PAY + ".alipay";

    /**
     * widdo.integration.oss.wxpay.
     */
    public static final String PROPERTY_PAY_WXIPAY = PROPERTY_INTEGRATION_PAY + ".wxpay";

    /**
     * widdo.management.graph.neo4j.
     */
    public static final String PROPERTY_GRAPH_NEO4J = PROPERTY_MANAGEMENT_GRAPH + ".neo4j";

    /**
     * widdo.management.graph.orientdb.
     */
    public static final String PROPERTY_GRAPH_ORIENTDB = PROPERTY_MANAGEMENT_GRAPH + ".orientdb";


    /* ---------- Spring 相关基础配置属性（第一层） ---------- */

    /**
     * spring.redis.redisson.
     */
    public static final String PROPERTY_REDIS_REDISSON = PROPERTY_SPRING_REDIS + ".redisson";

    /**
     * spring.cloud.nacos.config.
     */
    public static final String PROPERTY_NACOS_CONFIG = PROPERTY_SPRING_CLOUD + ".nacos.config";


    /* ---------- widdo 详细配置属性路径 ---------- */

    /**
     * widdo.platform.data-access-strategy.
     */
    public static final String ITEM_PLATFORM_DATA_ACCESS_STRATEGY = PROPERTY_SMART_PLATFORM + ".data-access-strategy";

    /**
     * widdo.platform.architecture.
     */
    public static final String ITEM_PLATFORM_ARCHITECTURE = PROPERTY_SMART_PLATFORM + ".architecture";

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
     * widdo.integration.oss.alipay.storage.
     */
    public static final String ITEM_ALIPAY_STORAGE = PROPERTY_PAY_ALIPAY + ".storage";

    /**
     * widdo.integration.oss.alipay.enabled.
     */
    public static final String ITEM_ALIPAY_ENABLED = PROPERTY_PAY_ALIPAY + PROPERTY_ENABLED;

    /**
     * widdo.integration.oss.wxpay.enabled.
     */
    public static final String ITEM_WXPAY_ENABLED = PROPERTY_PAY_WXIPAY + PROPERTY_ENABLED;

    /**
     * widdo.integration.influxdb.url.
     */
    public static final String ITEM_INFLUXDB_URL = PROPERTY_INTEGRATION_INFLUXDB + ".url";

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
     * widdo.platform.swagger.enabled.
     */
    public static final String ITEM_SWAGGER_ENABLED = PROPERTY_PLATFORM_SWAGGER + PROPERTY_ENABLED;

    /**
     * spring.redis.redisson.enabled.
     */
    public static final String ITEM_REDISSON_ENABLED = PROPERTY_REDIS_REDISSON + PROPERTY_ENABLED;

    /**
     * widdo.management.queue.kafka.enabled.
     */
    public static final String ITEM_KAFKA_ENABLED = PROPERTY_MANAGEMENT_QUEUE + ".kafka" + PROPERTY_ENABLED;

    /**
     * widdo.management.log-center.server-addr.
     */
    public static final String ITEM_LOG_CENTER_ENABLED = PROPERTY_MANAGEMENT_LOG_CENTER + ".server-addr";

    /**
     * spring.cloud.nacos.config.server-addr.
     */
    public static final String ITEM_NACOS_ENABLED = PROPERTY_NACOS_CONFIG + ".server-addr";

    /**
     * widdo.management.graph.neo4j.enabled.
     */
    public static final String ITEM_NEO4j_ENABLED = PROPERTY_GRAPH_NEO4J + PROPERTY_ENABLED;

    /**
     * widdo.management.graph.neo4j.actuator.
     */
    public static final String ITEM_NEO4j_ACTUATOR_ENABLED = PROPERTY_GRAPH_NEO4J + ".actuator" + PROPERTY_ENABLED;

    /**
     * widdo.management.graph.orientdb.enabled.
     */
    public static final String ITEM_ORIENTDB_ENABLED = PROPERTY_GRAPH_ORIENTDB + PROPERTY_ENABLED;


    /* ---------- Spring 详细配置属性路径 ---------- */

    /**
     * spring.application.name.
     */
    public static final String ITEM_SPRING_APPLICATION_NAME = "spring.application.name";

    /**
     * spring.jpa.hibernate.ddl-auto.
     */
    public static final String ITEM_SPRING_JPA_HIBERNATE_DDL_AUTO = "spring.jpa.hibernate.ddl-auto";

    /**
     * spring.sql.init.platform.
     */
    public static final String ITEM_SPRING_SQL_INIT_PLATFORM = "spring.sql.init.platform";


    /* ---------- 注解属性通用值 ---------- */

    /**
     * ${.
     */
    public static final String ANNOTATION_PREFIX = "${";

    /**
     * }.
     */
    public static final String ANNOTATION_SUFFIX = "}";

    /**
     * ${spring.application.name}.
     */
    public static final String ANNOTATION_APPLICATION_NAME = ANNOTATION_PREFIX
            + ITEM_SPRING_APPLICATION_NAME + ANNOTATION_SUFFIX;

    /**
     * ${spring.sql.init.platform}.
     */
    public static final String ANNOTATION_SQL_INIT_PLATFORM = ANNOTATION_PREFIX
            + ITEM_SPRING_SQL_INIT_PLATFORM + ANNOTATION_SUFFIX;

    /**
     * ${widdo.platform.debezium.enabled}.
     */
    public static final String ANNOTATION_DEBEZIUM_ENABLED = "${widdo.platform.debezium.enabled}";
}
