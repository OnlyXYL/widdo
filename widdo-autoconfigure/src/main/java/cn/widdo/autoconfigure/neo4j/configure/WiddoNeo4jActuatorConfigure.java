package cn.widdo.autoconfigure.neo4j.configure;

import cn.widdo.autoconfigure.neo4j.annotation.WiddoNeo4jActuator;
import cn.widdo.autoconfigure.neo4j.actuator.DefaultNeo4jActuator;
import cn.widdo.autoconfigure.neo4j.actuator.Neo4jActuator;
import cn.widdo.autoconfigure.neo4j.properties.WiddoNeo4jProperties;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.neo4j.driver.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * neo4j read writer actuator.
 *
 * @author XYL
 * @date 2022/10/18 0:50
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
@WiddoNeo4jActuator
public class WiddoNeo4jActuatorConfigure {

	private static final Logger log = LoggerFactory.getLogger(WiddoNeo4jActuatorConfigure.class);

	/**
	 * properties.
	 */
	private WiddoNeo4jProperties widdoNeo4jProperties;

	/**
	 * driver.
	 */
	@Resource
	private Driver driver;

	@PostConstruct
	private void postConstruct() {
		log.info("[Widdo] |- AutoConfigure [Widdo Neo4j] Actuator.");
	}

	/**
	 * create instance type of {@link Neo4jActuator}.
	 * @param widdoNeo4jProperties widdoNeo4jProperties
	 * @return return an instance type of {@link Neo4jActuator}
	 */
	@Bean
	@ConditionalOnMissingBean(Neo4jActuator.class)
	public Neo4jActuator neo4jActuator(WiddoNeo4jProperties widdoNeo4jProperties) {
		this.widdoNeo4jProperties = widdoNeo4jProperties;
		final String className = widdoNeo4jProperties.getActuator().getClassName();
		return createNeo4jActuator(className);
	}

	/**
	 * create an instance type of {@link Neo4jActuator}.
	 * @param className the className of {@link Neo4jActuator}
	 * @return return an instance type of {@link Neo4jActuator}
	 */
	private Neo4jActuator createNeo4jActuator(String className) {
		try {

			if (StringUtils.hasLength(className)) {
				// check whether it`s legitimate
				ClassUtils.isPresent(className, Neo4jActuator.class.getClassLoader());
			}
			else {
				// set the default classname.
				className = DefaultNeo4jActuator.class.getName();
			}

			final Class<?> aClass = Class.forName(className);

			// 反射，通过构造方法创建对象，需要Neo4jPreRWHelper实例.注意getConstructor方法只能获取public构造，protected和private需要getDeclaredConstructor方法
			final Constructor<?> constructor = aClass.getDeclaredConstructor(WiddoNeo4jProperties.class, Driver.class);
			// allow to access private constructor
			constructor.setAccessible(true);

			return (Neo4jActuator) constructor.newInstance(widdoNeo4jProperties, driver);

		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			e.printStackTrace();
		}

		return null;
	}

}
