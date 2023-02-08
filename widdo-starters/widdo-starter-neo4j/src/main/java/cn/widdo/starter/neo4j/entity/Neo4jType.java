package cn.widdo.starter.neo4j.entity;

/**
 * Neo4jType.
 *
 * @author XYL
 * @since 263.1.1.0
 * @date 2022/08/15 15:09
 */
public class Neo4jType {

    /**
     * NODE.
     */
    public static final String NODE = "NODE";
    /**
     * PATH.
     */
    public static final String PATH = "PATH";
    /**
     * RELATIONSHIP.
     */
    public static final String RELATIONSHIP = "RELATIONSHIP";
    /**
     * LIST.
     */
    public static final String LIST = "LIST";
    /**
     * LIST OF ANY?.
     */
    public static final String LISTOFANY = "LIST OF ANY?";
    /**
     * POINT.
     */
    public static final String POINT = "POINT";
    /**
     * POINT2D.
     */
    public static final String POINT2D = "POINT2D";
    /**
     * POINT3D.
     */
    public static final String POINT3D = "POINT3D";
    /**
     * BYTEARRAY.
     */
    public static final String BYTEARRAY = "BYTEARRAY";
    /**
     * MAP.
     */
    public static final String MAP = "MAP";
    /**
     * ISODURATION.
     */
    public static final String ISODURATION = "ISODURATION";
    /**
     * DOUBLE.
     */
    public static final String DOUBLE = "DOUBLE";
    /**
     * FLOAT.
     */
    public static final String FLOAT = "FLOAT";
    /**
     * INTEGER.
     */
    public static final String INTEGER = "INTEGER";
    /**
     * BOOLEAN.
     */
    public static final String BOOLEAN = "BOOLEAN";
    /**
     * LONG.
     */
    public static final String LONG = "LONG";
    /**
     * NUMBER.
     */
    public static final String NUMBER = "NUMBER";
    /**
     * LOCALDATE.
     */
    public static final String LOCALDATE = "LOCALDATE";
    /**
     * LOCALTIME.
     */
    public static final String LOCALTIME = "LOCALTIME";
    /**
     * LOCALDATETIME.
     */
    public static final String LOCALDATETIME = "LOCALDATETIME";
    /**
     * OFFSETTIME.
     */
    public static final String OFFSETTIME = "OFFSETTIME";
    /**
     * OFFSETDATETIME.
     */
    public static final String OFFSETDATETIME = "OFFSETDATETIME";
    /**
     * STRING.
     */
    public static final String STRING = "STRING";

    protected Neo4jType() {
        throw new UnsupportedOperationException(Neo4jType.class.getName() + "不能被实例化");
    }
}
