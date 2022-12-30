package cn.widdo.autoconfigure.neo4j.configure;

import cn.widdo.autoconfigure.condition.WiddoNeo4jActuator;
import cn.widdo.autoconfigure.neo4j.actuator.DefaultNeo4jActuator;
import cn.widdo.autoconfigure.neo4j.actuator.Neo4jActuator;
import cn.widdo.autoconfigure.neo4j.helper.Neo4jPreRWHelper;
import cn.widdo.autoconfigure.neo4j.properties.WiddoNeo4jProperties;
import org.neo4j.driver.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * neo4j read writer actuator.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/18 0:50
 */
@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
@WiddoNeo4jActuator
public class WiddoNeo4jActuatorConfigure {

    private static final Logger log = LoggerFactory.getLogger(WiddoNeo4jActuatorConfigure.class);

    /**
     * properties.
     */
    @Resource
    private WiddoNeo4jProperties widdoNeo4jProperties;

    /**
     * driver.
     */
    @Resource
    private Driver driver;

    /**
     * {@link Neo4jPreRWHelper}.
     */
    private Neo4jPreRWHelper neo4jPreRWHelper;

    /**
     * {@link Neo4jActuator}.
     */
    private Neo4jActuator neo4jActuator;

    @PostConstruct
    private void postConstruct() {
        log.info("[Widdo] |- AutoConfigure [Widdo Neo4j] Actuator.");
        createNeo4jPreRWHelper();
    }

    /**
     * get instance called {@link Neo4jPreRWHelper}.
     */
    private synchronized void createNeo4jPreRWHelper() {

        try {

            if (neo4jPreRWHelper == null) {
                final Class<?> aClass = Class.forName(Neo4jPreRWHelper.class.getName());
                final Constructor<?> constructor = aClass.getDeclaredConstructor(Driver.class);
                //allow to access private constructor
                constructor.setAccessible(true);
                neo4jPreRWHelper = (Neo4jPreRWHelper) constructor.newInstance(driver);
            }

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * create instance type of {@link Neo4jActuator}.
     *
     * @return return an instance type of {@link Neo4jActuator}
     */
    @Bean
    @ConditionalOnMissingBean(Neo4jActuator.class)
    public Neo4jActuator neo4jActuator() {
        final String className = widdoNeo4jProperties.getActuator().getClassName();
        return createNeo4jActuator(className);
    }

    /**
     * create an instance type of {@link Neo4jActuator}.
     *
     * @param className the className of {@link Neo4jActuator}
     * @return return an instance type of {@link Neo4jActuator}
     */
    private Neo4jActuator createNeo4jActuator(String className) {
        try {

            if (neo4jActuator != null) {
                return neo4jActuator;
            }

            if (StringUtils.hasLength(className)) {
                //check whether it`s legitimate
                ClassUtils.isPresent(className, DefaultNeo4jActuator.class.getClassLoader());
            } else {
                //set the default classname.
                className = DefaultNeo4jActuator.class.getName();
            }

            final Class<?> aClass = Class.forName(className);

            //反射，通过构造方法创建对象，需要Neo4jPreRWHelper实例.注意getConstructor方法只能获取public构造，protected和private需要getDeclaredConstructor方法
            final Constructor<?> constructor = aClass.getDeclaredConstructor(WiddoNeo4jProperties.class, Neo4jPreRWHelper.class);
            //allow to access private constructor
            constructor.setAccessible(true);
            return (Neo4jActuator) constructor.newInstance(widdoNeo4jProperties, neo4jPreRWHelper);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
