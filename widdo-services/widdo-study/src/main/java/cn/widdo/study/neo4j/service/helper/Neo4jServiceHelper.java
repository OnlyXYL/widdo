package cn.widdo.study.neo4j.service.helper;

import cn.widdo.graph.entity.neo4j.Neo4jType;
import cn.widdo.graph.entity.neo4j.Value;
import cn.widdo.graph.entity.neo4j.result.Result;
import cn.widdo.graph.entity.neo4j.result.ResultEnum;
import cn.widdo.graph.entity.neo4j.result.ResultUtil;
import cn.widdo.graph.utils.neo4j.Neo4jConvertor;
import cn.widdo.study.neo4j.service.CQLFunction;
import com.google.common.collect.Lists;
import org.neo4j.driver.*;
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
     * @param value_orign
     * @param value
     */
    private void setSizeAndEmpty(org.neo4j.driver.Value valueOrign, Value value) {
        try {
            value.setSize(valueOrign.size());
        } catch (Exception e) {
            value.setEmpty(false);
            value.setSize(1);
        }
    }

    /**
     * 执行查询
     *
     * @param model
     * @param cql
     * @param cqlFun
     * @return
     */

    public Result<List<Map<String, Value>>> toExecute(Driver driver,
                                                      String model,
                                                      String cql,
                                                      Map<String, Object> map,
                                                      CQLFunction cqlFun) {
        Session session = null;
        try {
            session = driver.session(SessionConfig.builder().withDefaultAccessMode(AccessMode.WRITE).build());
            switch (model) {
                case "query":
                    return session.readTransaction(tx -> cqlFun.execute(cql, map, tx));
                default:
                    return session.writeTransaction(tx -> cqlFun.execute(cql, map, tx));
            }
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
     * @param result
     * @return
     */
    public Result<List<Map<String, Value>>> packResult(org.neo4j.driver.Result result) {
        List<String> keys = result.keys();
        final int size = keys.size();
        List<Record> resultLs = result.list();
        List<Map<String, Value>> rs = new ArrayList<>();
        Neo4jConvertor convertor = Neo4jConvertor.build();
        for (int j = 0; j < resultLs.size(); j++) {
            Map<String, Value> map1 = new HashMap<>(size);
            for (int i = 0; i < size; i++) {
                String key = keys.get(i);
                try {
                    org.neo4j.driver.Value valueOrign = resultLs.get(j).get(key);
                    String typeName = valueOrign.type().name();
                    cn.widdo.graph.entity.neo4j.Value value = new cn.widdo.graph.entity.neo4j.Value();
                    setSizeAndEmpty(valueOrign, value);
                    value.setKeys(Lists.newArrayList(valueOrign.keys()));
                    switch (typeName) {
                        case Neo4jType.NODE: {
                            Node nodeOrigin = valueOrign.asNode();
                            value.setType(Neo4jType.NODE);
                            value.setValue(Neo4jConvertor.build().convertNeo4jNodeToNode(nodeOrigin));
                            break;
                        }

                        case Neo4jType.RELATIONSHIP: {
                            Relationship relationshipOrigin = valueOrign.asRelationship();
                            value.setType(Neo4jType.RELATIONSHIP);
                            value.setValue(Neo4jConvertor.build().convertNeo4jRelationshipToRelationship(relationshipOrigin));
                            break;
                        }
                        case Neo4jType.PATH: {
                            Path pathOrigin = valueOrign.asPath();
                            cn.widdo.graph.entity.neo4j.Path path = new cn.widdo.graph.entity.neo4j.Path();
                            //设置path
                            pathOrigin.nodes().forEach(n -> path.addNode(convertor.convertNeo4jNodeToNode(n)));
                            pathOrigin.relationships().forEach(r -> path.addRelationship(convertor.convertNeo4jRelationshipToRelationship(r)));
                            path.setStartNode(convertor.convertNeo4jNodeToNode(pathOrigin.start()));
                            path.setEndNode(convertor.convertNeo4jNodeToNode(pathOrigin.end()));
                            value.setType(Neo4jType.PATH);
                            value.setValue(path);
                            break;
                        }
                        case Neo4jType.LISTOFANY: {
                            List listOrigin = valueOrign.asList();
                            List list = new ArrayList();
                            listOrigin.forEach(l -> {
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
                            break;
                        }
                        case "POINT": {
                            Point pointOrigin = valueOrign.asPoint();
                            if (pointOrigin instanceof InternalPoint2D) {
                                value.setType(Neo4jType.POINT2D);
                                value.setValue(convertor.convertNeo4jPoint2DToPoint2D(pointOrigin));
                            } else if (pointOrigin instanceof InternalPoint3D) {
                                value.setType(Neo4jType.POINT3D);
                                value.setValue(convertor.convertNeo4jPoint2DToPoint3D(pointOrigin));
                            }
                            break;
                        }
                        case Neo4jType.BYTEARRAY: {
                            value.setType(Neo4jType.BYTEARRAY);
                            value.setValue(valueOrign.asByteArray());
                            break;
                        }
                        case Neo4jType.MAP: {
                            value.setType(Neo4jType.MAP);
                            value.setValue(valueOrign.asMap());
                            break;
                        }
                        case Neo4jType.LIST: {
                            value.setType(Neo4jType.LIST);
                            value.setValue(valueOrign.asList());
                            break;
                        }
                        case Neo4jType.ISODURATION: {
                            value.setType(Neo4jType.ISODURATION);
                            value.setValue(convertor.convertNeo4jDurationToDuration(valueOrign.asIsoDuration()));
                            break;
                        }
                        case Neo4jType.DOUBLE: {
                            value.setType(Neo4jType.DOUBLE);
                            value.setValue(valueOrign.asDouble());
                            break;
                        }
                        case Neo4jType.FLOAT: {
                            value.setType(Neo4jType.FLOAT);
                            value.setValue(valueOrign.asFloat());
                            break;
                        }
                        case Neo4jType.INTEGER: {
                            value.setType(Neo4jType.INTEGER);
                            value.setValue(valueOrign.asInt());
                            break;
                        }
                        case Neo4jType.BOOLEAN: {
                            value.setType(Neo4jType.BOOLEAN);
                            value.setValue(valueOrign.asBoolean());
                            break;
                        }
                        case Neo4jType.LONG: {
                            value.setType(Neo4jType.LONG);
                            value.setValue(valueOrign.asLong());
                            break;
                        }
                        case Neo4jType.NUMBER: {
                            value.setType(Neo4jType.NUMBER);
                            value.setValue(valueOrign.asNumber());
                            break;
                        }
                        case Neo4jType.LOCALDATE: {
                            value.setType(Neo4jType.LOCALDATE);
                            value.setValue(valueOrign.asLocalDate());
                            break;
                        }
                        case Neo4jType.LOCALTIME: {
                            value.setType(Neo4jType.LOCALTIME);
                            value.setValue(valueOrign.asLocalTime());
                            map1.put(key, value);
                            break;
                        }
                        case Neo4jType.LOCALDATETIME: {
                            value.setType(Neo4jType.LOCALDATETIME);
                            value.setValue(valueOrign.asLocalDateTime());
                            break;
                        }
                        case Neo4jType.OFFSETTIME: {
                            value.setType(Neo4jType.OFFSETTIME);
                            value.setValue(valueOrign.asOffsetTime());
                            break;
                        }
                        case Neo4jType.OFFSETDATETIME: {
                            value.setType(Neo4jType.OFFSETDATETIME);
                            value.setValue(valueOrign.asOffsetDateTime());
                            break;
                        }
                        default: {
                            value.setType(Neo4jType.STRING);
                            value.setValue(resultLs.get(j).get(key).toString());
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
}
