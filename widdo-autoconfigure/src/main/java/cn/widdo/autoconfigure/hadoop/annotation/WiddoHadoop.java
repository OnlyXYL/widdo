package cn.widdo.autoconfigure.hadoop.annotation;

import cn.widdo.starter.hadoop.WiddoStarterHadoop;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import java.lang.annotation.*;

/**
 * WiddoHadoop.
 *
 * @author XYL
 * @date 2023/09/08 14:25
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnHadoopEnabled
@ConditionalOnBean(WiddoStarterHadoop.class)
public @interface WiddoHadoop {

}
