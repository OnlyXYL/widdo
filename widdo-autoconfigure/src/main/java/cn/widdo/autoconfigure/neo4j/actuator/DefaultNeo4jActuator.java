package cn.widdo.autoconfigure.neo4j.actuator;

import cn.widdo.autoconfigure.neo4j.properties.WiddoNeo4jProperties;
import cn.widdo.autoconfigure.neo4j.reader.DefaultNeo4jReader;
import cn.widdo.autoconfigure.neo4j.reader.Neo4jReader;
import cn.widdo.autoconfigure.neo4j.writer.DefaultNeo4jWriter;
import cn.widdo.autoconfigure.neo4j.writer.Neo4jWriter;
import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;
import org.neo4j.driver.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

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
 * @date 2022/10/18 11:44
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public class DefaultNeo4jActuator extends AbstractNeo4jActuator<Map<String, Object>, Result<List<Map<String, Value>>>> {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultNeo4jActuator.class);

    /**
     * Type constant of neo4j query cypher.
     */
    private static final String CYPHER_READ = "read";

    /**
     * Type constant of neo4j write cypher.
     */
    private static final String CYPHER_WRITE = "write";

    /**
     * properties.
     */
    private final WiddoNeo4jProperties widdoNeo4jProperties;

    /**
     * constructor has no param,at the same time, if you create instance by this constructor,
     * it will throw exception typed {@link UnsupportedOperationException}.
     */
    protected DefaultNeo4jActuator() {
        throw new UnsupportedOperationException();
    }

    /**
     * constructor has two params one called {@link WiddoNeo4jProperties},and another called {@link Driver}.
     *
     * @param widdoNeo4jProperties {@link WiddoNeo4jProperties}
     * @param driver               {@link Driver}
     */
    private DefaultNeo4jActuator(final WiddoNeo4jProperties widdoNeo4jProperties, final Driver driver) {
        this.widdoNeo4jProperties = widdoNeo4jProperties;
        this.driver = driver;
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
     * create reader instance to execute neo4j reader.
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

        String className = widdoNeo4jProperties.getActuator().getReader().getClassName();

        if (StringUtils.hasLength(className)) {
            //check whether it`s legitimate.
            final boolean present = ClassUtils.isPresent(className, Neo4jReader.class.getClassLoader());

            if (!present) {
                className = DefaultNeo4jReader.class.getName();
            }
        } else {
            //set the default classname.
            className = DefaultNeo4jReader.class.getName();
        }

        return reflectObject(className, CYPHER_READ, params);
    }

    /**
     * create writer instance to execute neo4j writer.
     *
     * @param params params
     * @return cn.widdo.starter.neo4j.entity.result.Result<java.util.List < java.util.Map < java.lang.String, cn.widdo.starter.neo4j.entity.Value>>>
     * @author XYL
     * @date 2022/12/03 17:13:12
     **/
    private Result<List<Map<String, Value>>> writeIfHave(Map<String, Object> params) {
        String className = widdoNeo4jProperties.getActuator().getWriter().getClassName();

        if (StringUtils.hasLength(className)) {
            //check whether it`s legitimate
            final boolean present = ClassUtils.isPresent(className, Neo4jWriter.class.getClassLoader());
            if (!present) {
                className = DefaultNeo4jWriter.class.getName();
            }
        } else {
            //set the default classname.
            className = DefaultNeo4jWriter.class.getName();
        }

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
            final Constructor<?> constructor = aClass.getDeclaredConstructor(Driver.class);
            //allow to access private constructor
            constructor.setAccessible(true);
            final Object classObj = constructor.newInstance(driver);

            final Method query = aClass.getMethod(cypherType, Map.class);

            return (Result<List<Map<String, Value>>>) query.invoke(classObj, params);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException
                 | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
