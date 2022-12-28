package cn.widdo.autoconfigure.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.lang.annotation.*;

/**
 * 启用BabelNet注解.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/12/02 18:38
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnNeo4jEnabled
@ConditionalOnClass(WiddoBabelNet.class)
public @interface WiddoBabelNet {
}
