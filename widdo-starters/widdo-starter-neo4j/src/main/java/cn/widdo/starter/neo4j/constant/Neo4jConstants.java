package cn.widdo.starter.neo4j.constant;

/**
 * constants about Neo4j.
 *
 * @author XYL
 * @date 2023/03/02 17:35
 * @since 302.1.0.0
 */
public class Neo4jConstants {

    /**
     * constructor has none param.
     */
    protected Neo4jConstants() {
        throw new UnsupportedOperationException(Neo4jConstants.class.getName() + " can`t be instance.");
    }

    /**
     * the parameter key named 'cypherQL' about cypher.
     */
    public static final String PARAM_CYPHER_QL = "cypherQL";

    /**
     * the parameter key named 'map' about cypher.
     */
    public static final String PARAM_MAP = "map";

    /**
     * constants about neo4j.
     */
    public static final String RUNNER_WRITE = "write";

    /**
     * constants about parameter start with param.
     */
    public static final String RUNNER_READ = "read";
}
