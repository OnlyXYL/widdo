package cn.widdo.graph.annotation;

import cn.widdo.assistant.constant.PropertyConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * 是否开启neo4j注解
 *
 * 通用配置注解
 * <p>
 * 注解是一种能被加载到java源代码中的元数据，可以看作一种标记，可以用来标记 类，接口，方法，参数，属性，包。程序在编译或者运行时，可以检测到这些标记而进行一些特殊的处理
 * <p>
 * 创建一个注解的基本元素:
 * <p>
 * 1. 修饰符    必须为public,默认为public
 * 2. 关键字    @interface
 * 3. 注解名称  EnableCloudApplication
 * 4. 注解类型元素   注解中的内容，类似属性。
 * 5. 元注解
 * {@link Target    用于描述注解的作用范围 参考 {@link ElementType}}
 * {@link Retention}    表明该注解的生命周期 参考 {@link RetentionPolicy}
 * {@link Inherited}    是一个标记注解，表明某个被标注的类型是被继承的。如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类
 * {@link Documented}   表明该注解标记的元素可以被javadoc 或类似的工具文档化
 *
 * @author XYL
 * @version 1.0
 * @date 2022/08/15 15:19
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnProperty(value = PropertyConstant.ITEM_NEO4j_ENABLED, havingValue = "true")
public @interface ConditionalOnNeo4jEnabled {
}
