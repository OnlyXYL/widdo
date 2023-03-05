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
 * <p>
 * rename from CountPlugin to CountProcedure since 263.1.3.0
 *
 * @author XYL
 * @date 2023/02/07 18:09
 * @since 263.1.2.0
 */
public class TripleProcedure {

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
         * @param relIds relIds
         */
        public TripleContainer(final List<Long> relIds) {
            this.relIds = relIds;
        }
    }

    /**
     * procedure named countNode.
     * <p>
     * usage:
     * call widdo.triple.write({
     * triples:[
     * {
     * start:{
     * labels:["Person"],
     * match:{
     * name:"張三"
     * },
     * onCreate:{
     * name:"張三",
     * age:18
     * },
     * onMatch:{
     * <p>
     * }
     * },
     * relation:{
     * relType:"STUDY",
     * match:{
     * name:"學習"
     * },
     * onCreate:{
     * name:"學習"
     * },
     * onMatch:{
     * <p>
     * }
     * },
     * end:{
     * labels:["Course"],
     * match:{
     * name:"歷史"
     * },
     * onCreate:{
     * name:"歷史"
     * },
     * onMatch:{
     * <p>
     * }
     * }
     * },
     * {
     * start:{
     * labels:["Person"],
     * match:{
     * name:"李四"
     * },
     * onCreate:{
     * name:"李四",
     * age:20
     * },
     * onMatch:{
     * <p>
     * }
     * },
     * relation:{
     * relType:"STUDY",
     * match:{
     * name:"學習"
     * },
     * onCreate:{
     * name:"學習"
     * },
     * onMatch:{
     * <p>
     * }
     * },
     * end:{
     * labels:["Course"],
     * match:{
     * name:"生物"
     * },
     * onCreate:{
     * name:"生物"
     * },
     * onMatch:{
     * <p>
     * }
     * }
     * }
     * ]
     * }) yield relIds
     *
     * @param triples triples
     * @return the count of node
     */
    @Procedure(name = "widdo.triple.write", mode = Mode.WRITE)
    @Description("return count of the node which has the label of param, return  count of all node if param named label is null.")
    public Stream<TripleProcedure.TripleContainer> writeTriple(@Name("triples") Map<String, Object> triples) {

        String cypher = "UNWIND $triples AS triple \n"
                + "CALL apoc.merge.node(triple.start.labels, triple.start.match,triple.start.onCreate,triple.start.onMatch) YIELD node as startNode\n"
                + "CALL apoc.merge.node(triple.end.labels, triple.end.match,triple.end.onCreate,triple.end.onMatch) YIELD node as endNode\n"
                + "CALL apoc.merge.relationship(startNode, triple.relation.relType, triple.relation.match, triple.relation.onCreate, endNode, triple.relation.onMatch) YIELD rel\n"
                + "RETURN collect(id(rel)) AS relationshipIds";

        ResourceIterator<List<Long>> nodes = tx.execute(cypher, triples).columnAs("relationshipIds");
        return nodes.stream().map(TripleProcedure.TripleContainer::new);
    }

}
