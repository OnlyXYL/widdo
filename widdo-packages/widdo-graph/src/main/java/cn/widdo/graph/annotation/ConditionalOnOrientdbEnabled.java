package cn.widdo.graph.annotation;

import cn.widdo.assistant.constant.PropertyConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * 开启orientdb注解
 *
 * @author XYL
 * @version 1.0
 * @date 2022/08/15 15:29
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnProperty(value = PropertyConstant.ITEM_ORIENTDB_ENABLED, havingValue = "true")
public @interface ConditionalOnOrientdbEnabled {
}
