package cn.widdo.autoconfigure.jena.rdf.writer;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import java.util.Map;

/**
 * default rdf writer.
 *
 * @author XYL
 * @date 2023/03/13 18:55
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public class DefaultRDFWriter extends AbstractRDFWriter<Map<String, Object>, Map<String, Object>> {

    @Override
    public Map<String, Object> write(Map<String, Object> params) {

        final Model model = ModelFactory.createDefaultModel();


        return null;
    }
}
