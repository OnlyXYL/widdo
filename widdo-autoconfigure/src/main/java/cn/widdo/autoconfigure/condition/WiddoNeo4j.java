package cn.widdo.autoconfigure.condition;

import cn.widdo.starter.neo4j.WiddoStarterNeo4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.lang.annotation.*;

/**
 * 开启neo4j
 *
 * @author XYL
 * @version 263.1.0.0
 * @date 2022/10/18 1:08
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnNeo4jEnabled
@ConditionalOnClass(WiddoStarterNeo4j.class)
public @interface WiddoNeo4j {
}
