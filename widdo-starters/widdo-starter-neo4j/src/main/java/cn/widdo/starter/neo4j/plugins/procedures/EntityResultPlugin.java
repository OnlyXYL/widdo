package cn.widdo.starter.neo4j.plugins.procedures;

import cn.widdo.starter.neo4j.annotation.IgnoreNeo4jVisibility;
import org.apache.commons.lang3.StringUtils;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.procedure.*;

import java.util.stream.Stream;

/**
 * entity result plugin.
 *
 * @author XYL
 * @since 263.1.1.1
 * @date 2023/02/07 15:10
 */
public class EntityResultPlugin {

    /**
     * tx.
     */
    @Context
    @IgnoreNeo4jVisibility
    public Transaction tx;

    /**
     * entity container.
     */
    public class EntityContainer {

        /**
         * node.
         */
        @IgnoreNeo4jVisibility
        public Node node;

        /**
         * constructor has one param called {@link Node}.
         *
         * @param node
         */
        public EntityContainer(final Node node) {
            this.node = node;
        }
    }

    /**
     * procedure named allNodes.
     *
     * @param label label
     * @return allNodes
     */
    @Procedure(name = "widdo.allNodes", mode = Mode.READ)
    @Description("get allNodes with the label, return allNodes of labels if param named label is null.")
    public Stream<EntityContainer> allNodes(@Name("label") String label) {

        String cypher = "";

        if (StringUtils.isNotBlank(label)) {
            cypher = String.format("MATCH (n:%s) RETURN n", label);
        } else {
            cypher = "MATCH (n) RETURN n";
        }

        ResourceIterator<Node> nodes = tx.execute(cypher).columnAs("n");
        return nodes.stream().map(EntityContainer::new);
    }
}
