package cn.widdo.study.neo4j.service.helper;

import cn.widdo.graph.entity.neo4j.Neo4jType;
import cn.widdo.graph.entity.neo4j.Value;
import cn.widdo.graph.entity.neo4j.result.Result;
import cn.widdo.graph.entity.neo4j.result.ResultEnum;
import cn.widdo.graph.entity.neo4j.result.ResultUtil;
import cn.widdo.graph.utils.neo4j.Neo4jConvertor;
import cn.widdo.study.neo4j.service.CQLFunction;
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
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * neo4j 辅助类
 *
 * @author XYL
 * @version 1.0
 * @date 2022/07/15 0:17
 */
@Component
public class Neo4jServiceHelper {
    /**
     * 设置List值的长度
     *
     * @param valueOrigin 原始值
     * @param value       widdo 值
     */
    private void setSizeAndEmpty(org.neo4j.driver.Value valueOrigin, Value value) {
        try {
            value.setSize(valueOrigin.size());
        } catch (Exception e) {
            value.setEmpty(false);
            value.setSize(1);
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
    public Result<?> toExecute(Driver driver,
                               String model,
                               String cql,
                               Map<String, Object> map,
                               CQLFunction cqlFun) {
        Session session = null;
        try {
            session = driver.session(SessionConfig.builder().withDefaultAccessMode(AccessMode.WRITE).build());
            if ("query".equals(model)) {
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
     * neo4j 结果转换
     *
     * @param result 原始结果
     * @return cn.widdo.graph.entity.neo4j.result.Result<java.util.List < java.util.Map < java.lang.String, cn.widdo.graph.entity.neo4j.Value>>>
     * @author XYL
     * @className cn.widdo.study.neo4j.service.helper.Neo4jServiceHelper
     * @date 2022/09/26 18:12
     **/
    @SuppressWarnings("AlibabaSwitchStatement")
    public Result<List<Map<String, Value>>> packResult(org.neo4j.driver.Result result) {
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
                        case Neo4jType.NODE -> {
                            value.setType(Neo4jType.NODE);
                            value.setValue(Neo4jConvertor.build().convertNeo4jNodeToNode(valueOrigin.asNode()));
                        }
                        case Neo4jType.RELATIONSHIP -> {
                            value.setType(Neo4jType.RELATIONSHIP);
                            value.setValue(Neo4jConvertor.build().convertNeo4jRelationshipToRelationship(valueOrigin.asRelationship()));
                        }
                        case Neo4jType.PATH -> {
                            pathResult(valueOrigin, convertor, value);
                        }
                        case Neo4jType.LISTOFANY -> {
                            listResult(valueOrigin, convertor, value);
                        }
                        case Neo4jType.POINT -> {
                            pointResult(valueOrigin, convertor, value);
                        }
                        case Neo4jType.BYTEARRAY -> {
                            value.setType(Neo4jType.BYTEARRAY);
                            value.setValue(valueOrigin.asByteArray());
                        }
                        case Neo4jType.MAP -> {
                            value.setType(Neo4jType.MAP);
                            value.setValue(valueOrigin.asMap());
                        }
                        case Neo4jType.LIST -> {
                            value.setType(Neo4jType.LIST);
                            value.setValue(valueOrigin.asList());
                        }
                        case Neo4jType.ISODURATION -> {
                            value.setType(Neo4jType.ISODURATION);
                            value.setValue(convertor.convertNeo4jDurationToDuration(valueOrigin.asIsoDuration()));
                        }
                        case Neo4jType.DOUBLE -> {
                            value.setType(Neo4jType.DOUBLE);
                            value.setValue(valueOrigin.asDouble());
                        }
                        case Neo4jType.FLOAT -> {
                            value.setType(Neo4jType.FLOAT);
                            value.setValue(valueOrigin.asFloat());
                        }
                        case Neo4jType.INTEGER -> {
                            value.setType(Neo4jType.INTEGER);
                            value.setValue(valueOrigin.asInt());
                        }
                        case Neo4jType.BOOLEAN -> {
                            value.setType(Neo4jType.BOOLEAN);
                            value.setValue(valueOrigin.asBoolean());
                        }
                        case Neo4jType.LONG -> {
                            value.setType(Neo4jType.LONG);
                            value.setValue(valueOrigin.asLong());
                        }
                        case Neo4jType.NUMBER -> {
                            value.setType(Neo4jType.NUMBER);
                            value.setValue(valueOrigin.asNumber());
                        }
                        case Neo4jType.LOCALDATE -> {
                            value.setType(Neo4jType.LOCALDATE);
                            value.setValue(valueOrigin.asLocalDate());
                        }
                        case Neo4jType.LOCALTIME -> {
                            value.setType(Neo4jType.LOCALTIME);
                            value.setValue(valueOrigin.asLocalTime());
                            map1.put(key, value);
                        }
                        case Neo4jType.LOCALDATETIME -> {
                            value.setType(Neo4jType.LOCALDATETIME);
                            value.setValue(valueOrigin.asLocalDateTime());
                        }
                        case Neo4jType.OFFSETTIME -> {
                            value.setType(Neo4jType.OFFSETTIME);
                            value.setValue(valueOrigin.asOffsetTime());
                        }
                        case Neo4jType.OFFSETDATETIME -> {
                            value.setType(Neo4jType.OFFSETDATETIME);
                            value.setValue(valueOrigin.asOffsetDateTime());
                        }
                        default -> {
                            value.setType(Neo4jType.STRING);
                            value.setValue(resultL.get(key).toString());
                        }
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
     * path result
     *
     * @param valueOrigin 原始结果
     * @param convertor 结果转换器
     * @param value widdo value
     * @author XYL
     * @className cn.widdo.study.neo4j.service.helper.Neo4jServiceHelper
     * @date 2022/09/26 18:06
     **/
    private void pathResult(org.neo4j.driver.Value valueOrigin, Neo4jConvertor convertor, cn.widdo.graph.entity.neo4j.Value value) {
        Path pathOrigin = valueOrigin.asPath();
        cn.widdo.graph.entity.neo4j.Path path = new cn.widdo.graph.entity.neo4j.Path();
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
     * @param convertor 结果转换器
     * @param value widdo value
     * @author XYL
     * @className cn.widdo.study.neo4j.service.helper.Neo4jServiceHelper
     * @date 2022/09/26 18:07
     **/
    private void listResult(org.neo4j.driver.Value valueOrigin, Neo4jConvertor convertor, cn.widdo.graph.entity.neo4j.Value value) {
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
     * @param convertor 结果转换器
     * @param value widdo value
     * @author XYL
     * @className cn.widdo.study.neo4j.service.helper.Neo4jServiceHelper
     * @date 2022/09/26 18:08
     **/
    private void pointResult(org.neo4j.driver.Value valueOrigin, Neo4jConvertor convertor, cn.widdo.graph.entity.neo4j.Value value) {
        Point pointOrigin = valueOrigin.asPoint();
        if (pointOrigin instanceof InternalPoint2D) {
            value.setType(Neo4jType.POINT2D);
            value.setValue(convertor.convertNeo4jPoint2DToPoint2D(pointOrigin));
        } else if (pointOrigin instanceof InternalPoint3D) {
            value.setType(Neo4jType.POINT3D);
            value.setValue(convertor.convertNeo4jPoint2DToPoint3D(pointOrigin));
        }
    }
}
