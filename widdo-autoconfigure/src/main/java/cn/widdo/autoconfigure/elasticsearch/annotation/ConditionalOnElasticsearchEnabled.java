package cn.widdo.autoconfigure.elasticsearch.annotation;

import cn.widdo.assistant.constant.PropertyConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * ConditionalOnElasticsearchEnabled.
 *
 * @author XYL
 * @date 2023/10/27 15:34
 * @since 305.2.2.0
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnProperty(value = PropertyConstant.ITEM_ELASTICSEARCH_ENABLED, havingValue = "true")
public @interface ConditionalOnElasticsearchEnabled {

}
