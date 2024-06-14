package cn.widdo.autoconfigure.jena.rdf.reader;

import org.apache.jena.query.*;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.system.Txn;

import java.util.Map;

/**
 * default rdf reader.
 *
 * @author XYL
 * @date 2023/03/13 19:05
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public class DefaultRDFReader extends AbstractRDFReader<Map<String, Object>, Map<String, Object>> {

	@Override
	public Map<String, Object> read(Map<String, Object> params) {

		Query query = QueryFactory.create("SELECT * { {?s ?p ?o } UNION { GRAPH ?g { ?s ?p ?o } } }");
		Dataset dataset = DatasetFactory.createTxnMem();
		RDFConnection conn = RDFConnection.connect(dataset);

		Txn.executeWrite(conn, () -> {
			System.out.println("Load a file");
			conn.load("data.ttl");
			conn.load("http://example/g0", "data.ttl");
			System.out.println("In write transaction");
			conn.queryResultSet(query, ResultSetFormatter::out);
		});
		// And again - implicit READ transaction.
		System.out.println("After write transaction");
		conn.queryResultSet(query, ResultSetFormatter::out);

		return null;
	}

}
