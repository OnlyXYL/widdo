package cn.widdo.starter.neo4j.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.time.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Value
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/07/15 0:02
 */
public class Value implements Serializable {
    int size;
    boolean isEmpty;
    Object value;
    String type;
    List<String> keys;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    /**
     * 转换节点
     *
     * @param
     * @author XYL
     * @className cn.widdo.graph.entity.neo4j.Value
     * @return cn.widdo.graph.entity.neo4j.Node
     * @throws
     * @date 2022/09/14 16:41
     **/
    public Node asNode() {
        ObjectMapper objectMapper = new ObjectMapper();

        if (Neo4jType.NODE.equals(type)) {
            return objectMapper.convertValue(value, Node.class);
        }
        return null;
    }

    public Path asPath() {
        ObjectMapper objectMapper = new ObjectMapper();

        if (Neo4jType.PATH.equals(type)) {
            return objectMapper.convertValue(value, Path.class);
        }
        return null;
    }

    public Relationship asRelationship() {
        ObjectMapper objectMapper = new ObjectMapper();

        if (Neo4jType.RELATIONSHIP.equals(type)) {
            return objectMapper.convertValue(value, Relationship.class);
        }
        return null;
    }

    public List asList() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (type.equals(Neo4jType.LISTOFANY) || type.equals(Neo4jType.LIST)) {
            List list = (List) value;
            if (list != null && list.size() > 0) {
                Object obj = list.get(0);
                if (obj instanceof LinkedHashMap) {
                    Object startNodeId = ((LinkedHashMap) obj).get("startNodeId");
                    if (startNodeId != null) {
                        String relationshipsStr = JSON.toJSONString(value);
                        return JSONObject.parseArray(relationshipsStr, Relationship.class);
                    } else {
                        Object properties = ((LinkedHashMap) obj).get("properties");
                        if (properties != null) {
                            String propertiesStr = JSON.toJSONString(value);
                            return JSONObject.parseArray(propertiesStr, Node.class);
                        }
                    }
                }
            }
            return objectMapper.convertValue(value, List.class);
        } else {
            return null;
        }
    }

    public Point2D asPoint2D() {
        ObjectMapper objectMapper = new ObjectMapper();

        if (Neo4jType.POINT2D.equals(type)) {
            return objectMapper.convertValue(value, Point2D.class);
        }
        return null;
    }

    public Point3D asPoint3D() {
        ObjectMapper objectMapper = new ObjectMapper();

        if (Neo4jType.POINT3D.equals(type)) {
            return objectMapper.convertValue(value, Point3D.class);
        }
        return null;
    }

    public byte[] asByteArray() {
        ObjectMapper objectMapper = new ObjectMapper();

        if (Neo4jType.BYTEARRAY.equals(type)) {
            return objectMapper.convertValue(value, byte[].class);
        }
        return null;
    }

    public Map asMap() {
        ObjectMapper objectMapper = new ObjectMapper();

        if (Neo4jType.MAP.equals(type)) {
            return objectMapper.convertValue(value, Map.class);
        }
        return null;
    }

    public IsoDuration asIsoDuration() {
        ObjectMapper objectMapper = new ObjectMapper();

        if (Neo4jType.ISODURATION.equals(type)) {
            return objectMapper.convertValue(value, IsoDuration.class);
        }
        return null;
    }

    public double asDouble() {
        ObjectMapper objectMapper = new ObjectMapper();

        if (Neo4jType.DOUBLE.equals(type)) {
            return objectMapper.convertValue(value, double.class);
        }
        return 0;
    }

    public float asFloat() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.FLOAT.equals(type)) {
            return objectMapper.convertValue(value, float.class);
        }
        return 0;
    }

    public int asInt() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.INTEGER.equals(type)) {
            return objectMapper.convertValue(value, int.class);
        }
        return 0;
    }

    public boolean asBoolean() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.BOOLEAN.equals(type)) {
            return objectMapper.convertValue(value, boolean.class);
        }
        return false;
    }

    public long asLong() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.LONG.equals(type)) {
            return objectMapper.convertValue(value, long.class);
        }
        return 0;
    }

    public Number asNumber() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.NUMBER.equals(type)) {
            return objectMapper.convertValue(value, Number.class);
        }
        return null;
    }

    public LocalDate asLocalDate() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.LOCALDATE.equals(type)) {
            return objectMapper.convertValue(value, LocalDate.class);
        }
        return null;
    }

    public LocalTime asLocalTime() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.LOCALTIME.equals(type)) {
            return objectMapper.convertValue(value, LocalTime.class);
        }
        return null;
    }

    public LocalDateTime asLocalDateTime() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.LOCALDATETIME.equals(type)) {
            return objectMapper.convertValue(value, LocalDateTime.class);
        }
        return null;
    }

    public OffsetTime asOffsetTime() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.OFFSETTIME.equals(type)) {
            return objectMapper.convertValue(value, OffsetTime.class);
        }
        return null;
    }

    public OffsetDateTime asOffsetDateTime() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.OFFSETDATETIME.equals(type)) {
            return objectMapper.convertValue(value, OffsetDateTime.class);
        }
        return null;
    }

    public String asString() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.STRING.equals(type)) {
            return objectMapper.convertValue(value, String.class);
        }
        return null;
    }
}
