package cn.widdo.autoconfigure.neo4j.actuator;

import cn.widdo.autoconfigure.neo4j.properties.WiddoNeo4jProperties;
import cn.widdo.autoconfigure.neo4j.writer.DefaultNeo4jWriter;
import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * EnhanceDefaultNeo4jActuator.
 *
 * @author XYL
 * @date 2023/03/01 18:26
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public class EnhanceDefaultNeo4jActuator extends AbstractNeo4jActuatorDecorator<Map<String, Object>, Result<List<Map<String, Value>>>> {

    private static final Logger LOG = LoggerFactory.getLogger(EnhanceDefaultNeo4jActuator.class);

    /**
     * Type constant of neo4j write cypher.
     */
    private static final String CYPHER_NONE = "none";

    /**
     * properties.
     */
    private WiddoNeo4jProperties widdoNeo4jProperties;

    /**
     * constructor has two params. one typed {@link Neo4jActuator},anther typed {@link WiddoNeo4jProperties}.
     *
     * @param neo4jActuator        ne4jActuator
     * @param widdoNeo4jProperties widdoNeo4jProperties
     */
    public EnhanceDefaultNeo4jActuator(final Neo4jActuator neo4jActuator, final WiddoNeo4jProperties widdoNeo4jProperties) {
        this.neo4jActuator = neo4jActuator;
        this.widdoNeo4jProperties = widdoNeo4jProperties;
    }

    @Override
    public Result<List<Map<String, Value>>> read(Map<String, Object> params) {
        return neo4jActuator.read(params);
    }

    @Override
    public Result<List<Map<String, Value>>> write(Map<String, Object> params) {
        return neo4jActuator.write(params);
    }

    /**
     * add new function to {@link DefaultNeo4jActuator}.
     *
     * @param params params
     * @return cn.widdo.starter.neo4j.entity.result.Result<java.util.List < java.util.Map < java.lang.String, cn.widdo.starter.neo4j.entity.Value>>>
     * @author XYL
     * @date 2023/03/01 18:29:31
     * @since 302.1.0.0
     */
    public Result<List<Map<String, Value>>> none(Map<String, Object> params) {
        return this.writeIfHave(params);
    }

    /**
     * @param params params
     * @return cn.widdo.starter.neo4j.entity.result.Result<java.util.List < java.util.Map < java.lang.String, cn.widdo.starter.neo4j.entity.Value>>>
     * @author XYL
     * @date 2023/03/01 18:48:07
     */
    private Result<List<Map<String, Value>>> writeIfHave(Map<String, Object> params) {
        String className = widdoNeo4jProperties.getActuator().getWriter().getClassName();

        if (!StringUtils.hasLength(className)) {
            //check whether it`s legitimate
            final boolean present = ClassUtils.isPresent(className, DefaultNeo4jActuator.class.getClassLoader());
            if (!present) {
                className = DefaultNeo4jWriter.class.getName();
            }
        } else {
            //set the default classname.
            className = DefaultNeo4jWriter.class.getName();
        }

        return reflectObject(className, CYPHER_NONE, params);
    }

}
