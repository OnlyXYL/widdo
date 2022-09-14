package cn.widdo.graph.utils.neo4j;


import org.neo4j.driver.types.IsoDuration;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Point;
import org.neo4j.driver.types.Relationship;

/**
 * neo4j原生结果类型转换
 *
 * @author XYL
 * @version 1.0
 * @date 2022/08/15 15:09
 */
public class Neo4jConvertor {
    public static Neo4jConvertor build() {
        return new Neo4jConvertor();
    }

    /**
     * 转换neo4j的node
     *
     * @param nodeOrigin
     * @return
     */
    public cn.widdo.graph.entity.neo4j.Node convertNeo4jNodeToNode(Node nodeOrigin) {
        cn.widdo.graph.entity.neo4j.Node node = new cn.widdo.graph.entity.neo4j.Node();
        node.setProperties(nodeOrigin.asMap());
        node.setId(nodeOrigin.id());
        node.setLabels(nodeOrigin.labels());
        return node;
    }

    /**
     * 转换neo4j的relastionship
     *
     * @param relationshipOrigin
     * @return
     */
    public cn.widdo.graph.entity.neo4j.Relationship convertNeo4jRelationshipToRelationship(Relationship relationshipOrigin) {
        cn.widdo.graph.entity.neo4j.Relationship relationship = new cn.widdo.graph.entity.neo4j.Relationship();
        relationship.setId(relationshipOrigin.id());
        relationship.setProperties(relationshipOrigin.asMap());
        relationship.setStartNodeId(relationshipOrigin.startNodeId());
        relationship.setEndNodeId(relationshipOrigin.endNodeId());
        relationship.setType(relationshipOrigin.type());
        return relationship;
    }

    /**
     * 转换neo4j的Point2D
     *
     * @param pointOrigin
     * @return
     */
    public cn.widdo.graph.entity.neo4j.Point2D convertNeo4jPoint2DToPoint2D(Point pointOrigin) {
        cn.widdo.graph.entity.neo4j.Point2D point = new cn.widdo.graph.entity.neo4j.Point2D();
        point.setSrid(pointOrigin.srid());
        point.setX(pointOrigin.x());
        point.setY(pointOrigin.y());
        return point;
    }

    /**
     * 转换neo4j的Point3D
     *
     * @param pointOrigin
     * @return
     */
    public cn.widdo.graph.entity.neo4j.Point3D convertNeo4jPoint2DToPoint3D(Point pointOrigin) {
        cn.widdo.graph.entity.neo4j.Point3D point = new cn.widdo.graph.entity.neo4j.Point3D();
        point.setSrid(pointOrigin.srid());
        point.setX(pointOrigin.x());
        point.setY(pointOrigin.y());
        point.setZ(pointOrigin.z());
        return point;
    }

    /**
     * 转换neo4j的Duration
     *
     * @param durationOrigin
     * @return
     */
    public cn.widdo.graph.entity.neo4j.IsoDuration convertNeo4jDurationToDuration(IsoDuration durationOrigin) {
        cn.widdo.graph.entity.neo4j.IsoDuration duration = new cn.widdo.graph.entity.neo4j.IsoDuration();
        duration.setDays(durationOrigin.days());
        duration.setMonths(durationOrigin.months());
        duration.setSeconds(durationOrigin.seconds());
        duration.setNanoseconds(durationOrigin.nanoseconds());
        return duration;
    }
}
