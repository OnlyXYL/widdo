package cn.widdo.data.annotation;

import cn.widdo.data.configuration.DataConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启动数据处理.
 *
 * @author XYL
 * @date 2022/09/21 16:35
 * @since 263.1.1.0
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ DataConfiguration.class })
public @interface EnableWiddoData {

}
