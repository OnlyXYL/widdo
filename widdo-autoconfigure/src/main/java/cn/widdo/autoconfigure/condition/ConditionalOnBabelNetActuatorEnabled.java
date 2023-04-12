package cn.widdo.autoconfigure.condition;

import cn.widdo.assistant.constant.PropertyConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * ConditionalOnBabelNetActuator.
 *
 * @author XYL
 * @date 2023/03/15 15:36
 * @since 302.1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnProperty(value = PropertyConstant.ITEM_BABELNET_ACTUATOR_ENABLED, havingValue = "true")
public @interface ConditionalOnBabelNetActuatorEnabled {
}
