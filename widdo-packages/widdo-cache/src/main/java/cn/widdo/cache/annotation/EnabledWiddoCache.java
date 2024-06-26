package cn.widdo.cache.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启widdo cache注解.
 *
 * @author XYL
 * @date 2022/08/16 18:48
 * @since 263.1.1.0
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({})
public @interface EnabledWiddoCache {

}
