package cn.widdo.starter.neo4j.utils;

import cn.widdo.starter.neo4j.constant.Neo4jConstants;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.lang.model.SourceVersion;
import java.util.*;
import java.util.stream.Collectors;

/**
 * neo4j util.
 *
 * @author XYL
 * @date 2022/10/18 10:07
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public class Neo4jUtil {

    private static final Logger LOG = LoggerFactory.getLogger(Neo4jUtil.class);

    protected Neo4jUtil() {
        throw new UnsupportedOperationException(Neo4jUtil.class.getName() + "不能被实例化");
    }

    /**
     * 校验参数.
     *
     * @param params    参数map
     * @param checkKeys 需要校验的非空可变参数
     * @throws Exception 异常
     * @author XYL
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
     * neo4j 结果转换.
     *
     * @param result 原始结果
     * @return cn.widdo.starter.neo4j.entity.result.Result<?>
     * @author XYL
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
                        case Neo4jType.NODE:
                            setValue(value, Neo4jType.NODE, Neo4jConvertor.build().convertNeo4jNodeToNode(valueOrigin.asNode()));
                            break;
                        case Neo4jType.RELATIONSHIP:
                            setValue(value, Neo4jType.RELATIONSHIP, Neo4jConvertor.build().convertNeo4jRelationshipToRelationship(valueOrigin.asRelationship()));
                            break;
                        case Neo4jType.PATH:
                            pathResult(valueOrigin, convertor, value);
                            break;
                        case Neo4jType.LISTOFANY:
                            listResult(valueOrigin, convertor, value);
                            break;
                        case Neo4jType.POINT:
                            pointResult(valueOrigin, convertor, value);
                            break;
                        case Neo4jType.BYTEARRAY:
                            setValue(value, Neo4jType.BYTEARRAY, valueOrigin.asByteArray());
                            break;
                        case Neo4jType.MAP:
                            setValue(value, Neo4jType.MAP, valueOrigin.asMap());
                            break;
                        case Neo4jType.LIST:
                            setValue(value, Neo4jType.LIST, valueOrigin.asList());
                            break;
                        case Neo4jType.ISODURATION:
                            setValue(value, Neo4jType.ISODURATION, convertor.convertNeo4jDurationToDuration(valueOrigin.asIsoDuration()));
                            break;
                        case Neo4jType.DOUBLE:
                            setValue(value, Neo4jType.DOUBLE, valueOrigin.asDouble());
                            break;
                        case Neo4jType.FLOAT:
                            setValue(value, Neo4jType.FLOAT, valueOrigin.asFloat());
                            break;
                        case Neo4jType.INTEGER:
                            setValue(value, Neo4jType.INTEGER, valueOrigin.asInt());
                            break;
                        case Neo4jType.BOOLEAN:
                            setValue(value, Neo4jType.BOOLEAN, valueOrigin.asBoolean());
                            break;
                        case Neo4jType.LONG:
                            setValue(value, Neo4jType.LONG, valueOrigin.asLong());
                            break;
                        case Neo4jType.NUMBER:
                            setValue(value, Neo4jType.NUMBER, valueOrigin.asNumber());
                            break;
                        case Neo4jType.LOCALDATE:
                            setValue(value, Neo4jType.LOCALDATE, valueOrigin.asLocalDate());
                            break;
                        case Neo4jType.LOCALTIME:
                            setValue(value, Neo4jType.LOCALTIME, valueOrigin.asLocalTime());
                            break;
                        case Neo4jType.LOCALDATETIME:
                            setValue(value, Neo4jType.LOCALDATETIME, valueOrigin.asLocalDateTime());
                            break;
                        case Neo4jType.OFFSETTIME:
                            setValue(value, Neo4jType.OFFSETTIME, valueOrigin.asOffsetTime());
                            break;
                        case Neo4jType.OFFSETDATETIME:
                            setValue(value, Neo4jType.OFFSETDATETIME, valueOrigin.asOffsetDateTime());
                            break;
                        default:
                            setValue(value, Neo4jType.STRING, resultL.get(key).toString());
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
     * 设置值.
     *
     * @param value       widdo value
     * @param neo4jType   neo4j 类型
     * @param valueOrigin neo4j value
     * @author XYL
     * @date 2022/09/27 10:13
     **/
    private static void setValue(Value value, String neo4jType, Object valueOrigin) {
        value.setType(neo4jType);
        value.setValue(valueOrigin);
    }


    /**
     * 设置List值的长度.
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
     * path result.
     *
     * @param valueOrigin 原始结果
     * @param convertor   结果转换器
     * @param value       widdo value
     * @author XYL
     * @date 2022/09/26 18:06
     **/
    private static void pathResult(org.neo4j.driver.Value valueOrigin,
                                   Neo4jConvertor convertor,
                                   cn.widdo.starter.neo4j.entity.Value value) {
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
     * list result.
     *
     * @param valueOrigin 原始结果
     * @param convertor   结果转换器
     * @param value       widdo value
     * @author XYL
     * @date 2022/09/26 18:07
     **/
    private static void listResult(org.neo4j.driver.Value valueOrigin,
                                   Neo4jConvertor convertor,
                                   cn.widdo.starter.neo4j.entity.Value value) {
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
     * point result.
     *
     * @param valueOrigin 原始结果
     * @param convertor   结果转换器
     * @param value       widdo value
     * @author XYL
     * @date 2022/09/26 18:08
     **/
    private static void pointResult(org.neo4j.driver.Value valueOrigin,
                                    Neo4jConvertor convertor,
                                    cn.widdo.starter.neo4j.entity.Value value) {
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
     * 执行查询.
     *
     * @param driver 驱动
     * @param model  模式
     * @param cql    cql
     * @param map    参数
     * @param cqlFun 函数式接口
     * @return cn.widdo.starter.neo4j.entity.result.Result<?>
     * @author XYL
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
            if (Neo4jConstants.RUNNER_READ.equals(model)) {
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
     * 打印信息.
     *
     * @param cql cypher
     * @author XYL
     * @date 2022/10/19 1:29
     **/
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public static void printCQL(String cql) {
        final String format = String.format("---client ip:%s  cql: %s ...",
                NetUtil.getRealIp(), cql.substring(0, Math.min(cql.length(), 100)));
        LOG.info(format);
    }

    /**
     * @param var
     * @return java.lang.String
     * @author XYL
     * @date 2023/05/06 10:34:04
     */
    public static String quote(String var) {
        return SourceVersion.isIdentifier(var) && !var.contains("$") ? var : '`' + var + '`';
    }

    /**
     * 格式转换.
     *
     * @param labelNames
     * @return java.lang.String
     * @author XYL
     * @date 2023/05/06 10:35:57
     */
    public static String labelString(List<String> labelNames) {
        return labelNames.stream().map(Neo4jUtil::quote).collect(Collectors.joining(":"));
    }

    /**
     * 生成map.
     *
     * @param values values
     * @param <T>    t
     * @return java.util.Map<java.lang.String, T>
     * @author XYL
     * @date 2023/05/06 10:35:42
     */
    public static <T> Map<String, T> map(T... values) {
        Map<String, T> map = new LinkedHashMap<>();
        for (int i = 0; i < values.length; i += 2) {
            if (values[i] == null) {
                continue;
            }
            map.put(values[i].toString(), values[i + 1]);
        }
        return map;
    }
}
