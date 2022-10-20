package cn.widdo.starter.neo4j.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

/**
 * neo4j原生接口封装-节点
 *
 * @author XYL
 * @version 263.1.0.0
 * @date 2022/08/15 15:09
 */
public class Node implements Serializable {
    Map<String, Object> properties;
    Collection<String> labels = new HashSet<>();
    long id;

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public Collection<String> getLabels() {
        return labels;
    }

    public void setLabels(Iterable<String> labels) {
        labels.forEach(l -> this.labels.add(l));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean hasLabel(String label) {
        return labels.contains(label);
    }

    public boolean containsKey(String key) {
        return this.properties.containsKey(key);
    }

    public int size() {
        return properties.size();
    }

    public Iterable<String> keys() {
        return properties.keySet();
    }

    public Iterable<Object> values() {
        return properties.values();
    }
}
