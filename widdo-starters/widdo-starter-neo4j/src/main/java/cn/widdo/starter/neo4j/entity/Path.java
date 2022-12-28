package cn.widdo.starter.neo4j.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * neo4j原生接口封装-路径.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/08/15 15:09
 */
public class Path implements Serializable {

    /**
     * startNode.
     */
    private Node startNode;

    /**
     * endNode.
     */
    private Node endNode;

    /**
     * relationships.
     */
    private Collection<Relationship> relationships = new ArrayList<>();

    /**
     * nodes.
     */
    private Collection<Node> nodes = new ArrayList<>();

    /**
     * get startedNode.
     *
     * @return an object result type of {@link Node}
     */
    public Node getStartNode() {
        return startNode;
    }

    /**
     * set startedNode.
     *
     * @param startNode startNode
     */
    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    /**
     * get endedNode.
     *
     * @return an object result type of {@link Node}
     */
    public Node getEndNode() {
        return endNode;
    }

    /**
     * set endedNode.
     *
     * @param endNode   endNode
     */
    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    /**
     * get relationship.
     *
     * @return a collection of relationship
     */
    public Collection<Relationship> getRelationships() {
        return relationships;
    }

    /**
     * set relationship collection.
     *
     * @param relationships relationships
     */
    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }

    /**
     * add relationship to collection.
     *
     * @param relationship  relationship
     */
    public void addRelationship(Relationship relationship) {
        this.relationships.add(relationship);
    }

    /**
     * get node collection.
     *
     * @return a node collection
     */
    public Collection<Node> getNodes() {
        return nodes;
    }

    /**
     * set node collection.
     *
     * @param nodes nodes
     */
    public void setNodes(Iterable<Node> nodes) {
        nodes.forEach(n -> this.nodes.add(n));
    }

    /**
     * add {@link Node} to collection.
     *
     * @param node  node
     */
    public void addNode(Node node) {
        this.nodes.add(node);
    }

    /**
     * size of relationShip collection.
     *
     * @return relationShip collection size
     */
    public int size() {
        return this.relationships.size();
    }

    /**
     * check {@link Node}.
     *
     * @param node  node
     * @return a result type of boolean,which tell you if the node exists.
     */
    public boolean containsNode(Node node) {
        return this.nodes.contains(node);
    }

    /**
     * check {@link Relationship}.
     *
     * @param relationship  relationship
     * @return a result type of boolean, which tell you if the relationship exists.
     */
    public boolean containsRelationship(Relationship relationship) {
        return this.relationships.contains(relationship);
    }
}
