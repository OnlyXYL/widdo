package cn.widdo.starter.neo4j.entity;

import java.util.Map;

/**
 * neo4j原生接口封装-关系.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/08/15 15:09
 */
public class Relationship {

    /**
     * startNodeId.
     */
    private long startNodeId;

    /**
     * endNodeId.
     */
    private long endNodeId;

    /**
     * id.
     */
    private long id;

    /**
     * type.
     */
    private String type;

    /**
     * properties.
     */
    private Map<String, Object> properties;

    /**
     * get startNodeId.
     *
     * @return a result type of long
     */
    public long getStartNodeId() {
        return startNodeId;
    }

    /**
     * set startNodeId.
     *
     * @param startNodeId
     */
    public void setStartNodeId(long startNodeId) {
        this.startNodeId = startNodeId;
    }

    /**
     * get endNodeId.
     *
     * @return a result type of long
     */
    public long getEndNodeId() {
        return endNodeId;
    }

    /**
     * set endNodeId.
     *
     * @param endNodeId
     */
    public void setEndNodeId(long endNodeId) {
        this.endNodeId = endNodeId;
    }

    /**
     * get id.
     *
     * @return a result type of long
     */
    public long getId() {
        return id;
    }

    /**
     * set id.
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * get type.
     *
     * @return a result type of String
     */
    public String getType() {
        return type;
    }

    /**
     * set type.
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * check if have type.
     *
     * @param type
     * @return a result type of boolean
     */
    public boolean hasType(String type) {
        return this.type.equals(type);
    }

    /**
     * get properties.
     *
     * @return properties set type of Map
     */
    public Map<String, Object> getProperties() {
        return properties;
    }

    /**
     * set properties.
     *
     * @param properties
     */
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    /**
     * keys iterable.
     *
     * @return iterable of keys
     */
    public Iterable<String> keys() {
        return properties.keySet();
    }

    /**
     * values iterable.
     *
     * @return iterable of values
     */
    public Iterable<Object> values() {
        return properties.values();
    }
}
