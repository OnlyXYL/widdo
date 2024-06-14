package cn.widdo.autoconfigure.sql.annotation;

import cn.widdo.starter.sql.WiddoStarterSQL;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.lang.annotation.*;

/**
 * WiddoSQL.
 *
 * @author XYL
 * @date 2023/08/07 16:38
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnSQLParseEnabled
@ConditionalOnClass(WiddoStarterSQL.class)
public @interface WiddoSQL {

}
