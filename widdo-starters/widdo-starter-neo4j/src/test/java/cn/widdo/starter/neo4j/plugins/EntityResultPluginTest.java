package cn.widdo.starter.neo4j.plugins;

import cn.widdo.starter.neo4j.plugins.procedures.EntityResultPlugin;
import org.junit.jupiter.api.*;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.harness.Neo4j;
import org.neo4j.harness.Neo4jBuilders;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * test
 *
 * @author XYL
 * @version 263.1.1.1
 * @date 2023/02/07 15:12
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EntityResultPluginTest {

    private Neo4j neo4j;
    private Driver driver;

    @BeforeAll
    void initializeNeo4j() {

        this.neo4j = Neo4jBuilders.newInProcessBuilder()
                .withDisabledServer()
                .withFixture("\t    UNWIND range(1, 5) AS i\n" +
                        "\t\t\t\t    WITH i CREATE (n:SomeNode {idx: i})")
                .withProcedure(EntityResultPlugin.class)
                .build();

        driver = GraphDatabase.driver(neo4j.boltURI());
    }

    @AfterAll
    void closeDriver() {
        this.driver.close();
    }

    @AfterEach
    void cleanDb(){
        try(Session session = driver.session()) {
            session.run("MATCH (n) DETACH DELETE n");
        }
    }

    @Test
    void allNodesShouldWork() {
        try (final Session session = driver.session();) {
            final Stream<Integer> stream = session.run("CALL widdo.allNodes('') YIELD node")
                    .stream().map(r -> r.get("node").asNode().get("idx").asInt());
            assertThat(stream).hasSize(5).containsExactly(1,2,3,4,5);
        }
    }
}
