package cn.widdo.autoconfigure.condition;

import cn.widdo.autoconfigure.neo4j.configure.WiddoNeo4jAutoConfigure;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import java.lang.annotation.*;

/**
 * 开启neo4j读写.
 *
 * @author XYL
 * @date 2022/10/19 1:03
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AutoConfigureAfter(WiddoNeo4jAutoConfigure.class)
@ConditionalOnNeo4jActuatorEnabled
@ConditionalOnBean(WiddoNeo4jAutoConfigure.class)
public @interface WiddoNeo4jActuator {
}
