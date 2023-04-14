package cn.widdo.starter.neo4j.plugins;

import cn.widdo.starter.neo4j.plugins.procedures.TripleProcedure;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.*;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.harness.Neo4j;
import org.neo4j.harness.Neo4jBuilders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TriplePluginTest.
 *
 * @author XYL
 * @date 2023/02/07 18:13
 * @since 263.1.1.1
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TripleProcedureTest {

    private Neo4j neo4j;
    private Driver driver;

    @BeforeAll
    void initializeNeo4j() {

        this.neo4j = Neo4jBuilders.newInProcessBuilder()
                .withDisabledServer()
                .withProcedure(TripleProcedure.class)
                .build();

        driver = GraphDatabase.driver(neo4j.boltURI());
    }

    @AfterAll
    void closeDriver() {
        this.driver.close();
    }

    @AfterEach
    void cleanDb() {
        try (Session session = driver.session()) {
            session.run("MATCH (n) DETACH DELETE n");
        }
    }

    @Test
    void allNodesShouldWork() {
        try (final Session session = driver.session();) {

            final Map params = params();

            final Stream<List> stream = session.run("CALL widdo.triple.write($map) YIELD relIds", params)
                    .stream().map(r -> r.get("relationshipIds").asList());
            assertThat(stream).hasSize(2);
        }
    }

    public Map params() {

        String paramsStr = "{\n" +
                "\t\"triples\":[\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"start\":{\n" +
                "\t\t\t\t\t\"labels\":[\"Person\"],\n" +
                "\t\t\t\t\t\"match\":{\n" +
                "\t\t\t\t\t\t\"name\":\"張三\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"onCreate\":{\n" +
                "\t\t\t\t\t\t\"name\":\"張三\",\n" +
                "\t\t\t\t\t\t\"age\":18\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"onMatch\":{\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"relation\":{\n" +
                "\t\t\t\t\t\"relType\":\"STUDY\",\n" +
                "\t\t\t\t\t\"match\":{\n" +
                "\t\t\t\t\t\t\"name\":\"學習\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"onCreate\":{\n" +
                "\t\t\t\t\t\t\"name\":\"學習\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"onMatch\":{\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"end\":{\n" +
                "\t\t\t\t\t\"labels\":[\"Course\"],\n" +
                "\t\t\t\t\t\"match\":{\n" +
                "\t\t\t\t\t\t\"name\":\"歷史\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"onCreate\":{\n" +
                "\t\t\t\t\t\t\"name\":\"歷史\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"onMatch\":{\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"start\":{\n" +
                "\t\t\t\t\t\"labels\":[\"Person\"],\n" +
                "\t\t\t\t\t\"match\":{\n" +
                "\t\t\t\t\t\t\"name\":\"李四\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"onCreate\":{\n" +
                "\t\t\t\t\t\t\"name\":\"李四\",\n" +
                "\t\t\t\t\t\t\"age\":20\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"onMatch\":{\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"relation\":{\n" +
                "\t\t\t\t\t\"relType\":\"STUDY\",\n" +
                "\t\t\t\t\t\"match\":{\n" +
                "\t\t\t\t\t\t\"name\":\"學習\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"onCreate\":{\n" +
                "\t\t\t\t\t\t\"name\":\"學習\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"onMatch\":{\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"end\":{\n" +
                "\t\t\t\t\t\"labels\":[\"Course\"],\n" +
                "\t\t\t\t\t\"match\":{\n" +
                "\t\t\t\t\t\t\"name\":\"生物\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"onCreate\":{\n" +
                "\t\t\t\t\t\t\"name\":\"生物\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"onMatch\":{\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "}";

        final Map map = JSON.parseObject(paramsStr, Map.class);

        final HashMap hashMap = new HashMap();
        hashMap.put("map", map);

        return hashMap;
    }
}
