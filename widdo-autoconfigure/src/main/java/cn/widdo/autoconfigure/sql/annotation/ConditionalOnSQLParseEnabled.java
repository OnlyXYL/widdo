package cn.widdo.autoconfigure.sql.annotation;

import cn.widdo.assistant.constant.PropertyConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * ConditionalOnSQLParseEnabled.
 *
 * @author XYL
 * @date 2023/08/07 16:29
 * @since 305.2.2.0
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnProperty(value = PropertyConstant.ITEM_SQL_PARSER_ENABLED, havingValue = "true")
public @interface ConditionalOnSQLParseEnabled {

}
