package cn.widdo.starter.neo4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * 标识类。用来启动widdo neo4j的自动配置类。包含neo4j使用所必须的工具和实体。防止认为创建该标识类，进行使用widdo neo4j的自动配置
 * <p>
 * 使用方法：
 * <p>
 * 1. 引入widdo-autoconfigure
 * <p>
 * 2. 引入widdo-starter-neo4j
 * <p>
 * 3. 可以修改neo4j配置（可选）
 *
 * @author XYL
 * @version 263.1.0.0
 * @date 2022/10/14 14:57
 */
public class WiddoStarterNeo4j {

    private final Logger log = LoggerFactory.getLogger(WiddoStarterNeo4j.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Widdo] |- Starters [Widdo Starter Neo4j].");
    }
}
