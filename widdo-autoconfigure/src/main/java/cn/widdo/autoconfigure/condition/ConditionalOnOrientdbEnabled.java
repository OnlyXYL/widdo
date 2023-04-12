package cn.widdo.autoconfigure.condition;

import cn.widdo.assistant.constant.PropertyConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * 开启orientdb注解.
 *
 * @author XYL
 * @date 2022/08/15 15:29
 * @since 302.1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnProperty(value = PropertyConstant.ITEM_ORIENTDB_ENABLED, havingValue = "true")
public @interface ConditionalOnOrientdbEnabled {
}
