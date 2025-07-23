package cn.widdo.autoconfigure.elasticsearch.annotation;

import cn.widdo.starter.elasticsearch.WiddoStarterElasticSearch;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.lang.annotation.*;

/**
 * WiddoElasticsearch.
 *
 * @author XYL
 * @date 2023/10/27 15:32
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnElasticsearchEnabled
@ConditionalOnClass(WiddoStarterElasticSearch.class)
public @interface WiddoElasticsearch {

}
