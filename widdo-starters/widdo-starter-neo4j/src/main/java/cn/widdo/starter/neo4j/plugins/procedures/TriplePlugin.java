package cn.widdo.starter.neo4j.plugins.procedures;

import cn.widdo.starter.neo4j.annotation.IgnoreNeo4jVisibility;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.procedure.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * TriplePlugin.
 *
 * @author XYL
 * @version 263.1.1.1
 * @date 2023/02/07 18:09
 */
public class TriplePlugin {

    /**
     * tx.
     */
    @Context
    @IgnoreNeo4jVisibility
    public Transaction tx;

    /**
     * count container.
     */
    public class TripleContainer {

        /**
         * count.
         */
        @IgnoreNeo4jVisibility
        public List<Long> relIds;

        /**
         * constructor has one param called count.
         *
         * @param relIds
         */
        public TripleContainer(final List<Long> relIds) {
            this.relIds = relIds;
        }
    }

    /**
     * procedure named countNode.
     *
     * @param triples triples
     * @return the count of node
     */
    @Procedure(name = "widdo.triple.write", mode = Mode.READ)
    @Description("return count of the node which has the label of param, return  count of all node if param named label is null.")
    public Stream<TriplePlugin.TripleContainer> writeTriple(@Name("triples") Map<String, Object> triples) {

        String cypher = "UNWIND $triples AS triple \n"
                + "CALL apoc.merge.node(triple.start.labels, triple.start.match,triple.start.onCreate,triple.start.onMatch) YIELD node as startNode\n"
                + "CALL apoc.merge.node(triple.end.labels, triple.end.match,triple.end.onCreate,triple.end.onMatch) YIELD node as endNode\n"
                + "CALL apoc.merge.relationship(startNode, triple.relation.relType, triple.relation.match, triple.relation.onCreate, endNode, triple.relation.onMatch) YIELD rel\n"
                + "RETURN collect(id(rel)) AS relationshipIds";

        ResourceIterator<List<Long>> nodes = tx.execute(cypher, triples).columnAs("relationshipIds");
        return nodes.stream().map(TriplePlugin.TripleContainer::new);
    }

}
