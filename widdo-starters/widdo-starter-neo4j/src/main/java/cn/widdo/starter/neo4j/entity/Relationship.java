package cn.widdo.starter.neo4j.entity;

import java.util.Map;

/**
 * neo4j原生接口封装-关系.
 *
 * @author XYL
 * @date 2022/08/15 15:09
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public class Relationship {

    /**
     * startNodeElementId.
     */
    private Long startNodeElementId;

    /**
     * endNodeElementId.
     */
    private Long endNodeElementId;

    /**
     * id.
     */
    private Long id;

    /**
     * type.
     */
    private String type;

    /**
     * properties.
     */
    private Map<String, Object> properties;

    /**
     * get startNodeElementId.
     *
     * @return a result type of String
     */
    public Long getStartNodeElementId() {
        return startNodeElementId;
    }

    /**
     * set startNodeElementId.
     *
     * @param startNodeElementId startNodeElementId
     */
    public void setStartNodeElementId(Long startNodeElementId) {
        this.startNodeElementId = startNodeElementId;
    }

    /**
     * get endNodeElementId.
     *
     * @return a result type of String
     */
    public Long getEndNodeElementId() {
        return endNodeElementId;
    }

    /**
     * set endNodeElementId.
     *
     * @param endNodeElementId endNodeElementId
     */
    public void setEndNodeElementId(Long endNodeElementId) {
        this.endNodeElementId = endNodeElementId;
    }

    /**
     * get id.
     *
     * @return a result type of String
     */
    public Long getId() {
        return id;
    }

    /**
     * set id.
     *
     * @param id id
     */
    public void setId(Long id) {
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
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * check if you have type.
     *
     * @param type type
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
     * @param properties properties
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
