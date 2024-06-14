package cn.widdo.autoconfigure.hadoop.annotation;

import cn.widdo.assistant.constant.PropertyConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * ConditionalOnHadoopEnabled.
 *
 * @author XYL
 * @date 2023/09/08 11:53
 * @since 305.2.2.0
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnProperty(value = PropertyConstant.ITEM_HADOOP_ENABLED, havingValue = "true")
public @interface ConditionalOnHadoopEnabled {

}
