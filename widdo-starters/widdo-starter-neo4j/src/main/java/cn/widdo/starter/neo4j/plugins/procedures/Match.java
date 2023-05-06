package cn.widdo.starter.neo4j.plugins.procedures;

import cn.widdo.starter.neo4j.annotation.IgnoreNeo4jVisibility;
import cn.widdo.starter.neo4j.utils.Neo4jUtil;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.procedure.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Match.
 *
 * @author XYL
 * @date 2023/05/05 17:34
 * @since 305.1.1.0
 */
@SuppressWarnings("ALL")
public class Match {
    /**
     * tx.
     */
    @Context
    @IgnoreNeo4jVisibility
    public Transaction tx;

    /**
     * entity container.
     */
    public static class MatchContainer {

        /**
         * node.
         */
        @IgnoreNeo4jVisibility
        public Node node;

        /**
         * constructor has one param called {@link Node}.
         *
         * @param node node
         */
        public MatchContainer(final Node node) {
            this.node = node;
        }
    }

    /**
     * 动态标签，多条件查询节点.
     *
     * @param labelNames
     * @param identProps
     * @return java.util.stream.Stream<cn.widdo.starter.neo4j.plugins.procedures.Match.MatchContainer>
     * @author XYL
     * @date 2023/05/06 10:32:41
     */
    @Procedure(value = "widdo.match.node", mode = Mode.READ)
    @Description("Match the given node(s) with the given dynamic labels.")
    public Stream<MatchContainer> nodes(@Name("labels") List<String> labelNames,
                                        @Name("identProps") Map<String, Object> identProps) {
        final ResourceIterator<Node> nodeResult = getNodeResult(labelNames, identProps);
        return nodeResult.stream().map(MatchContainer::new);
    }

    /**
     * 获取查询结果.
     *
     * @param labelNames label集合
     * @param identProps 查询条件
     * @return org.neo4j.graphdb.ResourceIterator<org.neo4j.graphdb.Node>
     * @author XYL
     * @date 2023/05/06 10:31:07
     */
    private ResourceIterator<Node> getNodeResult(List<String> labelNames, Map<String, Object> identProps) {
        if (identProps == null || identProps.isEmpty()) {
            throw new IllegalArgumentException("you need to supply at least one identifying property for a match");
        }

        String labels = Neo4jUtil.labelString(labelNames);

        Map<String, Object> params = Neo4jUtil.map("identProps", identProps);
        String identPropsString = buildIdentPropsString(identProps);

        final String cypher = "MATCH (n:" + labels + "{" + identPropsString + "}) RETURN n";
        return tx.execute(cypher, params).columnAs("n");
    }

    /**
     * 格式转换.
     *
     * @param identProps 查询条件
     * @return java.lang.String
     * @author XYL
     * @date 2023/05/06 10:37:53
     */
    private String buildIdentPropsString(Map<String, Object> identProps) {
        if (identProps == null) {
            return "";
        }
        return identProps.keySet().stream().map(Neo4jUtil::quote)
                .map(s -> s + ":$identProps." + s)
                .collect(Collectors.joining(","));
    }
}
