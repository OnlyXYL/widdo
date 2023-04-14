package cn.widdo.autoconfigure.condition;

import cn.widdo.assistant.constant.PropertyConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * neo4j rw 开启.
 *
 * @author XYL
 * @date 2022/10/18 0:58
 * @since 263.1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnProperty(value = PropertyConstant.ITEM_NEO4j_ACTUATOR_ENABLED, havingValue = "true")
public @interface ConditionalOnNeo4jActuatorEnabled {
}
