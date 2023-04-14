package cn.widdo.cache.annotation;

import cn.widdo.cache.configuration.redisson.MyRedissonConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * redisson注解.
 *
 * @author XYL
 * @date 2022/09/07 18:16
 * @since 263.1.1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({MyRedissonConfiguration.class})
public @interface EnabledRedisson {
}
