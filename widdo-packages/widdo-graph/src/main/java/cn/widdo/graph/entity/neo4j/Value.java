package cn.widdo.graph.entity.neo4j;

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
 * @version 1.0
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

        switch (type) {
            case Neo4jType.NODE:
                return objectMapper.convertValue(value, Node.class);
            default:
                return null;
        }
    }

    public Path asPath() {
        ObjectMapper objectMapper = new ObjectMapper();

        switch (type) {
            case Neo4jType.PATH:
                return objectMapper.convertValue(value, Path.class);
            default:
                return null;
        }
    }

    public Relationship asRelationship() {
        ObjectMapper objectMapper = new ObjectMapper();

        switch (type) {
            case Neo4jType.RELATIONSHIP:
                return objectMapper.convertValue(value, Relationship.class);
            default:
                return null;
        }
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

        switch (type) {
            case Neo4jType.POINT2D:
                return objectMapper.convertValue(value, Point2D.class);
            default:
                return null;
        }
    }

    public Point3D asPoint3D() {
        ObjectMapper objectMapper = new ObjectMapper();

        switch (type) {
            case Neo4jType.POINT3D:
                return objectMapper.convertValue(value, Point3D.class);
            default:
                return null;
        }
    }

    public byte[] asByteArray() {
        ObjectMapper objectMapper = new ObjectMapper();

        switch (type) {
            case Neo4jType.BYTEARRAY:
                return objectMapper.convertValue(value, byte[].class);
            default:
                return null;
        }
    }

    public Map asMap() {
        ObjectMapper objectMapper = new ObjectMapper();

        switch (type) {
            case Neo4jType.MAP:
                return objectMapper.convertValue(value, Map.class);
            default:
                return null;
        }
    }

    public IsoDuration asIsoDuration() {
        ObjectMapper objectMapper = new ObjectMapper();

        switch (type) {
            case Neo4jType.ISODURATION:
                return objectMapper.convertValue(value, IsoDuration.class);
            default:
                return null;
        }
    }

    public double asDouble() {
        ObjectMapper objectMapper = new ObjectMapper();

        switch (type) {
            case Neo4jType.DOUBLE:
                return objectMapper.convertValue(value, double.class);
            default:
                return 0;
        }
    }

    public float asFloat() {
        ObjectMapper objectMapper = new ObjectMapper();
        switch (type) {
            case Neo4jType.FLOAT:
                return objectMapper.convertValue(value, float.class);
            default:
                return 0;
        }
    }

    public int asInt() {
        ObjectMapper objectMapper = new ObjectMapper();
        switch (type) {
            case Neo4jType.INTEGER:
                return objectMapper.convertValue(value, int.class);
            default:
                return 0;
        }
    }

    public boolean asBoolean() {
        ObjectMapper objectMapper = new ObjectMapper();
        switch (type) {
            case Neo4jType.BOOLEAN:
                return objectMapper.convertValue(value, boolean.class);
            default:
                return false;
        }
    }

    public long asLong() {
        ObjectMapper objectMapper = new ObjectMapper();
        switch (type) {
            case Neo4jType.LONG:
                return objectMapper.convertValue(value, long.class);
            default:
                return 0;
        }
    }

    public Number asNumber() {
        ObjectMapper objectMapper = new ObjectMapper();
        switch (type) {
            case Neo4jType.NUMBER:
                return objectMapper.convertValue(value, Number.class);
            default:
                return null;
        }
    }

    public LocalDate asLocalDate() {
        ObjectMapper objectMapper = new ObjectMapper();
        switch (type) {
            case Neo4jType.LOCALDATE:
                return objectMapper.convertValue(value, LocalDate.class);
            default:
                return null;
        }
    }

    public LocalTime asLocalTime() {
        ObjectMapper objectMapper = new ObjectMapper();
        switch (type) {
            case Neo4jType.LOCALTIME:
                return objectMapper.convertValue(value, LocalTime.class);
            default:
                return null;
        }
    }

    public LocalDateTime asLocalDateTime() {
        ObjectMapper objectMapper = new ObjectMapper();
        switch (type) {
            case Neo4jType.LOCALDATETIME:
                return objectMapper.convertValue(value, LocalDateTime.class);
            default:
                return null;
        }
    }

    public OffsetTime asOffsetTime() {
        ObjectMapper objectMapper = new ObjectMapper();
        switch (type) {
            case Neo4jType.OFFSETTIME:
                return objectMapper.convertValue(value, OffsetTime.class);
            default:
                return null;
        }
    }

    public OffsetDateTime asOffsetDateTime() {
        ObjectMapper objectMapper = new ObjectMapper();
        switch (type) {
            case Neo4jType.OFFSETDATETIME:
                return objectMapper.convertValue(value, OffsetDateTime.class);
            default:
                return null;
        }
    }

    public String asString() {
        ObjectMapper objectMapper = new ObjectMapper();
        switch (type) {
            case Neo4jType.STRING:
                return objectMapper.convertValue(value, String.class);
            default:
                return null;
        }
    }
}
