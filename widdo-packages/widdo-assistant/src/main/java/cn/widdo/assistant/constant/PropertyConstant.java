package cn.widdo.assistant.constant;

/**
 * 常量
 *
 * @author XYL
 * @version 1.0
 * @date 2022/08/15 15:03
 */
@SuppressWarnings("AlibabaConstantFieldShouldBeUpperCase")
public class PropertyConstant {

    /* ---------- 通用配置属性 ---------- */

    public static final String PROPERTY_ENABLED = ".enabled";

    public static final String PROPERTY_PREFIX_SPRING = "spring";
    public static final String PROPERTY_PREFIX_SMART = "widdo";

    public static final String PROPERTY_SPRING_CLOUD = PROPERTY_PREFIX_SPRING + ".cloud";
    public static final String PROPERTY_SPRING_REDIS = PROPERTY_PREFIX_SPRING + ".redis";


    /* ---------- widdo 基础配置属性（第一层） ---------- */

    public static final String PROPERTY_SMART_PLATFORM = PROPERTY_PREFIX_SMART + ".platform";
    public static final String PROPERTY_SMART_MANAGEMENT = PROPERTY_PREFIX_SMART + ".management";
    public static final String PROPERTY_SMART_SOCIAL = PROPERTY_PREFIX_SMART + ".social";
    public static final String PROPERTY_SMART_INTEGRATION = PROPERTY_PREFIX_SMART + ".integration";
    public static final String PROPERTY_SMART_MESSAGE = PROPERTY_PREFIX_SMART + ".message";
    public static final String PROPERTY_SMART_WEBSOCKET = PROPERTY_PREFIX_SMART + ".websocket";


    /* ---------- widdo 配置属性（第二层） ---------- */

    /**
     * platform
     */
    public static final String PROPERTY_PLATFORM_CACHE = PROPERTY_SMART_PLATFORM + ".cache";
    public static final String PROPERTY_PLATFORM_REST = PROPERTY_SMART_PLATFORM + ".rest";
    public static final String PROPERTY_PLATFORM_SECURITY = PROPERTY_SMART_PLATFORM + ".security";
    public static final String PROPERTY_PLATFORM_SWAGGER = PROPERTY_SMART_PLATFORM + ".swagger";
    public static final String PROPERTY_PLATFORM_STAMP = PROPERTY_SMART_PLATFORM + ".stamp";
    /**
     * management
     */
    public static final String PROPERTY_MANAGEMENT_NACOS = PROPERTY_SMART_MANAGEMENT + ".nacos";
    public static final String PROPERTY_MANAGEMENT_QUEUE = PROPERTY_SMART_MANAGEMENT + ".queue";
    public static final String PROPERTY_MANAGEMENT_LOG_CENTER = PROPERTY_SMART_MANAGEMENT + ".log-center";
    public static final String PROPERTY_MANAGEMENT_SERVICE = PROPERTY_SMART_MANAGEMENT + ".service";
    public static final String PROPERTY_MANAGEMENT_GRAPH = PROPERTY_SMART_MANAGEMENT + ".graph";
    /**
     * social
     */
    public static final String PROPERTY_SOCIAL_JUSTAUTH = PROPERTY_SMART_SOCIAL + ".justauth";
    public static final String PROPERTY_SOCIAL_EASEMOB = PROPERTY_SMART_SOCIAL + ".easemob";
    public static final String PROPERTY_SOCIAL_WXAPP = PROPERTY_SMART_SOCIAL + ".wxapp";
    public static final String PROPERTY_SOCIAL_WXMPP = PROPERTY_SMART_SOCIAL + ".wxmpp";
    /**
     * integration
     */
    public static final String PROPERTY_INTEGRATION_AUDIT = PROPERTY_SMART_INTEGRATION + ".audit";
    public static final String PROPERTY_INTEGRATION_OSS = PROPERTY_SMART_INTEGRATION + ".oss";
    public static final String PROPERTY_INTEGRATION_INFLUXDB = PROPERTY_SMART_INTEGRATION + ".influxdb";
    public static final String PROPERTY_INTEGRATION_MAVEN = PROPERTY_SMART_INTEGRATION + ".maven";
    public static final String PROPERTY_INTEGRATION_PAY = PROPERTY_SMART_INTEGRATION + ".pay";
    /**
     * message
     */
    public static final String PROPERTY_MESSAGE_SMS = PROPERTY_SMART_MESSAGE + ".sms";
    public static final String PROPERTY_MESSAGE_SMS_VERIFICATION_CODE = PROPERTY_MESSAGE_SMS + ".verification-code";
    public static final String PROPERTY_MESSAGE_PUSH_APNS = PROPERTY_SMART_MESSAGE + ".push.apns";
    public static final String PROPERTY_MESSAGE_PUSH_JPUSH = PROPERTY_SMART_MESSAGE + ".push.jpush";


    /* ---------- smart 详细配置属性（第三层） ---------- */

    /**
     * security
     */
    public static final String PROPERTY_SECURITY_CAPTCHA = PROPERTY_PLATFORM_SECURITY + ".captcha";
    /**
     * audit
     */
    public static final String PROPERTY_AUDIT_ALIYUN = PROPERTY_INTEGRATION_AUDIT + ".aliyun";
    public static final String PROPERTY_AUDIT_BAIDU = PROPERTY_INTEGRATION_AUDIT + ".baidu";
    public static final String PROPERTY_AUDIT_TIANYAN = PROPERTY_INTEGRATION_AUDIT + ".tianyan";
    /**
     * oss
     */
    public static final String PROPERTY_OSS_MINIO = PROPERTY_INTEGRATION_OSS + ".minio";
    public static final String PROPERTY_PAY_ALIPAY = PROPERTY_INTEGRATION_PAY + ".alipay";
    public static final String PROPERTY_PAY_WXIPAY = PROPERTY_INTEGRATION_PAY + ".wxpay";


    /* ---------- Spring 相关基础配置属性（第一层） ---------- */

    public static final String PROPERTY_REDIS_REDISSON = PROPERTY_SPRING_REDIS + ".redisson";
    public static final String PROPERTY_NACOS_CONFIG = PROPERTY_SPRING_CLOUD + ".nacos.config";


    /* ---------- widdo 详细配置属性路径 ---------- */

    public static final String ITEM_PLATFORM_DATA_ACCESS_STRATEGY = PROPERTY_SMART_PLATFORM + ".data-access-strategy";
    public static final String ITEM_PLATFORM_ARCHITECTURE = PROPERTY_SMART_PLATFORM + ".architecture";

    public static final String ITEM_MINIO_ENDPOINT = PROPERTY_OSS_MINIO + ".endpoint";
    public static final String ITEM_MINIO_ACCESSKEY = PROPERTY_OSS_MINIO + ".accessKey";
    public static final String ITEM_MINIO_SECRETKEY = PROPERTY_OSS_MINIO + ".secretKey";

    public static final String ITEM_ALIPAY_STORAGE = PROPERTY_PAY_ALIPAY + ".storage";
    public static final String ITEM_ALIPAY_ENABLED = PROPERTY_PAY_ALIPAY + PROPERTY_ENABLED;
    public static final String ITEM_WXPAY_ENABLED = PROPERTY_PAY_WXIPAY + PROPERTY_ENABLED;

    public static final String ITEM_INFLUXDB_URL = PROPERTY_INTEGRATION_INFLUXDB + ".url";
    public static final String ITEM_INFLUXDB_DATABASE = PROPERTY_INTEGRATION_INFLUXDB + ".database";
    public static final String ITEM_INFLUXDB_USERNAME = PROPERTY_INTEGRATION_INFLUXDB + ".username";
    public static final String ITEM_INFLUXDB_PASSWORD = PROPERTY_INTEGRATION_INFLUXDB + ".password";


    public static final String ITEM_SWAGGER_ENABLED = PROPERTY_PLATFORM_SWAGGER + PROPERTY_ENABLED;
    public static final String ITEM_REDISSON_ENABLED = PROPERTY_REDIS_REDISSON + PROPERTY_ENABLED;
    public static final String ITEM_KAFKA_ENABLED = PROPERTY_MANAGEMENT_QUEUE + ".kafka" + PROPERTY_ENABLED;
    public static final String ITEM_LOG_CENTER_ENABLED = PROPERTY_MANAGEMENT_LOG_CENTER + ".server-addr";
    public static final String ITEM_NACOS_ENABLED = PROPERTY_NACOS_CONFIG + ".server-addr";
    public static final String ITEM_NEO4j_ENABLED = PROPERTY_MANAGEMENT_GRAPH + ".neo4j" + PROPERTY_ENABLED;
    public static final String ITEM_ORIENTDB_ENABLED = PROPERTY_MANAGEMENT_GRAPH + ".orientdb" + PROPERTY_ENABLED;


    /* ---------- Spring 详细配置属性路径 ---------- */

    public static final String ITEM_SPRING_APPLICATION_NAME = "spring.application.name";
    public static final String ITEM_SPRING_JPA_HIBERNATE_DDL_AUTO = "spring.jpa.hibernate.ddl-auto";
    public static final String ITEM_SPRING_SQL_INIT_PLATFORM = "spring.sql.init.platform";


    /* ---------- 注解属性通用值 ---------- */

    public static final String ANNOTATION_PREFIX = "${";
    public static final String ANNOTATION_SUFFIX = "}";

    public static final String ANNOTATION_APPLICATION_NAME = ANNOTATION_PREFIX + ITEM_SPRING_APPLICATION_NAME + ANNOTATION_SUFFIX;
    public static final String ANNOTATION_SQL_INIT_PLATFORM = ANNOTATION_PREFIX + ITEM_SPRING_SQL_INIT_PLATFORM + ANNOTATION_SUFFIX;
    public static final String ANNOTATION_DEBEZIUM_ENABLED = "${widdo.platform.debezium.enabled}";
}
