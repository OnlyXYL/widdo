package cn.widdo.autoconfigure.neo4j.actuator;

import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;
import org.neo4j.driver.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Neo4j AbstractNeo4jActuator.
 *
 * @param <T> t
 * @param <R> r
 * @author XYL
 * @date 2023/03/03 11:17
 * @since 263.1.3.0
 */
public abstract class AbstractNeo4jActuator<T, R> implements Neo4jActuator<T, R> {

	/**
	 * logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger(AbstractNeo4jActuator.class);

	/**
	 * driver.
	 */
	protected Driver driver;

	@Override
	public Driver driver() {
		return driver;
	}

	/**
	 * create object instance by reflect.
	 * @param className the full path of the class
	 * @param cypherType cypher type.like: query,write
	 * @param params the params of the neo4j cypher
	 * @return cn.widdo.starter.neo4j.entity.result.Result<java.util.List < java.util.Map
	 * < java.lang.String, cn.widdo.starter.neo4j.entity.Value>>>
	 * @author XYL
	 * @date 2022/12/23 11:43:46
	 **/
	protected Result<List<Map<String, Value>>> reflectObject(String className, String cypherType,
			Map<String, Object> params) {
		try {
			final Class<?> aClass = Class.forName(className);

			// 反射，通过构造方法创建对象，需要Neo4jPreRWHelper实例.注意getConstructor方法只能获取public构造，protected和private需要getDeclaredConstructor方法
			final Constructor<?> constructor = aClass.getDeclaredConstructor(Driver.class);
			// allow to access private constructor
			constructor.setAccessible(true);
			final Object classObj = constructor.newInstance(driver);

			final Method query = aClass.getMethod(cypherType, Map.class);

			return (Result<List<Map<String, Value>>>) query.invoke(classObj, params);
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			e.printStackTrace();
			logger.error("reflectObject error:{}", e);
		}

		return null;
	}

}
