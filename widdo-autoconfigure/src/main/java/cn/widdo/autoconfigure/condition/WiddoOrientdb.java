package cn.widdo.autoconfigure.condition;

import cn.widdo.starter.orientdb.WiddoStarterOrientdb;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.lang.annotation.*;

/**
 * annotation to make orientdb enable.
 *
 * @author XYL
 * @date 2023/02/28 16:08
 * @since 263.1.3.0
 */
@SuppressWarnings("ALL")
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnOrientdbEnabled
@ConditionalOnClass(WiddoStarterOrientdb.class)
public @interface WiddoOrientdb {
}
