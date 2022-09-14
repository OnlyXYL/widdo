package cn.widdo.kernel.annotation;

import cn.widdo.assistant.constant.PropertyConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * 资源保护注解
 *
 * @author XYL
 * @version 1.0
 * @date 2022/08/16 9:43
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnProperty(value = PropertyConstant.ITEM_ORIENTDB_ENABLED, havingValue = "true")
public @interface ConditionalOnServerProtectEnabled {
}
