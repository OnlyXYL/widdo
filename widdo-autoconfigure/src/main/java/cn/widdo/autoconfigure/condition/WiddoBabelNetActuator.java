package cn.widdo.autoconfigure.condition;

import cn.widdo.autoconfigure.babelnet.configure.WiddoBabelNetAutoConfigure;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import java.lang.annotation.*;

/**
 * BabelNetActuator.
 *
 * @author XYL
 * @date 2023/03/15 15:30
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AutoConfigureAfter(WiddoBabelNetAutoConfigure.class)
@ConditionalOnBabelNetActuatorEnabled
@ConditionalOnBean(WiddoBabelNetAutoConfigure.class)
public @interface WiddoBabelNetActuator {
}
