package cn.widdo.autoconfigure.babelnet.configure;

import cn.widdo.autoconfigure.babelnet.actuator.BabelNetActuator;
import cn.widdo.autoconfigure.babelnet.actuator.DefaultBabelNetActuator;
import cn.widdo.autoconfigure.babelnet.properties.WiddoBabelNetProperties;
import cn.widdo.autoconfigure.condition.WiddoBabelNetActuator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * WiddoBabelNetActuatorConfigure.
 *
 * @author XYL
 * @date 2023/03/15 15:32
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
@WiddoBabelNetActuator
public class WiddoBabelNetActuatorConfigure {

    private static final Logger log = LoggerFactory.getLogger(WiddoBabelNetActuatorConfigure.class);

    /**
     * WiddoBabelNetProperties.
     */
    private WiddoBabelNetProperties widdoBabelNetProperties;

    @PostConstruct
    private void postConstruct() {
        log.info("[Widdo] |- AutoConfigure [Widdo BabelNet] Actuator.");
    }

    /**
     * an instance of BabelNetActuator.
     *
     * @param widdoBabelNetProperties
     * @return {@link BabelNetActuator}
     */
    @Bean
    @ConditionalOnMissingBean(BabelNetActuator.class)
    public BabelNetActuator babelNetActuator(WiddoBabelNetProperties widdoBabelNetProperties) {
        this.widdoBabelNetProperties = widdoBabelNetProperties;
        final String className = widdoBabelNetProperties.getActuator().getClassName();
        return createBabelNetActuator(className);
    }

    /**
     * create an instance type of {@link BabelNetActuator}.
     *
     * @param className the className of {@link BabelNetActuator}
     * @return return an instance type of {@link BabelNetActuator}
     */
    private BabelNetActuator createBabelNetActuator(String className) {
        try {

            if (StringUtils.hasLength(className)) {
                //check whether it`s legitimate
                ClassUtils.isPresent(className, DefaultBabelNetActuator.class.getClassLoader());
            } else {
                //set the default classname.
                className = DefaultBabelNetActuator.class.getName();
            }

            final Class<?> aClass = Class.forName(className);

            //反射，通过构造方法创建对象，需要Neo4jPreRWHelper实例.注意getConstructor方法只能获取public构造，protected和private需要getDeclaredConstructor方法
            final Constructor<?> constructor = aClass.getDeclaredConstructor(WiddoBabelNetProperties.class);
            //allow to access private constructor
            constructor.setAccessible(true);

            return (BabelNetActuator) constructor.newInstance(widdoBabelNetProperties);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException
                 | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
