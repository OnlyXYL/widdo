package cn.widdo.autoconfigure.neo4j.configure;

import cn.widdo.autoconfigure.condition.WiddoNeo4jActuator;
import cn.widdo.autoconfigure.neo4j.actuator.DefaultNeo4jActuator;
import cn.widdo.autoconfigure.neo4j.actuator.Neo4jActuator;
import cn.widdo.autoconfigure.neo4j.properties.WiddoNeo4jProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * neo4j read writer actuator
 *
 * @author XYL
 * @version 263.1.0.0
 * @date 2022/10/18 0:50
 */
@WiddoNeo4jActuator
public class WiddoNeo4jActuatorConfigure {

    private static final Logger log = LoggerFactory.getLogger(WiddoNeo4jActuatorConfigure.class);

    @Resource
    private WiddoNeo4jProperties widdoNeo4jProperties;

    @PostConstruct
    private void postConstruct() {
        log.info("[Widdo] |- AutoConfigure [Widdo Neo4j] Actuator.");
    }

    @Bean
    public Neo4jActuator neo4jActuator() {
        return new DefaultNeo4jActuator(widdoNeo4jProperties);
    }
}
