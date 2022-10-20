package cn.widdo.cache.annotation;

import cn.widdo.assistant.constant.PropertyConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * 是否开启redisson注解
 *
 * @author XYL
 * @version 263.1.0.0
 * @date 2022/08/16 18:47
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnProperty(value = PropertyConstant.ITEM_REDISSON_ENABLED, havingValue = "true")
public @interface ConditionalOnRedissonEnabled {
}
