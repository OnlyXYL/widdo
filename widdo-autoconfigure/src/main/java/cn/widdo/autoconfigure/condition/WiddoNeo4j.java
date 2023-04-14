package cn.widdo.autoconfigure.condition;

import cn.widdo.starter.neo4j.WiddoStarterNeo4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.lang.annotation.*;

/**
 * 开启neo4j.
 *
 * @author XYL
 * @date 2022/10/18 1:08
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnNeo4jEnabled
@ConditionalOnClass(WiddoStarterNeo4j.class)
public @interface WiddoNeo4j {
}
