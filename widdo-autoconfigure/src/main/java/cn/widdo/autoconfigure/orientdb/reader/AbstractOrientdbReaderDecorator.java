package cn.widdo.autoconfigure.orientdb.reader;

import cn.widdo.starter.neo4j.validator.ParamsValidator;

/**
 * an abstract Orientdb decorator, you can have yourself Orientdb Reader {@code OrientdbReader} implement by extends it.
 *
 * <code>
 * <p>
 * class CustomOrientdbReader extends OrientdbReader {
 * <p>
 * public CustomOrientdbReader(final OrientdbReader reader) {
 * this.reader = reader;
 * }
 * <p>
 * public void addNew(){
 * System.out.println("Adding new");
 * }
 * <p>
 * }
 * </code>
 *
 * @param <T> t
 * @param <R> r
 * @author XYL
 * @date 2023/02/28 16:13
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public abstract class AbstractOrientdbReaderDecorator<T, R> extends ParamsValidator implements OrientdbReader<T, R> {

    /**
     * the implement of OrientdbReader which you want to add something new.
     */
    protected OrientdbReader<T, R> orientdbReader;
}
