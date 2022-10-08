package cn.widdo.graph.annotation;

import cn.widdo.graph.configuration.GraphConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启图谱支持
 *
 * @author XYL
 * @version 1.0
 * @date 2022/08/15 15:09
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({GraphConfiguration.class})
public @interface EnableWiddoGraph {
}
