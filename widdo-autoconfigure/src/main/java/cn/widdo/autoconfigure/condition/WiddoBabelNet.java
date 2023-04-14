package cn.widdo.autoconfigure.condition;

import cn.widdo.babelnet.WiddoStarterBabelNet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.lang.annotation.*;

/**
 * 启用BabelNet注解.
 *
 * @author XYL
 * @date 2022/12/02 18:38
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnBabelNetEnabled
@ConditionalOnClass(WiddoStarterBabelNet.class)
public @interface WiddoBabelNet {
}
