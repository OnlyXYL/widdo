package cn.widdo.starter.neo4j.plugins;

import cn.widdo.starter.neo4j.plugins.procedures.DeleteProcedure;
import org.junit.jupiter.api.*;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.harness.Neo4j;
import org.neo4j.harness.Neo4jBuilders;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CountPluginTest.
 *
 * @author XYL
 * @date 2023/02/07 17:20
 * @since 263.1.1.1
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeleteProcedureTest {

	private Driver driver;

	@BeforeAll
	void initializeNeo4j() {

		Neo4j neo4j = Neo4jBuilders.newInProcessBuilder().withDisabledServer()
				.withFixture("\t    UNWIND range(1, 5) AS i\n" + "\t\t\t\t    WITH i CREATE (n:SomeNode {idx: i})")
				.withProcedure(DeleteProcedure.class).build();

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
		try (final Session session = driver.session()) {
			final Stream<Integer> stream = session.run("CALL widdo.node.delete('SomeNode',[1,2,3,4],{}) YIELD count")
					.stream().map(r -> r.get("count").asInt());
			assertThat(stream).hasSize(1).containsExactly(4);
		}
	}

}
