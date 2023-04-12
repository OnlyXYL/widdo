package cn.widdo.autoconfigure.jena.rdf.reader;

import cn.widdo.autoconfigure.jena.Reader;

/**
 * rdf reader.
 *
 * @param <R> r
 * @param <T> t
 * @author XYL
 * @date 2023/03/13 19:04
 * @since 302.1.0.0
 */
@SuppressWarnings({"AlibabaClassNamingShouldBeCamel"})
public interface RDFReader<T, R> extends Reader<T, R> {
}
