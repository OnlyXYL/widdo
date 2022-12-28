package cn.widdo.autoconfigure.neo4j.actuator;

import cn.widdo.autoconfigure.neo4j.helper.Neo4jPreRWHelper;
import cn.widdo.autoconfigure.neo4j.properties.WiddoNeo4jProperties;
import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;
import org.springframework.util.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * neo4j 默认执行器.
 * <p>
 * 需要实例化，操作neo4j的 reader 和 writer
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/18 11:44
 */
@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
public class DefaultNeo4jActuator implements Neo4jActuator<Map<String, Object>, Result<List<Map<String, Value>>>> {

    /**
     * Type constant of neo4j query cypher.
     */
    private static final String CYPHER_QUERY = "query";

    /**
     * Type constant of neo4j write cypher.
     */
    private static final String CYPHER_WRITE = "write";

    /**
     * properties.
     */
    private final WiddoNeo4jProperties widdoNeo4jProperties;

    /**
     * Neo4jPreWHelper.
     */
    private final Neo4jPreRWHelper neo4jPreRWHelper;

    /**
     * constructor has two params one called {@link WiddoNeo4jProperties},and another called {@link Neo4jPreRWHelper}.
     *
     * @param widdoNeo4jProperties {@link WiddoNeo4jProperties}
     * @param neo4jPreRWHelper     {@link Neo4jPreRWHelper}
     */
    public DefaultNeo4jActuator(final WiddoNeo4jProperties widdoNeo4jProperties, final Neo4jPreRWHelper neo4jPreRWHelper) {
        this.widdoNeo4jProperties = widdoNeo4jProperties;
        this.neo4jPreRWHelper = neo4jPreRWHelper;
    }

    @Override
    public Result<List<Map<String, Value>>> read(Map<String, Object> params) {
        return this.readIfHave(params);
    }

    @Override
    public Result<List<Map<String, Value>>> write(Map<String, Object> params) {
        return this.writeIfHave(params);
    }

    /**
     * 实例化 neo4j reader.
     * <p>
     * 根据配置文件中指定的reader实现类全路径，反射生成实例，并调用方法
     *
     * @param params params
     * @return cn.widdo.autoconfigure.neo4j.reader.Neo4jReader
     * @author XYL
     * @className cn.widdo.autoconfigure.neo4j.actuator.DefaultNeo4jActuator
     * @date 2022/10/18 21:45
     **/
    private Result<List<Map<String, Value>>> readIfHave(Map<String, Object> params) {

        final String className = widdoNeo4jProperties.getReader().getClassName();

        Assert.notNull(className, "[Widdo] |- AutoConfigure [Widdo Neo4j] Reader. [Message] |- The class of Neo4jReader must not be null.");

        return reflectObject(className, CYPHER_QUERY, params);
    }

    /**
     * 写数据.
     *
     * @param params params
     * @return cn.widdo.starter.neo4j.entity.result.Result<java.util.List < java.util.Map < java.lang.String, cn.widdo.starter.neo4j.entity.Value>>>
     * @author XYL
     * @date 2022/12/03 17:13:12
     **/
    private Result<List<Map<String, Value>>> writeIfHave(Map<String, Object> params) {
        final String className = widdoNeo4jProperties.getWriter().getClassName();

        Assert.notNull(className, "[Widdo] |- AutoConfigure [Widdo Neo4j] Writer. [Message] |- The class of Neo4jWriter must not be null.");

        return reflectObject(className, CYPHER_WRITE, params);
    }

    /**
     * create object instance by reflect.
     *
     * @param className  the full path of the class
     * @param cypherType cypher type.like: query,write
     * @param params     the params of the neo4j cypher
     * @return cn.widdo.starter.neo4j.entity.result.Result<java.util.List < java.util.Map < java.lang.String, cn.widdo.starter.neo4j.entity.Value>>>
     * @author XYL
     * @date 2022/12/23 11:43:46
     **/
    private Result<List<Map<String, Value>>> reflectObject(String className, String cypherType, Map<String, Object> params) {
        try {
            final Class<?> aClass = Class.forName(className);

            //反射，通过构造方法创建对象，需要Neo4jPreRWHelper实例.注意getConstructor方法只能获取public构造，protected和private需要getDeclaredConstructor方法
            final Constructor<?> constructor = aClass.getConstructor(Neo4jPreRWHelper.class);
            final Object classObj = constructor.newInstance(neo4jPreRWHelper);

            final Method query = aClass.getMethod(cypherType, Map.class);

            return (Result<List<Map<String, Value>>>) query.invoke(classObj, params);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
