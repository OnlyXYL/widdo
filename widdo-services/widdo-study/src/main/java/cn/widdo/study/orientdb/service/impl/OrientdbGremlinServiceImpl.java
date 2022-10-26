package cn.widdo.study.orientdb.service.impl;

import cn.widdo.assistant.entity.result.WebResult;
import cn.widdo.autoconfigure.orientdb.configure.WiddoOrientdbConfigure;
import cn.widdo.starter.orientdb.utils.OrientdbUtils;
import cn.widdo.study.orientdb.service.OrientdbGremlinService;
import org.apache.tinkerpop.gremlin.orientdb.OrientGraph;
import org.apache.tinkerpop.gremlin.orientdb.OrientGraphFactory;
import org.apache.tinkerpop.gremlin.process.traversal.Order;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.outV;

/**
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/07/15 2:08
 */
@Service
@ConditionalOnBean({WiddoOrientdbConfigure.class})
public class OrientdbGremlinServiceImpl implements OrientdbGremlinService {

    @Resource
    private OrientGraphFactory orientGraphFactory;

    @Override
    public WebResult queryE(Map<String, Object> params) {

        final String outLabel = params.get("outLabel").toString();

        final String outName = params.get("outName").toString();

        final String inLabel = params.get("inLabel").toString();

        final String inName = params.get("inName").toString();

        OrientGraph orientGraph = OrientdbUtils.openNoTx(orientGraphFactory);

        GraphTraversalSource g = orientGraph.traversal();

/*        List<Edge> vertices = g.E() //查询关系
                .where(outV()   //起点
                        .hasLabel(outLabel) //过滤，获取具有给定条件的label
                        .has("name", outName)   //过滤，获取具有给定条件的属性
                        .and()
                        .inV()  //终点
                        .hasLabel(inLabel)  //过滤，获取具有给定条件的label
                        .has("name", inName)    //过滤，获取具有给定条件的属性
                )
                .toList();*/

        //查询关系
        final List<Object> objects = g.E()
                //起点
                .where(outV()
                        //过滤，获取具有给定条件的label
                        .hasLabel(outLabel)
                        //过滤，获取具有给定条件的属性
                        .has("name", outName)
                        .and()
                        //终点
                        .inV()
                        //过滤，获取具有给定条件的label
                        .hasLabel(inLabel)
                        //过滤，获取具有给定条件的属性
                        .has("name", inName)
                ).path().unfold().toList();

        OrientdbUtils.close(orientGraph);

        return WebResult.success(objects);
    }

    @Override
    public WebResult queryV(Map<String, Object> params) {

        final String key = params.get("key").toString();

        final String value = params.get("value").toString();

        final String label = params.get("label").toString();

        OrientGraph orientGraph = OrientdbUtils.openNoTx(orientGraphFactory);

        GraphTraversalSource g = orientGraph.traversal();

/*        List<Vertex> vertices = g.E()
                .outV() //获取所有节点
                .has(label, properKey, propertyValue) //根据属性过滤
                .dedup()    //节点去重
                .by("name"    //去重条件，根据id去重的含义是：例如允许存在名称相同，但是节点不同的节点
                )
                .toList();*/
        List<Object> code = g.V()
                .match(
                        __.as("a")
                                .hasLabel(label)
                                .has(key, value)
                                .out()
                                .as("b")
                )
                .select("b")
                .dedup()
                .by("id")
                .order()
                .by("id", Order.decr)
                .limit(5)
                .toList();

/*        List<Object> code = g.E()
                .outV() //获取所有节点
                .hasLabel(label)
                .has(properKey, propertyValue) //根据属性过滤
                .dedup()    //节点去重
                .by("code"    //去重条件，根据id去重的含义是：例如允许存在名称相同，但是节点不同的节点
                )
                .limit(limit)
                .path()
                .unfold()
                .toList();*/


        return null;
    }

    @Override
    public WebResult delete() {

        return WebResult.success();
    }
}

