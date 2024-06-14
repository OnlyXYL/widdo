package cn.widdo.autoconfigure.elasticsearch.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import java.lang.annotation.*;

/**
 * WiddoElasticsearch.
 *
 * @author XYL
 * @date 2023/10/27 15:32
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnElasticsearchEnabled
@ConditionalOnBean(WiddoElasticsearch.class)
public @interface WiddoElasticsearch {

}
