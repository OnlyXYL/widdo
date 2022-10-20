package cn.widdo.autoconfigure.neo4j.actuator;

import cn.widdo.autoconfigure.neo4j.properties.WiddoNeo4jProperties;
import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * neo4j 默认执行器
 * <p>
 * 需要实例化，操作neo4j的 reader 和 writer
 *
 * @author XYL
 * @version 1.2
 * @date 2022/10/18 11:44
 */
public class DefaultNeo4jActuator implements Neo4jActuator<Map<String, Object>> {

    private WiddoNeo4jProperties widdoNeo4jProperties;

    public DefaultNeo4jActuator(WiddoNeo4jProperties widdoNeo4jProperties) {
        this.widdoNeo4jProperties = widdoNeo4jProperties;
    }

    @Override
    public Result<List<Map<String, Value>>> read(Map<String, Object> params) {
        return readIfHave(params);
    }

    @Override
    public Result<List<Map<String, Value>>> write(Map<String, Object> params) {
        return null;
    }

    /**
     * 实例化 neo4j reader
     * <p>
     * 根据配置文件中指定的reader实现类全路径，反射生成实例，并调用方法
     *
     * @return cn.widdo.autoconfigure.neo4j.reader.Neo4jReader
     * @author XYL
     * @className cn.widdo.autoconfigure.neo4j.actuator.DefaultNeo4jActuator
     * @date 2022/10/18 21:45
     **/
    private Result<List<Map<String, Value>>> readIfHave(Map<String, Object> params) {

        try {
            final String className = widdoNeo4jProperties.getReader().getClassName();

            Assert.notNull(className, "[Widdo] |- AutoConfigure [Widdo Neo4j] Actuator. [Message] |- The class of neo4jActuator must not be null.");

            final Class<?> aClass = Class.forName(className);

            final Object classObj = aClass.getDeclaredConstructor().newInstance();

            final Method query = aClass.getMethod("query", Map.class);

            return (Result<List<Map<String, Value>>>) query.invoke(classObj, params);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
