package cn.widdo.starter.neo4j.plugins.procedures;

import cn.widdo.starter.neo4j.annotation.IgnoreNeo4jVisibility;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.procedure.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * delete procedure. using this, you can batch delete nodes and it`s relation ,which nodes has relation.
 *
 * @author XYL
 * @date 2023/02/27 18:44
 * @since 263.1.3.0
 */
public class DeleteProcedure {

    /**
     * tx.
     */
    @Context
    @IgnoreNeo4jVisibility
    public Transaction tx;

    public static class DeleteContainer {

        /**
         * the variable which to be return of this procedure.
         */
        @IgnoreNeo4jVisibility
        public Map<String, Object> operations;

        /**
         * constructor has one param called count.
         *
         * @param operations operations
         */
        public DeleteContainer(final Map<String, Object> operations) {
            this.operations = operations;
        }
    }

    /**
     * procedure named delete.
     * <p>
     * note that:
     * you don`t pass in your parameters as params parameter to the apoc call . and with 3.x forward using apoc in this way.
     * <p>
     * but with 4.4 and newer versions you can utilize the <code>call {} in transactions </code>,like this:
     *
     * <code>
     * MATCH (n:Foo) where n.foo = 'bar'
     * <p>
     * CALL {
     * WITH n
     * DETACH DELETE n
     * } IN TRANSACTION OF 10000 ROWS;
     * </code>
     * <p>
     * however, if node deleted hava a large of number relationships, it may still lead to out of heap errors.
     * In this case it`s better to delete the relationships first and then the nodes in batches.like this:
     *
     * <code>
     * <p>
     * MATCH (n:Foo)-[r]-() where n.foo='bar'
     * <p>
     * // delete relationships
     * CALL { WITH r
     * DELETE r
     * } IN TRANSACTIONS OF 10000 ROWS
     * <p>
     * // reduce cardinality
     * WITH distinct n
     * // delete nodes
     * CALL { WITH n
     * DELETE n
     * } IN TRANSACTIONS OF 10000 ROWS;
     * </code>
     * <p>
     * you can get more from this {@link "https://neo4j.com/developer/kb/large-delete-transaction-best-practices-in-neo4j/?_ga=2.113458306.465137194.1677635279-1264917599.1677635279"}
     *
     * @param label  label of which node will be delete
     * @param ids    ids which node will be deleted
     * @param config config of this procedure
     * @return java.util.stream.Stream<cn.widdo.starter.neo4j.plugins.procedures.DeleteProcedure.DeleteContainer>
     * @author XYL
     * @date 2023/02/27 18:59:51
     */
    @Procedure(name = "widdo.node.delete", mode = Mode.WRITE)
    @Description(value = "batch delete nodes and it`s relations, if the node has relations,return the count of deleted.")
    public Stream<DeleteContainer> delete(@Name("label") String label, @Name("ids") List<Long> ids, @Name("config") Map<String, Object> config) {

        final Long batchSize = Optional.ofNullable(config.get("batchSize")).map(s -> (Long) s).orElse(2000L);

        final boolean parallel = Optional.ofNullable(config.get("parallel")).map(s -> (boolean) s).orElse(true);

        final boolean iterateList = Optional.ofNullable(config.get("iterateList")).map(s -> (boolean) s).orElse(true);

        String matchWhere;
        if (ids == null || ids.size() == 0) {
            //没有条件时，删除所有
            matchWhere = "where id(n) >= 0 ";
        } else {
            matchWhere = String.format("where id(n) in %s ", ids);
        }

        String labelCypher;

        if ("".equals(label) || null == label) {
            labelCypher = "n";
        } else {
            labelCypher = String.format("n:%s", label);
        }

        final String cypher = String.format("call apoc.periodic.iterate(\"MATCH (%s) %s return id(n) as ids\",\n"
                        + "\"MATCH (%s) where id(n) = ids detach delete n \",\n"
                        + "{batchSize:%s,parallel:%s,iterateList:%s}) YIELD operations return operations", labelCypher, matchWhere,
                labelCypher,
                batchSize, parallel, iterateList);

        ResourceIterator<Map<String, Object>> nodes = tx.execute(cypher).columnAs("operations");
        return nodes.stream().map(DeleteContainer::new);
    }
}
