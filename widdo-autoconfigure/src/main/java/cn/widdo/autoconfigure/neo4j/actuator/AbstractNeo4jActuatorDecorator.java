package cn.widdo.autoconfigure.neo4j.actuator;

import cn.widdo.autoconfigure.neo4j.helper.Neo4jPreRWHelper;
import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * AbstractNeo4jActuator.
 *
 * @param <R>
 * @param <T>
 * @author XYL
 * @date 2022/12/03 18:59
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public abstract class AbstractNeo4jActuatorDecorator<T, R> extends AbstractNeo4jActuator<T, R> {

    /**
     * {@link Neo4jActuator}.
     */
    protected Neo4jActuator<T, R> neo4jActuator;


    /**
     * create object instance by reflect.
     *
     * @param className  className
     * @param cypherType cypherType
     * @param params     params
     * @return cn.widdo.starter.neo4j.entity.result.Result<java.util.List < java.util.Map < java.lang.String, cn.widdo.starter.neo4j.entity.Value>>>
     * @author XYL
     * @date 2023/03/01 18:42:31
     * @since 302.1.0.0
     */
    protected Result<List<Map<String, Value>>> reflectObject(String className, String cypherType, Map<String, Object> params) {
        try {
            final Class<?> aClass = Class.forName(className);

            //反射，通过构造方法创建对象，需要Neo4jPreRWHelper实例.注意getConstructor方法只能获取public构造，protected和private需要getDeclaredConstructor方法
            final Constructor<?> constructor = aClass.getDeclaredConstructor(Neo4jPreRWHelper.class);
            //allow to access private constructor
            constructor.setAccessible(true);
            final Object classObj = constructor.newInstance(null);

            final Method query = aClass.getMethod(cypherType, Map.class);

            return (Result<List<Map<String, Value>>>) query.invoke(classObj, params);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
