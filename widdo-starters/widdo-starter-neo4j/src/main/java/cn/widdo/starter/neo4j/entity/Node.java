package cn.widdo.starter.neo4j.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

/**
 * neo4j原生接口封装-节点.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/08/15 15:09
 */
public class Node implements Serializable {

    /**
     * properties.
     */
    private Map<String, Object> properties;

    /**
     * labels.
     */
    private Collection<String> labels = new HashSet<>();

    /**
     * id.
     */
    private long id;

    /**
     * get properties.
     *
     * @return a properties result type of map
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
     * get label collection.
     *
     * @return a label collection result
     */
    public Collection<String> getLabels() {
        return labels;
    }

    /**
     * set labels.
     *
     * @param labels
     */
    public void setLabels(Iterable<String> labels) {
        labels.forEach(l -> this.labels.add(l));
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
     * check label.
     *
     * @param label
     * @return a result type of boolean,which tell you if the label exists
     */
    public boolean hasLabel(String label) {
        return labels.contains(label);
    }

    /**
     * check key.
     *
     * @param key
     * @return a reuslt type of boolean,which tell you if the key exists
     */
    public boolean containsKey(String key) {
        return this.properties.containsKey(key);
    }

    /**
     * properties size.
     *
     * @return a properties size result type of Integer
     */
    public int size() {
        return properties.size();
    }

    /**
     * keys iterable.
     *
     * @return key`s iterable type of {@link Iterable}
     */
    public Iterable<String> keys() {
        return properties.keySet();
    }

    /**
     * values iterable.
     *
     * @return value`s iterable type of {@link Iterable}
     */
    public Iterable<Object> values() {
        return properties.values();
    }
}
