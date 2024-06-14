package cn.widdo.autoconfigure.babelnet.annotation;

import cn.widdo.assistant.constant.PropertyConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * ConditionalOnBabelNetEnabled.
 *
 * @author XYL
 * @date 2023/03/15 14:30
 * @since 302.1.0.0
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnProperty(value = PropertyConstant.ITEM_BABELNET_ENABLED, havingValue = "true")
public @interface ConditionalOnBabelNetEnabled {

}
