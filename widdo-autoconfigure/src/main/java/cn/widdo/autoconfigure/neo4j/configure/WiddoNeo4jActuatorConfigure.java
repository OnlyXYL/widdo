package cn.widdo.autoconfigure.neo4j.configure;

import cn.widdo.autoconfigure.condition.WiddoNeo4jActuator;
import cn.widdo.autoconfigure.neo4j.actuator.DefaultNeo4jActuator;
import cn.widdo.autoconfigure.neo4j.actuator.Neo4jActuator;
import cn.widdo.autoconfigure.neo4j.helper.Neo4jPreRWHelper;
import cn.widdo.autoconfigure.neo4j.properties.WiddoNeo4jProperties;
import org.neo4j.driver.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

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
     * dirver.
     */
    @Resource
    private Driver driver;

    /**
     * {@link Neo4jPreRWHelper}.
     */
    private Neo4jPreRWHelper neo4jPreRWHelper;

    @PostConstruct
    private void postConstruct() {
        log.info("[Widdo] |- AutoConfigure [Widdo Neo4j] Actuator.");
        initNeo4jPreRWHelper();
    }

    /**
     * Neo4jActuator instance.
     *
     * @return a Neo4jActuator instance.
     */
    @Bean
    public Neo4jActuator neo4jActuator() {
        return new DefaultNeo4jActuator(widdoNeo4jProperties, neo4jPreRWHelper);
    }

    /**
     * get instance called {@link Neo4jPreRWHelper}.
     */
    private synchronized void initNeo4jPreRWHelper() {

        try {

            if (neo4jPreRWHelper == null) {
                final Class<?> aClass = Class.forName(Neo4jPreRWHelper.class.getName());
                final Constructor<?> constructor = aClass.getConstructor(Driver.class);
                neo4jPreRWHelper = (Neo4jPreRWHelper) constructor.newInstance(driver);
            }

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
