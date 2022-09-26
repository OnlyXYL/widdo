package cn.widdo.graph.configuration;

import cn.widdo.graph.properties.GraphProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * 图谱配置
 *
 * @author XYL
 * @version 1.0
 * @date 2022/08/15 15:15
 */
@Configuration
@EnableConfigurationProperties({GraphProperties.class})
@Import({Neo4jConfiguration.class, OrientdbConfiguration.class})
public class GraphConfiguration {

    private final Logger log = LoggerFactory.getLogger(GraphConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Widdo] |- Components [Widdo Graph] Auto Configure.");
    }
}
