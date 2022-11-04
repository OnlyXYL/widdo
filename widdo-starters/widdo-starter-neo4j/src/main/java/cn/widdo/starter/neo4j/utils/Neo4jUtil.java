package cn.widdo.starter.neo4j.utils;

import cn.widdo.starter.neo4j.entity.Neo4jType;
import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;
import cn.widdo.starter.neo4j.entity.result.ResultEnum;
import cn.widdo.starter.neo4j.function.CQLFunction;
import com.google.common.collect.Lists;
import org.neo4j.driver.AccessMode;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.neo4j.driver.exceptions.ServiceUnavailableException;
import org.neo4j.driver.internal.InternalPoint2D;
import org.neo4j.driver.internal.InternalPoint3D;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Path;
import org.neo4j.driver.types.Point;
import org.neo4j.driver.types.Relationship;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * neo4j util
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/18 10:07
 */
public class Neo4jUtil {

    public static final String CYPHER_QUERY = "query";

    /**
     * 校验参数
     *
     * @param params    参数map
     * @param checkKeys 需要校验的非空可变参数
     * @throws Exception 异常
     * @author XYL
     * @className cn.widdo.starter.neo4j.utils.Neo4jUtil
     * @date 2022/10/18 10:08
     **/
    public static void throwExceptionIfNull(Map<String, ?> params, String... checkKeys) throws Exception {
        Iterator<String> keys = params.keySet().iterator();
        Object value;
        if (checkKeys.length > 0) {
            for (String checkKey : checkKeys) {
                value = params.get(checkKey);
                if (value == null || !StringUtils.hasLength(value.toString())) {
                    throw new Exception("参数" + checkKey + "不能为空");
                }
            }
        } else {
            while (keys.hasNext()) {
                String k = keys.next();
                value = params.get(k);
                if (value == null || !StringUtils.hasLength(value.toString().trim())) {
                    throw new Exception("参数" + k + "不能为空");
                }
            }
        }
    }

    /**
     * neo4j 结果转换
     *
     * @param result 原始结果
     * @return cn.widdo.graph.entity.neo4j.result.Result<java.util.List < java.util.Map < java.lang.String, cn.widdo.graph.entity.neo4j.Value>>>
     * @author XYL
     * @className cn.widdo.autoconfigure.neo4j.helper.Neo4jServiceHelper
     * @date 2022/09/26 18:12
     **/
    @SuppressWarnings("AlibabaSwitchStatement")
    public static Result<List<Map<String, Value>>> packResult(org.neo4j.driver.Result result) {
        List<String> keys = result.keys();
        final int size = keys.size();
        List<org.neo4j.driver.Record> resultLs = result.list();
        List<Map<String, Value>> rs = new ArrayList<>();
        Neo4jConvertor convertor = Neo4jConvertor.build();
        for (org.neo4j.driver.Record resultL : resultLs) {
            Map<String, Value> map1 = new HashMap<>(size);
            for (String key : keys) {
                try {
                    org.neo4j.driver.Value valueOrigin = resultL.get(key);
                    String typeName = valueOrigin.type().name();
                    Value value = new Value();
                    setSizeAndEmpty(valueOrigin, value);
                    value.setKeys(Lists.newArrayList(valueOrigin.keys()));
                    //设置path
                    switch (typeName) {
                        case Neo4jType.NODE -> setValue(value, Neo4jType.NODE, Neo4jConvertor.build().convertNeo4jNodeToNode(valueOrigin.asNode()));
                        case Neo4jType.RELATIONSHIP -> setValue(value, Neo4jType.RELATIONSHIP, Neo4jConvertor.build().convertNeo4jRelationshipToRelationship(valueOrigin.asRelationship()));
                        case Neo4jType.PATH -> pathResult(valueOrigin, convertor, value);
                        case Neo4jType.LISTOFANY -> listResult(valueOrigin, convertor, value);
                        case Neo4jType.POINT -> pointResult(valueOrigin, convertor, value);
                        case Neo4jType.BYTEARRAY -> setValue(value, Neo4jType.BYTEARRAY, valueOrigin.asByteArray());
                        case Neo4jType.MAP -> setValue(value, Neo4jType.MAP, valueOrigin.asMap());
                        case Neo4jType.LIST -> setValue(value, Neo4jType.LIST, valueOrigin.asList());
                        case Neo4jType.ISODURATION -> setValue(value, Neo4jType.ISODURATION, convertor.convertNeo4jDurationToDuration(valueOrigin.asIsoDuration()));
                        case Neo4jType.DOUBLE -> setValue(value, Neo4jType.DOUBLE, valueOrigin.asDouble());
                        case Neo4jType.FLOAT -> setValue(value, Neo4jType.FLOAT, valueOrigin.asFloat());
                        case Neo4jType.INTEGER -> setValue(value, Neo4jType.INTEGER, valueOrigin.asInt());
                        case Neo4jType.BOOLEAN -> setValue(value, Neo4jType.BOOLEAN, valueOrigin.asBoolean());
                        case Neo4jType.LONG -> setValue(value, Neo4jType.LONG, valueOrigin.asLong());
                        case Neo4jType.NUMBER -> setValue(value, Neo4jType.NUMBER, valueOrigin.asNumber());
                        case Neo4jType.LOCALDATE -> setValue(value, Neo4jType.LOCALDATE, valueOrigin.asLocalDate());
                        case Neo4jType.LOCALTIME -> setValue(value, Neo4jType.LOCALTIME, valueOrigin.asLocalTime());
                        case Neo4jType.LOCALDATETIME -> setValue(value, Neo4jType.LOCALDATETIME, valueOrigin.asLocalDateTime());
                        case Neo4jType.OFFSETTIME -> setValue(value, Neo4jType.OFFSETTIME, valueOrigin.asOffsetTime());
                        case Neo4jType.OFFSETDATETIME -> setValue(value, Neo4jType.OFFSETDATETIME, valueOrigin.asOffsetDateTime());
                        default -> setValue(value, Neo4jType.STRING, resultL.get(key).toString());
                    }
                    map1.put(key, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            rs.add(map1);
        }
        return ResultUtil.success(rs);
    }

    /**
     * 设置值
     *
     * @param value widdo value
     * @param neo4jType neo4j 类型
     * @param valueOrigin   neo4j value
     * @author XYL
     * @className cn.widdo.autoconfigure.neo4j.helper.Neo4jServiceHelper
     * @date 2022/09/27 10:13
     **/
    private static void setValue(Value value, String neo4jType, Object valueOrigin) {
        value.setType(neo4jType);
        value.setValue(valueOrigin);
    }


    /**
     * 设置List值的长度
     *
     * @param valueOrigin 原始值
     * @param value       widdo 值
     */
    private static void setSizeAndEmpty(org.neo4j.driver.Value valueOrigin, Value value) {
        try {
            value.setSize(valueOrigin.size());
        } catch (Exception e) {
            value.setEmpty(false);
            value.setSize(1);
        }
    }

    /**
     * path result
     *
     * @param valueOrigin 原始结果
     * @param convertor   结果转换器
     * @param value       widdo value
     * @author XYL
     * @className cn.widdo.autoconfigure.neo4j.helper.Neo4jServiceHelper
     * @date 2022/09/26 18:06
     **/
    private static void pathResult(org.neo4j.driver.Value valueOrigin, Neo4jConvertor convertor, cn.widdo.starter.neo4j.entity.Value value) {
        Path pathOrigin = valueOrigin.asPath();
        cn.widdo.starter.neo4j.entity.Path path = new cn.widdo.starter.neo4j.entity.Path();
        pathOrigin.nodes().forEach(n -> path.addNode(convertor.convertNeo4jNodeToNode(n)));
        pathOrigin.relationships().forEach(r -> path.addRelationship(convertor.convertNeo4jRelationshipToRelationship(r)));
        path.setStartNode(convertor.convertNeo4jNodeToNode(pathOrigin.start()));
        path.setEndNode(convertor.convertNeo4jNodeToNode(pathOrigin.end()));
        value.setType(Neo4jType.PATH);
        value.setValue(path);
    }

    /**
     * list result
     *
     * @param valueOrigin 原始结果
     * @param convertor   结果转换器
     * @param value       widdo value
     * @author XYL
     * @className cn.widdo.autoconfigure.neo4j.helper.Neo4jServiceHelper
     * @date 2022/09/26 18:07
     **/
    private static void listResult(org.neo4j.driver.Value valueOrigin, Neo4jConvertor convertor, cn.widdo.starter.neo4j.entity.Value value) {
        List<Object> list = new ArrayList<>();
        valueOrigin.asList().forEach(l -> {
            if (l instanceof Node) {
                list.add(convertor.convertNeo4jNodeToNode((Node) l));
            } else if (l instanceof Relationship) {
                list.add(convertor.convertNeo4jRelationshipToRelationship((Relationship) l));
            } else {
                list.add(l.toString());
            }
        });
        value.setEmpty(list.isEmpty());
        value.setType(Neo4jType.LISTOFANY);
        value.setValue(list);
    }

    /**
     * point result
     *
     * @param valueOrigin 原始结果
     * @param convertor   结果转换器
     * @param value       widdo value
     * @author XYL
     * @className cn.widdo.autoconfigure.neo4j.helper.Neo4jServiceHelper
     * @date 2022/09/26 18:08
     **/
    private static void pointResult(org.neo4j.driver.Value valueOrigin, Neo4jConvertor convertor, cn.widdo.starter.neo4j.entity.Value value) {
        Point pointOrigin = valueOrigin.asPoint();
        if (pointOrigin instanceof InternalPoint2D) {
            value.setType(Neo4jType.POINT2D);
            value.setValue(convertor.convertNeo4jPoint2DToPoint2D(pointOrigin));
        } else if (pointOrigin instanceof InternalPoint3D) {
            value.setType(Neo4jType.POINT3D);
            value.setValue(convertor.convertNeo4jPoint2DToPoint3D(pointOrigin));
        }
    }

    /**
     * 执行查询
     *
     * @param driver 驱动
     * @param model  模式
     * @param cql    cql
     * @param map    参数
     * @param cqlFun 函数式接口
     * @return cn.widdo.graph.entity.neo4j.result.Result<java.util.List < java.util.Map < java.lang.String, cn.widdo.graph.entity.neo4j.Value>>>
     * @author XYL
     * @className cn.widdo.study.neo4j.service.helper.Neo4jServiceHelper
     * @date 2022/09/26 18:10
     **/
    public static Result<?> toExecute(Driver driver,
                               String model,
                               String cql,
                               Map<String, Object> map,
                               CQLFunction cqlFun) {
        Session session = null;
        try {
            session = driver.session(SessionConfig.builder().withDefaultAccessMode(AccessMode.WRITE).build());
            if (CYPHER_QUERY.equals(model)) {
                return session.readTransaction(tx -> cqlFun.execute(cql, map, tx));
            }
            return session.writeTransaction(tx -> cqlFun.execute(cql, map, tx));
        } catch (ServiceUnavailableException exception) {
            return ResultUtil.error(ResultEnum.ERROR, 10010, exception.getMessage());
        } catch (org.neo4j.driver.exceptions.ClientException exception) {
            System.out.println("--error by ClientException");
            return ResultUtil.error(ResultEnum.ERROR, exception.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * 打印信息
     *
     * @param cypherQL cypher
     * @author XYL
     * @className cn.widdo.autoconfigure.neo4j.helper.Neo4jPreRWHelper
     * @date 2022/10/19 1:29
     **/
    public static void printCypherQL(String cypherQL) {
        System.out.println("---client ip:" + NetUtil.getRealIp() + " cql:" + cypherQL.substring(0, Math.min(cypherQL.length(), 100)) + " ...");
    }
}
