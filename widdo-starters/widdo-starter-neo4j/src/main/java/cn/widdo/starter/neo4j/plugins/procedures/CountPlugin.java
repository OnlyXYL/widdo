package cn.widdo.starter.neo4j.plugins.procedures;

import cn.widdo.starter.neo4j.annotation.IgnoreNeo4jVisibility;
import org.apache.commons.lang3.StringUtils;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.procedure.*;

import java.util.stream.Stream;

/**
 * Count plugin.
 *
 * @author XYL
 * @date 2023/02/07 16:05
 * @since 263.1.2.0
 */
public class CountPlugin {

    /**
     * tx.
     */
    @Context
    @IgnoreNeo4jVisibility
    public Transaction tx;

    /**
     * count container.
     */
    public class CountContainer {

        /**
         * count.
         */
        @IgnoreNeo4jVisibility
        public Long count;

        /**
         * constructor has one param called count.
         *
         * @param count
         */
        public CountContainer(final Long count) {
            this.count = count;
        }
    }

    /**
     * procedure named countNode.
     *
     * @param label label
     * @return the count of node
     */
    @Procedure(name = "widdo.node.count", mode = Mode.READ)
    @Description("return count of the node which has the label of param, return  count of all node if param named label is null.")
    public Stream<CountContainer> countNode(@Name("label") String label) {

        String cypher = "";

        if (StringUtils.isNotBlank(label)) {
            cypher = String.format("MATCH (n:%s) RETURN count(n) AS count", label);
        } else {
            cypher = "MATCH (n) RETURN count(n)  AS count";
        }

        ResourceIterator<Long> nodes = tx.execute(cypher).columnAs("count");
        return nodes.stream().map(CountContainer::new);
    }

}
