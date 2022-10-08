package cn.widdo.graph.entity.neo4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * neo4j原生接口封装-路径
 *
 * @author XYL
 * @version 1.0
 * @date 2022/08/15 15:09
 */
public class Path implements Serializable {
    Node startNode;
    Node endNode;
    Collection<Relationship> relationships = new ArrayList();
    Collection<Node> nodes = new ArrayList();

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    public Collection<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }

    public void addRelationship(Relationship relationship) {
        this.relationships.add(relationship);
    }

    public Collection<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Iterable<Node> nodes) {
        nodes.forEach(n -> this.nodes.add(n));
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public int size() {
        return this.relationships.size();
    }

    public boolean containsNode(Node node) {
        return this.nodes.contains(node);
    }

    public boolean containsRelationship(Relationship relationship) {
        return this.relationships.contains(relationship);
    }
}
