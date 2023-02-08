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
 * Value.
 *
 * @author XYL
 * @since 263.1.1.0
 * @date 2022/07/15 0:02
 */
public class Value implements Serializable {
    /**
     * size.
     */
    private int size;
    /**
     * isEmpty.
     */
    private boolean isEmpty;
    /**
     * value.
     */
    private Object value;
    /**
     * type.
     */
    private String type;
    /**
     * keys.
     */
    private List<String> keys;

    /**
     * get size.
     *
     * @return a integer result
     */
    public int getSize() {
        return size;
    }

    /**
     * set size.
     * @param size  size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * check empty.
     * @return a boolean result
     */
    public boolean isEmpty() {
        return isEmpty;
    }

    /**
     * set empty.
     * @param empty empty
     */
    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    /**
     * get value.
     * @return return Object result
     */
    public Object getValue() {
        return value;
    }

    /**
     * set value.
     * @param value value
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * get type.
     * @return a String result
     */
    public String getType() {
        return type;
    }

    /**
     * set type.
     * @param type  type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * get keys.
     * @return a list of key
     */
    public List<String> getKeys() {
        return keys;
    }

    /**
     * set keys.
     * @param keys  keys
     */
    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    /**
     * 转换节点.
     *
     * @return cn.widdo.graph.entity.neo4j.Node
     * @author XYL
     * @className cn.widdo.graph.entity.neo4j.Value
     * @date 2022/09/14 16:41
     **/
    public Node asNode() {
        ObjectMapper objectMapper = new ObjectMapper();

        if (Neo4jType.NODE.equals(type)) {
            return objectMapper.convertValue(value, Node.class);
        }
        return null;
    }

    /**
     * 转化路径.
     *
     * @return cn.widdo.starter.neo4j.entity.Path
     * @author XYL
     * @date 2022/11/28 14:27:30
     **/
    public Path asPath() {
        ObjectMapper objectMapper = new ObjectMapper();

        if (Neo4jType.PATH.equals(type)) {
            return objectMapper.convertValue(value, Path.class);
        }
        return null;
    }

    /**
     * 转换关系.
     *
     * @return cn.widdo.starter.neo4j.entity.Relationship
     * @author XYL
     * @date 2022/11/28 14:27:47
     **/
    public Relationship asRelationship() {
        ObjectMapper objectMapper = new ObjectMapper();

        if (Neo4jType.RELATIONSHIP.equals(type)) {
            return objectMapper.convertValue(value, Relationship.class);
        }
        return null;
    }

    /**
     * 转换list.
     *
     * @return java.util.List
     * @author XYL
     * @date 2022/11/28 14:28:01
     **/
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

    /**
     * asPoint2D.
     *
     * @return cn.widdo.starter.neo4j.entity.Point2D
     * @author XYL
     * @date 2022/11/28 14:28:22
     **/
    public Point2D asPoint2D() {
        ObjectMapper objectMapper = new ObjectMapper();

        if (Neo4jType.POINT2D.equals(type)) {
            return objectMapper.convertValue(value, Point2D.class);
        }
        return null;
    }

    /**
     * asPoint3D.
     *
     * @return cn.widdo.starter.neo4j.entity.Point3D
     * @author XYL
     * @date 2022/11/28 14:28:35
     **/
    public Point3D asPoint3D() {
        ObjectMapper objectMapper = new ObjectMapper();

        if (Neo4jType.POINT3D.equals(type)) {
            return objectMapper.convertValue(value, Point3D.class);
        }
        return null;
    }

    /**
     * asByteArray.
     *
     * @return byte[]
     * @author XYL
     * @date 2022/11/28 14:28:44
     **/
    public byte[] asByteArray() {
        ObjectMapper objectMapper = new ObjectMapper();

        if (Neo4jType.BYTEARRAY.equals(type)) {
            return objectMapper.convertValue(value, byte[].class);
        }
        return null;
    }

    /**
     * asMap.
     *
     * @return java.util.Map
     * @author XYL
     * @date 2022/11/28 14:29:00
     **/
    public Map asMap() {
        ObjectMapper objectMapper = new ObjectMapper();

        if (Neo4jType.MAP.equals(type)) {
            return objectMapper.convertValue(value, Map.class);
        }
        return null;
    }

    /**
     * asIsoDuration.
     *
     * @return cn.widdo.starter.neo4j.entity.IsoDuration
     * @author XYL
     * @date 2022/11/28 14:29:08
     **/
    public IsoDuration asIsoDuration() {
        ObjectMapper objectMapper = new ObjectMapper();

        if (Neo4jType.ISODURATION.equals(type)) {
            return objectMapper.convertValue(value, IsoDuration.class);
        }
        return null;
    }

    /**
     * asDouble.
     *
     * @return double
     * @author XYL
     * @date 2022/11/28 14:29:28
     **/
    public double asDouble() {
        ObjectMapper objectMapper = new ObjectMapper();

        if (Neo4jType.DOUBLE.equals(type)) {
            return objectMapper.convertValue(value, double.class);
        }
        return 0;
    }

    /**
     * asFloat.
     *
     * @return float
     * @author XYL
     * @date 2022/11/28 14:29:39
     **/
    public float asFloat() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.FLOAT.equals(type)) {
            return objectMapper.convertValue(value, float.class);
        }
        return 0;
    }

    /**
     * asInt.
     *
     * @return int
     * @author XYL
     * @date 2022/11/28 14:29:48
     **/
    public int asInt() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.INTEGER.equals(type)) {
            return objectMapper.convertValue(value, int.class);
        }
        return 0;
    }

    /**
     * asBoolean.
     *
     * @return boolean
     * @author XYL
     * @date 2022/11/28 14:29:57
     **/
    public boolean asBoolean() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.BOOLEAN.equals(type)) {
            return objectMapper.convertValue(value, boolean.class);
        }
        return false;
    }

    /**
     * asLong.
     *
     * @return long
     * @author XYL
     * @date 2022/11/28 14:30:06
     **/
    public long asLong() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.LONG.equals(type)) {
            return objectMapper.convertValue(value, long.class);
        }
        return 0;
    }

    /**
     * asNumber.
     *
     * @return java.lang.Number
     * @author XYL
     * @date 2022/11/28 14:30:14
     **/
    public Number asNumber() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.NUMBER.equals(type)) {
            return objectMapper.convertValue(value, Number.class);
        }
        return null;
    }

    /**
     * asLocalDate.
     *
     * @return java.time.LocalDate
     * @author XYL
     * @date 2022/11/28 14:30:24
     **/
    public LocalDate asLocalDate() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.LOCALDATE.equals(type)) {
            return objectMapper.convertValue(value, LocalDate.class);
        }
        return null;
    }

    /**
     * asLocalTime.
     *
     * @return java.time.LocalTime
     * @author XYL
     * @date 2022/11/28 14:30:31
     **/
    public LocalTime asLocalTime() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.LOCALTIME.equals(type)) {
            return objectMapper.convertValue(value, LocalTime.class);
        }
        return null;
    }

    /**
     * asLocalDateTime.
     *
     * @return java.time.LocalDateTime
     * @author XYL
     * @date 2022/11/28 14:30:46
     **/
    public LocalDateTime asLocalDateTime() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.LOCALDATETIME.equals(type)) {
            return objectMapper.convertValue(value, LocalDateTime.class);
        }
        return null;
    }

    /**
     * asOffsetTime.
     *
     * @return java.time.OffsetTime
     * @author XYL
     * @date 2022/11/28 14:30:55
     **/
    public OffsetTime asOffsetTime() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.OFFSETTIME.equals(type)) {
            return objectMapper.convertValue(value, OffsetTime.class);
        }
        return null;
    }

    /**
     * asOffsetDateTime.
     *
     * @return java.time.OffsetDateTime
     * @author XYL
     * @date 2022/11/28 14:31:03
     **/
    public OffsetDateTime asOffsetDateTime() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.OFFSETDATETIME.equals(type)) {
            return objectMapper.convertValue(value, OffsetDateTime.class);
        }
        return null;
    }

    /**
     * asString.
     *
     * @return java.lang.String
     * @author XYL
     * @date 2022/11/28 14:31:11
     **/
    public String asString() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (Neo4jType.STRING.equals(type)) {
            return objectMapper.convertValue(value, String.class);
        }
        return null;
    }
}
